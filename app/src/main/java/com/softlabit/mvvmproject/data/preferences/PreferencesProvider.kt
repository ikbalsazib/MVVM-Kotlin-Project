package com.softlabit.mvvmproject.data.preferences

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager

private const val KEY_SAVE_AT = "key_save_at"

class PreferencesProvider(
    private val context: Context
) {

    private val applicationContext = context.applicationContext

    private val preference: SharedPreferences
    get() = PreferenceManager.getDefaultSharedPreferences(applicationContext)

    fun saveLastSaveAt(savedAt: String) {
        preference.edit().putString(
            KEY_SAVE_AT,
            savedAt
        ).apply()
    }

    fun getLastSaveAt(): String? {
        return preference.getString(KEY_SAVE_AT, null)
    }
}
