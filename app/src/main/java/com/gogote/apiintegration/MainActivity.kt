package com.gogote.apiintegration

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

class MainActivity : AppCompatActivity() {

    var url = "https://api.github.com/users"
    private var userinfoItem = ArrayList<userinfoItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Find RecyclerView in the layout
        val recyclerView = findViewById<RecyclerView>(R.id.rev)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Create the request
        val stringRequest = StringRequest(url, Response.Listener { response ->

            // Create a Gson instance
            val gsonBuilder = GsonBuilder()
            val gson = gsonBuilder.create()

            // Use TypeToken to handle the generic type ArrayList<userinfoItem>
            val type = object : TypeToken<ArrayList<userinfoItem>>() {}.type
            val items: ArrayList<userinfoItem> = gson.fromJson(response, type)

            // Clear and add all items to the userinfoItem list
            userinfoItem.clear()
            userinfoItem.addAll(items)

            // Set up RecyclerView adapter and layout manager
            val adapter = Adapter(this, userinfoItem)
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = adapter

        }, Response.ErrorListener { error ->
            Toast.makeText(this, "Something went wrong: ${error.toString()}", Toast.LENGTH_SHORT).show()
        })

        // Add the request to the Volley queue
        val volley = Volley.newRequestQueue(this)
        volley.add(stringRequest)
    }
}
