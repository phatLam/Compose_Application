package com.kzqzd.composeapplication

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.os.LocaleListCompat
import com.kzqzd.composeapplication.ui.theme.ComposeApplicationTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ListItem(
                        messages = messageList,
                        modifier = Modifier.padding(innerPadding),
                        viewModel = MainViewModel(),
                    ){
                        val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(it)
                        AppCompatDelegate.setApplicationLocales(appLocale)

                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {


    Button(modifier = modifier, onClick = {

    }) {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )
    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ComposeApplicationTheme {
        ListItem(
            messages = messageList,
            modifier = Modifier
        ){
           val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(it)
            AppCompatDelegate.setApplicationLocales(appLocale)

        }
    }
}
val messageList: List<String> = mutableListOf(
    "vn",
    "th"
)

@Composable
fun MessageView(message: String, onclick:(String)-> Unit) {
    TextButton(onClick = { onclick(message) }) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = message
        )
    }

}
@Composable
fun ListItem(viewModel: MainViewModel = MainViewModel(), messages: List<String>, modifier: Modifier, onclick:(String)-> Unit) {
    LazyColumn(modifier = Modifier) {
        item {
            Text(stringResource(id = R.string.hello))
        }
        items(messages) { msg ->
            MessageView(message = msg, onclick)
        }

        item {
            Text(text = stringResource(id = viewModel.language ))
        }
    }
}


