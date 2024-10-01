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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
                val snackbarHostState = SnackbarHostState()
                Scaffold(modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(hostState = snackbarHostState)}) { innerPadding ->
                    val viewModel = MainViewModel(NetworkConnectivityServiceImpl(context = this))
                    Greeting(viewModel = viewModel,
                        modifier = Modifier.padding(innerPadding),
                        snackbarHostState = snackbarHostState
                        )

                }
            }
        }
    }
}

@Composable
fun Greeting(viewModel: MainViewModel,
             modifier: Modifier = Modifier,
             snackbarHostState: SnackbarHostState) {
    val networkStatus by viewModel.networkStatus.collectAsState()
    if (networkStatus == NetworkStatus.Disconnected) {
        LaunchedEffect(key1 = networkStatus) {
            snackbarHostState.showSnackbar("you are offline")
        }
    }

    Text(text = "Connectivity Service")
}




