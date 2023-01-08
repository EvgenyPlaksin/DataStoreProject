package com.lnight.datastoreproject.proto_datastore.with_protobuf

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lnight.datastoreproject.UserData
import com.lnight.datastoreproject.protoDataStoreWithProtobuf
import kotlinx.coroutines.launch

@Composable
fun ProtoWithProtobufScreen() {

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val userData = context.protoDataStoreWithProtobuf.data.collectAsState(initial = UserData.getDefaultInstance()).value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "Change user data",
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(20.dp))
        var userNameText by rememberSaveable {
            mutableStateOf("")
        }
        TextField(
            value = userNameText,
            onValueChange = { userNameText = it },
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Username")
            },
            singleLine = true
        )
        Spacer(modifier = Modifier.height(10.dp))
        var passwordText by rememberSaveable {
            mutableStateOf("")
        }
        var passwordVisible by rememberSaveable { mutableStateOf(false) }
        TextField(
            value = passwordText,
            onValueChange = { if(it.length <= 8) passwordText = it },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword),
            visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            label = {
                Text(text = "Password")
            },
            singleLine = true,
            trailingIcon = {
                val image = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                val description = if (passwordVisible) "Hide password" else "Show password"

                IconButton(onClick = { passwordVisible = !passwordVisible }) {
                    Icon(imageVector = image, description)
                }
            }
        )
        Spacer(modifier = Modifier.height(10.dp))
        var emailText by rememberSaveable {
            mutableStateOf("")
        }
        TextField(
            value = emailText,
            onValueChange = { emailText = it },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
            label = {
                Text(text = "Email (optional)")
            }
        )
        Spacer(modifier = Modifier.height(15.dp))
        Button(
            onClick = {
                if (userNameText.isNotBlank() && passwordText.isNotBlank()) {
                    scope.launch {
                        context.protoDataStoreWithProtobuf.updateData {
                            it.toBuilder()
                                .setUserName(userNameText)
                                .setPassword(passwordText.toIntOrNull() ?: 0)
                                .setEmail(emailText)
                                .build()
                        }
                    }
                } else {
                    Toast.makeText(context, "Enter the data!", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "save user")
        }

        if(!userData.equals(UserData.getDefaultInstance())) {
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "Name: ${userData.userName}",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = "Password: ${userData.password}",
                modifier = Modifier.align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center,
                fontSize = 15.sp
            )
            Spacer(modifier = Modifier.height(5.dp))
            if(userData.email.isNotBlank()) {
                Text(
                    text = "Email: ${userData.email}",
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center,
                    fontSize = 15.sp
                )
            }
        }
}
}