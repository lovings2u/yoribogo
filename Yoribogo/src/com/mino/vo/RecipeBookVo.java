package com.mino.vo;

import java.util.Set;
import java.util.TreeMap;

public class RecipeBookVo {
	private TreeMap<String, RecipeVo> recipes;
	
	public RecipeBookVo() {
		recipes = new TreeMap<>();
	}

	public RecipeVo searchByName(String name) {
		return recipes.get(name);
	}

	public void addRecipes(RecipeVo recipe) {
		recipes.put(recipe.getName(), recipe);
	}
	public void deleteRecipe(String name) {
		recipes.remove(name);
	}
	public TreeMap<String, RecipeVo> getRecipe() {
		return recipes;
	}
	
	public Set<String> getRecipesName() {
		return recipes.keySet();
	}

}
