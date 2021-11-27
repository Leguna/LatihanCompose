package site.arksana.latihancompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import site.arksana.latihancompose.layout.Greetings
import site.arksana.latihancompose.layout.OnBoardingScreen
import site.arksana.latihancompose.ui.theme.LatihanComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LatihanComposeTheme {
                // Conversation(SampleData.conversationSample)
                MyApp()
            }
        }

    }
}

@Composable
fun MyApp() {
    var shouldShowOnBoarding by rememberSaveable { mutableStateOf(true) }

    if (shouldShowOnBoarding) {
        OnBoardingScreen(onContinueClicked = { shouldShowOnBoarding = false })
    } else {
        Greetings()
    }
}


@Preview(showBackground = true, widthDp = 320, heightDp = 720)
@Composable
fun DefaultPreview() {
    LatihanComposeTheme {
        MyApp()
    }
}

