// MyLMSApp/app/src/main/java/com/example/lms/utils/ThemeUtil.kt
package com.example.lms.utils

import android.app.Activity
import androidx.appcompat.app.AppCompatDelegate

object ThemeUtil {
    fun setMode(dark: Boolean) {
        val mode = if (dark) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        AppCompatDelegate.setDefaultNightMode(mode)
    }
    fun toggle(a: Activity, dark: Boolean) {
        setMode(dark)
        a.recreate()
    }
}
