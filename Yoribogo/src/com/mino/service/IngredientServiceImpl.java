package com.mino.service;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.mino.entity.IngredientEntity;
import com.mino.exception.NoSuchIngredientException;
import com.mino.vo.IngredientKindVo;
import com.mino.vo.IngredientVo;
import com.mino.vo.IngredientsVo;
import com.mino.vo.UserVo;

public class IngredientServiceImpl implements IIngredientService {
	IngredientEntity ingreEntity;
	IngredientsVo ingredients;

	public IngredientServiceImpl() {
		ingreEntity = new IngredientEntity();
		ingredients = ingreEntity.getAllIngredientData();
	}

	@Override
	public TreeMap<String, IngredientVo> searchIngredientByName(String name) throws Exception {
		TreeMap<String, IngredientVo> result = ingredients.searchIngredientVoByName(name);
		if (result.isEmpty()) {
			throw new NoSuchIngredientException("해당 재료가 없습니다!");
		}
		return result;
	}

	@Override
	public IngredientKindVo searchIngredientKind(String name) {
		IngredientKindVo kindVo = null;
		Iterator<String> kinds = ingredients.getKinds().iterator();
		while (kinds.hasNext()) {
			String kind = kinds.next();
			if (kind.contains(name)) {
				kindVo = ingredients.getEachKindOfIngredients(kind);
			}
		}
		return kindVo;
	}
	
	@Override
	public IngredientVo searchIngredient(IngredientKindVo ingredientKind, String name) {
		IngredientVo ingredient = null;
		Iterator<Entry<String, IngredientVo>> iterator = ingredientKind.getAllIngredient().entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<String, IngredientVo> kinds = iterator.next();
			if(kinds.getKey().contains(name)) {
				ingredient = kinds.getValue();
			}
		}
		return ingredient;
	}

	@Override
	public IngredientsVo getAllIngredients() {
		return ingredients;
	}

	@Override
	public void saveUserIngredient(IngredientVo ingredientVo, String id) {
		ingreEntity.saveUserIngredient(ingredientVo, id);
	}

	@Override
	public UserVo getUserIngredient(String id) {
		return ingreEntity.getUserIngredient(id);
	}

}
