package com.mino.service;

import java.io.FileReader;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.mino.entity.DatabaseInsertion;
import com.mino.vo.IngredientKindVo;
import com.mino.vo.IngredientVo;
import com.mino.vo.IngredientsVo;
import com.mino.vo.RecipeBookVo;
import com.mino.vo.RecipeVo;
import com.mino.vo.UserVo;

public class SaveDataFromJsonFile {
	private DatabaseInsertion entity;

	public SaveDataFromJsonFile() {
		entity = new DatabaseInsertion();
	}

	public UserVo getUserData(String fileName) throws Exception {
		JSONParser jsonParser = new JSONParser();
		UserVo userVo = null;
		Object obj = jsonParser.parse(new FileReader(fileName)); // parsing JSON
																	// file
		JSONObject jsonObject = (JSONObject) obj;
		JSONArray users = (JSONArray) jsonObject.get("User"); // get User data
																// from file
		Iterator<JSONObject> iterator = users.iterator(); // make iterator for
															// iterate searching
															// data

		// make JSONObject for each user
		while (iterator.hasNext()) {
			JSONObject object = iterator.next();
			String id = (String) object.get("id");
			String firstName = (String) object.get("first_name");
			String lastName = (String) object.get("last_name");
			String gender = (String) object.get("gender");
			int livingStyle = Integer.parseInt((String) object.get("living_style")); // get
																						// user
																						// data
																						// from
																						// json
																						// file
			int genderInteger = 0; // male = 0, female = 1
			if (gender.equals("female"))
				genderInteger = 1;

			userVo = new UserVo(id, firstName, lastName, genderInteger, livingStyle);
			JSONArray jsonArray1 = (JSONArray) object.get("ingredient");
			Iterator<JSONObject> ingredients = jsonArray1.iterator();
			IngredientsVo ingredientsVo = new IngredientsVo();
			while (ingredients.hasNext()) {
				JSONObject ingredient = ingredients.next();
				String name = (String) ingredient.get("name");
				Double quantity = Double.parseDouble((String) ingredient.get("quantity"));
				String measure = (String) ingredient.get("measure");
				IngredientVo ingreVo = new IngredientVo(name, quantity, measure);
				userVo.addItem(ingreVo);
			}
		}
		entity.createUserData(userVo);
		return userVo;
	}

	public IngredientsVo getIngredientsData(String fileName) throws Exception{
		JSONParser jsonParser = new JSONParser();
		IngredientsVo ingredientsVo = new IngredientsVo();

		Object obj = jsonParser.parse(new FileReader(fileName)); // parsing
																	// JSON
																	// file
		JSONObject jsonObject = (JSONObject) obj;
		JSONArray users = (JSONArray) jsonObject.get("ingredients"); // get
																		// User
																		// data
																		// from
																		// file
		Iterator<JSONObject> iterator = users.iterator(); // make iterator
															// for iterate
															// searching
															// data

		// make JSONObject for each user
		while (iterator.hasNext()) {
			JSONObject object = iterator.next();

			String kind = (String) object.get("kind");
			IngredientKindVo eachKindIngredient = new IngredientKindVo(kind);
			JSONArray jsonArray1 = (JSONArray) object.get("value");
			Iterator<JSONObject> ingredients = jsonArray1.iterator();

			while (ingredients.hasNext()) {
				JSONObject ingredient = ingredients.next();
				String name = (String) ingredient.get("name");
				String measure = (String) ingredient.get("measure");
				IngredientVo ingreVo = new IngredientVo(name, measure);
				eachKindIngredient.addIngredient(ingreVo);
			}
			ingredientsVo.addIngredients(eachKindIngredient);
		}
		entity.createIngredientData(ingredientsVo);
		return ingredientsVo;
	}

	public RecipeBookVo getRecipeDta(String fileName) throws Exception{
		JSONParser jsonParser = new JSONParser();
		RecipeBookVo recipeBook = new RecipeBookVo();

		Object obj = jsonParser.parse(new FileReader(fileName)); // parsing
																	// JSON
																	// file
		JSONObject jsonObject = (JSONObject) obj;
		JSONArray users = (JSONArray) jsonObject.get("recipe"); // get User
																// data from
																// file
		Iterator<JSONObject> iterator = users.iterator(); // make iterator
															// for iterate
															// searching
															// data
		while (iterator.hasNext()) {
			JSONObject recipes = (JSONObject) iterator.next();
			String name = (String) recipes.get("name");
			String core = (String) recipes.get("core");
			String url = (String) recipes.get("source");
			RecipeVo recipe = new RecipeVo(name, core, url);
			JSONArray jsonArray1 = (JSONArray) recipes.get("ingredient");
			Iterator<JSONObject> ingredients = jsonArray1.iterator();
			while (ingredients.hasNext()) {
				JSONObject ingredient = ingredients.next();
				String foodName = (String) ingredient.get("name");
				Double quantity = Double.parseDouble((String) ingredient.get("quantity"));
				String measure = (String) ingredient.get("measure");
				IngredientVo ingreVo = new IngredientVo(foodName, quantity, measure);
				recipe.addIngredient(ingreVo);
			}
			recipeBook.addRecipes(recipe);
		}
		entity.createRecipeData(recipeBook);
		return recipeBook;

	}
}
