package com.mino.vo;

import java.util.Set;
import java.util.TreeMap;

public class IngredientKindVo {
	private String kind;
	private TreeMap<String, IngredientVo> ingredient;
	
	public IngredientKindVo(String kind) {
		this.kind = kind;
		ingredient = new TreeMap<>();
	}
	
	public void addIngredient(IngredientVo ingredientVo) {
		ingredient.put(ingredientVo.getName(), ingredientVo);
	}
	
	public IngredientVo deleteIngredient(String name) {
		return ingredient.remove(name);
	}
	
	public TreeMap<String, IngredientVo> getAllIngredient() {
		return ingredient;
	}
	
	public IngredientVo getIngredient(String name) {
		return ingredient.get(name);
	}
	
	public String getKind() {
		return kind;
	}
	
	public Set<String> getIngredientName() {
		return ingredient.keySet();
	}
	
	

	@Override
	public String toString() {
		return "IngredientKindVo [kind=" + kind + ", ingredient=" + ingredient + "]";
	}
	
	
	

}	
