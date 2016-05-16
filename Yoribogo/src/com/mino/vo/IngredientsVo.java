package com.mino.vo;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

public class IngredientsVo {
	private TreeMap<String, IngredientKindVo> kindVo;
	
	public IngredientsVo() {
		kindVo = new TreeMap<>();
	}
	
	public void addIngredients(IngredientKindVo kindVo) {
		this.kindVo.put(kindVo.getKind(), kindVo);
	}
	
	public TreeMap<String, IngredientKindVo> getIngredients() {
		return kindVo;
	}
	
	public IngredientKindVo getEachKindOfIngredients(String kind) {
		return kindVo.get(kind);
	}
	
	public Set<String> getKinds() {
		return kindVo.keySet();
	}
	
	public TreeMap<String, IngredientVo> searchIngredientVoByName(String name) {
		TreeMap<String, IngredientVo> ingredientVo = new TreeMap<>();
		Iterator<Entry<String, IngredientKindVo>> iterator1 = kindVo.entrySet().iterator();
		while(iterator1.hasNext()) {
			IngredientKindVo kinds = iterator1.next().getValue();
			Iterator<Entry<String, IngredientVo>> iterator2 = kinds.getAllIngredient().entrySet().iterator();
			while(iterator2.hasNext()) {
				Entry<String, IngredientVo> ingre = iterator2.next();
				if(ingre.getKey().contains(name)) {
					ingredientVo.put(ingre.getKey(), ingre.getValue());
				}
			}
		}
		return ingredientVo;
	}
}
