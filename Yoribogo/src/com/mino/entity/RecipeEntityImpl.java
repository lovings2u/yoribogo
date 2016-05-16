package com.mino.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mino.vo.IngredientVo;
import com.mino.vo.RecipeBookVo;
import com.mino.vo.RecipeVo;

public class RecipeEntityImpl implements IRecipeEntity{
	RecipeBookVo recipeBookVo;
	Connection conn;
	
	public RecipeEntityImpl() {
		conn = DatabaseConnection.getConnection();
	}
	
	@Override
	public RecipeBookVo getAllRecipe(){
		String sql = "SELECT * FROM recipe;";
		String subSql = "SELECT * FROM recipe_ingredient WHERE recipe_name = ?;";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			recipeBookVo = new RecipeBookVo();
			while(rs.next()) {
				String recipeName = rs.getString("recipe_name");
				String coreIngredient = rs.getString("core_ingredient");
				String url = rs.getString("url");
				RecipeVo recipeVo = new RecipeVo(recipeName, coreIngredient, url);
				pstmt = conn.prepareStatement(subSql);
				pstmt.setString(1, recipeName);
				ResultSet rs2 = pstmt.executeQuery();
				while(rs2.next()) {
					recipeVo.addIngredient(new IngredientVo(
							rs2.getString("name"),
							rs2.getDouble("quantity"),
							rs2.getString("measure")));
				}
				recipeBookVo.addRecipes(recipeVo);
			}
			
			return recipeBookVo;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public void addRecipe(RecipeVo recipeVo) {
		String sql = "INSERT INTO recipe(recipe_name, core_ingredient, url) VALUES(?,?,?);";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, recipeVo.getName());
			pstmt.setString(2, recipeVo.getCoreIngredient());
			pstmt.setString(3, recipeVo.getURL());
			pstmt.executeUpdate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateRecipe(RecipeVo recipeVo) {
		String sql = "UPDATE recipe SET url = ? WHERE recipe_name = ?;";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, recipeVo.getURL());
			pstmt.setString(2, recipeVo.getName());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteRecipe(String name) {
		String sql = "DELETE FROM recipe WHERE recipe_name = ?;";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void updateRecipeIngredient(IngredientVo ingredientVo, String name){
		String sql = "UPDATE recipe_ingredient SET quantity = ? WHERE recipe_name = ?;";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, ingredientVo.getQuantity());
			pstmt.setString(2, name);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
