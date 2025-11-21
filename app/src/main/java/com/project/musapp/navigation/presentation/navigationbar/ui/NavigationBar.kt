package com.project.musapp.navigation.presentation.navigationbar.ui

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import com.project.musapp.R
import com.project.musapp.navigation.presentation.navigationbar.model.NavItem
import com.project.musapp.navigation.presentation.navigationbar.viewmodel.NavigationViewModel

@Composable
fun MusAppNavigationBar(
    navigationViewModel: NavigationViewModel,
    currentNavItemIndex: Int,
    onNavItemClick: (Int) -> Unit
) {
    val navItemList = listOf(
        NavItem(
            label = "Home",
            painter = painterResource(R.drawable.home_24px),
            contentDescription = "Pantalla principal"
        ),
        NavItem(
            label = "Colecciones",
            painter = painterResource(R.drawable.perm_media_24dp_e3e3e3_fill0_wght400_grad0_opsz24),
            contentDescription = "Apartado de colecciones creadas por el usuario"
        ),
        NavItem(
            label = "Cultura artística",
            imageVector = Icons.Outlined.School,
            contentDescription = "Apartado de glosario técnico relacionado con el arte plástico y curiosidades " +
                    "de los cuadros y artistas"
        )
    )

    NavigationBar(containerColor = Color(color = 0xFF12AA7A)) {
        navItemList.forEachIndexed { index, item ->
            MusAppNavigationItem(
                navItem = item,
                isSelected = index == currentNavItemIndex
            ) {
                navigationViewModel.onNavItemClick(currentNavItemIndex = index)

                onNavItemClick(index)
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
        icon = {
            if (navItem.imageVector != null) Icon(
                imageVector = navItem.imageVector,
                contentDescription = navItem.contentDescription
            ) else Icon(
                painter = navItem.painter!!,
                contentDescription = navItem.contentDescription
            )
        },
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