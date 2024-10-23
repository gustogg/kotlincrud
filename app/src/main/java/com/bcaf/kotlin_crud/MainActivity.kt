package com.bcaf.kotlin_crud

import android.content.Intent // Add this import statement
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bcaf.kotlin_crud.adapter.CreditAdapter
import com.bcaf.kotlin_crud.model.PengajuanCreditItem
import com.bcaf.kotlin_crud.viewmodel.PengajuanCreditViewModel
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.MediaType.Companion.toMediaTypeOrNull

class MainActivity : AppCompatActivity(), CreditAdapter.OnCreditItemRemoveListener {

    lateinit var viewModel: PengajuanCreditViewModel
    lateinit var lstPengajuanCredit: RecyclerView
    lateinit var btnTambahCredit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Apply window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Find view elements
        btnTambahCredit = findViewById(R.id.btnTambahKredit)

        // Set button click listener
        btnTambahCredit.setOnClickListener {
            val intent = Intent(this, UploadAllDocument::class.java) // Create the Intent
            startActivity(intent) // Start the activity
        }

        lstPengajuanCredit = findViewById(R.id.lstPengajuanCredit)
        lstPengajuanCredit.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this).get(PengajuanCreditViewModel::class.java)

        // Observe data and set adapter
        viewModel.getPengajuanCredit.observe(this) {
            val datax = it.data?.pengajuanCredit
            datax?.let { it1 ->
                lstPengajuanCredit.adapter = CreditAdapter(it1, this)
                lstPengajuanCredit.adapter?.notifyDataSetChanged()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.getPengajuanCredit()
    }

    // Handle remove button click
    // Handle remove button click
    override fun onRemoveClick(item: PengajuanCreditItem?, position: Int) {
        item?.id?.let { id ->
            viewModel.deletePengajuanCredit(id.toRequestBody())
        }
    }

    }




