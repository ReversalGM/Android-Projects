package com.example.composequadrant

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composequadrant.ui.theme.ComposeQuadrantTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeQuadrantTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Card(title: String, body: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(all = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(bottom = 16.dp),
            fontWeight = FontWeight.Bold,
        )
        Text(text = body, textAlign = TextAlign.Justify)
    }
}

@Composable
fun Quadrant() {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.weight(weight = 0.5f, fill = true)) {
            Card(
                title = stringResource(R.string.text_composable),
                body = stringResource(R.string.displays_text_and_follows_the_recommended_material_design_guidelines),
                modifier = Modifier
                    .weight(weight = 0.5f, fill = true)
                    .background(color = Color(0xFFEADDFF))
            )
            Card(
                title = stringResource(R.string.image_composable),
                body = stringResource(R.string.creates_a_composable_that_lays_out_and_draws_a_given_painter_class_object),
                modifier = Modifier
                    .weight(weight = 0.5f, fill = true)
                    .background(color = Color(0xFFD0BCFF))
            )
        }
        Row(modifier = Modifier.weight(weight = 0.5f, fill = true)) {
            Card(
                title = stringResource(R.string.row_composable),
                body = stringResource(R.string.a_layout_composable_that_places_its_children_in_a_horizontal_sequence),
                modifier = Modifier
                    .weight(weight = 0.5f, fill = true)
                    .background(color = Color(0xFFB69DF8))
            )
            Card(
                title = stringResource(R.string.column_composable),
                body = stringResource(R.string.a_layout_composable_that_places_its_children_in_a_vertical_sequence),
                modifier = Modifier
                    .weight(weight = 0.5f, fill = true)
                    .background(color = Color(0xFFF6EDFF))
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuadrantPreview() {
    ComposeQuadrantTheme {
        Quadrant()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}