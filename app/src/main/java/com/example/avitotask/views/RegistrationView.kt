package com.example.avitotask.views

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.avitotask.ui.theme.Typography

@Composable
fun RegistrationView() {

    val nameState = remember { mutableStateOf("") }
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val confirmPasswordState = remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(
                text = "Регистрация",
                style = Typography.titleLarge
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            OutlinedTextField(
                value = nameState.value,
                onValueChange = { nameState.value = it },
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                placeholder = { Text("Имя") },
                textStyle = Typography.bodyLarge,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF262624),
                    unfocusedContainerColor = Color(0xFF262624),

                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
            )
            OutlinedTextField(
                value = emailState.value,
                onValueChange = { emailState.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                textStyle = Typography.bodyLarge,
                shape = RoundedCornerShape(10.dp),
                placeholder = { Text("Электронная почта") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF262624),
                    unfocusedContainerColor = Color(0xFF262624),

                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
            )
            OutlinedTextField(
                value = passwordState.value,
                onValueChange = { passwordState.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                textStyle = Typography.bodyLarge,
                shape = RoundedCornerShape(10.dp),
                placeholder = { Text("Пароль") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF262624),
                    unfocusedContainerColor = Color(0xFF262624),

                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
            )
            OutlinedTextField(
                value = confirmPasswordState.value,
                onValueChange = { confirmPasswordState.value = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                textStyle = Typography.bodyLarge,
                shape = RoundedCornerShape(10.dp),
                placeholder = { Text("Подтвердите пароль") },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color(0xFF262624),
                    unfocusedContainerColor = Color(0xFF262624),

                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                ),
            )
        }

        Button(
            onClick = { },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 150.dp, bottom = 50.dp),
            shape = RoundedCornerShape(10.dp),

        ) {
            Text("Войти",
                style = Typography.labelMedium)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegistrationViewPreview() {
    RegistrationView()
}
