package com.example.datapengeluaran.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val tanggal: String,
    val kategori: String,
    val jumlah: Int,
    val keterangan: String

): Parcelable