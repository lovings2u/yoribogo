package com.mino.vo;

import com.mino.exception.WrongMeasureException;

public class IngredientVo {
	private String name;
	private Double quantity;
	private Measure measure;
	public IngredientVo(String name, String measure) throws Exception{
		this(name, 0.0, measure);
	}
	public IngredientVo(String name, Double quantity, String measure) throws Exception{
		super();
		this.name = name;
		this.quantity = quantity;
		switch(measure.toLowerCase()) {
		case "tsp":
			this.measure = Measure.TSP;
			break;
		case "g":
			this.measure = Measure.G;
			break;
		case "pcs":
			this.measure = Measure.PCS;
			break;
		case "slice":
			this.measure = Measure.SLICE;
			break;
		case "cup":
			this.measure = Measure.CUP;
			break;
		default :
			throw new WrongMeasureException("Wrong Measurement!!");
		}
	}
	
	public String getName() {
		return this.name;
	}
	public String getMeasure() {
		return measure.toString();
	}
	public double getQuantity() {
		return quantity;
	}
	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "IngredientsVo [name=" + name + ", quantity=" + quantity + ", measure=" + measure + "]";
	}
	
	
	
}
