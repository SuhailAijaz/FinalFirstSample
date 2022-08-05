package com.example.sampleproject1.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sampleproject1.Retrofit.PostsData
import com.example.sampleproject1.Retrofit.PostsDataItem


@Database(entities =[PostsDataItem::class], version = 1, exportSchema = true)
abstract class RoomDb :RoomDatabase(){
    abstract fun dao():DAO
    companion object {
        @Volatile
        private var INSTANCE: RoomDatabase? = null

        fun getDatabase(context: Context): RoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDb::class.java,
                    "api_data"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}