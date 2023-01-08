package com.lnight.datastoreproject.proto_datastore.with_protobuf

import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import com.lnight.datastoreproject.UserData
import java.io.InputStream
import java.io.OutputStream

object UserDataSerializer : Serializer<UserData> {
    override val defaultValue: UserData = UserData.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserData {
        return try {
            UserData.parseFrom(input)
        } catch (e: InvalidProtocolBufferException) {
            e.printStackTrace()
            defaultValue
        }
    }

    override suspend fun writeTo(t: UserData, output: OutputStream) = t.writeTo(output)
}