package ui.theme

import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFFBB86FC)

val Gray900 = Color(0xFF262626)
val Gray800 = Color(0xFF484848)

val Black = Color(0xFF000000)
val White = Color(0xFFFFFFFF)

val DarkColorPalette = darkColors(
    primary = Color(0xFF283353),
    background = Gray800,
    surface = Color(0xFF2F3340),
    onPrimary = Color.White
)
val LightColorPalette = lightColors(
    primary = White
)