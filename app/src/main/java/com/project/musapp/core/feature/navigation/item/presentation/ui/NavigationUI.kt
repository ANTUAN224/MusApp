package com.project.musapp.core.feature.navigation.item.presentation.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.project.musapp.core.feature.navigation.item.domain.model.NavItem
import com.project.musapp.core.feature.navigation.item.presentation.viewmodel.NavigationViewModel

@Composable
fun MusAppNavigationBar(navigationViewModel: NavigationViewModel, navItemIndex: Int) {
    val navItemList = listOf(
        NavItem(label = "Home", icon = Icons.Outlined.Home),
        NavItem(label = "Colecciones", icon = Icons.Outlined.Folder),
        NavItem(label = "Cultura artística", icon = Icons.Outlined.School)
    )
    NavigationBar(containerColor = Color(color = 0xFF12AA7A)) {
        navItemList.forEachIndexed { index, item ->
            MusAppNavigationItem(
                navItem = item, isSelected = index == navItemIndex
            ) {
                navigationViewModel.onNavItemClick(index)
            }
        }
    }
}

@Composable
fun RowScope.MusAppNavigationItem(
    navItem: NavItem, isSelected: Boolean, onNavItemClick: () -> Unit
) {
    NavigationBarItem(
        label = { Text(text = navItem.label) },
        icon = { Icon(imageVector = navItem.icon, contentDescription = null) },
        selected = isSelected,
        onClick = { onNavItemClick() },
        colors = NavigationBarItemDefaults.colors(
            indicatorColor = Color.Black,
            selectedIconColor = Color.White,
            selectedTextColor = Color.Black,
            unselectedIconColor = Color.White,
            unselectedTextColor = Color.White,
        )
    )
}