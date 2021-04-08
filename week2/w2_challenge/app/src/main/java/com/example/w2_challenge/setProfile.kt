package com.example.w2_challenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import android.widget.Toast


class setProfile : AppCompatActivity() {
    private lateinit var tvFullName: TextView
    private lateinit var tvUserName: TextView
    private lateinit var tvEmail: TextView
    private lateinit var tvPhoneNumber: TextView
    private lateinit var edtData: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )*/
        setContentView(R.layout.profile)
        findViewsById()
        getUserFromBundle()
        editInfo()
    }

    private fun getUserFromBundle() {
        val bundle = intent.extras
        bundle?.let {
            val user = bundle.getParcelable<Detail>(LoginActivity)
            initInfo(user)
        }
    }

    private fun findViewsById() {
        tvFullName = findViewById(R.id.fname)
        tvUserName = findViewById(R.id.nametext)
        tvEmail = findViewById(R.id.emailbox)
        tvPhoneNumber = findViewById(R.id.phonenum)
    }

    private fun initInfo(user: Detail?) {
        tvUserName.text = user?.fullName
        tvFullName.text = user?.fullName
        tvEmail.text = user?.email
        tvPhoneNumber.text = user?.phoneNumber
    }

    private fun setupAlertDialog(title: String, textHint: String, textView: TextView) {
        val view: View = LayoutInflater
            .from(this)
            .inflate(R.layout.dialog, null, false)
        edtData = view.findViewById(R.id.edtdulieu)
        edtData.setText(textView.text)
        edtData.hint = textHint
        val builder: AlertDialog.Builder = AlertDialog.Builder(this)
        builder.setView(view)
            .setTitle(title)
            .setPositiveButton("OK") { dialog, _ ->
                textView.text = edtData.text
                showToastMessage(edtData.text.toString())
                dialog.dismiss()
            }
            .setNegativeButton("CANCEL") { dialog, _ ->
                dialog.dismiss()
            }.show()

    }

    private fun editInfo() {
        tvFullName.setOnClickListener {
            setupAlertDialog("Edit full name", "Enter your full name", tvFullName)
        }

        tvEmail.setOnClickListener {
            setupAlertDialog("Edit e-mail ", "Enter your e-mail", tvEmail)
        }

        tvPhoneNumber.setOnClickListener {
            setupAlertDialog("Edit phone number ", "Enter your phone number", tvPhoneNumber)
        }
    }

    private fun showToastMessage(message:String){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
    }
}