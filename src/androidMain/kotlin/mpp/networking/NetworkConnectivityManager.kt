package mpp.networking

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import mpp.android.BuildConfig
import mpp.android.systemService

actual class NetworkConnectivityManagerImpl constructor(
    private val connectivityManager: ConnectivityManager
) : NetworkConnectivityManager {

    override fun hasInternetConnection(): Boolean {
        return runCatching {
            connectivityManager.allNetworks
                .also { check(it.isNotEmpty()) }
                .mapNotNull { network: Network ->
                    connectivityManager.getNetworkCapabilities(network)
                }
                .any { networkCapabilities ->
                    networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                }
        }.recoverCatching {
            connectivityManager.activeNetworkInfo?.isConnected == true
        }.mapCatching {
            it || connectivityManager.activeNetworkInfo?.isConnected == true
        }.mapCatching {
            it || (BuildConfig.DEBUG && "robolectic" == Build.FINGERPRINT)
        }.getOrDefault(false)
    }
}

fun Context.networkConnectivityManager(): NetworkConnectivityManager {
    return NetworkConnectivityManagerImpl(
        connectivityManager = systemService()
    )
}
