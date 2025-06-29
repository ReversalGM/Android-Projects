package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Composable
fun LemonadeApp() {
    var step by remember { mutableIntStateOf(0) }
    var squeezesNeeded by remember { mutableIntStateOf((2..4).random()) }
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = stringResource(R.string.app_name),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = Modifier
                .background(Color(0xfff9e44c))
                .fillMaxWidth()
                .padding(16.dp)
                .weight(0.1f)
                .wrapContentHeight(align = Alignment.Bottom)
        )
        when (step) {
            0 -> Screen(
                painter = painterResource(R.drawable.lemon_tree),
                contentDescription = stringResource(R.string.lemon_tree),
                text = stringResource(R.string.tap_the_lemon_tree_to_select_a_lemon),
                clicksToAdvanceScreen = 1,
                advanceScreen = { step += 1 },
                modifier = Modifier
                    .weight(0.9f)
                    .fillMaxSize()
            )

            1 -> Screen(
                painter = painterResource(R.drawable.lemon_squeeze),
                contentDescription = stringResource(R.string.lemon),
                text = stringResource(R.string.keep_tapping_the_lemon_to_squeeze_it),
                clicksToAdvanceScreen = squeezesNeeded,
                advanceScreen = { step += 1 },
                modifier = Modifier
                    .weight(0.9f)
                    .fillMaxSize()
            )

            2 -> Screen(
                painter = painterResource(R.drawable.lemon_drink),
                contentDescription = stringResource(R.string.glass_of_lemonade),
                text = stringResource(R.string.tap_the_lemonade_to_drink_it),
                clicksToAdvanceScreen = 1,
                advanceScreen = { step += 1 },
                modifier = Modifier
                    .weight(0.9f)
                    .fillMaxSize()
            )

            else -> Screen(
                painter = painterResource(R.drawable.lemon_restart),
                contentDescription = stringResource(R.string.empty_glass),
                text = stringResource(R.string.tap_the_empty_glass_to_start_again),
                clicksToAdvanceScreen = 1,
                advanceScreen = {
                    step = 0
                    squeezesNeeded = (2..4).random()
                },
                modifier = Modifier
                    .weight(0.9f)
                    .fillMaxSize()
            )
        }
    }
}

@Composable
fun Screen(
    painter: Painter,
    contentDescription: String,
    text: String,
    clicksToAdvanceScreen: Int,
    advanceScreen: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var clickCount by remember { mutableIntStateOf(0) }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                clickCount += 1
                if (clickCount >= clicksToAdvanceScreen) {
                    advanceScreen()
                }
            }, colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xffc8ecd4)
            ), shape = RoundedCornerShape(size = 32.dp)
        ) {
            Image(painter = painter, contentDescription = contentDescription)
        }
        Spacer(modifier = Modifier.padding(16.dp))
        Text(text = text, fontSize = 18.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}