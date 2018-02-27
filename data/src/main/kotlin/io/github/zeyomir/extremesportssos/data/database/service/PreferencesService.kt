package io.github.zeyomir.extremesportssos.data.database.service

import android.content.SharedPreferences
import io.github.zeyomir.extremesportssos.data.database.KeyValueService
import io.github.zeyomir.extremesportssos.domain.entity.SosContact
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class PreferencesService @Inject constructor(private val preferences: SharedPreferences) : KeyValueService {

    override fun getSosContact(): SosContact? {
        val info = preferences.getString(CONTACT_INFO_KEY, null)
        val name = preferences.getString(CONTACT_NAME_KEY, null)
        Timber.v("Sos contact retrieved (number: %s, name: %s)", info, name)
        return if (info == null) null
        else SosContact(info, name)
    }

    override fun getSosMessage(): String? {
        val message = preferences.getString(MESSAGE_HELP_KEY, null)
        Timber.v("Sos message retrieved (%s)", message)
        return message
    }

    override fun saveSosContact(contact: SosContact) {
        val editor = preferences.edit()
        editor.putString(CONTACT_INFO_KEY, contact.contactInfo)
        editor.putString(CONTACT_NAME_KEY, contact.contactName)
        editor.apply()
        Timber.v("Sos contact saved (number: %s, name: %s)", contact.contactInfo, contact.contactName)
    }

    override fun saveSosMessage(message: String) {
        val editor = preferences.edit()
        editor.putString(MESSAGE_HELP_KEY, message)
        Timber.v("Sos message saved (%s)", message)
        editor.apply()
    }

    companion object {
        private const val PREFIX = "io.github.zeyomir.preferences"
        private const val CONTACT_INFO_KEY = """$PREFIX.contact.info"""
        private const val CONTACT_NAME_KEY = """$PREFIX.contact.name"""
        private const val MESSAGE_HELP_KEY = """$PREFIX.message.help"""
    }
}
