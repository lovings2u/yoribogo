package com.mino.service;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

import com.mino.entity.RecipeEntity;
import com.mino.exception.NoRecipePossibleException;
import com.mino.vo.IngredientVo;
import com.mino.vo.RecipeBookVo;
import com.mino.vo.RecipeVo;
import com.mino.vo.UserVo;

public class RecipeServiceImpl implements IRecipeService{
	RecipeEntity recipeEntity;
	RecipeBookVo recipeBookVo;
	IIngredientService ingredientService;
	public RecipeServiceImpl() {
		recipeEntity = new RecipeEntity();
		ingredientService = new IngredientServiceImpl();
		recipeBookVo = recipeEntity.getAllRecipe();
	}
	
	@Override
	public RecipeBookVo getAllRecipe() {
		return recipeEntity.getAllRecipe();
	}

	@Override
	public TreeMap<String, RecipeVo> searchRecipeByName(RecipeBookVo recipeBookVo, String name) {
		TreeMap<String, RecipeVo> result = new TreeMap<>();
		Iterator<Entry<String, RecipeVo>> iterator = recipeBookVo.getRecipe().entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<String, RecipeVo> recipe = iterator.next();
			if(recipe.getKey().contains(name)) {
				result.put(recipe.getKey(), recipe.getValue());
			}
		}
		return result;
	}

	@Override
	public TreeMap<String, RecipeVo> searchRecipeByCoreIngredient(RecipeBookVo recipeBookVo,String name) {
		TreeMap<String, RecipeVo> result = new TreeMap<>();
		Iterator<Entry<String, RecipeVo>> iterator = recipeBookVo.getRecipe().entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<String, RecipeVo> recipe = iterator.next();
			if(recipe.getValue().getCoreIngredient().contains(name)) {
				result.put(recipe.getKey(), recipe.getValue());
			}
		}
		return result;
	}
	
	@Override
	public TreeMap<String, RecipeVo> searchRecipeByUserIngredient(RecipeBookVo recipeBookVo, UserVo userVo, int count) throws Exception{
		TreeMap<String, IngredientVo> userIngredient = userVo.getItems();
		TreeMap<String, RecipeVo> result = new TreeMap<>();
		
		Iterator<Entry<String, RecipeVo>> iterator1 = recipeBookVo.getRecipe().entrySet().iterator();
		while(iterator1.hasNext()) {
			RecipeVo recipe = iterator1.next().getValue();
			if(userVo.hasIngredient(recipe.getCoreIngredient()))
				continue;
			TreeMap<String, IngredientVo> ingredient = recipe.getIngredient();
			Iterator<String> userIterator = userIngredient.keySet().iterator();
			while(userIterator.hasNext()) {
				ingredient.remove(userIterator.next());
			}
			if(ingredient.size() <= count) 
				result.put(recipe.getName(), recipe);
		}
		if(result.isEmpty())
			throw new NoRecipePossibleException("가능한 요리 레시피가 없습니다!");
		return result;
	}
}
