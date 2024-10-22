package com.bcaf.kotlin_crud.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bcaf.kotlin_crud.R
import com.bcaf.kotlin_crud.model.PengajuanCreditItem

class CreditAdapter(
    private val creditList: List<PengajuanCreditItem?>,
    private val listener: OnCreditItemRemoveListener
) : RecyclerView.Adapter<CreditAdapter.CreditViewHolder>() {

    inner class CreditViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var txtUsernameItemCredit = itemView.findViewById<TextView>(R.id.txtUsernameItemCredit)
        var txtAlamatItemCredit = itemView.findViewById<TextView>(R.id.txtAlamatCredit)
        var txtOspoCredit = itemView.findViewById<TextView>(R.id.txtOspoCredit)
        var btnRemove = itemView.findViewById<TextView>(R.id.btnRemove)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreditViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_credit, parent, false)
        return CreditViewHolder(view)
    }

    override fun getItemCount(): Int {
        return creditList.size
    }

    override fun onBindViewHolder(holder: CreditViewHolder, position: Int) {
        val credit = creditList[position]

        holder.txtUsernameItemCredit.text = credit?.username
        holder.txtAlamatItemCredit.text = credit?.alamat
        holder.txtOspoCredit.text = "Rp.${credit?.ospo}"

        holder.btnRemove.setOnClickListener {
            listener.onRemoveClick(credit, position)
        }
    }

    interface OnCreditItemRemoveListener {
        fun onRemoveClick(item: PengajuanCreditItem?, position: Int)
    }
}
