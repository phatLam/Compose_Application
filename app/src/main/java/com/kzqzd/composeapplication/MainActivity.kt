package com.kzqzd.composeapplication

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import com.kzqzd.composeapplication.ui.theme.ComposeApplicationTheme

//https://proandroiddev.com/collapsing-toolbar-in-jetpack-compose-lazycolumn-3-approaches-702684d61843
class MainActivity : AppCompatActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeApplicationTheme {
                val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(
                    rememberTopAppBarState()
                )
                val isCollapsed: Boolean by remember {
                    derivedStateOf {
                        scrollBehavior.state.collapsedFraction == 1f
                    }
                }
                Scaffold(modifier = Modifier
                    .fillMaxSize()
                    .nestedScroll(scrollBehavior.nestedScrollConnection),
                    topBar = {
                        LibraryTopBar(scrollBehavior, isCollapsed)
                    }
                ) { innerPadding ->
                    ListItem(
                        messages = messageList,
                        modifier = Modifier.padding(innerPadding),
                        viewModel = MainViewModel(),
                    ) {
                        val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(it)
                        AppCompatDelegate.setApplicationLocales(appLocale)

                    }

                }
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LibraryTopBar(scrollBehavior: TopAppBarScrollBehavior, isCollapsed: Boolean) {
    LargeTopAppBar(
        title = { Text(text = "Library") },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            scrolledContainerColor = MaterialTheme.colorScheme.background,
            titleContentColor = if (isCollapsed) {
                MaterialTheme.colorScheme.onBackground
            } else {
                MaterialTheme.colorScheme.onPrimary
            },
        ),
        scrollBehavior = scrollBehavior,
    )
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
        ) {
            val appLocale: LocaleListCompat = LocaleListCompat.forLanguageTags(it)
            AppCompatDelegate.setApplicationLocales(appLocale)

        }
    }
}

val messageList: List<String> = mutableListOf(
    "vn",
    "th",
    "df",
    "ds",
    "f",
    "qw1",
    "f2",
    "f3",
    "f4",
    "f5",
    "f6",
    )

@Composable
fun MessageView(message: String, onclick: (String) -> Unit) {
    TextButton(onClick = { onclick(message) }) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            text = message
        )
    }

}

@Composable
fun ListItem(
    viewModel: MainViewModel = MainViewModel(),
    messages: List<String>,
    modifier: Modifier,
    onclick: (String) -> Unit
) {
    LazyColumn(modifier = Modifier) {
        item {
            Text(stringResource(id = R.string.hello))
        }
        items(messages) { msg ->
            MessageView(message = msg, onclick)
        }

        item {
            Text(text = stringResource(id = viewModel.language))
        }
    }
}


