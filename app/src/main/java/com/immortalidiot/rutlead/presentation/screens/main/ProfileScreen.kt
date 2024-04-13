package com.immortalidiot.rutlead.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.immortalidiot.rutlead.R
import com.immortalidiot.rutlead.navigation.auth.AuthScreen
import com.immortalidiot.rutlead.presentation.viemodels.main.ProfileScreenViewModel
import com.immortalidiot.rutlead.presentation.viemodels.main.ThemeViewModel
import com.immortalidiot.rutlead.providers.LocalSnackbarHostState
import com.immortalidiot.rutlead.providers.showMessage
import com.immortalidiot.rutlead.ui.components.buttons.PrimaryButton
import com.immortalidiot.rutlead.ui.components.dialogs.PrimaryProfileDialog
import com.immortalidiot.rutlead.ui.components.fields.PrimaryTextField
import com.immortalidiot.rutlead.ui.components.fields.constant.FullNameField
import com.immortalidiot.rutlead.ui.components.fields.constant.GroupField
import com.immortalidiot.rutlead.ui.components.other.UserAvatar
import com.immortalidiot.rutlead.ui.theme.ClassicColors
import com.immortalidiot.rutlead.ui.theme.ClassicGray
import com.immortalidiot.rutlead.ui.theme.DarkBlue
import com.immortalidiot.rutlead.ui.theme.LightBlue
import com.immortalidiot.rutlead.ui.theme.LightRed
import com.immortalidiot.rutlead.ui.theme.LocalDimensions
import com.immortalidiot.rutlead.ui.theme.boldInter16
import com.immortalidiot.rutlead.ui.theme.boldLato20
import com.immortalidiot.rutlead.ui.theme.mediumInter12
import com.immortalidiot.rutlead.ui.theme.mediumInter14
import com.immortalidiot.rutlead.ui.theme.mediumInter16
import com.immortalidiot.rutlead.ui.theme.mediumInter32

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    colorUserAvatar: Color,
    themeViewModel: ThemeViewModel,
    profileScreenViewModel: ProfileScreenViewModel
) {
    val state by themeViewModel.mutableState.collectAsState()
    val uiState by profileScreenViewModel.uiState.collectAsState()

    val profileState by profileScreenViewModel.mutableState.collectAsState()

    val scheme = MaterialTheme.colorScheme

    val dimensions = LocalDimensions.current
    val roundedShape = RoundedCornerShape(dimensions.shapeXLarge)
    val commonTextStyle = boldInter16.copy(color = MaterialTheme.colorScheme.onPrimary)
    val themeContentColor = scheme.onPrimary

    val snackbarHostState = LocalSnackbarHostState.current

    var groupError = ""

    (profileState as? ProfileScreenViewModel.State.GroupValidationError)?.let { errorState ->
        groupError = errorState.groupError.toString()
    }

    LaunchedEffect(key1 = profileState) {
        if (profileState is ProfileScreenViewModel.State.GroupValidationError) {
            val errorState = profileState as ProfileScreenViewModel.State.GroupValidationError
            if (errorState.groupError != null) {
                snackbarHostState.showMessage(groupError)
            }
            profileScreenViewModel.clearErrorStack()
        }
    }

    // TODO(): get values from the db
    val firstName = "Иванов"
    val secondName = "Александр"
    val thirdName = "Дмитриевич"
    val group = "УВП-212"
    val url = null
    val initials = firstName.take(1) + secondName.take(1)

    if (profileState is ProfileScreenViewModel.State.LogoutDialog) {
        Dialog(
            onDismissRequest = {
                profileScreenViewModel.onCancelled()
            },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = false,
                usePlatformDefaultWidth = false
            )
        ) {
            Column(
                modifier = modifier
                    .fillMaxWidth(0.8f)
                    .fillMaxHeight(0.2f)
                    .clip(roundedShape)
                    .background(color = scheme.onBackground)
                    .border(
                        width = LocalDimensions.current.borderXSSmall,
                        color = ClassicGray,
                        shape = roundedShape
                    ),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.confirmation),
                    style = boldInter16.copy(color = scheme.onSurface)
                )
                Text(
                    text = stringResource(id = R.string.exit_text),
                    style = mediumInter16.copy(color = scheme.onSurface)
                )
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .background(color = scheme.onBackground),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    PrimaryButton(
                        modifier = modifier
                            .fillMaxHeight(0.45f)
                            .fillMaxWidth(0.3f),
                        containerColor = LightRed,
                        scheme = scheme,
                        text = stringResource(id = R.string.confirm),
                        textStyle = mediumInter14.copy(),
                        colorText = scheme.onPrimary,
                        onButtonClick = {
                            navController.navigate(AuthScreen.LoginScreen.route) {
                                popUpTo(0) {
                                    inclusive = false
                                    saveState = true
                                }
                            }
                        },
                        shape = RoundedCornerShape(dimensions.shapeXLarge),
                        outlineColor = scheme.onBackground,
                        borderWidth = dimensions.borderOne
                    )
                    PrimaryButton(
                        modifier = modifier
                            .fillMaxHeight(0.45f)
                            .fillMaxWidth(0.4f),
                        containerColor = DarkBlue,
                        scheme = scheme,
                        text = stringResource(id = R.string.cancel),
                        textStyle = mediumInter14.copy(),
                        colorText = scheme.onPrimary,
                        onButtonClick = {
                            profileScreenViewModel.onCancelled()
                        },
                        shape = RoundedCornerShape(dimensions.shapeXLarge),
                        outlineColor = scheme.onBackground,
                        borderWidth = dimensions.borderOne
                    )
                }
            }
        }
    }

    if (profileState is ProfileScreenViewModel.State.ChangeGroupDialog ||
        profileState is ProfileScreenViewModel.State.GroupValidationError
    ) {
        Box(
            modifier = modifier
                .fillMaxHeight()
                .verticalScroll(rememberScrollState()),
            contentAlignment = Alignment.Center
        ) {
            PrimaryProfileDialog(
                modifier = modifier,
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true,
                    usePlatformDefaultWidth = false
                ),
                headerText = stringResource(id = R.string.profile_change_group),
                headerTextStyle = boldInter16.copy(),
                headerTextColor = scheme.onSurface,
                onCancelled = { profileScreenViewModel.onCancelled() },
                isSnackbar = true,
                snackbarHostState = snackbarHostState
            ) {
                PrimaryTextField(
                    modifier = modifier.border(
                        width = dimensions.borderXSSmall,
                        color = LightBlue,
                        shape = roundedShape
                    ),
                    value = uiState.group,
                    isSingleLine = true,
                    label = {
                        Text(
                            text = stringResource(id = R.string.group_with_example),
                            style = if (uiState.isGroupFieldFocused || uiState.group.isNotBlank()) {
                                mediumInter12.copy(color = scheme.primary)
                            } else {
                                mediumInter14.copy(color = scheme.primary)
                            }
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = scheme.primaryContainer,
                        textColor = scheme.primary,
                        cursorColor = scheme.onSecondary,
                        unfocusedLabelColor = scheme.onPrimary,
                        focusedLabelColor = scheme.onPrimary,
                        focusedSupportingTextColor = Color.White,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent
                    ),
                    onTextChange = { group -> profileScreenViewModel.changeGroup(group = group) }
                )
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .background(color = scheme.onBackground)
                        .padding(horizontal = dimensions.horizontalNormalPadding),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    PrimaryButton(
                        modifier = modifier.weight(1f),
                        containerColor = scheme.onBackground,
                        scheme = scheme,
                        text = stringResource(id = R.string.cancel_group),
                        textStyle = mediumInter14.copy(),
                        colorText = scheme.onSurface,
                        onButtonClick = {
                            profileScreenViewModel.onCancelled()
                        },
                        shape = RoundedCornerShape(dimensions.shapeXLLarge),
                        outlineColor = LightBlue,
                        borderWidth = dimensions.borderXSSmall
                    )
                    Spacer(modifier = modifier.width(dimensions.horizontalNormalPadding))
                    PrimaryButton(
                        modifier = modifier.weight(1f),
                        containerColor = LightBlue,
                        scheme = scheme,
                        text = stringResource(id = R.string.save_group),
                        textStyle = mediumInter14.copy(),
                        colorText = scheme.onPrimary,
                        onButtonClick = {
                            profileScreenViewModel.changeGroup()
                        },
                        shape = RoundedCornerShape(dimensions.shapeXLLarge),
                        backgroundColor = LightBlue,
                        outlineColor = LightBlue,
                        borderWidth = dimensions.borderOne
                    )
                }
            }
            SnackbarHost(
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter),
                hostState = snackbarHostState
            ) {
                Snackbar(
                    snackbarData = it,
                    containerColor = scheme.errorContainer,
                    contentColor = scheme.onPrimary,
                    dismissActionContentColor = scheme.onPrimary,
                    shape = RoundedCornerShape(LocalDimensions.current.shapeNormal)
                )
            }
        }

        Dialog(
            onDismissRequest = { profileScreenViewModel.onCancelled() },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
                usePlatformDefaultWidth = false
            )
        ) {
            Box(
                modifier = modifier
                    .fillMaxHeight()
                    .verticalScroll(rememberScrollState()),
            ) {
                Column(
                    modifier = modifier
                        .heightIn(max = 200.dp)
                        .widthIn(max = 300.dp)
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clip(roundedShape)
                        .background(color = scheme.onBackground)
                        .border(
                            width = LocalDimensions.current.borderXSSmall,
                            color = ClassicGray,
                            shape = roundedShape
                        )
                        .align(Alignment.Center),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(id = R.string.confirmation),
                        style = boldInter16.copy(color = scheme.onSurface)
                    )
                    PrimaryTextField(
                        modifier = modifier.border(
                            width = dimensions.borderXSSmall,
                            color = LightBlue,
                            shape = roundedShape
                        ),
                        value = uiState.group,
                        isSingleLine = true,
                        label = {
                            Text(
                                text = stringResource(id = R.string.group_with_example),
                                style = if (uiState.isGroupFieldFocused || uiState.group.isNotBlank()) {
                                    mediumInter12.copy(color = scheme.primary)
                                } else {
                                    mediumInter14.copy(color = scheme.primary)
                                }
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            containerColor = scheme.primaryContainer,
                            textColor = scheme.primary,
                            cursorColor = scheme.onSecondary,
                            unfocusedLabelColor = scheme.onPrimary,
                            focusedLabelColor = scheme.onPrimary,
                            focusedSupportingTextColor = Color.White,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        ),
                        onTextChange = { group -> profileScreenViewModel.changeGroup(group = group) }
                    )
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .background(color = scheme.onBackground)
                            .padding(horizontal = dimensions.horizontalNormalPadding),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        PrimaryButton(
                            modifier = modifier.weight(1f),
                            containerColor = scheme.onBackground,
                            scheme = scheme,
                            text = stringResource(id = R.string.cancel_group),
                            textStyle = mediumInter14.copy(),
                            colorText = scheme.onSurface,
                            onButtonClick = {
                                profileScreenViewModel.onCancelled()
                            },
                            shape = RoundedCornerShape(dimensions.shapeXLLarge),
                            outlineColor = LightBlue,
                            borderWidth = dimensions.borderXSSmall
                        )
                        Spacer(modifier = modifier.width(dimensions.horizontalNormalPadding))
                        PrimaryButton(
                            modifier = modifier.weight(1f),
                            containerColor = LightBlue,
                            scheme = scheme,
                            text = stringResource(id = R.string.save_group),
                            textStyle = mediumInter14.copy(),
                            colorText = scheme.onPrimary,
                            onButtonClick = {
                                profileScreenViewModel.changeGroup()
                            },
                            shape = RoundedCornerShape(dimensions.shapeXLLarge),
                            outlineColor = scheme.onBackground,
                            borderWidth = dimensions.borderOne
                        )
                    }
                }
                SnackbarHost(
                    modifier = modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter),
                    hostState = snackbarHostState
                ) {
                    Snackbar(
                        snackbarData = it,
                        containerColor = scheme.errorContainer,
                        contentColor = scheme.onPrimary,
                        dismissActionContentColor = scheme.onPrimary,
                        shape = RoundedCornerShape(LocalDimensions.current.shapeNormal)
                    )
                }
            }
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(scheme.onBackground),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth(0.8f)
                .clip(roundedShape)
                .border(
                    width = dimensions.borderSSmall,
                    color = scheme.outline,
                    shape = roundedShape
                )
                .background(color = scheme.onSecondary)
                .padding(vertical = dimensions.bigPadding),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    modifier = modifier
                        .padding(dimensions.bigPadding)
                        .clickable {
                            profileScreenViewModel.changeLogoutDialogVisibility()
                            //TODO: add log out via the viewmodel
                        },
                    imageVector = ImageVector.vectorResource(id = R.drawable.exit_img),
                    contentDescription = "log out",
                    tint = themeContentColor
                )
            }
            UserAvatar(modifier = modifier,
                url = url,
                initials = initials,
                backgroundColor = colorUserAvatar,
                textStyle = mediumInter32.copy(color = scheme.onPrimary),
                onIconClick = {
                    //TODO: add the ability to upload photo from the local storage
                }
            )
            Spacer(modifier = modifier.height(dimensions.verticalSmallPadding))
            FullNameField(
                modifier = modifier,
                fullNameTextStyle = commonTextStyle,
                firstName = firstName,
                secondName = secondName,
                thirdName = thirdName
            )
            GroupField(
                modifier = modifier.padding(vertical = dimensions.verticalSmallPadding),
                group = group,
                groupTextStyle = commonTextStyle
            )
            Spacer(modifier = modifier.height(dimensions.verticalSLarge))
            Row(
                modifier = modifier
                    .fillMaxWidth(0.9f)
                    .fillMaxHeight(0.12f)
                    .border(
                        width = dimensions.borderSSmall,
                        color = themeContentColor,
                        shape = roundedShape
                    )
                    .padding(vertical = dimensions.verticalSmallPadding),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                val customModifier = modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(dimensions.normalPadding)
                    .border(
                        width = dimensions.borderSSmall,
                        color = Color.Transparent,
                        shape = roundedShape
                    )
                    .clip(roundedShape)
                    .background(color = themeContentColor)

                Box(
                    modifier = if (state is ThemeViewModel.State.AutoThemeSelected) {
                        customModifier
                    } else {
                        modifier
                            .weight(1f)
                            .clickable(onClick = { themeViewModel.onAutoTheme() })
                    },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.theme_auto),
                        style = boldLato20.copy(
                            color = if (state is ThemeViewModel.State.AutoThemeSelected) {
                                scheme.onSecondary
                            } else {
                                themeContentColor
                            }
                        ),
                        textAlign = TextAlign.Center
                    )
                }
                Box(
                    modifier = if (state is ThemeViewModel.State.LightThemeSelected) {
                        customModifier
                    } else {
                        modifier
                            .weight(1f)
                            .clickable(onClick = { themeViewModel.onLightTheme() })
                    },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.light_theme_controller),
                        contentDescription = "light_theme_controller",
                        tint = if (state is ThemeViewModel.State.LightThemeSelected) {
                            scheme.onSecondary
                        } else {
                            themeContentColor
                        }
                    )
                }
                Box(
                    modifier = if (state is ThemeViewModel.State.DarkThemeSelected) {
                        customModifier
                    } else {
                        modifier
                            .weight(1f)
                            .clickable(onClick = { themeViewModel.onDarkTheme() })
                    },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.dark_theme_controller),
                        contentDescription = "dark_theme_controller",
                        tint = if (state is ThemeViewModel.State.DarkThemeSelected) {
                            scheme.onSecondary
                        } else {
                            themeContentColor
                        }
                    )
                }
            }
        }
        Spacer(modifier = modifier.height(dimensions.verticalStandard))
        PrimaryButton(
            modifier = modifier.fillMaxWidth(0.8f),
            borderWidth = dimensions.borderXSSmall,
            shape = RoundedCornerShape(dimensions.shapeMLarge),
            scheme = scheme,
            text = stringResource(id = R.string.profile_change_group),
            textStyle = mediumInter16,
            colorText = scheme.onSecondary,
            outlineColor = scheme.onSecondary,
            containerColor = scheme.onBackground,
            onButtonClick = {
                profileScreenViewModel.changeGroupDialogVisibility()
            }
        )
        Spacer(modifier = modifier.height(dimensions.verticalStandard))
        PrimaryButton(
            modifier = modifier.fillMaxWidth(0.8f),
            borderWidth = dimensions.borderXSSmall,
            shape = RoundedCornerShape(dimensions.shapeMLarge),
            scheme = scheme,
            text = stringResource(id = R.string.delete_account),
            textStyle = mediumInter16,
            colorText = scheme.error,
            outlineColor = scheme.error,
            containerColor = scheme.onBackground,
            onButtonClick = {
                //TODO(): open the delete account dialog
            }
        )
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    val backgroundUserColor = ClassicColors.AvatarColor.getRandomColor()
    ProfileScreen(
        colorUserAvatar = backgroundUserColor,
        themeViewModel = ThemeViewModel(),
        profileScreenViewModel = ProfileScreenViewModel(),
        navController = rememberNavController()
    )
}
