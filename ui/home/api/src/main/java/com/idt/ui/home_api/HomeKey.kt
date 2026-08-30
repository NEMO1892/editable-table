package com.idt.ui.home_api

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface HomeNavKey : NavKey

@Serializable
data object HomeKey : HomeNavKey
