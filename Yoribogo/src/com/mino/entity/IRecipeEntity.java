package com.mino.entity;

import com.mino.vo.IngredientVo;
import com.mino.vo.RecipeBookVo;
import com.mino.vo.RecipeVo;

public interface IRecipeEntity {
	RecipeBookVo getAllRecipe();
	void addRecipe(RecipeVo recipeVo);
	void updateRecipe(RecipeVo recipeVo);
	void deleteRecipe(String name);
	void updateRecipeIngredient(IngredientVo ingredientVo, String recipe_name);
}
