package com.softlabit.mvvmproject.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

const val CURRENT_USER_ID = 0

@Entity
data class User(
    var id: String? = null,
    var email: String? = null,
    var name: String? = null,
    var phone: String? = null,
    var profileImage: String? = null,
    var hasAccess: String? = null,
    var createdAt: String? = null,
    var updatedAt: String? = null
) {
    @PrimaryKey(autoGenerate = false)
    var uid: Int = CURRENT_USER_ID
}