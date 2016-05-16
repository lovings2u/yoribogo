package com.mino.entity;

import com.mino.vo.RecipeBookVo;

public class RecipeEntity {
	IRecipeEntity recipeEntity;
	public RecipeEntity() {
		recipeEntity = new RecipeEntityImpl();
	}
	
	public RecipeBookVo getAllRecipe() {
		return recipeEntity.getAllRecipe();
	}
	
	
}
