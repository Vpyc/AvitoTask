package com.example.avitotask.views

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.avitotask.R
import com.example.avitotask.ui.theme.InputColor
import com.example.avitotask.ui.theme.Typography

/**
 * Компонент для ввода текста
 * @param modifier [Modifier]
 * @param value Значение поля
 * @param onValueChange Обработчик изменения значения поля
 * @param placeholder Подсказка для поля
 * @param isPassword Флаг, указывающий, является ли поле ввода паролем
 * @param keyboardType Тип клавиатуры для ввода
 * @param isError Флаг, указывающий на ошибку при валидации
 */
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
    Spacer(
        modifier = modifier.height(10.dp)
    )
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
    Spacer(
        modifier = modifier.height(10.dp)
    )
}

/**
 * Компонент для кнопки Вход/Войти
 * @param modifier [Modifier]
 * @param text Текст кнопки
 * @param onClick Обработчик нажатия на кнопку
 */
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
            style = Typography.labelMedium
        )
    }
}

/**
 * Компонент для изображения продукта
 * @param modifier [Modifier]
 * @param images Список с url картинок
 * @param index Индекс изображения
 * @param context Контекст приложения
 */
@Composable
fun ProductImage(
    images: List<String>,
    index: Int = 0,
    modifier: Modifier = Modifier,
    context: Context
) {
    val imageUrl = images.getOrNull(index) // если нет изображения - передаём null
    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(imageUrl ?: R.drawable.ic_no_image)
            .error(R.drawable.ic_no_image)
            .placeholder(R.drawable.ic_serch_image)
            .build(),
        contentDescription = stringResource(R.string.product_image),
        modifier = modifier
            .size(150.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White),
        contentScale = ContentScale.Fit
    )
}

/**
 * Компонент для изображения категории
 * @param image Url изображения
 * @param modifier [Modifier]
 * @param name Название категории
 * @param context Контекст приложения
 */
@Composable
fun CategoryImage(
    image: String,
    name: String,
    modifier: Modifier = Modifier,
    context: Context
) {
    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(image)
            .error(R.drawable.ic_no_image)
            .placeholder(R.drawable.ic_serch_image)
            .build(),
        contentDescription = name,
        modifier = modifier
            .size(90.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White),
        contentScale = ContentScale.Fit
    )
}

/**
 * Компонент для поля цены
 * @param discountedPrice Цена со скидкой
 * @param price Цена без скидки
 */
@Composable
fun PriceField(
    discountedPrice: Int,
    price: Int,
) {
    Column {
        if (discountedPrice != null && discountedPrice < price) {
            Text(
                text = "$discountedPrice ₽",
                style = Typography.labelLarge,
                modifier = Modifier.padding(bottom = 4.dp),
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