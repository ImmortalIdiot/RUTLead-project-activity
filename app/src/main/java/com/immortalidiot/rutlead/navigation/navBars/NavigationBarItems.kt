package com.immortalidiot.rutlead.navigation.navBars

import androidx.annotation.DrawableRes
import com.immortalidiot.rutlead.R
import com.immortalidiot.rutlead.navigation.main.MainScreen

sealed class NavigationBarItem(
    val route: String,
    val name: String,
    @DrawableRes val icon: Int
) {
    object Journal : NavigationBarItem(
        route = MainScreen.JournalScreen.route,
        name = "Журнал",
        icon = R.drawable.journal
    )

    object Profile : NavigationBarItem(
        route = MainScreen.ProfileScreen.route,
        name = "Профиль",
        icon = R.drawable.profile
    )
}

val navigationBarItems = listOf(
    NavigationBarItem.Journal,
    NavigationBarItem.Profile
)
