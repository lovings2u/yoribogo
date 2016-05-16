package com.mino.entity;

import com.mino.vo.IngredientVo;
import com.mino.vo.IngredientsVo;
import com.mino.vo.UserVo;

public interface IIngredientEntity {
	IngredientsVo getAllIngredient();
	UserVo getUserIngredient(String id);
	void addUserIngredient(IngredientVo ingredientVo, String id);
	void deleteUserIngredient(String name);
	void updateUserIngredientQuantity(String name, double quantity);
}
