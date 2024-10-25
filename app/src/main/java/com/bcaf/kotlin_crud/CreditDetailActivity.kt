package com.bcaf.kotlin_crud

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bcaf.kotlin_crud.model.PengajuanCreditItem

class CreditDetailActivity : AppCompatActivity() {

    lateinit var txtDetailUsername: TextView
    lateinit var txtDetailAlamat: TextView
    lateinit var txtDetailOspo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        // Find view elements
        txtDetailUsername = findViewById(R.id.txtUsernameUpdate)
        txtDetailAlamat = findViewById(R.id.txtAlamatUpdate)
        txtDetailOspo = findViewById(R.id.txtOsUpdate)

        // Retrieve data passed via intent
        val pengajuanCreditItem = intent.getParcelableExtra<PengajuanCreditItem>("credit_item")

        // Populate the UI with credit details
        pengajuanCreditItem?.let {
            txtDetailUsername.text = it.username
            txtDetailAlamat.text = it.alamat
            txtDetailOspo.text = "Rp.${it.ospo}"
        }
    }
}
