package com.ucv.game;

public class Application {
	
	public static void main(String... args) {
		try {
			new TicTacToeGame().setVisible(true);
		} catch(Throwable t) {
			t.printStackTrace();
		}
	}

}
