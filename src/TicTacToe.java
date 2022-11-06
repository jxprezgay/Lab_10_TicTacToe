

import java.util.Scanner;

public class TicTacToe {


    public static final int ROW = 3;

    private static final int COL = 3;

    public static final String[][] board = new String[ROW][COL];

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        boolean finished = false;
        boolean playing = true;
        String player = "X";
        int moveCount = 0; //after 5 check for win, tie after 7
        int row = -1;
        int col = -1;
        final int MOVES_FOR_WIN = 5;
        final int MOVES_FOR_TIE = 7;
        //if input is 1,3 then array is 0,2
        //at end toggle from x to o
        //move is single move by single player
        //TIE if an x and an o in the same vector



        do {// program loop
            //begin a game
            player = "X";
            playing = true;
            moveCount = 0;
            clearBoard();
            do {//game loop
                do { //get the move
                    display();
                    System.out.println("Enter move for " + player);
                    row = SafeInput.getRangedInt(in, "Enter row",1,3);
                    col = SafeInput.getRangedInt(in,"Enter a col",1,3);
                    row--; col--;
                } while (!(isValidMove(row,col)));
                board[row][col] = player;
                moveCount++;
                if (moveCount >= MOVES_FOR_WIN){
                    if (isWin(player)){
                        display();
                        System.out.println("Player " + player + " wins!");
                        playing=false;
                    }
                }
                if (moveCount >= MOVES_FOR_TIE){
                    if (isTie()){
                        display();
                        System.out.println("It's a tie!");
                        playing = false;
                    }
                }
                if (player.equals("X")){
                    player = "O";
                } else {
                    player = "X";
                }
            } while (playing);
            finished = SafeInput.getYNConfirm(in, "Are you done playing?");
        } while (!finished);
    }
    public static void clearBoard(){
        for (int row = 0; row<ROW;row++){
            for (int col=0;col<COL;col++){
                board[row][col]= " ";
            }
        }
    }
    private static void display(){
        for (int row = 0;row<ROW;row++){
            System.out.print("| ");
            for (int col=0; col<COL; col++){
                System.out.print(board[row][col] +" | ");
            }
            System.out.println();
        }
    }


    public static boolean isValidMove(int row, int col)//returns true if there is a space at the given coords
    {
        boolean retVal = false;
        if (board[row][col].equals(" "))
            retVal = true;
        return retVal;
    }
    private static boolean isWin(String player){
        if (isColWin(player) || isRowWin(player) || isDiagonalWin(player)){
            return true;
        }
        return false;
    }
    private static boolean isRowWin(String player){
        for (int row = 0; row<ROW;row++){
            if(board[row][0].equals(player) &&
                    board[row][1].equals(player) &&
                    board[row][2].equals(player))
            {
                return true;
            }
        }
        return false;
    }
    private static boolean isColWin(String player){
        for (int col = 0; col<COL;col++){
            if(board[0][col].equals(player) &&
                    board[1][col].equals(player) &&
                    board[2][col].equals(player))
            {
                return true;
            }
        }
        return false;
    }
    private static boolean isDiagonalWin(String player){
        if(board[0][2].equals(player) &&
                board[1][1].equals(player) &&
                board[2][0].equals(player))
        {
            return true;
        }
        if(board[0][0].equals(player) &&
                board[1][1].equals(player) &&
                board[2][2].equals(player))
        {
            return true;
        }
        return false;
    }
    private static boolean isTie(){
        boolean xFlag = false;
        boolean oFlag = false;
        for (int row=0; row < ROW; row++){
            if (board[row][0].equals("X") ||
                    board[row][1].equals("X") ||
                    board[row][2].equals("X")){
                xFlag = true;
            }
            if (board[row][0].equals("O") ||
                    board[row][1].equals("O") ||
                    board[row][2].equals("O")){
                oFlag = true;
            }
            if (!(xFlag && oFlag)){
                return false;
            }
            xFlag = oFlag = false;
        }
        for (int col=0; col < ROW; col++) {
            if (board[0][col].equals("X") ||
                    board[1][col].equals("X") ||
                    board[2][col].equals("X")) {
                xFlag = true;
            }
            if (board[0][col].equals("O") ||
                    board[1][col].equals("O") ||
                    board[2][col].equals("O")) {
                oFlag = true;
            }
            if (!(xFlag && oFlag)) {
                return false;
            }
        }
        xFlag = oFlag = false;

        if (board[0][0].equals("X") ||
                board[1][1].equals("X") ||
                board[2][2].equals("X")){
            xFlag = true;
        }
        if (board[0][0].equals("O") ||
                board[1][1].equals("O") ||
                board[2][2].equals("O")){
            oFlag = true;
        }
        if (!(xFlag && oFlag)){
            return false;
        }
        if (board[2][0].equals("X") ||
                board[1][1].equals("X") ||
                board[0][2].equals("X")){
            xFlag = true;
        }
        if (board[2][0].equals("O") ||
                board[1][1].equals("O") ||
                board[2][0].equals("O")){
            oFlag = true;
        }
        if (!(xFlag && oFlag)) {
            return false;
        }
        return true;
    }
}