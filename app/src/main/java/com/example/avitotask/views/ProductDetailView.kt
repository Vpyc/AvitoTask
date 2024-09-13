package com.example.avitotask.views

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.avitotask.R
import com.example.avitotask.retrofit.Product
import com.example.avitotask.viewModels.ProductDetailViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductDetailView(productId: String, onBackClick: () -> Unit) {
    val productDetailVM: ProductDetailViewModel = hiltViewModel()

    val product = productDetailVM.product.value
    val pagerState = rememberPagerState(pageCount = { product?.images?.size ?: 0 })
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val isLoading = productDetailVM.isLoading.value

    LaunchedEffect(Unit) {
        productDetailVM.getProductById(productId)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TopAppBar(
            title = { Text("") },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back_arrow),
                        contentDescription = stringResource(R.string.back)
                    )
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Transparent)
        )

        if (isLoading) IsLoading()
        else if (productDetailVM.errorMessage.value != null) {
            ErrorScreen(
                errorMessage = productDetailVM.errorMessage.value!!,
                onRetry = { productDetailVM.getProductById(productId) }
            )
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
                    .verticalScroll(scrollState)
            ) {
                if (product != null) {

                    if (product.images.isNotEmpty()) {
                        ImageSlider(pagerState, product, context)
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    PriceField(
                        discountedPrice = product.discounted_price,
                        price = product.price
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = product.name,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(
                        text = stringResource(R.string.characteristics),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Spacer(modifier = Modifier.height(4.dp))

                    Characteristics(product)

                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = stringResource(R.string.description),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = product.description ?: stringResource(
                            R.string.no_information_about,
                            "описании"
                        ),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun Characteristics(product: Product) {
    Column {
        val specsToShow = product.product_specifications
        if (specsToShow.isNotEmpty()) {
            specsToShow.forEach { specification ->
                Text(
                    text = buildAnnotatedString {
                        if (specification.key != null) {
                            withStyle(
                                style = SpanStyle(
                                    color = Color.Gray,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append("${specification.key}: ")
                            }
                        }
                        withStyle(style = SpanStyle(color = Color.White)) {
                            append(specification.value)
                        }
                    },
                    fontSize = 12.sp,
                    modifier = Modifier.padding(vertical = 2.dp)
                )
            }
        } else {
            Text(
                text = stringResource(R.string.no_information_about, "характеристиках"),
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
private fun ImageSlider(
    pagerState: PagerState,
    product: Product,
    context: Context
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth()
                .height(350.dp),
            pageSpacing = 16.dp,

            ) { page ->
            ProductImage(
                images = product.images,
                index = page,
                modifier = Modifier
                    .fillMaxSize(),
                context
            )
        }
        if (pagerState.pageCount > 1)
            Text(
                text = " ${pagerState.currentPage + 1}-${product.images.size} ",
                color = Color.White,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(8.dp)
                    .background(Color.Gray)
            )
    }
}
