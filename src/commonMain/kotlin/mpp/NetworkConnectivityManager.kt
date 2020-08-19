package mpp

interface NetworkConnectivityManager {
    fun hasInternetConnection(): Boolean
}

expect class NetworkConnectivityManagerImpl : NetworkConnectivityManager
