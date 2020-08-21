package com.example.cc_storage

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.cc_storage.database.Memo
import kotlinx.android.synthetic.main.item_memo.view.*

class MemoAdapter(val listMemo: List<Memo>): RecyclerView.Adapter<MemoAdapter.ViewHolder>(){
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val activity = itemView.context as? ProfileActivity

        fun bind(listMemo: List<Memo>) {
            val dataListin2 = listMemo[adapterPosition]

            val memo = itemView.etMemo
            val tanggal = itemView.etTanggal

            memo.setText(dataListin2.isiMemo)
            tanggal.setText(dataListin2.tanggal)

            itemView.setOnClickListener {

                activity?.showEditDialog(dataListin2)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_memo, parent, false)
        return ViewHolder(view)

//        val dialog = Dialog(parent.context)
//        dialog.setContentView(R.layout.fragment_edit_memo)
//
//        val fm = (parent.context).getSuppo
//
//
//        tampil.itemView.setOnClickListener {
//
//        }
//
//        return tampil
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listMemo)
    }

    override fun getItemCount(): Int {
        return listMemo.size
    }


}