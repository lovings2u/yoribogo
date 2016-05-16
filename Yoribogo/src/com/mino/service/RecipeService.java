package com.mino.service;

import java.util.TreeMap;

import com.mino.vo.RecipeBookVo;
import com.mino.vo.RecipeVo;
import com.mino.vo.UserVo;

public class RecipeService {
	IRecipeService recipeService;
	public RecipeService() {
		recipeService = new RecipeServiceImpl();
	}
	public RecipeBookVo getAllRecipe() {
		return recipeService.getAllRecipe();
	}
	
	public TreeMap<String, RecipeVo> searchRecipeByName(RecipeBookVo recipeBookVo, String name) {
		return recipeService.searchRecipeByName(recipeBookVo, name);
	}
	
	public TreeMap<String, RecipeVo> searchRecipeByCoreIngredient(RecipeBookVo recipeBookVo, String name) {
		return recipeService.searchRecipeByCoreIngredient(recipeBookVo, name);
	}
	
	public TreeMap<String, RecipeVo> searchRecipeByUserIngredient(RecipeBookVo recipeBookVo, UserVo userVo, int count) throws Exception {
		return recipeService.searchRecipeByUserIngredient(recipeBookVo, userVo, count);
	}
	
}
