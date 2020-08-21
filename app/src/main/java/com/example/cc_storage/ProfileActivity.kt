package com.example.cc_storage

import android.content.Context
import android.os.Bundle
import android.os.Parcel
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cc_storage.database.DatabaseMemo
import com.example.cc_storage.database.Memo
import kotlinx.android.synthetic.main.activity_profile.*
import kotlinx.android.synthetic.main.fragment_edit_memo.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ProfileActivity : AppCompatActivity() {
    private lateinit var db: DatabaseMemo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val sharedPreferences = getSharedPreferences(userData.SP_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        DatabaseMemo.getInstance(this)?.let {
            db = it
        }

        val username = sharedPreferences.getString(userData.FIELD_USERNAME, "Belum Ada Data")
        val email = sharedPreferences.getString(userData.FIELD_EMAIL, "Belum Ada Data")

        etEmail.setText(email)
        etUsername.setText(username)

        etEmail.addTextChangedListener(object : TextWatcher {
            var emailChange = ""
            override fun afterTextChanged(p0: Editable?) {
                editor.putString(userData.FIELD_EMAIL, emailChange)
                editor.apply()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                emailChange = etEmail.text.toString()
            }
        })

        etUsername.addTextChangedListener(object : TextWatcher {
            var usernameChange = ""
            override fun afterTextChanged(p0: Editable?) {
                editor.putString(userData.FIELD_USERNAME, usernameChange)
                editor.apply()
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                usernameChange = etUsername.text.toString()
            }
        })

        fabProfile.setOnClickListener {
            val fm  = supportFragmentManager

            AddMemoFragment().show(fm, "Add Memo Fragment")
        }
    }

    override fun onResume() {
        super.onResume()
        fetchData()
    }

    fun fetchData(){
        GlobalScope.launch {
            val listMemo = db.memoDao().getAll()

            runOnUiThread {
                rvContainer.layoutManager= LinearLayoutManager(this@ProfileActivity, LinearLayoutManager.VERTICAL, false)
                rvContainer.adapter = MemoAdapter(listMemo)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return super.onSupportNavigateUp()
    }

    fun showEditDialog(memo: Memo){
        val fm  = supportFragmentManager

        EditMemoFragment.dataMemo(memo).show(fm, "Edit Memo Fragment")
    }
}