package com.example.dishfinder

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.example.dishfinder.api.Meal

class MealDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_detail)

        val ivThumb = findViewById<ImageView>(R.id.ivDetailThumb)
        val tvName = findViewById<TextView>(R.id.tvDetailName)
        val tvId = findViewById<TextView>(R.id.tvDetailId)
        val tvIngredients = findViewById<TextView>(R.id.tvDetailIngredients)
        val tvInstructions = findViewById<TextView>(R.id.tvDetailInstructions)
        val btnBack = findViewById<Button>(R.id.btnBackHome)

        val meal = intent.getSerializableExtra("meal") as? Meal

        meal?.let {
            tvName.text = it.name
            tvId.text = "Meal ID: ${it.id}"

            // Image
            ivThumb.load(it.thumbnail) {
                crossfade(true)
            }

            // Ingredients & instructions from the API
            tvIngredients.text = it.buildIngredientsText()
            tvInstructions.text = it.buildInstructionsText()
        }

        btnBack.setOnClickListener {
            finish()
        }

        supportActionBar?.title = "Recipe Details"
    }
}
