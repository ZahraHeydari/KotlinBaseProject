package com.android.kotlinbaseproject.util.helper

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.preference.PreferenceManager
import java.util.*

object LocaleHelper {

    private const val SELECTED_LANGUAGE = "Locale.Helper.Selected.Language"

    fun onAttach(context: Context?, defaultLanguage: String): Context? {
        val lang = getPersistedData(
            context,
            defaultLanguage
        )

        return if (context == null) context else setLocale(
            context,
            lang
        )
    }

    private fun getPersistedData(context: Context?, defaultLanguage: String): String? {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context!!)
        return preferences.getString(SELECTED_LANGUAGE, defaultLanguage)
    }

    private fun setLocale(context: Context?, language: String?): Context? {
        persist(context, language)
        return when {
            context == null -> null
            Build.VERSION.SDK_INT >= Build.VERSION_CODES.N -> updateResources(
                context,
                language
            )
            else -> updateResourcesLegacy(
                context,
                language
            )
        }

    }

    private fun persist(context: Context?, language: String?) {
        val preferences = PreferenceManager.getDefaultSharedPreferences(context!!)
        val editor = preferences.edit()

        editor.putString(SELECTED_LANGUAGE, language)
        editor.apply()
    }

    @TargetApi(Build.VERSION_CODES.N)
    private fun updateResources(context: Context, language: String?): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)

        context.resources.configuration.setLocale(locale)
        context.resources.configuration.setLayoutDirection(locale)

        return context.createConfigurationContext(context.resources.configuration)
    }

    private fun updateResourcesLegacy(context: Context, language: String?): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)

        context.resources.configuration.locale = locale
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            context.resources.configuration.setLayoutDirection(locale)
        }

        context.resources.updateConfiguration(context.resources.configuration, context.resources.displayMetrics)

        return context
    }

}