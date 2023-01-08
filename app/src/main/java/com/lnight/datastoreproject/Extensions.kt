package com.lnight.datastoreproject

import android.content.Context
import androidx.datastore.dataStore
import androidx.datastore.preferences.preferencesDataStore
import com.lnight.datastoreproject.proto_datastore.with_protobuf.UserDataSerializer
import com.lnight.datastoreproject.proto_datastore.without_protobuf.AppSettingsSerializer

private const val DATASTORE_PREFERENCES_NAME = "preferences_datastore"
private const val DATASTORE_PROTO_WITHOUT_PROTOBUF_NAME = "proto_datastore_no_protobuf.json"
private const val DATASTORE_PROTO_WITH_PROTOBUF_NAME = "proto_datastore_protobuf.json"

val Context.preferencesDataStore by preferencesDataStore(
    name = DATASTORE_PREFERENCES_NAME
)

val Context.protoDataStoreWithoutProtobuf by dataStore(
    fileName = DATASTORE_PROTO_WITHOUT_PROTOBUF_NAME,
    serializer = AppSettingsSerializer
)

val Context.protoDataStoreWithProtobuf by dataStore(
    fileName = DATASTORE_PROTO_WITH_PROTOBUF_NAME,
    serializer = UserDataSerializer
)