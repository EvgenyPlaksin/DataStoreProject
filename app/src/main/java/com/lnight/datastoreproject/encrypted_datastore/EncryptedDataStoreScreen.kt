package com.lnight.datastoreproject.encrypted_datastore

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
import androidx.navigation.NavController
import com.lnight.datastoreproject.encryptedPreferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun EncryptedDataStoreScreen(
    navController: NavController
) {
    var username by rememberSaveable {
        mutableStateOf("")
    }

    var password by rememberSaveable {
        mutableStateOf("")
    }

    var settings by remember {
        mutableStateOf(UserSettings())
    }

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("proto_without_protobuf_screen") }) {
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
                .padding(32.dp)
        ) {
            TextField(
                value = username,
                onValueChange = { username = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = "Username") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(text = "Password") }
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                Button(onClick = {
                    scope.launch {
                        context.encryptedPreferencesDataStore.updateData {
                            UserSettings(
                                username = username,
                                password = password
                            )
                        }
                        Toast.makeText(context, "Saved!", Toast.LENGTH_SHORT).show()
                    }
                }) {
                    Text(text = "Save")
                }
                Spacer(modifier = Modifier.width(8.dp))
                Button(onClick = {
                    scope.launch {
                        settings = context.encryptedPreferencesDataStore.data.first()
                    }
                }) {
                    Text(text = "Load")
                }
            }
            Spacer(modifier = Modifier.height(15.dp))
            Text(
                text = "Username: ${settings.username.ifBlank { "null" }}",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontSize = 17.sp
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Password: ${settings.password.ifBlank { "null" }}",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontSize = 17.sp
            )
        }
    }
}