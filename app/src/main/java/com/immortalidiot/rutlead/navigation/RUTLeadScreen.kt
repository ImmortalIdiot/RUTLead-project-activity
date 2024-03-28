package com.immortalidiot.rutlead.navigation

import androidx.compose.runtime.Immutable

@Immutable
sealed class RUTLeadScreen (val route: String) {
    object AuthScreenFlow: RUTLeadScreen("Auth")
    object MainScreenFlow: RUTLeadScreen("Main")
}
