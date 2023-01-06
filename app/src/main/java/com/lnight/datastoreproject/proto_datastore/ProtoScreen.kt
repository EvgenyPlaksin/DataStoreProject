package com.lnight.datastoreproject.proto_datastore

import androidx.compose.foundation.layout.*
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.lnight.datastoreproject.protoDataStore
import kotlinx.coroutines.launch
import kotlin.random.Random

@Composable
fun ProtoScreen() {

    val context = LocalContext.current
    val appSettings = context.protoDataStore.data.collectAsState(initial = AppSettings()).value
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        for(i in 0..2) {
            val language = Language.values()[i]
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = language == appSettings.language,
                    onClick = {
                        scope.launch {
                            context.protoDataStore.updateData {
                                it.copy(
                                    language = language,
                                    knownLocations = it.knownLocations + Location(Random.nextDouble(), Random.nextDouble())
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