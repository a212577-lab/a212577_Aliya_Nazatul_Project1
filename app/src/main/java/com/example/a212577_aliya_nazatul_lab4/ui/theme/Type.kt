package com.example.a212577_aliya_nazatul_lab4.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import com.example.a212577_aliya_nazatul_lab4.R

val Afacad = FontFamily(
    Font(R.font.afacad)
)

val Karla = FontFamily(
    Font(R.font.karla_regular),
    Font(R.font.karla_bold, FontWeight.Bold)
)


val Typography = Typography(

    displayMedium = TextStyle(
        fontFamily = Afacad,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp
    ),
    titleLarge = TextStyle(
        fontFamily = Karla,
        fontWeight = FontWeight.Bold,
        fontSize = 20.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = Karla,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    )
)
