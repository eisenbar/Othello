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
    
    //Call on these to change coordinates
    public void moveUp(){
    	this.y--;
    }
    
    public void moveDown(){
    	this.y++;
    }
    
    public void moveLeft(){
    	this.x--;
    }
    public void moveRight(){
    	this.x++;
    }
    public void moveUpRight(){
    	this.y--;
    	this.x++;
    }
    public void moveUpLeft(){
    	this.y--;
    	this.x--;
    }
    public void moveDownLeft(){
    	this.y++;
    	this.x++;
    }
    public void moveDownRight(){
    	this.y++;
    	this.x--;
    }
}
