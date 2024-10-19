package com.example.gplaytipcalc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gplaytipcalc.ui.theme.Darkgray
import com.example.gplaytipcalc.ui.theme.GplayTipCalcTheme
import com.example.gplaytipcalc.ui.theme.Lightgray

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GplayTipCalcTheme {
                Greeting()
            }
        }
    }
}

@Composable
fun TipButton(
    tipPercentage: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val backgroundColor = if (isSelected) Color.White else Lightgray
    val textColor = if (isSelected) Color.Black else Color.White

    Row(
        modifier = Modifier
            .border(1.dp, Color.White, RoundedCornerShape(14.dp))
            .clip(RoundedCornerShape(14.dp))
            .width(80.dp)
            .height(65.dp)
            .background(backgroundColor)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = tipPercentage,
            fontSize = 20.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
            color = textColor
        )
    }
}

var currentAmount: Double = 0.0

fun TipCalc(amount: Double, percentage: Double): Double {
    return amount * percentage / 100
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Greeting(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }
    var tip by remember { mutableStateOf(0.0) }
    var totalAmount by remember { mutableStateOf(0.0) }
    var selectedButton by remember { mutableStateOf<String?>(null) } // Track selected button

    fun recalculateTipAndTotal() {
        selectedButton?.let { percentageString ->
            val percentage = percentageString.toDoubleOrNull() ?: 0.0
            tip = TipCalc(currentAmount, percentage)
            totalAmount = currentAmount + tip
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Darkgray)
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "TIP CALCULATOR",
            color = Color.White,
            fontSize = 24.sp,
            modifier = modifier
                .align(Alignment.CenterHorizontally)

        )

        Spacer(modifier = Modifier.padding(top = 24.dp))

        OutlinedTextField(
            value = text,

            onValueChange = {
                if (it.length <= 9) {
                    text = it
                    currentAmount = it.toDoubleOrNull() ?: 0.0
                    recalculateTipAndTotal()
                }
            },
            singleLine = true,

            label = {
                Text(
                    text = "Enter the amount: ",
                    color = Color.White,
                    fontSize = 16.sp,
                )
            },
            textStyle = TextStyle(color = Color.White),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                containerColor = Lightgray
            ),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.padding(top = 24.dp))

        Row(modifier = Modifier.padding(start = 52.dp, end = 52.dp)) {
            Box(
                modifier = Modifier
                    .height(1.dp)
                    .weight(1f)
                    .background(Color.White)
                    .align(Alignment.CenterVertically)
            )

            Text(
                text = " PERCENT ",
                color = Color.White,
                fontSize = 20.sp,
            )

            Box(
                modifier = Modifier
                    .height(1.dp)
                    .weight(1f)
                    .background(Color.White)
                    .align(Alignment.CenterVertically)
            )
        }

        Spacer(modifier = Modifier.padding(top = 24.dp))

        // Button row 1
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            TipButton("5", selectedButton == "5") {
                selectedButton = "5"
                recalculateTipAndTotal()
            }
            Spacer(modifier = Modifier.padding(start = 24.dp))
            TipButton("10", selectedButton == "10") {
                selectedButton = "10"
                recalculateTipAndTotal()
            }
            Spacer(modifier = Modifier.padding(start = 24.dp))
            TipButton("15", selectedButton == "15") {
                selectedButton = "15"
                recalculateTipAndTotal()
            }
        }

        Spacer(modifier = Modifier.padding(top = 24.dp))

        // Button row 2
        Row(modifier = Modifier.align(Alignment.CenterHorizontally)) {
            TipButton("20", selectedButton == "20") {
                selectedButton = "20"
                recalculateTipAndTotal()
            }
            Spacer(modifier = Modifier.padding(start = 24.dp))
            TipButton("25", selectedButton == "25") {
                selectedButton = "25"
                recalculateTipAndTotal()
            }
            Spacer(modifier = Modifier.padding(start = 24.dp))
            TipButton("30", selectedButton == "30") {
                selectedButton = "30"
                recalculateTipAndTotal()
            }
        }

        Spacer(modifier = Modifier.padding(top = 24.dp))

        // Display calculated values
        Column(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(start = 50.dp, end = 50.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(Color.White)
                    .align(alignment = Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.padding(top = 15.dp))

            Row {
                Text(
                    text = "Tip:",
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = modifier.weight(1f)
                )

                Text(
                    text = "$tip$",
                    color = Color.White,
                    fontSize = 20.sp,
                )
            }

            Spacer(modifier = Modifier.padding(top = 3.dp))

            Row {
                Text(
                    text = "Total amount:",
                    color = Color.White,
                    fontSize = 20.sp,
                    modifier = modifier.weight(1f)
                )

                Text(
                    text = "$totalAmount$",
                    color = Color.White,
                    fontSize = 20.sp,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GplayTipCalcTheme {
        Greeting()
    }
}
