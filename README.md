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

### Encrypted DataStore
This repository also contains a bonus part about data encryption in the DataStore. It uses a special class from my [Encryption and Decryption](https://github.com/EvgenyPlaksin/EncriptionAndDecriptionProject) repository.

### Jetpack DataStore vs SharedPreferences

In this table you can see the main advantages of DataStore over SharedPreferences:

<img src="https://user-images.githubusercontent.com/94696816/211642500-33357165-6ce9-4fde-90e1-a5ab7a0f044a.png" width="720" height="450" />

(1) SharedPreferences has a synchronous API that can appear safe to call on the UI thread, but which actually does disk I/O operations. Furthermore, ```apply()``` blocks the UI thread on ```fsync()```. Pending ```fsync()``` calls are triggered every time any service starts or stops, and every time an activity starts or stops anywhere in your application. The UI thread is blocked on pending ```fsync()``` calls scheduled by a```pply()```, often becoming a source of [ANRs](https://developer.android.com/topic/performance/vitals/anr).

(2) SharedPreferences throws parsing errors as runtime exceptions.
