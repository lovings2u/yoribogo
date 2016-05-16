package com.mino.test;

import com.mino.controller.YoriController;
import com.mino.entity.DatabaseConnection;

public class Test {
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		YoriController yoriController = new YoriController("newSource.json", "recipe.json", "user.json", "scsc");
		long middleTime = System.currentTimeMillis();
		System.out.println("Initialize Time: " + (middleTime - startTime) + "ms");
		yoriController.execute();
		long endTime = System.currentTimeMillis();
		System.out.println("Total Time: " + (endTime - middleTime) + "ms");
		DatabaseConnection.close();
		
	}
}
