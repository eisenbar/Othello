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

        playerOne = state.getPlayer() == 1;


        System.out.println("AI returning canned move for game state - " + state);

        //AI
        ArrayList<Coordinate> moves = possibleMoves(state.getBoard());

        assignValues(moves, state.getBoard());


        //TODO: Select best move for player (high/low)
        int[] bestMove = new int[2];
        bestMove[0] = -1;
        bestMove[1] = -1;
        bestMove = getBestPosition(moves);

        //for testing purposes
        System.out.println("THIS IS THE BEST MOVE: " + bestMove);


       int[][] newBoard = state.getBoard();

        if(playerOne){
            newBoard[bestMove[0]][bestMove[1]] = 1;
        }
        else{
            newBoard[bestMove[0]][bestMove[1]] = 2;
        }

        moveList = Arrays.asList(newBoard).listIterator();

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
                        moves.add(new Coordinate(i - 1, j, 0));
                        nLeft = true;
                    }

                    if (i != board.length - 1 && board[i + 1][j] == 0) {
                        moves.add(new Coordinate((i + 1), j, 1));
                        nRight = true;
                    }


                    if (j != board[i].length - 1 && board[i][j + 1] == 0) {
                        moves.add(new Coordinate(i, j + 1, 2));
                        nBottom = true;
                    }

                    if (j != 0 && board[i][j - 1] == 0) {
                        moves.add(new Coordinate(i, j - 1, 3));
                        nTop = true;
                    }

                    //Checks diagonal moves
                    if (nLeft && nTop && board[i - 1][j - 1] == 0)
                        moves.add(new Coordinate(i - 1, j - 1, 4));

                    if (nLeft && nBottom && board[i - 1][j + 1] == 0)
                        moves.add(new Coordinate(i - 1, j + 1, 5));

                    if (nRight && nTop && board[i + 1][j - 1] == 0)
                        moves.add(new Coordinate(i + 1, j - 1, 6));

                    if (nRight && nBottom && board[i + 1][j + 1] == 0)
                        moves.add(new Coordinate(i + 1, j + 1, 7));
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

        int playerValue = playerOne ? 1 : -1;

        for (Coordinate c : moves) {
            //check possible perpendicular values
            int rightPoints = -1;
            int leftPoints = -1;
            int downPoints = -1;
            int upPoints = -1;

            int value = 0;

            switch (c.direction) {

                //checks to the right
                case 0:
                    value = hasPerpandicular(board[c.x], c.x, 1, playerValue);
                    break;

                //checks to the left
                case 1:
                    value = hasPerpandicular(board[c.x], c.x, -1, playerValue);
                    break;

                //checks down
                case 2:
                    value = hasPerpandicular(board[c.y], c.y, 1, playerValue);
                    break;

                //checks up
                case 3:
                    value = hasPerpandicular(board[c.y], c.y, -1, playerValue);
                    break;

                //check Diagonal
                /*int upLeft;
                int downLeft;
                int upRight;
                int downRight;
                */
            }
            //assign to c

            c.value = value;


        }
    }


    public int[] getBestPosition(ArrayList<Coordinate> board) {
        int best = 0;
        int[] position = new int[2];

        //iterate through given coordinates
        for (int i = 0; i < board.size(); i++) {
            if (board.get(i).value > best)
                best = board.get(i).value;
            position[0] = board.get(i).x;
            position[1] = board.get(i).y;
        }

        return position;
    }

    private int maxValue(int[] values) {
        int max = 0;

        for (int i = 0; i < values.length; i++) {
            if (values[i] > max)
                max = values[i];
        }

        return max;
    }

}

class Coordinate {
    protected int x, y;

    protected int value;

    //right -> 0, left -> 1; down -> 2; up -> 3; upLeft = 4; downLeft = 5; upRight = 6; downRight = 7;
    int direction;

    public Coordinate() {
        this.x = -1;
        this.y = -1;
        this.value = 0;
    }

    public Coordinate(int x, int y, int direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
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

