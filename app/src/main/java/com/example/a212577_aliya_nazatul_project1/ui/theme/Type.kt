package com.example.a212577_aliya_nazatul_project1.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.Font
import com.example.a212577_aliya_nazatul_project1.R

val Afacad = FontFamily(
    Font(R.font.afacad)
)

val Karla = FontFamily(
    // Pointing directly to the .ttf font files instead of XML font families
    // resolves the "XmlPullParser.getDepth()" null pointer exception during rendering.
    Font(R.font.karla, FontWeight.Normal),
    Font(R.font.karla, FontWeight.Bold)
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
