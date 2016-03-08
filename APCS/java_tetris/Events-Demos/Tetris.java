//Tara Stentz tstentz Tetris

import javax.swing.*;
import java.awt.*;
import java.util.Random;



public class Tetris extends JComponentWithEvents{

    //instance variables
    private int width = getWidth();
    private int height = getHeight();
    private static int rows = 15;
    private static int cols = 10;
    private static int boxSize = 35;
    private static  int margin = 25;
    private static Color emptyColor = Color.pink;
    private static Color background = Color.green;
    private static Color[][] board;
    private static boolean gameOver;
    private static Random random = new Random();
    private static int score;

    //falling piece info
    private static boolean[][] fallingPiece;
    private static Color fallingPieceColor;
    private static int fallingPieceRow;
    private static int fallingPieceCol;
    private static int fallingPieceRows;
    private static int fallingPieceCols;

    //set up pieces
    private static final boolean[][] S_PIECE = {
            {false, true, true},
            {true, true, false}
    };
    private static final boolean[][] I_PIECE = {
    { true,  true,  true,  true}
    };
    private static final boolean[][] J_PIECE = {
    { true, false, false },
    { true, true,  true}
    };
    private static final boolean[][] L_PIECE = {
    { false, false, true},
    { true,  true,  true}
    };
    private static final boolean[][] O_PIECE = {
    { true, true},
    { true, true}
    };
    private static final boolean[][] T_PIECE = {
    { false, true, false },
    { true,  true, true}
    };
    private static final boolean[][] Z_PIECE = {
    { true,  true, false },
    { false, true, true}
    };

    //colors for pieces
    private static Color[] TETRIS_PIECE_COLORS = {
    Color.red, Color.yellow, Color.magenta, Color.blue,
    Color.cyan, Color.green, Color.orange
    };

    //piece array
    private static boolean[][][] TETRIS_PIECES = {
    I_PIECE, J_PIECE, L_PIECE, O_PIECE, S_PIECE, T_PIECE, Z_PIECE
    };


    //start method
    public void start(){
        gameOver = false;
        score = 0;
        fillBoard();
        newFallingPiece();
    }

