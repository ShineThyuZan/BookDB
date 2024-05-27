@file:OptIn(ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class)
package com.po.bookDB.ui.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.po.bookDB.ui.common.CommonTextField
import com.po.bookDB.ui.common.CustomVerticalSpacer
import com.po.bookDB.ui.common.PasswordTextField
import com.po.bookDB.ui.theme.resources.dimen
import com.po.bookDB.R
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LoginScreen(
    onNavToBookPage : () -> Unit,
    onNavToSignUpPage: () -> Unit,
    navController: NavController,
    loginViewModel: AuthViewModel
) {
    val loginUiState = loginViewModel.loginUiState
    val isError = loginUiState.loginError
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val snackState = remember { SnackbarHostState() }
    val loginBtnState = remember {
        mutableStateOf(false)
    }
    if (loginUiState!!.userName.isNotBlank() && loginUiState.password.isNotBlank()) {
        loginBtnState.value = true
    }

    LaunchedEffect(key1 = Unit) {
        loginViewModel.loginEvent.collectLatest {
            onNavToBookPage()
        }
    }

    LaunchedEffect(key1 = loginViewModel.hasUser) {
        if (loginViewModel.hasUser) {
          //  navController.navigate("book")
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
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .verticalScroll(rememberScrollState())
                        .fillMaxWidth()
                        .imePadding()
                        .padding(MaterialTheme.dimen.base_2x),
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
                    CustomVerticalSpacer(size = MaterialTheme.dimen.base_4x)
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        CommonTextField(
                            modifier = Modifier
                                .fillMaxWidth(),
                            placeholder = "Type Email ( eg.moe@gmail.com )",
                            value = loginUiState.userName,
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Next,
                            onValueChanged = {
                                loginViewModel.onUserNameChange(it)
                            },
                            onValueCleared = {
                                loginViewModel.onUserNameClear()
                            },
                            isError = isError ?: false,
                            errorMessage = "Please type correct Email name"
                        )
                        CustomVerticalSpacer(size = MaterialTheme.dimen.base_2x)
                        PasswordTextField(
                            onValueChanged = {
                                loginViewModel.onPasswordNameChange(it)
                            },
                            keyboardAction = { keyboardController!!.hide() },
                            isError = isError ?: false,
                            errorMessage = "Password length must be at least 4 ( eg.kozaw123 )",
                            password = loginUiState.password,
                            imeAction = ImeAction.Done,
                            keyboardType = KeyboardType.Password,
                            placeholder = "Type password",
                        )
                        Spacer(modifier = Modifier.size(16.dp))
                        Button(
                            onClick = {
                                loginViewModel.loginUser()
                            },
                            shape = CircleShape,
                            enabled = loginBtnState.value
                        ) {
                            Text(text = "Login")
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
                                onNavToSignUpPage()
                            }
                        ) {
                            Text(
                                text = "Sign Up",
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
