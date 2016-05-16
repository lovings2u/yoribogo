package com.mino.entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mino.vo.IngredientKindVo;
import com.mino.vo.IngredientVo;
import com.mino.vo.IngredientsVo;
import com.mino.vo.RecipeBookVo;
import com.mino.vo.UserVo;

public class IngredientEntityImpl implements IIngredientEntity{
	IngredientsVo ingredientsVo;
	UserVo userVo;
	RecipeBookVo recipeBookVo;
	Connection conn = DatabaseConnection.getConnection();
	
	public IngredientEntityImpl() {
	}
	@Override
	public IngredientsVo getAllIngredient() {
		ingredientsVo = new IngredientsVo();
		String sql = "SELECT * FROM ingredient_kind;";
		String subSql = "SELECT * FROM ingredient WHERE kind = ?;";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String kind = rs.getString("kind_name");
				IngredientKindVo kindVo = new IngredientKindVo(kind);
				pstmt = conn.prepareStatement(subSql);
				pstmt.setString(1, kind);
				ResultSet rs2 = pstmt.executeQuery();
				while(rs2.next()) {
					IngredientVo ingredientVo = new IngredientVo(
							rs2.getString("name"),
							rs2.getDouble("quantity"),
							rs2.getString("measure"));
					kindVo.addIngredient(ingredientVo);
				}
				ingredientsVo.addIngredients(kindVo);
			}
			return ingredientsVo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public UserVo getUserIngredient(String id) {
		String sql = "SELECT * FROM user_data WHERE id = ?;";
		String subSql = "SELECT * FROM user_ingredient WHERE user_id = ?;";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				int gender = rs.getInt("gender");
				int livingStyle = rs.getInt("living_style");
				userVo = new UserVo(id, firstName, lastName, gender, livingStyle);
				
				pstmt = conn.prepareStatement(subSql);
				pstmt.setString(1, id);
				ResultSet rs2= pstmt.executeQuery();
				while(rs2.next()) {
					userVo.addItem(new IngredientVo(
							rs2.getString("name"),
							rs2.getDouble("quantity"),
							rs2.getString("measure")));
				}
			}
			return userVo;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void addUserIngredient(IngredientVo ingredientVo, String id) {
		String sql = "INSERT INTO user_ingredient(name, quantity, measure, user_id) VALUES (?,?,?,?);";
		String subSql = "UPDATE user_ingredient SET quantity = quantity + ? WHERE name = ?;";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ingredientVo.getName());
			pstmt.setDouble(2, ingredientVo.getQuantity());
			pstmt.setString(3, ingredientVo.getMeasure());
			pstmt.setString(4, id);
			pstmt.executeUpdate();
						
		} catch (Exception e1) {
			System.err.println("이미 있는 재료 입니다. 재료 양을 수정합니다.");
			try {
			pstmt = conn.prepareStatement(subSql);
			pstmt.setDouble(1, ingredientVo.getQuantity());
			pstmt.setString(2, ingredientVo.getName());
			pstmt.executeUpdate();
			} catch (Exception e2) {
				System.err.println(e2.getMessage());
			}
		}
	}
	@Override
	public void deleteUserIngredient(String name) {
		String sql = "DELETE FROM user_ingredient WHERE name = ?;";
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
	public void updateUserIngredientQuantity(String name, double quantity) {
		String sql = "UPDATE user_ingredient SET quantity = ? WHERE name = ?;";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, quantity);
			pstmt.setString(2,  name);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
