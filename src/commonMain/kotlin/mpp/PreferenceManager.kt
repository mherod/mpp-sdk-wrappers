package mpp

interface PreferenceManager {
    fun getPreferences(name: String): Preferences

    interface Preferences {

        fun getAll(): MutableMap<String, *>
        fun getString(key: String, default: String? = null): String?
        fun getStringSet(key: String, default: Set<String>? = null): Set<String>?
        fun getInt(key: String, default: Int): Int
        fun getLong(key: String, default: Long): Long
        fun getFloat(key: String, default: Float): Float
        fun getBoolean(key: String, default: Boolean): Boolean
        fun contains(key: String): Boolean

        fun edit(): Editor

        interface Editor {

            fun putBoolean(key: String, value: Boolean): Editor
            fun putFloat(key: String, value: Float): Editor
            fun putLong(key: String, value: Long): Editor
            fun putInt(key: String, value: Int): Editor
            fun putString(key: String, value: String): Editor
            fun putStringSet(key: String, value: Set<String>?): Editor
            fun remove(key: String): Editor
            fun clear(): Editor
            fun commit(): Boolean
            fun apply()
        }
    }
}

expect class PreferenceManagerImpl : PreferenceManager
