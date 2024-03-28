package com.immortalidiot.rutlead.navigation.navBars

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.immortalidiot.rutlead.ui.theme.ClassicGray
import com.immortalidiot.rutlead.ui.theme.LightBlue
import com.immortalidiot.rutlead.ui.theme.ThemeColors

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    palette: ThemeColors = if (isSystemInDarkTheme()) ThemeColors.Dark else ThemeColors.Light
) {
    val navigationBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navigationBackStackEntry?.destination?.route

    NavigationBar(
        modifier = modifier,
        containerColor = palette.backgroundScreen,
    ) {
        navigationBarItems.forEach { item: NavigationBarItem ->
            val isSelected = currentRoute == item.route

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    if (currentRoute != item.route) {
                        navController.navigate(item.route) {
                            // TODO: send the user when the stack is empty and the user press back system button
                            if (currentRoute != null) { popUpTo(route = currentRoute) }
                        }
                    }
                },
                icon = {
                    Icon(
                        imageVector = ImageVector.vectorResource(item.icon),
                        contentDescription = null,
                        tint = if (isSelected) LightBlue else ClassicGray
                    )
                },
                label = {
                    Text(
                        text = item.name,
                        color = palette.textNavBar
                    )
                },
                alwaysShowLabel = false,
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = palette.indicatorNavBar,
                )
            )
        }
    }
}

@Preview
@Composable
fun MainNavigationBarPreview() {
    BottomNavigationBar(navController = rememberNavController())
}
