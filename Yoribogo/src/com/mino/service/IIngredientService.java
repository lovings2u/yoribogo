package com.mino.service;

import java.util.TreeMap;

import com.mino.vo.IngredientKindVo;
import com.mino.vo.IngredientVo;
import com.mino.vo.IngredientsVo;
import com.mino.vo.UserVo;

public interface IIngredientService {
	TreeMap<String, IngredientVo> searchIngredientByName(String name) throws Exception;
	IngredientKindVo searchIngredientKind(String name);
	IngredientVo searchIngredient(IngredientKindVo kindVo, String name);
	IngredientsVo getAllIngredients();
	void saveUserIngredient(IngredientVo ingredientVo, String id);
	UserVo getUserIngredient(String id);
}
