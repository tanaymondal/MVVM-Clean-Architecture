package com.example.mvvmapplication.ui.home

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.mvvmapplication.domain.model.RequestState
import com.example.mvvmapplication.domain.model.Results
import com.example.mvvmapplication.ui.theme.MVVMApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MVVMApplicationTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    val list = rememberSaveable { mutableStateOf<List<Results>>(mutableListOf()) }
                    val isError = rememberSaveable { mutableStateOf<Boolean>(false) }
                    val state: RequestState = viewModel.state.collectAsStateWithLifecycle().value
                    when (state) {
                        RequestState.Loading -> {
                            ProgressBar()
                        }

                        is RequestState.Error -> {
                            isError.value = true
                        }

                        is RequestState.Success -> {
                            list.value = state.response.results
                        }
                    }

                    MyList(modifier = Modifier.padding(innerPadding), list = list.value) { text ->
                        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }
}

@Composable
fun MyList(modifier: Modifier = Modifier, list: List<Results>, onClick: (String) -> Unit) {
    LazyColumn(modifier = modifier) {
        items(count = list.size, key = { item -> item }) { index ->

            Text(
                modifier = Modifier
                    .padding(20.dp)
                    .clickable {
                        onClick(list[index].name)
                    }
                    .fillMaxWidth(),
                text = list[index].name, fontSize = 20.sp
            )
        }
    }
}

@Composable
fun ProgressBar() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(
            modifier = Modifier.width(60.dp),
            color = MaterialTheme.colorScheme.secondary,
            trackColor = MaterialTheme.colorScheme.surfaceVariant
        )
    }
}