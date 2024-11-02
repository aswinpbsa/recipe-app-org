package ganapati.example.reciepefood

sealed class SCREEN(val route:String) {
    object RecipeScreen:SCREEN("recipescreen")
    object DetailScreen:SCREEN("detailscreen")
}