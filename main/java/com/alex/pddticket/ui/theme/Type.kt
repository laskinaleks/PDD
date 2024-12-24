package com.alex.pddticket.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.alex.pddticket.R

private val manropeFamily = FontFamily(
    Font(R.font.manrope_bold, FontWeight.Bold),
    Font(R.font.manrope_semibold, FontWeight.SemiBold),
    Font(R.font.manrope_medium, FontWeight.Medium),
    Font(R.font.manrope_regular, FontWeight.Normal),
    Font(R.font.manrope_light, FontWeight.Thin)
)

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontSize = 36.sp,
        fontFamily = manropeFamily,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Left,
    ),
    displayMedium = TextStyle(
        fontSize = 24.sp,
        fontFamily = manropeFamily,
        fontWeight = FontWeight.Normal,
        textAlign = TextAlign.Left,
    ),
    bodyLarge = TextStyle(
        fontSize = 16.sp,
        fontFamily = manropeFamily,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Center,
    ),
    bodyMedium = TextStyle(
        fontSize = 14.sp,
        fontFamily = manropeFamily,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Left,
    ),
    bodySmall = TextStyle(
        fontSize = 12.sp,
        fontFamily = manropeFamily,
        fontWeight = FontWeight.Medium,
        textAlign = TextAlign.Left,
    ),
    titleLarge = TextStyle(
        fontSize = 24.sp,
        fontFamily = manropeFamily,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center
    )
)