package com.example.recyclerviewtest

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        rv.postDelayed(Runnable {
            repeat(3) {
                rv.adapter?.notifyItemChanged(4)
            }
            Log.d("ASD", "time out")
        }, 10000)

    }

    private fun initRecyclerView() {
        rv.adapter = RecyclerAdapter(arrayListOf(0, 1, 2, 3, 4))
        rv.layoutManager = LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
    }

    class RecyclerAdapter(private val list: ArrayList<Int>) : RecyclerView.Adapter<CustomViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_item, parent, false) as ViewGroup
            return CustomViewHolder(view)
        }

        override fun getItemCount(): Int = list.size

        @SuppressLint("SetTextI18n")
        override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
            Log.d("ASD", "onBindViewHolder position = $position")
            holder.tv.text = "$position == ${list[position]}"
        }

    }

    class CustomViewHolder(root: ViewGroup) : RecyclerView.ViewHolder(root) {
        val tv: TextView = root.findViewById(R.id.tv)
    }
}
