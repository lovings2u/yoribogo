package com.mino.entity;

import com.mino.vo.IngredientVo;
import com.mino.vo.IngredientsVo;
import com.mino.vo.UserVo;

public class IngredientEntity {
	IIngredientEntity ingreEntity;
	
	public IngredientEntity () {
		ingreEntity = new IngredientEntityImpl();
	}
	
	public IngredientsVo getAllIngredientData() {
		return ingreEntity.getAllIngredient();
	}
	
	public void saveUserIngredient(IngredientVo ingredientVo, String id) {
		ingreEntity.addUserIngredient(ingredientVo, id);
	}
	
	public UserVo getUserIngredient(String id) {
		return ingreEntity.getUserIngredient(id);
	}
}
