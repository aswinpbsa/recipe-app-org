package ganapati.example.reciepefood

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberAsyncImagePainter

@Composable
fun RecipeScreen(modifier: Modifier = Modifier,
    viewState:MainViewModel.RecipeState
                 ,navigatetodetails: (Category) -> Unit) { // Renamed to RecipeScreen
    val recipeViewModel: MainViewModel = viewModel() // Corrected naming convention

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            viewState.loading -> {
                CircularProgressIndicator( modifier = Modifier.align(alignment = Alignment.Center),progress = 0.5f)


            }
            viewState.error != null -> {
                Text("Error occurred")
            }
            else -> {
                CategoryScreen(categories = viewState.list,navigatetodetails) // Corrected naming
            }
        }
    }
}

@Composable
fun CategoryScreen(categories: List<Category>,navigatetodetails: (Category) -> Unit) { // Renamed to CategoryScreen
    LazyVerticalGrid(GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
        items(categories) { category ->
            CategoryItem(category = category, navigatetodetails)
        }
    }
}

@Composable
fun CategoryItem(category: Category,navigatetodetails:(Category)->Unit) { // Renamed to Category
    Column(
        modifier = Modifier
            .padding(8.dp)
            .clickable { navigatetodetails(category) }
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = rememberAsyncImagePainter(category.strCategoryThumb),
            contentDescription = null,
            modifier = Modifier.fillMaxSize().aspectRatio(1f)
        )
        Text(
            text = category.strCategory,
            color = Color.Black,
            style = androidx.compose.ui.text.TextStyle(fontWeight = FontWeight.Bold),
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}
