package com.example.avitotask.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.avitotask.R
import com.example.avitotask.ui.theme.Typography

/**
 * Экран ошибки
 * @param errorMessage сообщение об ошибке
 * @param onRetry нажатие на кнопку "Повторить". Повторяет вложенный запрос
 */
@Composable
fun ErrorScreen(errorMessage: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = errorMessage,
            style = Typography.bodyMedium,
            modifier = Modifier
                .padding(16.dp),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onRetry() }) {
            Text(stringResource(R.string.retry), color = Color.White)
        }
    }
}