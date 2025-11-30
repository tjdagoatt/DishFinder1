package com.example.dishfinder.api

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Meal(
    @SerializedName("idMeal") val id: String,
    @SerializedName("strMeal") val name: String,
    @SerializedName("strMealThumb") val thumbnail: String?,

    @SerializedName("strInstructions") val instructions: String?,

    // We’ll use up to 10 ingredients to keep it simple
    @SerializedName("strIngredient1") val ingredient1: String?,
    @SerializedName("strIngredient2") val ingredient2: String?,
    @SerializedName("strIngredient3") val ingredient3: String?,
    @SerializedName("strIngredient4") val ingredient4: String?,
    @SerializedName("strIngredient5") val ingredient5: String?,
    @SerializedName("strIngredient6") val ingredient6: String?,
    @SerializedName("strIngredient7") val ingredient7: String?,
    @SerializedName("strIngredient8") val ingredient8: String?,
    @SerializedName("strIngredient9") val ingredient9: String?,
    @SerializedName("strIngredient10") val ingredient10: String?,

    @SerializedName("strMeasure1") val measure1: String?,
    @SerializedName("strMeasure2") val measure2: String?,
    @SerializedName("strMeasure3") val measure3: String?,
    @SerializedName("strMeasure4") val measure4: String?,
    @SerializedName("strMeasure5") val measure5: String?,
    @SerializedName("strMeasure6") val measure6: String?,
    @SerializedName("strMeasure7") val measure7: String?,
    @SerializedName("strMeasure8") val measure8: String?,
    @SerializedName("strMeasure9") val measure9: String?,
    @SerializedName("strMeasure10") val measure10: String?
) : Serializable {

    /** Builds a bullet list of ingredients + measures */
    fun buildIngredientsText(): String {
        val pairs = listOf(
            measure1 to ingredient1,
            measure2 to ingredient2,
            measure3 to ingredient3,
            measure4 to ingredient4,
            measure5 to ingredient5,
            measure6 to ingredient6,
            measure7 to ingredient7,
            measure8 to ingredient8,
            measure9 to ingredient9,
            measure10 to ingredient10
        )

        val lines = pairs.mapNotNull { (m, ing) ->
            val ingredient = ing?.trim()
            if (ingredient.isNullOrEmpty()) {
                null
            } else {
                val measure = m?.trim().orEmpty()
                val label = if (measure.isNotEmpty()) "$measure $ingredient" else ingredient
                "• $label"
            }
        }

        return if (lines.isEmpty()) {
            "No ingredient information available."
        } else {
            lines.joinToString("\n")
        }
    }

    /** Returns nice instructions text, or a fallback. */
    fun buildInstructionsText(): String {
        val clean = instructions?.trim()
        return if (clean.isNullOrEmpty()) {
            "No instructions available for this recipe."
        } else {
            clean
        }
    }
}
