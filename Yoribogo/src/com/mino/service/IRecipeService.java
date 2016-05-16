package com.mino.service;

import java.util.TreeMap;

import com.mino.vo.RecipeBookVo;
import com.mino.vo.RecipeVo;
import com.mino.vo.UserVo;

public interface IRecipeService {
	RecipeBookVo getAllRecipe();
	TreeMap<String, RecipeVo> searchRecipeByName(RecipeBookVo recipeBookVo, String name);
	TreeMap<String, RecipeVo> searchRecipeByCoreIngredient(RecipeBookVo recipeBookVo, String name); 
	TreeMap<String, RecipeVo> searchRecipeByUserIngredient(RecipeBookVo recipeBookVo, UserVo userVo, int count) throws Exception;
}
