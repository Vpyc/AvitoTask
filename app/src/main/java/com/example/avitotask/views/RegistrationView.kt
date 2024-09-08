package com.example.avitotask.views

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.avitotask.ui.theme.InputColor
import com.example.avitotask.ui.theme.Typography
import com.example.avitotask.viewModels.RegistrationViewModel
import com.example.avitotask.viewModels.RegistrationViewModelFactory

@Composable
fun RegistrationView() {

    val registerViewModel: RegistrationViewModel = viewModel(factory = RegistrationViewModelFactory())
    val context = LocalContext.current

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
            CustomOutlinedTextField(
                value = registerViewModel.name.value,
                onValueChange = registerViewModel::onNameChange,
                placeholder = "Имя"
            )
            CustomOutlinedTextField(
                value = registerViewModel.email.value,
                onValueChange = registerViewModel::onEmailChange,
                placeholder = "Электронная почта",
                keyboardType = KeyboardType.Email,
                isError = registerViewModel.emailError.value,
            )
            CustomOutlinedTextField(
                value = registerViewModel.password.value,
                onValueChange = registerViewModel::onPasswordChange,
                placeholder = "Пароль",
                isPassword = true,
                keyboardType = KeyboardType.Password,
                isError = registerViewModel.passwordError.value,
            )
            CustomOutlinedTextField(
                value = registerViewModel.confirmPassword.value,
                onValueChange = registerViewModel::onConfirmPasswordChange,
                placeholder = "Подтвердите пароль",
                isPassword = true,
                keyboardType = KeyboardType.Password,
                isError = registerViewModel.confirmPasswordError.value,
            )
            if (registerViewModel.confirmPasswordError.value) {
                Text("Пароли не совпадают", color = MaterialTheme.colorScheme.error)}
        }

        Button(
            onClick = { registerClick(registerViewModel, context) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            shape = RoundedCornerShape(10.dp),

        ) {
            Text("Войти",
                style = Typography.labelMedium)
        }
    }
}

fun registerClick(rvm : RegistrationViewModel, context: Context) {
    if (rvm.emailError.value || rvm.passwordError.value || rvm.confirmPasswordError.value) {
        Toast.makeText(context, "Введите корректные данные", Toast.LENGTH_SHORT).show()
        return
    }
    else {
        rvm.register(
                onSuccess = {
                    Toast.makeText(context,"Пабеда", Toast.LENGTH_SHORT).show()
                },
                onError = { message ->
                    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
                }
            )
    }
}

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

@Preview(showBackground = true)
@Composable
fun RegistrationViewPreview() {
    RegistrationView()
}
