package com.atomicobject.othello;

import java.util.Arrays;
import java.util.ListIterator;

public class AI {
	
	ListIterator<int[]> moveList;

	public AI(int[][] moves) {
		moveList = Arrays.asList(moves).listIterator();
	}

	 public int[] computeMove(GameState state) {

        playerOne = state.getPlayer() == 1 ? true : false;


        System.out.println("AI returning canned move for game state - " + state);

        //AI
        ArrayList<Coordinate> moves = possibleMoves(state.getBoard());

        return moveList.next();
    }

    public ArrayList<Coordinate> possibleMoves(int[][] board) {

        ArrayList<Coordinate> moves = new ArrayList<Coordinate>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 1) {
                    if (playerOne) {

                        //Checks to see if the spot is open
                        if (board[i][j] == 0) {

                            //TODO: Add a method that determins if a friendly piece is adjacent

                            //Makes sure spot doesn't try to add an out of bounds piece
                            if (i != 0)
                                moves.add(new Coordinate(i - 1, j));

                            if (i != board.length - 1)
                                moves.add(new Coordinate((i + 1), j));

                            if (j != 0)
                                moves.add(new Coordinate(i, j - 1));

                            if (j != board[i].length - 1)
                                moves.add(new Coordinate(i, j + 1));

                        }
                    }
                }
            }
        }

        return moves;
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
