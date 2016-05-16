package com.mino.view;

import java.util.TreeMap;

import com.mino.vo.IngredientKindVo;
import com.mino.vo.IngredientVo;
import com.mino.vo.IngredientsVo;
import com.mino.vo.RecipeBookVo;
import com.mino.vo.RecipeVo;
import com.mino.vo.UserVo;

public class YoriView {
	private IYoriView yoriView;
	
	public YoriView() {
		yoriView = new YoriViewImpl();
	}
	
	public int printMenu(){
		return yoriView.printMenu();
	}
	public int printRecipeMenu(){
		return yoriView.printRecipeMenu();
	}
	public int printIngredientMenu(){
		return yoriView.printIngredientMenu();
	}
	
	public void searchIngredientKind(IngredientsVo ingredients){
		yoriView.searchIngredientKind(ingredients);
	}
	public String searchIngredient(IngredientKindVo ingredientKind) {
		return yoriView.searchIngredient(ingredientKind);
	}
	public IngredientVo saveIngredient(TreeMap<String, IngredientVo> ingredients) {
		return yoriView.saveIngredient(ingredients);
	}
	
	
	public void checkIngredient(UserVo userVo) {
		yoriView.checkIngredient(userVo);
	}
	public String getRecipeName() {
		return yoriView.getRecipeName();
	}
	public String getCoreIngredientName() {
		return yoriView.getCoreIngredientName();
	}
	
	
	public String checkRecipe(TreeMap<String, RecipeVo> recipes) {
		return yoriView.checkRecipe(recipes);
	}
	public void printRecipe(RecipeVo recipe) {
		yoriView.printRecipe(recipe);
	}
	public RecipeVo cookWithRecipe(RecipeBookVo recipeBook) throws Exception{
		return yoriView.cookWithRecipe(recipeBook);
	}
}
