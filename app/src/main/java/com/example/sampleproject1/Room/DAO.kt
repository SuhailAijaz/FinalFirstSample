package com.example.sampleproject1.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.sampleproject1.Retrofit.PostsData
import com.example.sampleproject1.Retrofit.PostsDataItem
import retrofit2.Response

@Dao
interface DAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAPiData(postsDataItem: PostsDataItem)

    @Query("Select * from api_data")
    suspend fun getAllData() :List<PostsDataItem>
}