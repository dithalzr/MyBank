package com.dicoding.picodiploma.mybank.view

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.mybank.R
import com.dicoding.picodiploma.mybank.helper.FunctionHelper.rupiahFormat
import com.dicoding.picodiploma.mybank.model.ModelDatabase
import kotlinx.android.synthetic.main.list_item_history.view.*

class HistoryAdapter(
    var mContext: Context,
    modelInputList: MutableList<ModelDatabase>,
    adapterCallback: HistoryAdapterCallback
) : RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {

    var modelDatabase: MutableList<ModelDatabase>
    var mAdapterCallback: HistoryAdapterCallback

    @SuppressLint("NotifyDataSetChanged")
    fun setDataAdapter(items: List<ModelDatabase>) {
        modelDatabase.clear()
        modelDatabase.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_history, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data: ModelDatabase = modelDatabase[position]

        holder.tvNama.setText(data.namaPengguna)
        holder.tvDate.setText(data.tanggal)
        holder.tvKategori.text = "Sampah " + data.jenisSampah
        holder.tvBerat.text = "Berat : " + data.berat.toString() + " Kg"
        holder.tvSaldo.text = "Pendapatan : " + rupiahFormat(data.harga)

        if (data.berat < 5) {
            holder.tvStatus.setTextColor(ContextCompat.getColor(mContext, R.color.purple_200))
            holder.tvStatus.text = "Masih dalam proses"
        } else {
            holder.tvStatus.setTextColor(ContextCompat.getColor(mContext, R.color.teal_700))
            holder.tvStatus.text = "Sudah di konfirmasi"
        }
    }

    override fun getItemCount(): Int {
        return modelDatabase.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvNama: TextView = itemView.tvNama
        var tvDate: TextView = itemView.tvDate
        var tvKategori: TextView = itemView.tvKategori
        var tvBerat: TextView = itemView.tvBerat
        var tvSaldo: TextView = itemView.tvSaldo
        var tvStatus: TextView = itemView.tvStatus
        var imageDelete: ImageView = itemView.imageDelete

        init {
            imageDelete.setOnClickListener { view: View? ->
                val modelLaundry: ModelDatabase = modelDatabase[adapterPosition]
                mAdapterCallback.onDelete(modelLaundry)
            }
        }
    }

    interface HistoryAdapterCallback {
        fun onDelete(modelDatabase: ModelDatabase?)
    }

    init {
        modelDatabase = modelInputList
        mAdapterCallback = adapterCallback
    }

}