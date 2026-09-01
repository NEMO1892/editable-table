package com.idt.editable_table

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.idt.ui.home_api.HomeKey
import com.idt.ui.table.api.TableKey
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

@Composable
internal fun NavigationRoot(
    entryBuilders: Set<@JvmSuppressWildcards EntryProviderScope<NavKey>.(NavBackStack<NavKey>) -> Unit>,
    modifier: Modifier = Modifier
) {
    val backStack = rememberNavBackStack(
        configuration = SavedStateConfiguration {
            serializersModule = SerializersModule {
                polymorphic(NavKey::class) {
                    subclass(HomeKey::class, HomeKey.serializer())
                    subclass(TableKey::class, TableKey.serializer())
                }
            }
        },
        HomeKey
    )

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator(),
        ),
        transitionSpec = { ForwardTransition },
        popTransitionSpec = { BackwardTransition },
        predictivePopTransitionSpec = { BackwardTransition },
        entryProvider = entryProvider {
            entryBuilders.forEach { builder -> this.builder(backStack) }
        },
        modifier = modifier
    )
}

private const val TRANSITION_DURATION_MILLIS = 300

private fun slideTransition(
    initialOffsetX: (fullWidth: Int) -> Int,
    targetOffsetX: (fullWidth: Int) -> Int
): ContentTransform = slideInHorizontally(
    animationSpec = tween(TRANSITION_DURATION_MILLIS),
    initialOffsetX = initialOffsetX
) togetherWith slideOutHorizontally(
    animationSpec = tween(TRANSITION_DURATION_MILLIS),
    targetOffsetX = targetOffsetX
)

private val ForwardTransition = slideTransition(
    initialOffsetX = { fullWidth -> fullWidth },
    targetOffsetX = { fullWidth -> -fullWidth }
)

private val BackwardTransition = slideTransition(
    initialOffsetX = { fullWidth -> -fullWidth },
    targetOffsetX = { fullWidth -> fullWidth }
)