    //fills the board with the empty color, which is pink
    public static void fillBoard(){
        board = new Color[rows][cols];
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                board[i][j] = emptyColor;
            }
        }
    }


    //overwrite framework

    //key pressed method
    public void keyPressed(char key) {
    
    if(key == LEFT){
        moveFallingPiece(0, -1);
    }
    if(key == RIGHT){
        moveFallingPiece(0, 1);
    }
    if(key == DOWN){
        moveFallingPiece(1, 0);
    }
    if(key == UP){
        rotateFallingPiece();
    }
    if(key == 'r'){
        start();
    }
    }

    //timer fired method
    public void timerFired(){

        if(!moveFallingPiece(1, 0)){
            placeFallingPiece();
            removeFullRows();
            newFallingPiece();
            if(!fallingPieceIsLegal()){
                gameOver = true;
            }
        };
    }

    
    //functions to manipulate the falling piece

    //rotates the falling piece
    public static void rotateFallingPiece(){
        boolean[][] oldFallingPiece = fallingPiece;
        int oldRows = fallingPieceRows;
        int oldCols = fallingPieceCols;
        int oldFallingPieceRow = fallingPieceRow;
        int oldFallingPieceCol = fallingPieceCol;
        int oldCenterRow = fallingPieceRow + fallingPieceRows/2;
        int oldCenterCol = fallingPieceCol + fallingPieceCols/2;
        boolean[][] newPiece = new boolean[oldCols][oldRows];
        for (int row = 0; row < oldRows; row++){
            for (int col = 0; col < oldCols; col++){
                newPiece[oldCols-1-col][row] = oldFallingPiece[row][col];
            }
        }

        fallingPiece = newPiece;
        fallingPieceRows = oldCols;
        fallingPieceCols = oldRows;

        //make sure the position is legal, otherwise, unrotate it
        if(!fallingPieceIsLegal()){
            fallingPieceRows = oldRows;
            fallingPieceCols = oldCols;
            fallingPiece = oldFallingPiece;
        }
    }

    //checks and sees if the placement of the piece is legal
    public static boolean fallingPieceIsLegal(){
        int currRow;
        int currCol;
        for (int i = 0; i < fallingPieceRows; i++){
            for (int j = 0; j < fallingPieceCols; j++){
                currRow = fallingPieceRow + i;
                currCol = fallingPieceCol + j;
                if(fallingPiece[i][j]){
                    //check and see if another piece is there
                    
                    if(currRow < 0 || currRow >= rows){
                        return false;
                    }
                    else if(currCol < 0 || currCol >= cols){
                        return false;
                    }
                    else if(board[currRow][currCol] != emptyColor){
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //moves falling piece, checks if move was legal, then unmoves piece if 
    //it was not
    public static boolean moveFallingPiece(int drow, int dcol){
        int oldRow = fallingPieceRow;
        int oldCol = fallingPieceCol;
        fallingPieceRow += drow;
        fallingPieceCol += dcol;
        if(!fallingPieceIsLegal()){
            fallingPieceRow = oldRow;
            fallingPieceCol = oldCol;
            return false;
        }
        return true;
    }

    //puts the falling piece on the board when it reaches the bottom
    public static void placeFallingPiece(){
        int currRow;
        int currCol;
        for (int i = 0; i < fallingPieceRows; i++){
            for (int j = 0; j < fallingPieceCols; j++){
                currRow = fallingPieceRow + i;
                currCol = fallingPieceCol + j;
                if(fallingPiece[i][j]){
                    //check and see if another piece is there
                    board[currRow][currCol] = fallingPieceColor;
                }
            }
        }
    }

    //board manipulation functions

    //helper function for removeFullRows, copies each cell of
    //a row back and returns true if the row is full
    public static boolean copyRow(int currRow, int newRow){
        boolean full = true;
        for (int col = 0; col < cols; col++){
            board[newRow][col] = board[currRow][col];
            if(board[currRow][col] == emptyColor){
                full = false;
            }
        }
        return full;
    }

    //removes the full rows and increments the score
    public static void removeFullRows(){
        int newRow = rows - 1;
        int fullRows = 0;
        for (int oldRow = rows - 1; oldRow >= 0; oldRow--){
            if(copyRow(oldRow, newRow)){
                fullRows++;
            }
            else{
                newRow--;
            }
        }
        score += fullRows*fullRows;
    }

    //paint methods

    //puts the falling piece on top of the board
    public static void paintFallingPiece(Graphics2D page){
        int startingX = fallingPieceCol*boxSize + margin;
        int startingY = fallingPieceRow*boxSize + margin;
        int rectMargin = 2;
        int smallSize = boxSize - 2*rectMargin;
        int currRow;
        int currCol;
        int x;
        int y;
        for (int i = 0; i < fallingPieceRows; i++){
            for (int j = 0; j < fallingPieceCols; j++){
                x = startingX + j*boxSize;
                y = startingY + i*boxSize;
                currRow = fallingPieceRow + i;
                currCol = fallingPieceCol + j;
                if(fallingPiece[i][j]){
                    page.setColor(fallingPieceColor);
                }
                else{
                    page.setColor(board[currRow][currCol]);
                }
                page.fillRect(x + rectMargin, y + rectMargin,
                    smallSize, smallSize);
            }
        }
    }

    //paints the 15x10 grid of pink
    public static void paintBoard(Graphics2D page){
        int startingX = margin;
        int startingY = margin;
        int rectMargin = 2;
        int smallSize = boxSize - 2*rectMargin;
        int x;
        int y;
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                x = startingX + j*boxSize;
                y = startingY + i*boxSize;
                page.setColor(Color.black);
                page.fillRect(x, y, boxSize, boxSize);
                page.setColor(board[i][j]);
                page.fillRect(x + rectMargin, y + rectMargin,
                    smallSize, smallSize);
            }
        }
    }


    //main paint method
    public void paint(Graphics2D page){
        page.setColor(background);
        page.fillRect(0,0,width,height);
        if(!gameOver){
            paintBoard(page);
            paintFallingPiece(page);
        }
        else{
            page.setColor(Color.black);
            page.setFont(new Font("SansSerif", Font.BOLD, 16));
            page.drawString("Game Over!", width/2 - margin, height/2);
        }
        page.setColor(Color.black);
        page.setFont(new Font("SansSerif", Font.BOLD, 16));
        page.drawString("Score: " + Integer.toString(score), width/2 - margin,
            height - margin/2);

    }

    //Standard main method
    public static void main(String[] args){ launch(400, 600);}


}
