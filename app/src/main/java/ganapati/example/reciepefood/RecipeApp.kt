package ganapati.example.reciepefood

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun RecipeApp(navController:NavHostController){
    val recipeViewModel: MainViewModel = viewModel()
    val viewstate by recipeViewModel.categoriesState
    NavHost(navController = navController, startDestination = SCREEN.RecipeScreen.route ){
      composable(SCREEN.RecipeScreen.route){
          RecipeScreen(viewState = viewstate, navigatetodetails = {
              navController.currentBackStackEntry?.savedStateHandle?.set("cat",it)
              navController.navigate(SCREEN.DetailScreen.route)
          })
      }
        composable(SCREEN.DetailScreen.route){
           val category = navController.previousBackStackEntry?.savedStateHandle?.
           get<Category>("cat")?: Category("","","","")
            categorydetailScreen(category = category)
        }
    }

}
