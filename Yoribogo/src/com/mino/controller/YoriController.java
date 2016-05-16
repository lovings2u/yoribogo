package com.mino.controller;

import java.util.TreeMap;

import com.mino.service.IngredientService;
import com.mino.service.RecipeService;
import com.mino.service.SaveDataFromJsonFile;
import com.mino.view.YoriView;
import com.mino.vo.IngredientKindVo;
import com.mino.vo.IngredientVo;
import com.mino.vo.IngredientsVo;
import com.mino.vo.RecipeBookVo;
import com.mino.vo.RecipeVo;
import com.mino.vo.UserVo;

public class YoriController {
	private YoriView yoriView;
	private IngredientService ingredientService;
	private RecipeService recipeService;
	private SaveDataFromJsonFile data;
	private UserVo userData;
	private RecipeBookVo recipeBook;
	private IngredientsVo ingredients;
	private TreeMap<String, RecipeVo> result;
	private RecipeVo recipeVo;
	
	public YoriController(String ingredientFile, String recipeFile, String userFile, String id) {
		yoriView = new YoriView();
		ingredientService = new IngredientService();
		ingredients = ingredientService.getAllIngredient();
		recipeService = new RecipeService();
		data = new SaveDataFromJsonFile();
		saveFirstData(ingredientFile, recipeFile, userFile);
		userData = ingredientService.getUserVo(id);
		recipeBook = recipeService.getAllRecipe();
	}
	
	public void saveFirstData(String ingredientFile, String recipeFile, String userFile) {
		try {
			data.getIngredientsData(ingredientFile);
			data.getRecipeDta(recipeFile);
			data.getUserData(userFile);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	public void execute() {
		boolean flag = true;
		while(flag) {
			yoriView = new YoriView();
			switch(printMenu()){
			case 1:
				switch(printIngredientMenu()) {
				case 1:
					saveIngredientByName();
					userData = ingredientService.getUserVo(userData.getId());
					break;
				case 2:
					saveIngredientByIndex();
					userData = ingredientService.getUserVo(userData.getId());
					break;
				default:
					System.err.println("잘못된 입력입니다.");
					break;
				}
				break;
			case 2:
				checkIngredient(userData);
				break;
			case 3:
				switch(printRecipeMenu()) {
				case 1:
					result = searchRecipeByName(recipeBook, getRecipeName());
					recipeVo = recipeBook.getRecipe().get(checkRecipe(result));
					printRecipe(recipeVo);
					break;
				case 2:
					result = searchRecipeByCoreIngredient(recipeBook, getCoreIngredientName());
					recipeVo = recipeBook.getRecipe().get(checkRecipe(result));
					printRecipe(recipeVo);
					break;
				case 3:
					checkRecipe(recipeBook.getRecipe());
					break;
				default:
					System.out.println("잘못된 입력입니다.");
					break;
				}
				break;
			case 4:
				int count = 10;
				result = searchRecipeByUserIngredient(recipeBook, userData, count);
				recipeVo = recipeBook.getRecipe().get(checkRecipe(result));
				printRecipe(recipeVo);
				break;
			case 5:
				flag = false;
				break;
			default:
				System.err.println("잘못된 입력입니다.");
				break;
			}
		}
		System.out.println("프로그램을 종료합니다.");
	}
	int printMenu() {
		return yoriView.printMenu();
	}
	
	int printIngredientMenu() {
		return yoriView.printIngredientMenu();
	}
	void saveIngredient(IngredientVo ingredientVo, String id) {
		ingredientService.saveIngredient(ingredientVo, id);
	}
	void saveIngredientByName() {
		try {
			TreeMap<String, IngredientVo> ingredient = ingredientService.searchIngredientByName();
			saveIngredient(yoriView.saveIngredient(ingredient), userData.getId());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	void saveIngredientByIndex() {
		try {
			yoriView.searchIngredientKind(ingredients);
			IngredientKindVo ingredientKind = ingredientService.searchIngredientByIndex();
			TreeMap<String, IngredientVo> ingredient = ingredientKind.getAllIngredient();
			saveIngredient(yoriView.saveIngredient(ingredient), userData.getId());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
	
	
	int printRecipeMenu() {
		return yoriView.printRecipeMenu();
	}
	
	String getRecipeName() {
		String name = yoriView.getRecipeName();
		System.err.println("here" + name);
		return name;
	}
	String getCoreIngredientName() {
		return yoriView.getCoreIngredientName();
	}
	
	TreeMap<String, RecipeVo> searchRecipeByName(RecipeBookVo recipeBookVo, String name) {
		return recipeService.searchRecipeByName(recipeBookVo, name);
	}
	TreeMap<String, RecipeVo> searchRecipeByCoreIngredient(RecipeBookVo recipeBookVo, String name) {
		return recipeService.searchRecipeByCoreIngredient(recipeBookVo, name);
	}
	TreeMap<String, RecipeVo> searchRecipeByUserIngredient(RecipeBookVo recipeBookVo, UserVo userVo, int count) {
		try {
			return recipeService.searchRecipeByUserIngredient(recipeBookVo, userVo, count);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return null;
	}

	void checkIngredient(UserVo userVo) {
		yoriView.checkIngredient(userVo);
	}
	String checkRecipe(TreeMap<String, RecipeVo> recipes) {
		return yoriView.checkRecipe(recipes);
	}
	void printRecipe(RecipeVo recipe) {
		yoriView.printRecipe(recipe);
	}

}
