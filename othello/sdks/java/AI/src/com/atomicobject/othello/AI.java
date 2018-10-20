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

        assignValues(moves, state.getBoard());


        //TODO: Select best move for player (high/low)
        return moveList.next();
    }

    private ArrayList<Coordinate> possibleMoves(int[][] board) {

        ArrayList<Coordinate> moves = new ArrayList<Coordinate>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {

                //Checks to see if the spot is a valid spot
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


    private int hasPerpandicular(int[] arry, int startValue, int direction, int player) {

        int counter = 0;
        boolean hasSame = false;
        //if the direction is right or down
        if (direction == 1) {

            for (int i = startValue; i < arry.length; i++) {


                if (arry[i] != player) {
                    counter++;
                } else {
                    hasSame = true;
                    break;
                }
            }
        }

        //if the direction is left or up
        else {
            for (int i = startValue; i >= 0; i--) {
                if (arry[i] != player) {
                    counter++;
                } else {
                    hasSame = true;
                    break;
                }
            }
        }


        if (hasSame)
            return counter;

        //returns 0 if there is no endpoint
        return 0;
    }


    public void assignValues(ArrayList<Coordinate> moves, int[][] board) {

        int playerValue = playerOne? 1:-1;

        for (Coordinate c : moves) {
            //check possible perpendicular values

            //checks to the right
            int rightPoints = hasPerpandicular(board[c.x], c.x, 1, playerValue);

            //checks to the left
            int leftPoints = hasPerpandicular(board[c.x], c.x, -1, playerValue);

            //checks down
            int downPoints = hasPerpandicular(board[c.y], c.y, 1, playerValue);

            //checks up
            int upPoints = hasPerpandicular(board[c.y], c.y, -1, playerValue);


            //check Diagonal


            //assign to c
            int[] values = {rightPoints, leftPoints, downPoints, upPoints};
            c.value = maxValue(values);
        }
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

    private int maxValue(int[] values){
        int max = 0;

        for(int i = 0; i < values.length; i++){
            if(values[i] > max)
                max = values[i];
        }

        return max;
    }

}

class Coordinate {
    protected int x, y;

    protected int value;

    public Coordinate() {
        this.x = -1;
        this.y = -1;
        this.value = 0;
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
        this.value = 0;
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

