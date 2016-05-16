package com.mino.vo;

import java.util.TreeMap;

public class UserVo {
	private String id;
	private String first_name;
	private String last_name;
	private int gender;
	private int livingStyle;
	private TreeMap<String, IngredientVo> items;
	
	public UserVo(String id, String first_name, String last_name, int gender, int livingStyle) {
		super();
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.gender = gender;
		this.livingStyle = livingStyle;
		items = new TreeMap<>();
	}

	public String getId() {
		return id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public int getGender() {
		return gender;
	}

	public int getLivingStyle() {
		return livingStyle;
	}

	public void addItem(IngredientVo ingredientVo) {
		items.put(ingredientVo.getName(), ingredientVo);
	}
	
	public void deleteItem(String name) {
		items.remove(name);
	}
	
	public TreeMap<String, IngredientVo> getItems() {
		return items;
	}
	
	public boolean hasIngredient(String name) {
		if(items.containsKey(name))
			return true;
		return false;
	}
	
}
