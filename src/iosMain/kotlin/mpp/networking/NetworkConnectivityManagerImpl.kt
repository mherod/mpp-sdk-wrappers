package mpp.networking

import cocoapods.Reachability.Reachability

actual class NetworkConnectivityManagerImpl : NetworkConnectivityManager {
    override fun hasInternetConnection(): Boolean {
        return Reachability.reachabilityForInternetConnection()?.isReachable() == true
    }
}
