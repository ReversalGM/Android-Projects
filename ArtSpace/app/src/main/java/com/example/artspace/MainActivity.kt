package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                ArtSpaceApp()
            }
        }
    }
}

@Composable
fun ArtSpaceApp() {
    val images = listOf(
        R.drawable.min_an,
        R.drawable.dan_cristian_pdure,
        R.drawable.chait_goli,
        R.drawable.steve_johnson
    )
    val artisits = listOf(
        R.string.min_an,
        R.string.dan_cristian_paduret,
        R.string.chait_goli,
        R.string.steve_johnson
    )
    var curArtIndex by remember { mutableIntStateOf(0) }
    val artworkCount = images.size
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Painting(
                image = images[curArtIndex],
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .weight(1f)
            )
            DescriptionRow(
                author = artisits[curArtIndex],
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            )
            NavigationRow(
                onPreviousClick = { curArtIndex = (curArtIndex - 1 + artworkCount) % artworkCount },
                onNextClick = { curArtIndex = (curArtIndex + 1) % artworkCount })

        }
    }
}

@Composable
fun Painting(
    @DrawableRes image: Int, modifier: Modifier = Modifier
) {
    ElevatedCard(modifier = modifier) {
        Image(
            painter = painterResource(image),
            contentDescription = null
        )
    }

}

@Composable
fun DescriptionRow(@StringRes author: Int, modifier: Modifier = Modifier) {
    Text(text = "By ${stringResource(author)}", fontSize = 24.sp, modifier = modifier)
}

@Composable
fun NavigationRow(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth()
    ) {
        Button(onClick = onPreviousClick, modifier = Modifier.width(150.dp)) {
            Text(text = "Previous")
        }
        Button(onClick = onNextClick, modifier = Modifier.width(150.dp)) {
            Text(text = "Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpaceAppPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}