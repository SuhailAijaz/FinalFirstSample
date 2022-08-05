package com.example.sampleproject1

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.sampleproject1.Retrofit.PostsData
import com.example.sampleproject1.Retrofit.PostsDataItem
import com.example.sampleproject1.Retrofit.PostsInstance
import com.example.sampleproject1.Room.RoomDb
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Suppress("DEPRECATION")

class MainActivity : AppCompatActivity() {
    private lateinit var database: RoomDb
    private lateinit var recyclerView: RecyclerView
    private val madapter by lazy {
        MainAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        database = Room.databaseBuilder(applicationContext, RoomDb::class.java, "api_data").build()

        recyclerView = findViewById(R.id.mainRecyclerView)
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = madapter
        }

        // Method to check the internet connection is On OR oFF
        CheckConnectionType()
    }

    fun CheckConnectionType() {
        val connectionManager =
            this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val wifi_Connection = connectionManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
        val mobile_data_connection =
            connectionManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)

        if (wifi_Connection!!.isConnectedOrConnecting || mobile_data_connection!!.isConnectedOrConnecting) {

            // Api showing data method and set to the Room
            ApiData()
            Toast.makeText(applicationContext, "Data Connection Established", Toast.LENGTH_SHORT)
                .show()
        }
        else {

                Toast.makeText(applicationContext,"Data Connection Lost",Toast.LENGTH_SHORT).show()

                    ShowingData()               // Showing data from room  database when internet connection lost
        }
    }
    private  fun ShowingData() {
    lifecycleScope.launch(){
        val home=database.dao().getAllData()
        madapter.receiveData(home)
//        Log.d("TAG", "ShowingData: ${madapter.receiveData(home)}")
//        Toast.makeText(applicationContext,""+madapter.receiveData(home),Toast.LENGTH_LONG).show()
    }

    }

    private fun ApiData() {
        lifecycleScope.launch() {
            val api_data = withContext(Dispatchers.IO) {
                PostsInstance.getPhotosService().getphotosdata()
            }
            if (api_data.isSuccessful) {
                madapter.receiveData(api_data.body() ?: PostsData())
                Log.d("API DATA", "onCreate: ${api_data.body()}")
            }

            withContext(Dispatchers.IO) {
//                database.dao().insertAPiData(api_data.body()?.get(8)?:PostsDataItem("",1,"",1))
                for(i in 0 until (api_data.body()?.size ?:0)) {

                }

                for (i in api_data.body() ?: PostsData()) {

                    val home = database.dao().insertAPiData(
                        i ?: PostsDataItem(
                            "",
                            1,
                            "",
                            1
                        )
                    )
                }
            }
        }
    }
}









