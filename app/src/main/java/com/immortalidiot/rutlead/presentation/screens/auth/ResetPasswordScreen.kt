package com.immortalidiot.rutlead.presentation.screens.auth

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.immortalidiot.rutlead.R
import com.immortalidiot.rutlead.navigation.auth.AuthScreen
import com.immortalidiot.rutlead.ui.components.buttons.PrimaryButton
import com.immortalidiot.rutlead.ui.components.other.BottomSnackbar
import com.immortalidiot.rutlead.ui.components.other.BoxLabel
import com.immortalidiot.rutlead.ui.components.other.RedirectText
import com.immortalidiot.rutlead.ui.components.fields.PasswordField
import com.immortalidiot.rutlead.ui.components.fields.PrimaryTextField
import com.immortalidiot.rutlead.providers.LocalSnackbarHostState
import com.immortalidiot.rutlead.providers.showMessage
import com.immortalidiot.rutlead.ui.theme.LocalDimensions
import com.immortalidiot.rutlead.ui.theme.ThemeColors
import com.immortalidiot.rutlead.ui.theme.boldInter14
import com.immortalidiot.rutlead.ui.theme.mediumInter12
import com.immortalidiot.rutlead.ui.theme.mediumInter14
import com.immortalidiot.rutlead.presentation.viemodels.auth.ResetPasswordViewModel

