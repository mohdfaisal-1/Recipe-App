package eu.tutorials.hommenu

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun RecipeScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    viewstate: MainViewModel.RecipeState,
    navigateToDetail: (Category) -> Unit
                 ){
    val recipeViewModel: MainViewModel = viewModel()
    val viewState by recipeViewModel.categoriesState
    Box(modifier= Modifier.fillMaxSize()){
        when{
            viewState.loading ->{
                CircularProgressIndicator(modifier.align(Alignment.Center))
            }
            viewState.error != null ->{
                Text("Error: ${viewState.error}")
            }
            else ->{
                CategoryScreen(categories = viewState.list, navigateToDetail)
            }
        }
    }
}

@Composable
fun CategoryScreen(categories: List<Category>,
                   navigateToDetail: (Category) -> Unit
                   ){
    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize().padding(vertical = 16.dp)) {
        items(categories){
            category ->
            CategoryItem(category = category, navigateToDetail)
        }
    }
}

@Composable
fun CategoryItem(category: Category,
                 navigateToDetail: (Category) -> Unit
                 ){
    Column (
        modifier = Modifier.padding(vertical = 16.dp, horizontal = 8.dp).fillMaxWidth().
        clickable{ navigateToDetail(category) },
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier.fillMaxSize().aspectRatio(1f)
        )
        Text(
            text = category.strCategory,
            color = Color.Black,
            style = TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top =0.dp)
        )
    }
}





























