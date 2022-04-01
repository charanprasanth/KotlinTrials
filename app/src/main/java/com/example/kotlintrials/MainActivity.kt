package com.example.kotlintrials

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SmoothScroller
import com.bumptech.glide.Glide
import java.util.*
import kotlin.random.Random


class MainActivity : AppCompatActivity(), View.OnClickListener {

    var data = arrayListOf(
        "Stark",
        "Rogers",
        "Ruffalo",
        "Thor",
        "Odin",
        "Black",
        "Wanda",
        "Strange",
        "Parker",
        "Ork",
        "Goblin"
    )
    lateinit var linearLayoutManager: LinearLayoutManager
    lateinit var adapter: RecyclerAdapter
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addBtn = findViewById<Button>(R.id.addBtn)
        val deleteBtn = findViewById<Button>(R.id.deleteBtn)
        val clearBtn = findViewById<Button>(R.id.clearBtn)
        val button = findViewById<Button>(R.id.button)
        recyclerView = findViewById(R.id.recyclerView)

        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        linearLayoutManager = LinearLayoutManager(this)
        adapter = RecyclerAdapter(data)
        recyclerView.adapter = adapter

        addBtn.setOnClickListener(this)
        deleteBtn.setOnClickListener(this)
        clearBtn.setOnClickListener(this)
        button.setOnClickListener(this)
    }

    override fun onClick(v: View?) {

        when {
            v?.id?.equals(R.id.addBtn) == true -> {
                data.add("hello")
                adapter.notifyDataSetChanged()
                recyclerView.smoothScrollToPosition(data.size - 1)
            }
            v?.id?.equals(R.id.deleteBtn) == true -> {
                if (data.size > 0) {
                    data.removeAt(0)
                    adapter.notifyDataSetChanged()
                    recyclerView.smoothScrollToPosition(0)
                } else {
                    Toast.makeText(applicationContext, "no data", Toast.LENGTH_SHORT).show()
                }
            }
            v?.id?.equals(R.id.clearBtn) == true -> {
                if (data.size > 0) {
                    data.clear()
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(applicationContext, "no data", Toast.LENGTH_SHORT).show()
                }
            }
            v?.id?.equals(R.id.button) == true -> {
                startActivity(Intent(this, RetrofitApiActivity::class.java))
            }
        }
    }
}

class RecyclerAdapter(private val data: ArrayList<String>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text.setText(position.toString() + ". " + data[position])
        holder.layout.setOnClickListener {
            data.removeAt(position)
        }
        Glide.with(holder.layout).load(randomImage()).into(holder.img);
    }

    override fun getItemCount() = data.size

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var text: TextView = itemView.findViewById(R.id.text)
        var layout: LinearLayout = itemView.findViewById(R.id.layout)
        var img: ImageView = itemView.findViewById(R.id.img)
    }

    fun randomImage(): String {
        val list = listOf(
            "https://picsum.photos/500/300",
            "https://picsum.photos/600/300",
            "https://picsum.photos/700/400",
            "https://picsum.photos/500/500",
            "https://picsum.photos/600/200",
            "https://picsum.photos/1100/900",
            "https://picsum.photos/500/300",
            "https://picsum.photos/670/380",
            "https://picsum.photos/1500/970",
            "https://picsum.photos/580/200",
            "https://picsum.photos/500/300",
            "https://picsum.photos/600/300",
            "https://picsum.photos/700/400",
            "https://picsum.photos/500/500",
            "https://picsum.photos/600/200",
            "https://picsum.photos/1100/900",
            "https://picsum.photos/500/300",
            "https://picsum.photos/670/380",
            "https://picsum.photos/1500/970",
            "https://picsum.photos/580/200",
            "https://picsum.photos/500/300",
            "https://picsum.photos/500/300",
            "https://picsum.photos/600/300",
            "https://picsum.photos/700/400",
            "https://picsum.photos/500/500",
            "https://picsum.photos/600/200",
            "https://picsum.photos/1100/900",
            "https://picsum.photos/500/300",
            "https://picsum.photos/670/380",
            "https://picsum.photos/1500/970",
            "https://picsum.photos/580/200",
            "https://picsum.photos/500/300",
            "https://picsum.photos/600/300",
            "https://picsum.photos/700/400",
            "https://picsum.photos/500/500",
            "https://picsum.photos/600/200",
            "https://picsum.photos/1100/900",
            "https://picsum.photos/500/300",
            "https://picsum.photos/670/380",
            "https://picsum.photos/1500/970",
            "https://picsum.photos/580/200",
            "https://picsum.photos/500/300"
        )
        val randomIndex = Random.nextInt(list.size);
        val randomElement = list[randomIndex]

        // here we can use the selected element to print it for example
        return randomElement
    }
}