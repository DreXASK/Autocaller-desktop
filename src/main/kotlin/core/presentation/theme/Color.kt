package core.presentation.theme

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
    primary = Color(0xFF4fc7ff),
    onPrimary = Color(0xFF2F3340),
    secondary = Color(0xFF03DAC6),

    primaryVariant = Color.Black,
    secondaryVariant = Color.Black,


    surface = Color(0xFF1A1C23),
    //onSurface = Color.White
    onSurface = Color.White
)
val LightColorPalette = lightColors(
    primary = White
)