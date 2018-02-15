package io.github.zeyomir.extremesportssos.view.contact

import android.app.Activity
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import dagger.android.AndroidInjection
import io.github.zeyomir.extremesportssos.R
import io.github.zeyomir.extremesportssos.presenter.contact.ContactPresenter
import javax.inject.Inject

import kotlinx.android.synthetic.main.activity_contact.pick
import kotlinx.android.synthetic.main.activity_contact.info
import kotlinx.android.synthetic.main.activity_contact.name
import kotlinx.android.synthetic.main.activity_contact.next
import android.provider.ContactsContract
import android.content.Intent
import android.database.Cursor
import io.github.zeyomir.extremesportssos.view.message.ConfigureMessageActivity


class ConfigureContactActivity : AppCompatActivity(), ConfigureContactView {
    @Inject
    lateinit var presenter: ContactPresenter

    private val SELECT_PHONE_NUMBER: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)
        presenter.bind(this)
        presenter.fetchContactInfo()
        pick.setOnClickListener {
            val i = Intent(Intent.ACTION_PICK)
            i.type = ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE
            startActivityForResult(i, SELECT_PHONE_NUMBER)
        }
        next.setOnClickListener {
            presenter.saveData(info.text.toString(), name.text.toString())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode != Activity.RESULT_OK || data == null)
            return
        if (requestCode == SELECT_PHONE_NUMBER)
            parseContactInfo(data)
    }

    private fun parseContactInfo(data: Intent) {
        val cursor: Cursor = contentResolver.query(
                data.data,
                arrayOf(ContactsContract.CommonDataKinds.Phone.NUMBER, ContactsContract.CommonDataKinds.Identity.DISPLAY_NAME),
                null, null, null)

        // If the cursor returned is valid, get the phone number
        if (cursor.moveToFirst()) {
            val contactNumber: String = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            val contactName: String = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Identity.DISPLAY_NAME))
            info.setText(contactNumber)
            name.setText(contactName)
        }

        cursor.close()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbind()
    }

    override fun setData(contactInfo: String?, contactName: String?) {
        info.setText(contactInfo)
        name.setText(contactName)
    }

    override fun showContactInfoEmptyError() {
        Toast.makeText(this, R.string.configure_contact_validation_error, Toast.LENGTH_SHORT).show()
    }

    override fun nextScreen() {
        val i = Intent(this, ConfigureMessageActivity::class.java)
        startActivity(i)
    }
}
