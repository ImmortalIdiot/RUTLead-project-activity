package com.immortalidiot.rutlead.navigation.navBars

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.immortalidiot.rutlead.ui.theme.ClassicGray
import com.immortalidiot.rutlead.ui.theme.LightBlue
import com.immortalidiot.rutlead.ui.theme.LocalDimensions

@Composable
fun BottomNavigationBar(
    modifier: Modifier = Modifier,
    navController: NavHostController,
) {
    val navigationBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navigationBackStackEntry?.destination?.route

    val scheme = MaterialTheme.colorScheme
    val dimensions = LocalDimensions.current

    val roundedBorderShape = RoundedCornerShape(dimensions.shapeMLarge)

    Box(
        modifier = modifier
            .border(
                width = dimensions.borderXSSmall,
                color = ClassicGray,
                shape = roundedBorderShape
            )
    ) {
        NavigationBar(
            modifier = modifier,
            containerColor = scheme.onBackground,
        ) {
            navigationBarItems.forEach { item: NavigationBarItem ->
                val isSelected = currentRoute == item.route

                NavigationBarItem(
                    selected = isSelected,
                    onClick = {
                        if (currentRoute != item.route) {
                            navController.navigate(item.route) {
                                // TODO: send the user when the stack is empty and the user press back system button
                                if (currentRoute != null) {
                                    popUpTo(route = currentRoute)
                                }
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
                            color = scheme.tertiary
                        )
                    },
                    alwaysShowLabel = false,
                    colors = NavigationBarItemDefaults.colors(indicatorColor = scheme.onBackground)
                )
            }
        }
    }
}

@Preview
@Composable
fun MainNavigationBarPreview() {
    BottomNavigationBar(navController = rememberNavController())
}
