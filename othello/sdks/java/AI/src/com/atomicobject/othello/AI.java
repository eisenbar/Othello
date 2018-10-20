package com.atomicobject.othello;

import java.util.Arrays;
import java.util.ListIterator;

public class AI {
	
	ListIterator<int[]> moveList;

	public AI(int[][] moves) {
		moveList = Arrays.asList(moves).listIterator();
	}

	public int[] computeMove(GameState state) {
		System.out.println("AI returning canned move for game state - " + state);

		//AI

		return moveList.next();
	}

}

class Coordinates{
    protected int x, y;

    public Coordinates(){
        this.x = -1;
        this.y = -1;
    }

    public Coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }
}
