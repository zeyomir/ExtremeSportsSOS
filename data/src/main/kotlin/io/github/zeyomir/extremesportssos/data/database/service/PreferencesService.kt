package io.github.zeyomir.extremesportssos.data.database.service

import android.content.SharedPreferences
import io.github.zeyomir.extremesportssos.data.database.KeyValueService
import io.github.zeyomir.extremesportssos.domain.entity.SosContact
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class PreferencesService @Inject constructor(private val preferences: SharedPreferences) : KeyValueService {

    override fun getSosContact(): SosContact? {
        val info = preferences.getString(CONTACT_INFO_KEY, null)
        val name = preferences.getString(CONTACT_NAME_KEY, null)
        return if (info == null) null
        else SosContact(info, name)
    }

    override fun getSosMessage() = preferences.getString(MESSAGE_HELP_KEY, null)

    override fun saveSosContact(contact: SosContact) {
        val editor = preferences.edit()
        editor.putString(CONTACT_INFO_KEY, contact.contactInfo)
        editor.putString(CONTACT_NAME_KEY, contact.contactName)
        editor.apply()
    }

    override fun saveSosMessage(message: String?) {
        val editor = preferences.edit()
        editor.putString(MESSAGE_HELP_KEY, message)
        editor.apply()
    }

    companion object {
        private const val PREFIX = "io.github.zeyomir.preferences"
        private const val CONTACT_INFO_KEY = """$PREFIX.contact.info"""
        private const val CONTACT_NAME_KEY = """$PREFIX.contact.name"""
        private const val MESSAGE_HELP_KEY = """$PREFIX.message.help"""
    }
}