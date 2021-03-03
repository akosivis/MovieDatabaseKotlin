package com.viselvis.moviedatabasekotlin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Contact(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "contactName") val contactName: String?,
    @ColumnInfo(name = "contactNum") val contactNumber: String?
)