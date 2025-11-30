# DishFinder

## Table of Contents

1. [App Overview](#App-Overview)  
1. [Product Spec](#Product-Spec)  
1. [Wireframes](#Wireframes)  
1. [Build Notes](#Build-Notes)

## App Overview

### Description 

**DishFinder** is a simple Android app that lets users search for recipes by entering a dish name or ingredient keyword. It uses the free public **TheMealDB** API to display real recipes with images, ingredients, and cooking instructions.

### App Evaluation

- **Category:** Food / Lifestyle  
- **Mobile:** Focused on quick, on-the-go recipe discovery. A mobile-first experience using native components (RecyclerView, Activities, Coil for images) instead of a web view.  
- **Story:** “You’re hungry, you’ve got a couple of ingredients, and you want to quickly find a dish you can make. DishFinder helps you go from ingredient to full recipe in just a few taps.”  
- **Market:** Anyone who cooks at home, students, or beginners who want simple recipe inspiration using what they have on hand.  
- **Habit:** Users can open the app whenever they are about to cook and want ideas using a specific ingredient or meal type.  
- **Scope:** Small but complete: one main search screen plus a detailed recipe view. Achievable within the course timeline while still demonstrating networking, RecyclerView, and API integration.

## Product Spec

### 1. User Features (Required and Optional)

**Required Features:**

- User can see a **home/search screen** with:
  - App title / short description
  - Text input to type a dish/ingredient
  - A search button
- User can tap the **Search** button to:
  - Trigger an API call to TheMealDB
  - See a loading indicator while data is fetched
- User sees **search results** in a scrollable list (RecyclerView):
  - Each item shows meal image, title, and basic info (category/area when available)
- User can tap a recipe item to open a **detail screen**:
  - Shows full-size image
  - Recipe name, category, area
  - Ingredients + measurements displayed in a readable list
  - Cooking instructions in a scrollable text area
- Detail screen includes a **Back** button in the top app bar that returns to the home/search screen.
- User sees a friendly message if:
  - No recipes match the search term
  - There was an error loading data (e.g., no internet)

**Stretch Features:**

- Pre-populated “suggested searches” buttons (e.g., “Chicken”, “Pasta”, “Beef”).
- Allow user to tap a random button to get a random meal from the API.
- Save favorite meals locally (e.g., using Room or SharedPreferences).
- Filter by category or area if time permits.

### 2. Chosen API(s)

**Primary API:** [TheMealDB](https://www.themealdb.com/api.php)

- **Endpoint: Search meals by name/keyword**

  ```text
  GET https://www.themealdb.com/api/json/v1/1/search.php?s={query}
