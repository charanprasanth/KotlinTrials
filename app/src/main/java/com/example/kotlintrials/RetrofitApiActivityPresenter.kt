package com.example.kotlintrials

import android.content.Context
import android.widget.TextView
import android.widget.Toast
import com.example.kotlintrials.model.DataModelItem
import com.example.kotlintrials.services.ApiServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitApiActivityPresenter(var view: RetrofitApiActivityContract.View) :
    RetrofitApiActivityContract.Presenter {
    override fun getDataFromWeb(context: Context, textView: TextView) {
        var apiData = ""

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

                for (DataModel in responseBody) {
                    apiData += (DataModel.title + "\n\n")
                    textView.text = apiData
                }
            }

            override fun onFailure(call: Call<List<DataModelItem>?>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }
}