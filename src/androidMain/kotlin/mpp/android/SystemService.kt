package mpp.android

import android.content.Context
import androidx.core.content.ContextCompat

inline fun <reified T> Context.systemService(): T {
    return ContextCompat.getSystemService(this, T::class.java) as T
}
