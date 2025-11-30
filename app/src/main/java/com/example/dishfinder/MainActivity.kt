package com.example.dishfinder

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dishfinder.api.Meal
import com.example.dishfinder.api.MealApi
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var etIngredient: EditText
    private lateinit var btnSearch: Button
    private lateinit var rvMeals: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var tvEmpty: TextView

    private lateinit var adapter: MealAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etIngredient = findViewById(R.id.etIngredient)
        btnSearch = findViewById(R.id.btnSearch)
        rvMeals = findViewById(R.id.rvMeals)
        progressBar = findViewById(R.id.progressBar)
        tvEmpty = findViewById(R.id.tvEmpty)

        rvMeals.layoutManager = LinearLayoutManager(this)

        // Explicit type for lambda parameter
        adapter = MealAdapter(emptyList()) { meal: Meal ->
            openMealDetails(meal)
        }
        rvMeals.adapter = adapter

        tvEmpty.visibility = View.VISIBLE
        tvEmpty.text = "Search for recipes by ingredient!"

        btnSearch.setOnClickListener {
            val ingredient = etIngredient.text.toString().trim()

            if (ingredient.isEmpty()) {
                Toast.makeText(this, "Please enter an ingredient", Toast.LENGTH_SHORT).show()
            } else {
                searchMeals(ingredient)
            }
        }
    }

    private fun searchMeals(ingredient: String) {
        progressBar.visibility = View.VISIBLE
        tvEmpty.visibility = View.GONE

        lifecycleScope.launch {
            try {
                // Call our API helper
                val response = MealApi.service.searchMeals(ingredient)
                val meals: List<Meal> = response.meals ?: emptyList()

                if (meals.isEmpty()) {
                    adapter.updateMeals(emptyList())
                    tvEmpty.text = "No recipes found for \"$ingredient\""
                    tvEmpty.visibility = View.VISIBLE
                } else {
                    adapter.updateMeals(meals)
                }
            } catch (e: Exception) {
                adapter.updateMeals(emptyList())
                tvEmpty.text =
                    "Error loading recipes. Check your internet and try again."
                tvEmpty.visibility = View.VISIBLE
            } finally {
                progressBar.visibility = View.GONE
            }
        }
    }

    private fun openMealDetails(meal: Meal) {
        val intent = Intent(this, MealDetailActivity::class.java)
        intent.putExtra("meal", meal)
        startActivity(intent)
    }
}
