package com.atomicobject.othello;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;

public class AI {

    ListIterator<int[]> moveList;

    private boolean playerOne;

    public AI(int[][] moves) {
        moveList = Arrays.asList(moves).listIterator();
    }

    public int[] computeMove(GameState state) {

        playerOne = state.getPlayer() == 1 ? true : false;


        System.out.println("AI returning canned move for game state - " + state);

        //AI
        ArrayList<Coordinate> moves = possibleMoves(state.getBoard());

        //TODO: Select best move for player (high/low)
        return moveList.next();
    }

    private ArrayList<Coordinate> possibleMoves(int[][] board) {

        ArrayList<Coordinate> moves = new ArrayList<Coordinate>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                //Checks to see if the spot is a
                if (board[i][j] == 1 && playerOne || board[i][j] == 2 && !playerOne) {

                    boolean nLeft = false, nRight = false, nTop = false, nBottom = false;
                    //Makes sure spot doesn't try to add an out of bounds piece

                    //checks perpendicular moves
                    if (i != 0 && board[i - 1][j] == 0) {
                        moves.add(new Coordinate(i - 1, j));
                        nLeft = true;
                    }

                    if (i != board.length - 1 && board[i + 1][j] == 0) {
                        moves.add(new Coordinate((i + 1), j));
                        nRight = true;
                    }

                    if (j != 0 && board[i][j - 1] == 0) {
                        moves.add(new Coordinate(i, j - 1));
                        nTop = true;
                    }

                    if (j != board[i].length - 1 && board[i][j + 1] == 0) {
                        moves.add(new Coordinate(i, j + 1));
                        nBottom = true;
                    }

                    //Checks diagonal moves
                    if (nLeft && nTop && board[i - 1][j - 1] == 0)
                        moves.add(new Coordinate(i - 1, j - 1));
                    
                    if (nLeft && nBottom && board[i - 1][j + 1] == 0)
                        moves.add(new Coordinate(i - 1, j + 1));
                    
                    if (nRight && nTop && board[i + 1][j - 1] == 0)
                        moves.add(new Coordinate(i + 1, j - 1));
                    
                    if (nRight && nBottom && board[i + 1][j + 1] == 0)
                        moves.add(new Coordinate(i + 1, j + 1));
                }
            }
        }

        return moves;
    }

    public int[] returnBestPosition(int[][] board) {
        int best = 0;
        int[] position = new int[2];
        position[0] = 0;
        position[1] = 0;

        //call possible moves function
        ArrayList<Coordinate> possible = possibleMoves(board);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (possible[i][j] > best)
                    best = possible[i][j];
                position[0] = i;
                position[1] = j;
            }
        }

        return position;
    }

}

class Coordinate {
    protected int x, y;

    public Coordinate() {
        this.x = -1;
        this.y = -1;
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int[] getSpot() {
        int[] rtn = {x, y};
        return rtn;
    }

    //Call on these to change coordinates
    public void moveUp() {
        this.y--;
    }

    public void moveDown() {
        this.y++;
    }

    public void moveLeft() {
        this.x--;
    }

    public void moveRight() {
        this.x++;
    }

    public void moveUpRight() {
        this.y--;
        this.x++;
    }

    public void moveUpLeft() {
        this.y--;
        this.x--;
    }

    public void moveDownLeft() {
        this.y++;
        this.x++;
    }

    public void moveDownRight() {
        this.y++;
        this.x--;
    }
}

