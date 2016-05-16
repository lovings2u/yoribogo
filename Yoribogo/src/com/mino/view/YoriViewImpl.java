package com.mino.view;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.TreeMap;

import com.mino.exception.NoRecipePossibleException;
import com.mino.vo.IngredientKindVo;
import com.mino.vo.IngredientVo;
import com.mino.vo.IngredientsVo;
import com.mino.vo.RecipeBookVo;
import com.mino.vo.RecipeVo;
import com.mino.vo.UserVo;

public class YoriViewImpl implements IYoriView{
	private final String[] mainMenu = {"새로운 재료 저장하기", "가지고 있는 재료 목록보기", "레시피 찾기", "나에게 맞는 레시피 찾기", "종료하기"};
	private final String[] recipeMenu = {"이름으로 검색", "주재료로 검색", "레시피 전체보기"};
	private final String[] ingredientMenu = {"이름으로 재료 찾기", "색인으로 재료 찾기"};
	private Scanner sc;

	public YoriViewImpl() {
		sc = new Scanner(System.in);
	}
	@Override
	public int printMenu() {
		for(int i = 0; i < mainMenu.length; i++) {
			System.out.println("|" + (i+1) + "| " + mainMenu[i]);
		}
		System.out.print(">> Select Menu -> ");
		int choice = 0;
		try {
			choice = sc.nextInt();
		} catch (Exception e) {
			e.getMessage();
		}
		return choice;
	}

	@Override
	public int printIngredientMenu(){
		for(int i = 0; i < ingredientMenu.length; i++) {
			System.out.println("|..." + (i+1) + "| " + ingredientMenu[i]);
		}
		System.out.print(">>  Select Menu -> ");
		
		int choice = 0;
		try {
			choice = sc.nextInt();
		} catch (Exception e) {
			e.getMessage();
		}
		return choice;
	}
	
	@Override
	public int printRecipeMenu() {
		for(int i = 0; i < recipeMenu.length; i++) {
			System.out.println("|..." + (i+1) + "| " + recipeMenu[i]);
		}
		System.out.print(">> 메뉴를 선택하세요 -> ");
		int choice = 0;
		try {
			choice = sc.nextInt();
		} catch (Exception e) {
			e.getMessage();
		}
		return choice;
	}
	
	@Override
	public void searchIngredientKind(IngredientsVo ingredientsVo){
		Iterator<String> kinds = ingredientsVo.getKinds().iterator();
		while(kinds.hasNext()) {
			System.out.println(kinds.next());
		}
	}
	@Override
	public String searchIngredient(IngredientKindVo kind) {
		Iterator<String> ingredients = kind.getIngredientName().iterator();
		int i = 0;
		while(ingredients.hasNext()){ 
			System.out.printf("%10s", ingredients.next());
			i++;
			if(i % 5 == 0) {
				System.out.println();
			}
		}
		System.out.print(">> 재료의 이름을 입력하세요 -> ");
		String name = sc.nextLine();
		return name;
	}
	
	public IngredientVo saveIngredient(TreeMap<String, IngredientVo> ingredients) {
		Iterator<String> ingredient = ingredients.keySet().iterator();
		System.out.println("검색결과");
		int i = 0;
		while(ingredient.hasNext()){ 
			System.out.printf("%-30s", ingredient.next());
			i++;
			if(i % 5 == 0) {
				System.out.println();
			}
		}
		System.out.println();
		sc.nextLine();
		System.out.print(">> 재료명을 정확히 입력해주세요 -> ");
		String name = sc.nextLine();
		IngredientVo ingredientVo = ingredients.get(name);
		System.out.print(">> 가지고 계신 재료의 양을 입력해주세요 -> ");
		double quantity = sc.nextDouble();
		ingredientVo.setQuantity(quantity);
		return ingredientVo;
	}
	
	
	
	
	@Override
	public String getRecipeName() {
		sc.nextLine();
		System.out.print(">> 원하시는 요리의 이름을 입력해주세요 -> ");
		String recipeName = sc.nextLine();
		return recipeName;
	}

	@Override
	public String getCoreIngredientName() {
		sc.nextLine();
		System.out.print(">> 찾으시려는 요리의 주재료를 입력해주세요 -> ");
		return sc.nextLine();
	}
	
	@Override
	public void checkIngredient(UserVo userVo) {
		Iterator<String> ingredients = userVo.getItems().keySet().iterator();
		System.out.println(userVo.getId() + "'s Items");
		System.out.println("**가지고 있는 재료 목록***");
		int i = 1;
		while(ingredients.hasNext()) {
			if(i % 5 == 0) {
				System.out.println(ingredients.next());
			} else {
				System.out.print(ingredients.next() + ", ");
			}
			i++;
		}
		System.out.println("\n");
		
		
	}
	
	@Override
	public String checkRecipe(TreeMap<String, RecipeVo> recipes) {
		Iterator<String> recipe = recipes.keySet().iterator();
		int i = 1;
		while(recipe.hasNext()) {
			System.out.println(recipe.next());
		}
		sc.nextLine();
		System.out.print(">> 원하시는 요리의 이름을 입력해주세요 ->");
		return sc.nextLine();
	}
	
	@Override
	public void printRecipe(RecipeVo recipe) {
		System.out.println("*****************" + recipe.getName() + "*****************");
		Iterator<String> ingredients = recipe.getIngredientList();
		while(ingredients.hasNext()) {
			System.out.println(ingredients.next());
		}
		OpenBrowser.openURL(recipe.getURL());
	}
	
	@Override
	public RecipeVo cookWithRecipe(RecipeBookVo recipeBookVo) throws Exception{
		Iterator<String> recipe = recipeBookVo.getRecipe().keySet().iterator();
		int i = 0;
		while(recipe.hasNext()) {
			System.out.println((++i) + recipe.next());
		}
		
		if(i == 0) {
			throw new NoRecipePossibleException("You have No Recipes to Cook!");
		}
		System.out.println("You have " + i + " Recipe(s) possible.");
		System.out.print(">> Insert Recipe Name Correctly-> ");
		String name = sc.nextLine();
		
		return recipeBookVo.searchByName(name);
				
	}
	

}
