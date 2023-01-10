# DataStoreProject

A simple example of using different variants of [Jetpack DataStore](https://developer.android.com/topic/libraries/architecture/datastore) with  [Jetpack Compose](https://developer.android.com/jetpack/compose).

### Types of Jetpack DataStore

- Preferences DataStore
- Proto DataStore

### [Preferences DataStore](https://developer.android.com/topic/libraries/architecture/datastore#preferences-datastore-dependencies)

Preferences DataStore stores and accesses data using keys. This implementation does not require a predefined schema, and it does not provide type safety. 
It is intended to replace the outdated SharedPreferences and has a number of advantages over them. 

### [Proto DataStore](https://developer.android.com/topic/libraries/architecture/datastore#proto-datastore)

Proto DataStore stores data as instances of a custom data type.
This type of DataStore may require schema definition using protocol buffers, but provides type safety.
The project also shows an example of Proto DataStore implementation without schema definition, using [Kotlin Serialisation](https://kotlinlang.org/docs/serialization.html) plugin.

### Jetpack DataStore vs SharedPreferences

In this table you can see the main advantages of DataStore over SharedPreferences:

<img src="https://user-images.githubusercontent.com/94696816/211640422-93807765-220b-4b7a-944e-ff53b0bf35e9.png" width="600" height="500" />
