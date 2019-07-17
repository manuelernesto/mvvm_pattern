package io.github.manuelernesto.mvvmapp.data.preferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.core.content.edit

private const val KEY_SAVED_AT = "key_saved_at"

class PreferenceProvider(context: Context) {

    private val appContext = context.applicationContext
    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)


    fun saveLastSavedAt(savedAt: String) {
        preference.edit {
            this.putString(KEY_SAVED_AT, savedAt).apply()
        }
    }

    fun getLastSavedAt() = preference.getString(KEY_SAVED_AT, null)

}