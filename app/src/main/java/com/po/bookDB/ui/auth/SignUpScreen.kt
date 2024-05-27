@file:OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)

package com.po.bookDB.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.po.bookDB.ui.common.CommonTextField
import com.po.bookDB.ui.common.CustomVerticalSpacer
import com.po.bookDB.ui.common.PasswordTextField
import com.po.bookDB.ui.theme.resources.PhotoAlbumTheme
import com.po.bookDB.ui.theme.resources.dimen
import com.po.bookDB.R
import kotlinx.coroutines.flow.collectLatest

@Composable
fun SignUpScreen(
    loginViewModel: AuthViewModel? = null,
    onNavToLoginPage: () -> Unit,
    navController: NavController
) {
    val loginUiState = loginViewModel?.loginUiState
    val isErrorPwdNotMatch = loginUiState?.signUpError
    val isErrorEmailInvalidFormat = loginUiState?.errorSignUpInvalidEmail
    val context = LocalContext.current
    val snackState = remember { SnackbarHostState() }
    val keyboardController = LocalSoftwareKeyboardController.current
    val signUpBtnState = remember {
        mutableStateOf(false)
    }

    if (loginUiState!!.userNameSignUp.isNotBlank() &&
        loginUiState.passwordSignUp.isNotBlank() &&
        loginUiState.confirmPasswordSignUp.isNotBlank() &&
        (loginUiState.passwordSignUp.length >= 8) &&
        (loginUiState.confirmPasswordSignUp.length >= 8)
    ) {
        signUpBtnState.value = true
    }

    LaunchedEffect(key1 = Unit) {
        loginViewModel.signUpEvent.collectLatest {
            onNavToLoginPage()
        }
    }

//    if(loginUiState.isRegisterSuccess){
//        onNavToLoginPage()
//    }
    LaunchedEffect(key1 = loginViewModel.hasUser) {
        if (loginViewModel.hasUser) {
            // onNavToHomePage.invoke()
//            navController.navigate("book")
        }
    }
    Box {
        Scaffold(
            topBar = {},
            snackbarHost = {
                SnackbarHost(hostState = snackState)
            },
            containerColor = Color.Transparent
        ) { paddingValues ->
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .verticalScroll(rememberScrollState())
                        .fillMaxWidth()
                        .imePadding()
                        .padding(MaterialTheme.dimen.base_2x),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Book Store",
                            color = Color.Black,
                            style = TextStyle(fontSize = 28.sp)
                        )
                        CustomVerticalSpacer(size = MaterialTheme.dimen.base_4x)

                        Box(
                            contentAlignment = Alignment.Center
                        ) {
                            Image(painter = painterResource(id = R.drawable.book),
                                contentDescription = "book login",
                                modifier = Modifier
                                    .width(200.dp)
                                    .height(200.dp))
                        }
                        CustomVerticalSpacer(size = MaterialTheme.dimen.base_2x)

                        CommonTextField(
                            modifier = Modifier
                                .fillMaxWidth(),
                            placeholder = "Type Email( eg.moe@gmail.com )",
                            value = loginUiState.userNameSignUp,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next,
                            onValueChanged = {
                                loginViewModel.onUserNameSignUpChange(it)
                            },
                            onValueCleared = {},
                            isError = isErrorEmailInvalidFormat ?: false,
                            errorMessage = "Sample name is abc@gmail.com"
                        )

                        CustomVerticalSpacer(size = MaterialTheme.dimen.base_2x)

                        PasswordTextField(
                            onValueChanged = {
                                loginViewModel.onPasswordSignUpChange(it)
                            },
                            keyboardAction = { keyboardController!!.hide() },
                            isError = isErrorPwdNotMatch ?: false,
                            errorMessage = "Password and Confirm Password does not match.",
                            password = loginUiState.passwordSignUp,
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Password,
                            placeholder = "Type Password"
                        )

                        CustomVerticalSpacer(size = MaterialTheme.dimen.base_2x)

                        PasswordTextField(
                            onValueChanged = {
                                loginViewModel.onConfirmPasswordChange(it)
                            },
                            keyboardAction = { keyboardController!!.hide() },
                            isError = isErrorPwdNotMatch ?: false,
                            errorMessage = "Password and Confirm Password does not match.",
                            password = loginUiState.confirmPasswordSignUp,
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Password,
                            placeholder = "Confirm Password"
                        )

                        CustomVerticalSpacer(size = MaterialTheme.dimen.base_2x)

                        Button(
                            onClick = {
                                loginViewModel.createUser()
                            },
                            enabled = signUpBtnState.value
                        ) {
                            Text(text = "Sign Up")
                            CustomVerticalSpacer(size = MaterialTheme.dimen.base)
                            Icon(
                                painter = painterResource(id = R.drawable.ic_small_chevron_right),
                                contentDescription = "chevron right"
                            )
                        }
                        CustomVerticalSpacer(size = MaterialTheme.dimen.base_2x)

                        TextButton(
                            modifier = Modifier.padding(end = MaterialTheme.dimen.small),
                            onClick = {
                                onNavToLoginPage()
                            }
                        ) {
                            Text(
                                text = "Login",
                                color = MaterialTheme.colorScheme.primary,
                            )
                        }
                        if (loginUiState.isLoading) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SignUpScreen() {
    PhotoAlbumTheme {
        SignUpScreen()
    }
}
