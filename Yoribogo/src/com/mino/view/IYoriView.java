package com.mino.view;

import java.util.TreeMap;

import com.mino.vo.IngredientKindVo;
import com.mino.vo.IngredientVo;
import com.mino.vo.IngredientsVo;
import com.mino.vo.RecipeBookVo;
import com.mino.vo.RecipeVo;
import com.mino.vo.UserVo;

public interface IYoriView {
	int printMenu();
	int printIngredientMenu();
	int printRecipeMenu();
	void searchIngredientKind(IngredientsVo ingredients);
	String searchIngredient(IngredientKindVo ingredientKind);
	IngredientVo saveIngredient(TreeMap<String, IngredientVo> ingredients);
	String getRecipeName();
	String getCoreIngredientName();
	String checkRecipe(TreeMap<String, RecipeVo> recipes);
	void printRecipe(RecipeVo recipe);
	void checkIngredient(UserVo userVo);
	RecipeVo cookWithRecipe(RecipeBookVo recipeBookVo) throws Exception;
}
