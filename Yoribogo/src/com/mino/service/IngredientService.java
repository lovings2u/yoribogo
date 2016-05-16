package com.mino.service;

import java.util.Scanner;
import java.util.TreeMap;

import com.mino.vo.IngredientKindVo;
import com.mino.vo.IngredientVo;
import com.mino.vo.IngredientsVo;
import com.mino.vo.UserVo;

public class IngredientService {
	private IIngredientService ingredientService;
	private Scanner sc = new Scanner(System.in);
	public IngredientService() {
		ingredientService = new IngredientServiceImpl();
	}
	public IngredientsVo getAllIngredient() {
		return ingredientService.getAllIngredients();
	}
	
	public TreeMap<String, IngredientVo> searchIngredientByName() throws Exception {
		System.out.print(">> 찾으시는 재료이름을 입력하세요 -> ");
		String name = sc.nextLine();
		return ingredientService.searchIngredientByName(name);
	}
	
	public IngredientKindVo searchIngredientByIndex() throws Exception{
		System.out.print(">> 찾으시는 재료의 종류를 입력하세요 -> ");
		String name = sc.nextLine();
		IngredientKindVo ingredientKind = ingredientService.searchIngredientKind(name);
		return ingredientKind;
	}

	public void saveIngredient(IngredientVo ingredientVo, String id) {
		ingredientService.saveUserIngredient(ingredientVo, id);
	}
	
	public UserVo getUserVo(String id) {
		return ingredientService.getUserIngredient(id);
	}


	
}
