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
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

private val selectedGenres = mutableListOf<String>()
private const val maxSelect = 3
private const val numCols = 3
//private const val numGenres = 26
private val genreList = arrayOf("Adventure", "Adult", "Animation", "Biography", "Crime", "Documentary", "Family", "Fantasy", "Film-Noir", "Game-Show", "History", "Horror", "Music", "Musical", "Mystery", "News", "Reality", "Romance", "Rom-Com", "Sci-Fi", "Short", "Sport", "Talk-Show", "Thriller", "War", "Western")

class GenreSelect : AppCompatActivity() {
    //TODO: Search, gray out continue button instead of sending toast
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_genre)

        val numSelectedTextView: TextView = findViewById(R.id.numSelected)
        val customAdapter = CustomAdapter(genreList, selectedGenres, numSelectedTextView)

        //RecyclerView with 3 columns
        val recyclerView: RecyclerView = findViewById(R.id.genreRecyclerView)
        recyclerView.layoutManager = GridLayoutManager(this, numCols)
        recyclerView.adapter = customAdapter

        //Handle button
        val continueButton: Button = findViewById(R.id.continueButton)
        continueButton.setOnClickListener {
            if (customAdapter.selectedGenres.size == maxSelect) {
                val intent = Intent(this, MovieSelectActivity::class.java)
                startActivity(intent)
            } else {
                // Three items aren't selected
                Toast.makeText(this, "Select three genres to continue", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

class CustomAdapter(private val dataSet: Array<String>, val selectedGenres: MutableList<String>, private val numSelectedTextView: TextView) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.text_row_item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genre = dataSet[position]
        holder.textView.text = genre

        // Set colors based on selection
        if (selectedGenres.contains(genre)) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(holder.itemView.context, R.color.highEmphasis))
            holder.textView.setTextColor(ContextCompat.getColor(holder.itemView.context, R.color.secondary))
        } else {
            //remove background if item is unselected
            holder.itemView.setBackgroundColor(Color.TRANSPARENT)
            selectedGenres.remove(genre)
        }

        // Handle item click
        holder.itemView.setOnClickListener {
            if (selectedGenres.contains(genre)) { //remove if selected
                selectedGenres.remove(genre)
            } else { //add if unselected and less than max
                if (selectedGenres.size < maxSelect){
                    selectedGenres.add(genre)
                }
            }
            //change text
            numSelectedTextView.text = "${selectedGenres.size}/${maxSelect} selected"
            notifyDataSetChanged() // Update UI
        }
    }

    override fun getItemCount(): Int = dataSet.size
}
