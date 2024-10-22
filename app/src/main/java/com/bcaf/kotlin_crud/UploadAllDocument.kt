package com.bcaf.kotlin_crud

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bcaf.kotlin_crud.viewmodel.PengajuanCreditViewModel
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody

class UploadAllDocument : AppCompatActivity() {

    lateinit var txtUsername: EditText
    lateinit var txtAlamat: EditText
    lateinit var txtOs: EditText
    lateinit var btnSendData: Button

    // Initialize PengajuanCreditViewModel
    private val viewModel: PengajuanCreditViewModel by viewModels()

    fun initComponent() {
        txtUsername = findViewById(R.id.txtUsernameCredit)
        txtAlamat = findViewById(R.id.txtAlamat)
        txtOs = findViewById(R.id.txtOs)
        btnSendData = findViewById(R.id.btnSendData)
        txtUsername.setText(viewModel.getSharePreferencesLogin("username").toString())
        txtAlamat.setText(viewModel.getSharePreferencesLogin("alamat").toString())
        txtOs.setText(viewModel.getSharePreferencesLogin("ospo").toString())


        btnSendData.setOnClickListener {
            sendData()
        }
    }

    fun sendData() {
        val username = txtUsername.text.toString()
        val alamat = txtAlamat.text.toString()
        val ospo = txtOs.text.toString()

        if (username.isNotEmpty() && alamat.isNotEmpty()) {
            val rbUsername = RequestBody.create("text/plain".toMediaTypeOrNull(), username)
            val rbAlamat = RequestBody.create("text/plain".toMediaTypeOrNull(), alamat)
            val rbOspo = RequestBody.create("text/plain".toMediaTypeOrNull(), ospo)

            viewModel.postDataCredit(rbUsername, rbAlamat, rbOspo)

        } else {
            Toast.makeText(this, "Data belum lengkap", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_upload_all_document)
        initComponent()

        viewModel.post.observe(this) {
            if (it.status == true) {
                finish()
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