@OptIn(
    ExperimentalMaterial3Api::class,
    ExperimentalComposeUiApi::class
)
@Composable
fun ResetPassword(
    modifier: Modifier = Modifier,
    viewModel: ResetPasswordViewModel,
    navController: NavHostController
) {
    val uiState by viewModel.uiState.collectAsState()
    val state by viewModel.mutableState.collectAsState()

    val dimensions = LocalDimensions.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val snackbarHostState = LocalSnackbarHostState.current
    val focusManager = LocalFocusManager.current

    val palette = if (isSystemInDarkTheme()) ThemeColors.Dark else ThemeColors.Light

    val roundedShape = RoundedCornerShape(dimensions.shapeXLarge)

    var emailErrorMessage = ""
    var passwordErrorMessage = ""
    var confirmPasswordErrorMessage = ""


    LaunchedEffect(key1 = state) {
        (state as? ResetPasswordViewModel.State.ValidationError)?.let {
            emailErrorMessage = it.emailError.toString()
            passwordErrorMessage = it.passwordError.toString()
            confirmPasswordErrorMessage = it.confirmPasswordError.toString()
        }

        if (state is ResetPasswordViewModel.State.ValidationError) {
            val errorState = state as ResetPasswordViewModel.State.ValidationError

            when {
                errorState.emailError != null ->
                    snackbarHostState.showMessage(emailErrorMessage)
                errorState.passwordError != null ->
                    snackbarHostState.showMessage(passwordErrorMessage)
                errorState.confirmPasswordError != null ->
                    snackbarHostState.showMessage(confirmPasswordErrorMessage)
            }
            viewModel.clearErrorStack()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = palette.backgroundScreen),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = modifier
                .fillMaxWidth(0.4f)
                .fillMaxHeight(0.07f),
            imageVector = ImageVector.vectorResource(
                id = if (isSystemInDarkTheme()) {
                    R.drawable.ic_app_dark_logo
                } else {
                    R.drawable.ic_app_light_logo
                }
            ),
            contentDescription = "logo",
        )
        Spacer(modifier = modifier.height(dimensions.verticalXXLarge))
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .fillMaxWidth(0.85f)
                .clip(roundedShape)
                .background(color = palette.primary)
                .border(
                    width = dimensions.borderSSmall,
                    color = palette.outline,
                    shape = roundedShape
                )
                .padding(vertical = dimensions.verticalBigPadding)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.fillMaxWidth(0.85f)
            ) {
                BoxLabel(
                    text = stringResource(id = R.string.reset_password),
                    palette = palette
                )
                Spacer(modifier = modifier.height(dimensions.verticalXXLarge))
                PrimaryTextField(
                    modifier = modifier.border(
                        width = dimensions.borderSSmall,
                        color = palette.outline,
                        shape = roundedShape
                    ),
                    value = uiState.email,
                    isSingleLine = true,
                    label = {
                        Text(
                            text = stringResource(id = R.string.email),
                            style = if (uiState.isFocused || uiState.email.isNotBlank()) {
                                mediumInter12.copy(color = palette.containerText)
                            } else {
                                mediumInter14.copy(color = palette.containerText)
                            }
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = palette.container,
                        textColor = palette.containerText,
                        cursorColor = palette.cursor,
                        unfocusedLabelColor = palette.label,
                        focusedLabelColor = palette.label,
                        focusedSupportingTextColor = Color.White,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    ),
                    onTextChange = { email -> viewModel.changeEmail(email = email) }
                )
                Spacer(modifier = modifier.height(dimensions.verticalXLarge))
                PasswordField(
                    hint = stringResource(id = R.string.password),
                    palette = palette,
                    modifier = modifier.border(
                        width = dimensions.borderSSmall,
                        color = palette.outline,
                        shape = roundedShape
                    ),
                    passwordValue = uiState.password,
                    imageVector = if (uiState.isPasswordVisible) {
                        ImageVector.vectorResource(id = R.drawable.password_visibility_on)
                    } else {
                        ImageVector.vectorResource(id = R.drawable.password_visibility_off)
                    },
                    visualTransformation = if (uiState.isPasswordVisible) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    onDoneAction = {},
                    onIconClick = {
                        viewModel.changePasswordVisibility(uiState.isPasswordVisible)
                    },
                    onTextChange = { password -> viewModel.changePassword(password) },
                )
                Spacer(modifier = modifier.height(dimensions.verticalXLarge))
                PasswordField(
                    hint = stringResource(id = R.string.confirm_password),
                    palette = palette,
                    modifier = modifier.border(
                        width = dimensions.borderSSmall,
                        color = palette.outline,
                        shape = roundedShape
                    ),
                    passwordValue = uiState.confirmPassword,
                    imageVector = if (uiState.isPasswordVisible) {
                        ImageVector.vectorResource(id = R.drawable.password_visibility_on)
                    } else {
                        ImageVector.vectorResource(id = R.drawable.password_visibility_off)
                    },
                    visualTransformation = if (uiState.isPasswordVisible) {
                        VisualTransformation.None
                    } else {
                        PasswordVisualTransformation()
                    },
                    onDoneAction = {
                        focusManager.clearFocus()
                        keyboardController?.hide()
                        viewModel.resetPassword()
                    },
                    onIconClick = {
                        viewModel.changePasswordVisibility(uiState.isPasswordVisible)
                    },
                    onTextChange = { confirmPassword ->
                        viewModel.changeConfirmPassword(confirmPassword = confirmPassword)
                    },
                )
                Spacer(modifier = Modifier.height(dimensions.verticalXLarge))
                PrimaryButton(
                    modifier = modifier
                        .fillMaxHeight(0.16f)
                        .fillMaxWidth(0.55f),
                    palette = palette,
                    text = stringResource(id = R.string.change_password),
                    onButtonClick = {
                        focusManager.clearFocus()
                        keyboardController?.hide()
                        viewModel.resetPassword()
                    },
                )
                Spacer(modifier = modifier.height(dimensions.verticalSLarge))
                Row(
                    modifier = modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.account_existing),
                        style = boldInter14.copy(color = palette.text),
                    )
                    RedirectText(
                        modifier = modifier,
                        text = stringResource(id = R.string.login_text_button),
                        palette = palette,
                        onTextClick = { navController.navigate(AuthScreen.LoginScreen.route) }
                    )
                }
            }
        }
    }
    BottomSnackbar(
        modifier = modifier,
        palette = palette,
        snackbarHostState = snackbarHostState
    )
}

@Preview
@Composable
fun ResetPasswordPreview() {
    val snackbarHostState = remember { SnackbarHostState() }

    CompositionLocalProvider(LocalSnackbarHostState provides snackbarHostState) {
        ResetPassword(
            viewModel = ResetPasswordViewModel(),
            navController = rememberNavController()
        )
    }
}
