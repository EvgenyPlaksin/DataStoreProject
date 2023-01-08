package com.lnight.datastoreproject.proto_datastore.without_protobuf

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.lnight.datastoreproject.protoDataStoreWithoutProtobuf
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun ProtoWithoutProtobufScreen(
    navController: NavController
) {

    val context = LocalContext.current
    val appSettings = context.protoDataStoreWithoutProtobuf.data.collectAsState(initial = AppSettings()).value
    val scope = rememberCoroutineScope()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("proto_with_protobuf_screen") }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowRight,
                    contentDescription = "Navigate"
                )
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            for (i in 0..2) {
                val language = Language.values()[i]
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = language == appSettings.language,
                        onClick = {
                            scope.launch {
                                context.protoDataStoreWithoutProtobuf.updateData {
                                    it.copy(
                                        language = language,
                                        knownLocations = it.knownLocations + Location(
                                            Random.nextDouble(),
                                            Random.nextDouble()
                                        )
                                    )
                                }
                            }
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = language.toString())
                }
            }
        }
    }
}