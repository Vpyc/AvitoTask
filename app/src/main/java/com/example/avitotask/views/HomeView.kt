package com.example.avitotask.views

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.avitotask.R
import com.example.avitotask.retrofit.ProductList
import com.example.avitotask.ui.theme.Typography
import com.example.avitotask.viewModels.HomeViewModel
import com.example.avitotask.viewModels.SortOrder

@Composable
fun HomeView(onProductClick: (String) -> Unit) {
    val homeViewModel: HomeViewModel = hiltViewModel()
    val gridState = rememberLazyGridState()

    LaunchedEffect(gridState) {
        snapshotFlow { gridState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisibleItemIndex ->
                if (lastVisibleItemIndex == homeViewModel.products.value.lastIndex && !homeViewModel.isLoading.value) {
                    homeViewModel.getProducts()
                }
            }
    }

    LaunchedEffect(Unit) {
        if (homeViewModel.products.value.isEmpty()) {
            homeViewModel.getProducts()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {

        ButtonRowWithImages({ category ->
            homeViewModel.setCategory(category)
        }, context = LocalContext.current)

        SortRow(sortOrder = homeViewModel.sortOrder.value) { sortOrder ->
            homeViewModel.setSortOrder(sortOrder)
        }

        if (homeViewModel.isLoading.value && homeViewModel.products.value.isEmpty()) {
            IsLoading()
        } else if (homeViewModel.errorMessage.value != null) {
            ErrorScreen(
                errorMessage = homeViewModel.errorMessage.value!!,
                onRetry = { homeViewModel.getProducts() }
            )
        } else {
            ProductListScreen(
                products = homeViewModel.products.value,
                onProductClick = onProductClick,
                gridState = gridState,
                isLoading = homeViewModel.isLoading.value
            )
        }
    }
}

data class ButtonData(val title: String, val imageUrl: String, val name: String)

@Composable
fun ButtonRowWithImages(
    onButtonClick: (String?) -> Unit,
    context: Context
) {
    val activeButtonIndex = remember { mutableStateOf(-1) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        val buttons = listOf(
            ButtonData(
                "clothing",
                "https://avatars.mds.yandex.net/i?id=f239fd141d2fa1a8ebd4e6d845d7136115ff9198f84a1213-12666658-images-thumbs&n=13",
                stringResource(R.string.clothes)
            ),
            ButtonData(
                "computers",
                "https://avatars.mds.yandex.net/i?id=37503429a658ad514f31db98ab6a0c388bc8843db190b710-5555892-images-thumbs&n=13",
                stringResource(R.string.computers)
            ),
            ButtonData(
                "furniture",
                "https://avatars.mds.yandex.net/i?id=a3293607fd39ad8863533f6ce03ad367_l-8219563-images-thumbs&n=13",
                stringResource(R.string.furniture)
            )
        )

        buttons.forEachIndexed { index, buttonData ->
            CategoryImage(
                image = buttonData.imageUrl,
                name = buttonData.name,
                context = context,
                modifier = Modifier
                    .clickable {
                        activeButtonIndex.value =
                            if (activeButtonIndex.value == index) -1 else index
                        onButtonClick(
                            if (activeButtonIndex.value == -1) null else buttonData.title
                        )
                    }
            )
        }
    }
}

@Composable
fun SortRow(sortOrder: SortOrder, onSortChange: (SortOrder) -> Unit) {
    var currentSort by remember { mutableStateOf(sortOrder) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.End
    ) {
        TextButton(onClick = {
            currentSort = when (currentSort) {
                SortOrder.NONE -> SortOrder.ASCENDING
                SortOrder.ASCENDING -> SortOrder.DESCENDING
                SortOrder.DESCENDING -> SortOrder.NONE
            }
            onSortChange(currentSort)
        }) {
            Text(
                text = when (currentSort) {
                    SortOrder.NONE -> stringResource(R.string.no_sort)
                    SortOrder.ASCENDING -> stringResource(R.string.chipper)
                    SortOrder.DESCENDING -> stringResource(R.string.expensive)
                },
                style = Typography.labelLarge
            )
        }
    }
}

@Composable
fun ProductCard(product: ProductList, context: Context, onProductClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { onProductClick(product._id) },
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            ProductImage(
                product.images,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                context = context
            )

            Spacer(modifier = Modifier.height(8.dp))

            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = product.name,
                    style = Typography.labelSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(8.dp))

                PriceField(product.discounted_price, product.price)
            }
        }
    }
}

@Composable
fun ProductListScreen(
    products: List<ProductList>,
    onProductClick: (String) -> Unit,
    gridState: LazyGridState,
    isLoading: Boolean
) {
    LazyVerticalGrid(
        state = gridState,
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(products.size) { index ->
            ProductCard(product = products[index], context = LocalContext.current, onProductClick)
        }
        if (isLoading) {
            item(
                span = { GridItemSpan(maxLineSpan) }
            )
            { IsLoading() }
        }
    }
}

