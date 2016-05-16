package com.mino.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import com.mino.vo.IngredientKindVo;
import com.mino.vo.IngredientVo;
import com.mino.vo.IngredientsVo;
import com.mino.vo.RecipeBookVo;
import com.mino.vo.RecipeVo;
import com.mino.vo.UserVo;

public class DatabaseInsertion {
	IngredientVo ingredientVo;

	public void createIngredientData(IngredientsVo ingredientsVo) {
		String sql = "INSERT INTO ingredient_kind(kind_name) VALUES(?);";
		String subSql = "INSERT INTO ingredient(name, measure, kind) VALUES(?,?,?)";
		PreparedStatement pstmt = null;
		Connection conn = DatabaseConnection.getConnection();
		IngredientKindVo kindVo;

		try {
			Set<String> kinds = ingredientsVo.getKinds();

			TreeMap<String, IngredientKindVo> kind = ingredientsVo.getIngredients();
			Iterator<Entry<String, IngredientKindVo>> iterator = kind.entrySet().iterator();
			while (iterator.hasNext()) {
				IngredientKindVo ingredientKindVo = iterator.next().getValue();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, ingredientKindVo.getKind());
				pstmt.executeUpdate();
				TreeMap<String, IngredientVo> ingredientVo = ingredientKindVo.getAllIngredient();
				Iterator<Entry<String, IngredientVo>> ingredients = ingredientVo.entrySet().iterator();
				while (ingredients.hasNext()) {
					IngredientVo ingredient = ingredients.next().getValue();
					pstmt = conn.prepareStatement(subSql);
					pstmt.setString(1, ingredient.getName());
					pstmt.setString(2, ingredient.getMeasure());
					pstmt.setString(3, ingredientKindVo.getKind());
					pstmt.executeUpdate();
				}
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public void createRecipeData(RecipeBookVo recipeBookVo) {
		String sql = "INSERT INTO recipe(recipe_name, core_ingredient,url) VALUES(?,?,?);";
		String subSql = "INSERT INTO recipe_ingredient(name, quantity, measure, recipe_name) VALUES(?,?,?,?)";
		PreparedStatement pstmt = null;
		Connection conn = DatabaseConnection.getConnection();
		try {
			TreeMap<String, RecipeVo> recipeVo = recipeBookVo.getRecipe();
			Iterator<Entry<String, RecipeVo>> iterator = recipeVo.entrySet().iterator();
			while (iterator.hasNext()) {
				RecipeVo recipe = iterator.next().getValue();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, recipe.getName());
				pstmt.setString(2, recipe.getCoreIngredient());
				pstmt.setString(3, recipe.getURL());
				pstmt.executeUpdate();
				TreeMap<String, IngredientVo> ingredients = recipe.getIngredient();
				Iterator<Entry<String, IngredientVo>> ingreIterator = ingredients.entrySet().iterator();
				while (ingreIterator.hasNext()) {
					Entry<String, IngredientVo> ingredient = ingreIterator.next();
					IngredientVo ingredientVo = ingredient.getValue();
					pstmt = conn.prepareStatement(subSql);
					pstmt.setString(1, ingredientVo.getName());
					pstmt.setDouble(2, ingredientVo.getQuantity());
					pstmt.setString(3, ingredientVo.getMeasure());
					pstmt.setString(4, recipe.getName());
					pstmt.executeUpdate();
				}

			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public void createUserData(UserVo userVo) {
		String sql = "INSERT INTO user_data(id, first_name, last_name, gender, living_style) VALUES(?,?,?,?,?);";
		String subSql = "INSERT INTO user_ingredient(name, quantity, measure, user_id) VALUES(?,?,?,?)";
		PreparedStatement pstmt = null;
		Connection conn = DatabaseConnection.getConnection();
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userVo.getId());
			pstmt.setString(2, userVo.getFirst_name());
			pstmt.setString(3, userVo.getLast_name());
			pstmt.setInt(4, userVo.getGender());
			pstmt.setInt(5, userVo.getLivingStyle());
			pstmt.executeUpdate();
			Iterator<Entry<String, IngredientVo>> iterator= userVo.getItems().entrySet().iterator();
			while(iterator.hasNext()) {
				IngredientVo ingredient = iterator.next().getValue();
				pstmt = conn.prepareStatement(subSql);
				pstmt.setString(1, ingredient.getName());
				pstmt.setDouble(2, ingredient.getQuantity());
				pstmt.setString(3, ingredient.getMeasure());
				pstmt.setString(4, userVo.getId());
				pstmt.executeUpdate();
			}
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}	
	}
}
