package com.example.w2_challenge

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Detail(var fullName: String, var email: String, var password: String, var phoneNumber: String) : Parcelable