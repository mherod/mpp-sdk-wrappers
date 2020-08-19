package mpp

import android.content.Context
import android.content.SharedPreferences

actual class PreferenceManagerImpl(private val context: Context) : PreferenceManager {
    override fun getPreferences(name: String): PreferenceManager.Preferences = AndroidPreferences(
        sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)
    )
}

inline class AndroidPreferences(private val sharedPreferences: SharedPreferences) : PreferenceManager.Preferences {

    override fun getAll(): MutableMap<String, *> {
        return sharedPreferences.all
    }

    override fun getString(key: String, default: String?): String? {
        return sharedPreferences.getString(key, default)
    }

    override fun getStringSet(key: String, default: Set<String>?): Set<String>? {
        return sharedPreferences.getStringSet(key, default) ?: default
    }

    override fun getInt(key: String, default: Int): Int {
        return sharedPreferences.getInt(key, default)
    }

    override fun getLong(key: String, default: Long): Long {
        return sharedPreferences.getLong(key, default)
    }

    override fun getFloat(key: String, default: Float): Float {
        return sharedPreferences.getFloat(key, default)
    }

    override fun getBoolean(key: String, default: Boolean): Boolean {
        return sharedPreferences.getBoolean(key, default)
    }

    override fun contains(key: String): Boolean {
        return sharedPreferences.contains(key)
    }

    override fun edit(): PreferenceManager.Preferences.Editor = Editor()

    inner class Editor : PreferenceManager.Preferences.Editor {

        var changesMade = false

        private val editor: SharedPreferences.Editor = sharedPreferences.edit()

        override fun putString(key: String, value: String): PreferenceManager.Preferences.Editor {
            return editor.putString(key, value).let {
                changesMade = true
                this
            }
        }

        override fun putStringSet(key: String, value: Set<String>?): PreferenceManager.Preferences.Editor {
            return editor.putStringSet(key, value).let {
                changesMade = true
                this
            }
        }

        override fun putInt(key: String, value: Int): PreferenceManager.Preferences.Editor {
            return editor.putInt(key, value).let {
                changesMade = true
                this
            }
        }

        override fun putLong(key: String, value: Long): PreferenceManager.Preferences.Editor {
            return editor.putLong(key, value).let {
                changesMade = true
                this
            }
        }

        override fun putFloat(key: String, value: Float): PreferenceManager.Preferences.Editor {
            return editor.putFloat(key, value).let {
                changesMade = true
                this
            }
        }

        override fun putBoolean(key: String, value: Boolean): PreferenceManager.Preferences.Editor {
            return editor.putBoolean(key, value).let {
                changesMade = true
                this
            }
        }

        override fun remove(key: String): PreferenceManager.Preferences.Editor {
            return editor.remove(key).let {
                changesMade = true
                this
            }
        }

        override fun clear(): PreferenceManager.Preferences.Editor {
            return editor.clear().let {
                changesMade = true
                this
            }
        }

        override fun commit(): Boolean {
            return changesMade && editor.commit()
        }

        override fun apply() {
            if (changesMade) editor.apply()
        }
    }
}
