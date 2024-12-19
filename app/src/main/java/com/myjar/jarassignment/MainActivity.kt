package com.myjar.jarassignment

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.launch
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
 import com.myjar.jarassignment.ui.vm.JarViewModel
import com.myjar.jarassignment.ui.composables.AppNavigation
import com.myjar.jarassignment.ui.theme.JarAssignmentTheme

class MainActivity : ComponentActivity() {




    override fun onCreate(savedInstanceState: Bundle?) {

        val viewModel by viewModels<JarViewModel>()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            LaunchedEffect(Unit) {
                viewModel.fetchData()
            }

            var items = viewModel.listStringData.collectAsState()
            Log.d("MainActivity", "onCreate: ${items.value}")
            val navController = rememberNavController()

            JarAssignmentTheme {

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    AppNavigation(
                        items = items.value,
                        navController = navController
                    )
                }
            }
        }
    }
}

