package ua.edu.lntu.ipz_cw_4

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ua.edu.lntu.ipz_cw_4.ui.theme.IPZ_CR_4Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            IPZ_CR_4Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "textList") {
        composable("textList") {
            TextListScreen(navController = navController)
        }
        composable("text/{text}") { backStackEntry ->
            val text = backStackEntry.arguments?.getString("text")
            text?.let {
                TextDetailScreen(text = it)
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextListScreen(navController: NavController) {
    val texts = listOf("Text 1", "Text 2", "Text 3")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Text List") }
            )
        }
    ) {
        innerPadding -> Column(modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)){
        TextList(texts = texts, onItemClick = { text ->
            navController.navigate("text/$text")
        })
    }
    }
}

@Composable
fun TextList(texts: List<String>, onItemClick: (String) -> Unit) {
    LazyColumn {
        items(texts) { text ->
            Text(
                text = text,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable { onItemClick(text) }
            )
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextDetailScreen(text: String) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Text Detail") }
            )
        }
    ) {innerPadding -> Column(modifier = Modifier.padding(innerPadding),
        verticalArrangement = Arrangement.spacedBy(16.dp)){
        TextDetail(text = text)}
    }
}

@Composable
fun TextDetail(text: String) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    )
}
