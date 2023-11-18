package edu.bu.recyclingtracker.data

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Recycling
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.Timeline
import androidx.compose.material3.Icon
import edu.bu.recyclingtracker.ui.screens.Routes

var navItems = listOf<NavItem>(
    NavItem("Home", Icons.Default.Recycling, Routes.HOME_SCREEN),
    NavItem("Bin", Icons.Default.ShoppingCart, Routes.BIN_SUMMARY_SCREEN),
    NavItem("Stats", Icons.Default.Timeline, Routes.STATS_SCREEN)
)