package com.example.avitotask.views

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.avitotask.ui.theme.InputColor
import com.example.avitotask.ui.theme.Typography
import androidx.compose.material3.Button
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextDecoration
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.avitotask.R

@Composable
fun CustomOutlinedTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isPassword: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Text,
    isError: Boolean = false,
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(10.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(48.dp),
        placeholder = { Text(placeholder) },
        textStyle = Typography.bodyLarge,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = InputColor,
            unfocusedContainerColor = InputColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        ),
        isError = isError
    )
}

@Composable
fun InButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
    ) {
        Text(
            text,
            style = Typography.labelMedium)
    }
}

@Composable
fun ProductImage(
    images: List<String>,
    index: Int = 0,
    modifier: Modifier = Modifier,
    context: Context
){
    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(images[index])
            .error(R.drawable.ic_no_image)
            .placeholder(R.drawable.ic_serch_image)
            .build(),
        contentDescription = "Product Image",
        modifier = modifier
            .size(150.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White),
        contentScale = ContentScale.Fit
    )
}

@Composable
fun PriceField(
    modifier: Modifier,
    discountedPrice: Int,
    price: Int,
)
{
    Column {
        if (discountedPrice != null && discountedPrice < price) {
            Text(
                text = "$discountedPrice ₽",
                style = Typography.labelLarge,
                modifier = modifier
            )
            Text(
                text = "$price ₽",
                style = Typography.labelMedium.copy(
                    textDecoration = TextDecoration.LineThrough
                )
            )
        } else {
            Text(
                text = "$price ₽",
                style = Typography.labelLarge
            )
        }
    }
}