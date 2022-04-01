package com.example.kotlintrials

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import java.lang.StringBuilder

class RetrofitApiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_api)

        getDatafromWeb()
    }

    private fun getDatafromWeb() {
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/").build()
            .create(ApiServices::class.java)

        val retrofitData = retrofitBuilder.getDataFromApi()
        retrofitData.enqueue(object : Callback<List<DataModelItem>?> {
            override fun onResponse(
                call: Call<List<DataModelItem>?>,
                response: Response<List<DataModelItem>?>
            ) {
                val responseBody = response.body()!!

                val stringBuilder = StringBuilder()
                for (DataModel in responseBody) {
                    stringBuilder.append(DataModel.title)
                    stringBuilder.append("\n\n")
                }
                val textView = findViewById<TextView>(R.id.textView)
                textView.text = stringBuilder
            }

            override fun onFailure(call: Call<List<DataModelItem>?>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}

interface ApiServices {

    @GET("todos/")
    fun getDataFromApi(): Call<List<DataModelItem>>

    @POST("posts/")
    suspend fun pushData(@Body post: Post): Response<Post>
}

