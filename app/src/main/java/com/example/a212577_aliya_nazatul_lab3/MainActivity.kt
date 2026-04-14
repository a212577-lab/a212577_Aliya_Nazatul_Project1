package com.example.a212577_aliya_nazatul_lab3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextFieldDefaults
import com.example.a212577_aliya_nazatul_lab3.ui.theme.A212577_Aliya_Nazatul_Lab3Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            A212577_Aliya_Nazatul_Lab3Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = colorScheme.background
                ) {
                    BMIApp()
                }
            }
        }
    }
}
@Composable
fun BMIApp() {

    // STATE (LAB 2 REQUIREMENT)
    //mutableStateOf - stores input value
    //remember - keep value even when recomposition happens

    var heightInput by remember { mutableStateOf("") }
    var weightInput by remember { mutableStateOf("") }

    //controls when card is shown (initial: false)
    var showResult by remember { mutableStateOf(false) }
    var bmiResult by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var diet by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(20.dp)) {

        Spacer(modifier = Modifier.height(18.dp))

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Diet Diary",
                color = colorScheme.onBackground,
                style = MaterialTheme.typography.displayMedium
            )

            Image(
                painter = painterResource(id = R.drawable.diaryblue), // your icon
                contentDescription = "App Icon",
                modifier = Modifier
                    .size(36.dp)
                    .padding(start = 10.dp)
            )
        }


        Spacer(modifier = Modifier.height(12.dp))

        // HEIGHT INPUT
        OutlinedTextField(
            value = heightInput,
            onValueChange = { heightInput = it }, // it: new text, updates heightInput
            label = { Text("Height (cm)") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorScheme.secondary,
                unfocusedBorderColor = colorScheme.secondary.copy(alpha = 0.5f),
                focusedLabelColor = colorScheme.secondary
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

        // WEIGHT INPUT
        OutlinedTextField(
            value = weightInput,
            onValueChange = { weightInput = it },
            label = { Text("Weight (kg)") },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = colorScheme.secondary,
                unfocusedBorderColor = colorScheme.secondary.copy(alpha = 0.5f),
                focusedLabelColor = colorScheme.secondary
            )
        )

        Spacer(modifier = Modifier.height(12.dp))

        // BUTTON
        Button(
            onClick = {
                //convert text to number
                val height = heightInput.toFloatOrNull()
                val weight = weightInput.toFloatOrNull()

                if (height != null && weight != null && height > 0) {
                    val heightInMeter = height / 100
                    val bmi = weight / (heightInMeter * heightInMeter)
                    bmiResult = "%.2f".format(bmi)

                    // Determine category
                    category = when {
                        bmi < 18.5 -> "Underweight"
                        bmi < 24.9 -> "Normal weight"
                        bmi < 29.9 -> "Overweight"
                        else -> "Obese"
                    }

                    // Provide diet suggestion
                    diet = when (category) {
                        "Underweight" -> "Eat more calories with healthy foods."
                        "Normal weight" -> "Maintain your current diet."
                        "Overweight" -> "Reduce calorie intake and exercise regularly."
                        "Obese" -> "Consult a dietitian and follow a strict diet plan."
                        else -> ""
                    }

                    // true = card appear
                    showResult = true
                } else {
                    // Handle invalid input
                    bmiResult = "0.0"
                    category = "Invalid input"
                    diet = ""
                    showResult = true
                }
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorScheme.primary
            )
        ) {
            Text("Calculate BMI")
        }

        Spacer(modifier = Modifier.height(20.dp))

        val imageRes = when (category) {
            "Underweight" -> R.drawable.under
            "Normal weight" -> R.drawable.normal
            "Overweight" -> R.drawable.over
            "Obese" -> R.drawable.over
            else -> R.drawable.normal // default image
        }

        // RESULT CARD
        if (showResult) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .animateContentSize()
                    .clickable { expanded = !expanded },
                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween // push content apart
                ) {


                    Column(
                        modifier = Modifier.weight(1f) // take available space
                    ) {
                        Text(
                            "BMI: %.2f".format(bmiResult.toFloatOrNull() ?: 0f),
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Category: $category",
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.onSurface
                        )

                        Spacer(modifier = Modifier.height(8.dp))
                        if (expanded){
                            Text("Diet Suggestion:")
                            Text(diet)
                        }
                    }

                    Image(
                        painter = painterResource(id = imageRes),
                        contentDescription = "BMI Image",
                        modifier = Modifier.size(60.dp).padding(start = 12.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text("Monthly Weight Progress", fontSize = 18.sp, color = colorScheme.secondary)

        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.Bottom
        ) {
            FakeBar(0.6f, "Jan")
            FakeBar(0.7f, "Feb")
            FakeBar(0.5f, "Mar")
            FakeBar(0.75f, "Apr")
            FakeBar(0.6f, "May")
        }
    }
}
@Composable
fun FakeBar(value: Float, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = Modifier.height(120.dp)
    ) {
        Box(
            modifier = Modifier
                .width(20.dp)
                .fillMaxHeight(value)
        ) {
            LinearProgressIndicator(
                progress = { 1f },
                modifier = Modifier.fillMaxSize(),
                color = colorScheme.tertiary,
                trackColor = colorScheme.tertiary.copy(alpha = 0.3f),
                strokeCap = ProgressIndicatorDefaults.LinearStrokeCap
            )
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall
        )
    }
}
@Preview(showBackground = true)
@Composable
fun Preview() {
    A212577_Aliya_Nazatul_Lab3Theme {
        BMIApp()
    }
}
@Preview
@Composable
fun DarkThemePreview() {
    A212577_Aliya_Nazatul_Lab3Theme(darkTheme = true) {
        BMIApp()
    }
}
