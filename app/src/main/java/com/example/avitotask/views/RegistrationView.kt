package com.example.avitotask.views

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.avitotask.R
import com.example.avitotask.ui.theme.Typography
import com.example.avitotask.viewModels.RegistrationViewModel

@Composable
fun RegistrationView(onRegister: () -> Unit) {

    val registerViewModel: RegistrationViewModel = hiltViewModel()
    val context = LocalContext.current
    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp)
            .imePadding()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Top,
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 50.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = stringResource(R.string.registration),
                    style = Typography.titleLarge
                )
            }

            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                CustomOutlinedTextField(
                    value = registerViewModel.name.value,
                    onValueChange = registerViewModel::onNameChange,
                    placeholder = stringResource(R.string.name)
                )
                CustomOutlinedTextField(
                    value = registerViewModel.email.value,
                    onValueChange = registerViewModel::onEmailChange,
                    placeholder = stringResource(R.string.email),
                    keyboardType = KeyboardType.Email,
                    isError = registerViewModel.emailError.value,
                )
                if (registerViewModel.emailError.value) {
                    Text(
                        stringResource(R.string.email_error),
                        color = MaterialTheme.colorScheme.error
                    )
                }
                CustomOutlinedTextField(
                    value = registerViewModel.password.value,
                    onValueChange = registerViewModel::onPasswordChange,
                    placeholder = stringResource(R.string.password),
                    isPassword = true,
                    keyboardType = KeyboardType.Password,
                    isError = registerViewModel.passwordError.value,
                )
                if (registerViewModel.passwordError.value) {
                    Text(
                        stringResource(R.string.password_error),
                        color = MaterialTheme.colorScheme.error
                    )
                }
                CustomOutlinedTextField(
                    value = registerViewModel.confirmPassword.value,
                    onValueChange = registerViewModel::onConfirmPasswordChange,
                    placeholder = stringResource(R.string.cpassword),
                    isPassword = true,
                    keyboardType = KeyboardType.Password,
                    isError = registerViewModel.confirmPasswordError.value,
                )
                if (registerViewModel.confirmPasswordError.value) {
                    Text(
                        stringResource(R.string.сpassword_error),
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
        InButton(
            onClick = { registerClick(registerViewModel, context, onRegister) },
            modifier = Modifier
                .align(Alignment.BottomCenter),
            text = stringResource(R.string.registr_button)
        )
    }
}

fun registerClick(rvm: RegistrationViewModel, context: Context, onRegister: () -> Unit) {
    if (rvm.emailError.value || rvm.passwordError.value || rvm.confirmPasswordError.value) {
        Toast.makeText(context, R.string.validation_error, Toast.LENGTH_SHORT).show()
        return
    } else {
        rvm.register(
            onSuccess = onRegister,
            onError = { message ->
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        )
    }
}