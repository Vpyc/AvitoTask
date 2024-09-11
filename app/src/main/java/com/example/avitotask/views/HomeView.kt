package com.example.avitotask.views

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.avitotask.ui.theme.Typography
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.avitotask.retrofit.ProductList
import com.example.avitotask.viewModels.HomeViewModel

@Composable
fun HomeView(onProductClick: (String) -> Unit) {
    val homeViewModel: HomeViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        if (homeViewModel.products.value.isEmpty()) {
            homeViewModel.getProducts()
        }
    }
    if (homeViewModel.isLoading.value) {
        IsLoading()
    }
    else{
        Column{
            ProductListScreen(homeViewModel.products.value, onProductClick)
        }
    }
}

@Composable
fun ProductCard(product: ProductList, context: Context, onProductClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
            .clickable { onProductClick(product._id)},
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
                context = context)

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
fun ProductListScreen(products:List<ProductList>, onProductClick: (String) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(products.size) { index ->
            ProductCard(product = products[index], context = LocalContext.current, onProductClick)
        }
    }
}