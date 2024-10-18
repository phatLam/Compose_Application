package com.kzqzd.composeapplication.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFFC0303)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

@Immutable
data class ColorFamily(
    val backgroundColor: Color,
    val onContainerColor: Color,
)

@Immutable
data class ExtendedColorScheme(
    val extra: ColorFamily = extendedLight.extra
)

val extendedLight = ExtendedColorScheme(
    extra = ColorFamily(
        backgroundColor = Color(0xFFFC0303),
        onContainerColor = PurpleGrey80
    )
)

val extendedDark = ExtendedColorScheme(
    extra = ColorFamily(
        backgroundColor = Color(0xFF6650a4),
        onContainerColor = PurpleGrey40
    )
)
val LocalExColorScheme = staticCompositionLocalOf { ExtendedColorScheme() }
