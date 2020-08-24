package com.example.cc_storage

import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.cc_storage.database.DatabaseMemo
import com.example.cc_storage.database.Memo
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_add_memo.*
import kotlinx.android.synthetic.main.fragment_add_memo.btnCancel
import kotlinx.android.synthetic.main.fragment_edit_memo.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [EditMemoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditMemoFragment : DialogFragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var db: DatabaseMemo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_edit_memo, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        this.context?.let { it ->
            DatabaseMemo.getInstance(it)?.let {
                db = it
            }
        }

        etMemoEdit.setText(memo.isiMemo)
        etTanggalEdit.setText(memo.tanggal)

        btnCancelEdit.setOnClickListener {
            dismiss()
        }

        btnHapus.setOnClickListener {
            GlobalScope.launch {
                val rowDeleted = db.memoDao().delete(memo)

                activity?.runOnUiThread {
                    if (rowDeleted >0){
                        Snackbar.make(view.rootView, "Data Memo Terhapus", Snackbar.LENGTH_LONG).show()
                        dismiss()
                    } else {
                        Snackbar.make(view.rootView, "Data Memo Gagal Terhapus", Snackbar.LENGTH_LONG).show()
                    }
                    (activity as ProfileActivity).fetchData()
                }
            }
        }

        btnUpdate.setOnClickListener {
            memo.apply {
                tanggal = etTanggalEdit.text.toString()
                isiMemo = etMemoEdit.text.toString()
            }

            GlobalScope.launch {
                val memoUpdated: Int = db.memoDao().update(memo)

                activity?.runOnUiThread {
                    if (memoUpdated >0){
                        Snackbar.make(view.rootView, "Data Memo Terupdate", Snackbar.LENGTH_LONG).show()
                        dismiss()
                    } else {
                        Snackbar.make(view.rootView, "Data Memo Gagal Terupdate", Snackbar.LENGTH_LONG).show()
                    }
                    (activity as ProfileActivity).fetchData()
                }
            }
        }

    }


    companion object {
        lateinit var memo: Memo

        fun setDataMemo(dataMemo: Memo): EditMemoFragment {
            memo = dataMemo
            return EditMemoFragment()
        }
    }
}