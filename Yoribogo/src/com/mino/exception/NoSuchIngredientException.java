package com.mino.exception;

public class NoSuchIngredientException extends Exception{
	public NoSuchIngredientException() {
		
	}
	public NoSuchIngredientException(String msg) {
		super(msg);
	}
}
