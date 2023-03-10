package com.lnight.datastoreproject.preferences_datastore

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.lnight.datastoreproject.preferencesDataStore
import kotlinx.coroutines.flow.first

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun PreferencesScreen(
    navController: NavController,
    viewModel: PreferencesDataStoreViewModel = viewModel()
) {

    val context = LocalContext.current
    var text by rememberSaveable {
        mutableStateOf("")
    }
    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when (event) {
                is UiEvent.SaveValue -> {
                    val dataStoreKey = stringPreferencesKey(event.key)
                    context.preferencesDataStore.edit {
                        it[dataStoreKey] = event.value
                    }
                    Toast.makeText(context, "Saved!", Toast.LENGTH_SHORT).show()
                }
                is UiEvent.ClearPreferences -> {
                    context.preferencesDataStore.edit {
                        it.clear()
                    }
                    Toast.makeText(context, "Cleared!", Toast.LENGTH_SHORT).show()
                }
                is UiEvent.GetValue -> {
                    val dataStoreKey = stringPreferencesKey(event.key)
                    val preferences = context.preferencesDataStore.data.first()
                   event.OnSuccess(preferences[dataStoreKey] ?: "No data found")
                }
                is UiEvent.Navigate -> {
                    navController.navigate(event.route)
                }
            }
        }
    }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.navigate("encrypted_datastore_screen") }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Navigate"
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(50.dp))

            var edittextKeyText by rememberSaveable {
                mutableStateOf("")
            }
            var edittextValueText by rememberSaveable {
                mutableStateOf("")
            }

            TextField(
                value = edittextKeyText,
                onValueChange = { edittextKeyText = it },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Enter key")
                }
            )

            Spacer(modifier = Modifier.height(10.dp))

            TextField(
                value = edittextValueText,
                onValueChange = { edittextValueText = it },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Enter value")
                }
            )

            Button(
                onClick = {
                    viewModel.save(edittextKeyText, edittextValueText)
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Save data")
            }

            Spacer(modifier = Modifier.height(50.dp))

            var edittextGetText by rememberSaveable {
                mutableStateOf("")
            }

            TextField(
                value = edittextGetText,
                onValueChange = { edittextGetText = it },
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(text = "Enter key")
                }
            )

            Button(
                onClick = {
                    viewModel.get(edittextGetText) { text = it }
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Get data by key")
            }

            Spacer(modifier = Modifier.height(10.dp))

            Button(
                onClick = {
                    viewModel.clear()
                },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Clear preferences")
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = text,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 20.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}