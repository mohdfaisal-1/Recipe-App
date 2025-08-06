package eu.tutorials.hommenu

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.State


class MainViewModel : ViewModel(){

    private val _categorieState = mutableStateOf(RecipeState(loading = true))
    val categoriesState: State<RecipeState> = _categorieState

    init {
        fetchCategories()
    }


    private fun fetchCategories(){
        viewModelScope.launch {
            try {
                val response = recipeservice.getcategories()
                _categorieState.value = RecipeState(
                    list = response.categories,
                    loading = false,
                    error = null
                )
            }catch (e: Exception){
                _categorieState.value = RecipeState(
                    loading = false,
                    error = ("Error fetching Categories ${e.message}")
                )
            }
        }
    }

    data class RecipeState(
        val loading: Boolean = false,
        val list: List<Category> = emptyList(),
        val error: String? = null
    )
}