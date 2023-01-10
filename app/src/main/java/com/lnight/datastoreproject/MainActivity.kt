package com.lnight.datastoreproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lnight.datastoreproject.preferences_datastore.ecrypted_preferences.EncryptedPreferencesScreen
import com.lnight.datastoreproject.preferences_datastore.simple_exapmle.PreferencesScreen
import com.lnight.datastoreproject.proto_datastore.with_protobuf.ProtoWithProtobufScreen
import com.lnight.datastoreproject.proto_datastore.without_protobuf.ProtoWithoutProtobufScreen
import com.lnight.datastoreproject.ui.theme.DataStoreProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataStoreProjectTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "preferences_screen") {
                        composable("preferences_screen") {
                            PreferencesScreen(navController = navController)
                        }
                        composable("encrypted_preferences_screen") {
                            EncryptedPreferencesScreen(navController = navController)
                        }
                        composable("proto_without_protobuf_screen") {
                            ProtoWithoutProtobufScreen(navController = navController)
                        }
                        composable("proto_with_protobuf_screen") {
                            ProtoWithProtobufScreen()
                        }
                    }
                }
            }
        }
    }
}
