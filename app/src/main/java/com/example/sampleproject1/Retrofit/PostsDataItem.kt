package com.example.sampleproject1.Retrofit


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "api_data")
data class PostsDataItem(
    @ColumnInfo(name = "body")
    @SerializedName("body")
    val body: String?,
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    val id: Int?,
    @ColumnInfo(name = "title")
    @SerializedName("title")
    val title: String?,
    @ColumnInfo(name = "userId")
    @SerializedName("userId")
    val userId: Int?
)