package eu.tutorials.hommenu

import okhttp3.Route

sealed class Screen(val route: String){
    object RecipeScreen : Screen("recipescreen")
    object DetailScreen : Screen("detailscreen")
}