package com.example.cc_storage.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.sql.Date

@Entity
@Parcelize

data class Memo(
    @PrimaryKey(autoGenerate = true) var id:Int?,
    @ColumnInfo(name = "isiMemo") var isiMemo:String,
    @ColumnInfo(name = "tanggal") var tanggal:String
) : Parcelable