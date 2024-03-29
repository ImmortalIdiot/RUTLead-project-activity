package com.immortalidiot.rutlead.presentation.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.immortalidiot.rutlead.R
import com.immortalidiot.rutlead.ui.components.fields.constant.FullNameField
import com.immortalidiot.rutlead.ui.components.fields.constant.GroupField
import com.immortalidiot.rutlead.ui.components.other.UserAvatar
import com.immortalidiot.rutlead.ui.theme.ClassicColors
import com.immortalidiot.rutlead.ui.theme.ClassicGray
import com.immortalidiot.rutlead.ui.theme.LightBlue
import com.immortalidiot.rutlead.ui.theme.LightRed
import com.immortalidiot.rutlead.ui.theme.LocalDimensions
import com.immortalidiot.rutlead.ui.theme.ThemeColors
import com.immortalidiot.rutlead.ui.theme.boldInter16
import com.immortalidiot.rutlead.ui.theme.boldLato20
import com.immortalidiot.rutlead.ui.theme.mediumInter32

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    colorUserAvatar: Color,
    //TODO(): add viewmodel
) {
    val palette = if (isSystemInDarkTheme()) ThemeColors.Dark else ThemeColors.Light

    val dimensions = LocalDimensions.current
    val roundedShape = RoundedCornerShape(dimensions.shapeXLarge)
    val commonTextStyle = boldInter16.copy(color = palette.text)
    val themeContentColor = palette.themeContent

    // TODO(): get values from the db
    val firstName = "Иванов"
    val secondName = "Александр"
    val thirdName = "Дмитриевич"
    val group = "УВП-212"
    val url = null
    val initials = firstName.take(1) + secondName.take(1)

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(palette.backgroundScreen),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = modifier
                .fillMaxWidth(0.8f)
                .clip(roundedShape)
                .border(
                    width = dimensions.borderSSmall,
                    color = palette.outline,
                    shape = roundedShape
                )
                .background(color = palette.profileContent)
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
                            //TODO: add log out via the viewmodel
                        },
                    imageVector = ImageVector.vectorResource(id = R.drawable.exit_img),
                    contentDescription = "log out",
                    tint = themeContentColor
                )
            }
            UserAvatar(
                modifier = modifier,
                url = url,
                initials = initials,
                backgroundColor = colorUserAvatar,
                textStyle = mediumInter32.copy(color = palette.text),
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
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                //TODO(): paint over the background of the selected mode
                Text(
                    text = "Авто",
                    style = boldLato20.copy(color = themeContentColor),
                    textAlign = TextAlign.Center
                )
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.light_theme_controller),
                    contentDescription = "light_theme_controller",
                    tint = themeContentColor
                )
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.dark_theme_controller),
                    contentDescription = "dark_theme_controller",
                    tint = themeContentColor
                )
            }
        }
        Spacer(modifier = modifier.height(dimensions.verticalXLarge))
        Column(
            modifier = modifier
                .fillMaxWidth(0.8f)
                .border(
                    width = dimensions.borderSSmall,
                    color = LightBlue,
                    shape = roundedShape
                )
                .padding(vertical = dimensions.verticalBigPadding)
        ) {
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.account_management),
                    color = palette.labelText,
                    style = commonTextStyle,
                    textAlign = TextAlign.Center
                )
            }
            Spacer(modifier = modifier.height(dimensions.verticalStandard))
            Column(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = dimensions.horizontalBigPadding)
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(end = dimensions.horizontalNormalPadding)
                        .clickable {
                            //TODO(): open the change password dialog
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.change_password),
                        color = palette.labelText,
                        style = commonTextStyle,
                        textAlign = TextAlign.Center
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "forward_arrow",
                        tint = ClassicGray
                    )
                }
                Spacer(modifier = modifier.height(dimensions.verticalStandard))
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(end = dimensions.horizontalNormalPadding)
                        .clickable {
                            //TODO(): open the delete account dialog
                        },
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(id = R.string.delete_account),
                        color = LightRed,
                        style = commonTextStyle,
                        textAlign = TextAlign.Center
                    )
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowRight,
                        contentDescription = "forward_arrow",
                        tint = ClassicGray
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ProfileScreenPreview() {
    val backgroundUserColor = ClassicColors.AvatarColor.getRandomColor()
    ProfileScreen(colorUserAvatar = backgroundUserColor)
}
