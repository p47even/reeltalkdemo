package com.p47.reeltalk

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

private val selectedMovies = mutableListOf<String>()
private const val maxSelect = 5
private const val numCols = 2

class MovieSelectActivity : AppCompatActivity() {

    //TODO: Search, gray out continue button instead of sending toast, API request
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_select)

        val movieList = arrayOf("The Shawshank Redemption (1994)", "Inception (2010)", "Wall-E (2008)")

        val numSelectedTextView: TextView = findViewById(R.id.numSelected)
        val customAdapter = CustomAdapterMov(movieList, selectedMovies, numSelectedTextView)

        //RecyclerView with 3 columns
        val recyclerView: RecyclerView = findViewById(R.id.movieRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, numCols)
        recyclerView.adapter = customAdapter

        //Handle button
        val continueButton: Button = findViewById(R.id.continueButton)
        continueButton.setOnClickListener {
            if (customAdapter.getSelectedMovies().size == maxSelect) {
                //val intent = Intent(this, MovieSelectActivity::class.java)
                //startActivity(intent)
            } else {
                // Five items aren't selected
                Toast.makeText(this, "Select five movies to continue", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

class CustomAdapterMov(
    private val dataSet: Array<String>,
    private val selectedMovies: MutableList<String>,
    private val numSelectedTextView: TextView
) : RecyclerView.Adapter<CustomAdapterMov.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.text_row_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = dataSet[position]
        holder.textView.text = movie

        // Set colors based on selection
        if (selectedMovies.contains(movie)) {
            holder.itemView.setBackgroundColor(
                ContextCompat.getColor(holder.itemView.context, R.color.highEmphasis)
            )
            holder.textView.setTextColor(
                ContextCompat.getColor(holder.itemView.context, R.color.secondary)
            )
        } else {
            // Remove background if item is unselected
            holder.itemView.setBackgroundColor(Color.TRANSPARENT)
            selectedMovies.remove(movie)
        }

        // Handle item click
        holder.itemView.setOnClickListener {
            if (selectedMovies.contains(movie)) {
                // Remove if selected
                selectedMovies.remove(movie)
            } else {
                // Add if unselected and less than max
                if (selectedMovies.size < maxSelect) {
                    selectedMovies.add(movie)
                }
            }
            // Change text
            numSelectedTextView.text = "${selectedMovies.size}/${maxSelect} selected"
            notifyDataSetChanged() // Update UI
        }
    }
    fun getSelectedMovies() = selectedMovies

    override fun getItemCount(): Int = dataSet.size
}
