package com.example.avitotask.views

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.avitotask.ui.theme.Typography
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.avitotask.R
import com.example.avitotask.retrofit.Product
import com.example.avitotask.viewModels.HomeViewModel

@Composable
fun HomeView() {
    val homeViewModel: HomeViewModel = hiltViewModel()
    LaunchedEffect(Unit) {
        homeViewModel.getProducts()
    }
    if (homeViewModel.isLoading.value) {
        IsLoading()
    }
    else{
        Column{
            ProductListScreen(homeViewModel.products.value)
        }
    }
}

@Composable
fun ProductCard(product: Product, context: Context) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(product.images[0])
                    .error(R.drawable.ic_no_image)
                    .placeholder(R.drawable.ic_serch_image)
                    .build(),
                contentDescription = "Product Image",
                modifier = Modifier
                    .size(150.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .align(Alignment.CenterHorizontally),
                contentScale = ContentScale.Fit
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

                Column() {
                    if (product.discounted_price != null && product.discounted_price < product.price) {
                        Text(
                            text = "${product.discounted_price} ₽",
                            style = Typography.labelLarge,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Text(
                            text = "${product.price} ₽",
                            style = Typography.labelMedium.copy(
                                textDecoration = TextDecoration.LineThrough
                            )
                        )
                    } else {
                        Text(
                            text = "${product.price} ₽",
                            style = Typography.labelLarge
                        )
                    }
                }

            }
        }
    }
}

@Composable
fun ProductListScreen(products:List<Product>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(products.size) { index ->
            ProductCard(product = products[index], context = LocalContext.current)
        }
    }
}