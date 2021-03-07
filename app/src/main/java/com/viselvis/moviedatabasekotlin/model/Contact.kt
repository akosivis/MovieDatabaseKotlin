package com.viselvis.moviedatabasekotlin.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_table")
data class Contact(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "contactName") val contactName: String?,
    @ColumnInfo(name = "contactNum") val contactNumber: String?
) {
    constructor(contactName: String?, contactNumber: String?) : this(0, contactName, contactNumber)
}