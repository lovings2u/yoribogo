package com.mino.vo;

import java.util.Iterator;
import java.util.TreeMap;

public class RecipeVo {
	private String name;
	private String coreIngredient;
	private TreeMap<String, IngredientVo> ingredient;
	private String url;
	
	public RecipeVo(String name, String coreIngredient, String url) {
		this.name = name;
		this.coreIngredient = coreIngredient;
		this.url = url;
		ingredient = new TreeMap<>();
	}

	public String getName() {
		return name;
	}

	public String getCoreIngredient() {
		return coreIngredient;
	}

	public IngredientVo getIngredient(String name) {
		return ingredient.get(name);
	}
		
	public void addIngredient(IngredientVo ingredientVo) {
		ingredient.put(ingredientVo.getName(), ingredientVo);
	}
	
	public TreeMap<String, IngredientVo> getIngredient() {
		return ingredient;
	}
	
	public String getURL() {
		return url;
	}
	
	public Iterator<String> getIngredientList() {
		return ingredient.keySet().iterator();
		
	}

	@Override
	public String toString() {
		return "RecipeVo [name=" + name + ", coreIngredient=" + coreIngredient + "]";
	}
	
	
	
}
