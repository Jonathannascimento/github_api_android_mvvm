package com.br.agile_github.agilegithubapi.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class User(

        var id: Int? = null,

        var login: String? = null,

        var email: String? = null,

        @SerializedName("avatar_url")
        var avatarURL: String? = null

) : Parcelable {

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        login = parcel.readString()
        email = parcel.readString()
        avatarURL = parcel.readString()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(login)
        parcel.writeString(email)
        parcel.writeString(avatarURL)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<User> {
        override fun createFromParcel(parcel: Parcel): User {
            return User(parcel)
        }

        override fun newArray(size: Int): Array<User?> {
            return arrayOfNulls(size)
        }
    }
}
