//Wendy Zhu
//2048

import java.awt.*;
import javax.swing.*;
import java.util.*;

//in this version, the way I move numbers is different
public class projectVersion2 extends JComponentWithEvents {
  
  
  private int rows = 4;
  private int cols = 4;
  private int width = getWidth();
  private int height = getHeight()-100;
  private int score=0;
  private int possibleMove=0;
  private boolean placeTwo=true;
  private boolean gameOver=false;
  private static int[][] NUMBER_BOARD={
    {0,0,0,0},
    {0,0,0,0},
    {0,0,0,0},
    {0,0,0,0}
  };
  
  
  public void timerFired(){
    if(!gameOver){
      placeTwo();
    }
  }
  
  public void reset(){
    gameOver=false;
    score=0;
  }
  
  public void placeTwo(){
    Random random = new Random();
    while(placeTwo){
      int i= random.nextInt(NUMBER_BOARD.length);
      int j= random.nextInt(NUMBER_BOARD[0].length);
      if(NUMBER_BOARD[i][j]==0){
        NUMBER_BOARD[i][j]=2;
        placeTwo=false;
      }
    }
  }

  //checks if there are any possible movement
  // if there is no possible movement, the game will be over
  public void gameOver(){
    for (int row=0; row<rows; row++){
      for (int col=0; col<cols; col++){
        int number=NUMBER_BOARD[row][col];
        if(NUMBER_BOARD[row][col]==0){
          possibleMove++;
        }else if(row==0){
          if(col==0){
            if(NUMBER_BOARD[row+1][col]==number
                 || NUMBER_BOARD[row][col+1]==number){
              possibleMove++;
            }
          }else if(col==cols-1){
            if(NUMBER_BOARD[row+1][col]==number
                 || NUMBER_BOARD[row][col-1]==number){
              possibleMove++;
            }
          }else if(NUMBER_BOARD[row+1][col]==number
                     || NUMBER_BOARD[row][col+1]==number
                     || NUMBER_BOARD[row][col-1]==number){
            possibleMove++;
          }
        }else if(row==rows-1){
          if(col==0){
            if(NUMBER_BOARD[row-1][col]==number
                 || NUMBER_BOARD[row][col+1]==number){
              possibleMove++;
            }
          }else if(col==cols-1){
            if(NUMBER_BOARD[row-1][col]==number
                 || NUMBER_BOARD[row][col-1]==number){
              possibleMove++;
            }
          }else if( NUMBER_BOARD[row-1][col]==number
                     || NUMBER_BOARD[row][col+1]==number
                     || NUMBER_BOARD[row][col-1]==number){
            possibleMove++;
          }
        }else if(col==0){
          if(NUMBER_BOARD[row+1][col]==number
               || NUMBER_BOARD[row-1][col]==number
               || NUMBER_BOARD[row][col+1]==number){
            possibleMove++;
          }
        }else if(col==cols-1){
          if(NUMBER_BOARD[row+1][col]==number
               || NUMBER_BOARD[row-1][col]==number
               || NUMBER_BOARD[row][col-1]==number){
            possibleMove++;
          }
        }else if(NUMBER_BOARD[row+1][col]==number
                   || NUMBER_BOARD[row-1][col]==number
                   || NUMBER_BOARD[row][col+1]==number
                   || NUMBER_BOARD[row][col-1]==number){
          possibleMove++;
        }
      }
    }
    if(possibleMove==0){
      gameOver=true;
    }
  }
  
  
  
  //I used a boolean methods to check if the number will be off board 
  //or there is an another number in the way when moving the number
  public boolean movingIsLegal(int [][] board, int row, int col){
    if( row<0 || row>=rows || col<0 || col>=cols || board[row][col]!=0){
      return false;
    }else{
      return true;
    }
  }
  
  public void moveUp(){
    for (int row=0; row<rows; row++){
      for (int col=0; col<cols; col++){
        int step=1;
        int number=NUMBER_BOARD[row][col];
        while(NUMBER_BOARD[row][col]!=0 && movingIsLegal(NUMBER_BOARD,row-step,col)){
          step++;
        }
        step--;
        NUMBER_BOARD[row-step][col]=number;
        NUMBER_BOARD[row][col]=0;
        if(step==0){
          NUMBER_BOARD[row][col]=number;
        }
      }
    }
    for (int row=1; row<rows; row++){
      for (int col=0; col<cols; col++){
        int number=NUMBER_BOARD[row][col];
        if(NUMBER_BOARD[row][col]!=0 && NUMBER_BOARD[row-1][col]==number){
          NUMBER_BOARD[row-1][col]=2*number;
          NUMBER_BOARD[row][col]=0;
          score = score+2*number;
        }
      }
    }
    for (int row=0; row<rows; row++){
      for (int col=0; col<cols; col++){
        int step=1;
        int number=NUMBER_BOARD[row][col];
        while(NUMBER_BOARD[row][col]!=0 && movingIsLegal(NUMBER_BOARD,row-step,col)){
          step++;
        }
        step--;
        NUMBER_BOARD[row-step][col]=number;
        NUMBER_BOARD[row][col]=0;
        if(step==0){
          NUMBER_BOARD[row][col]=number;
        }
      }
    }
  }
  
  public void moveDown(){
    for (int row=rows-1; row>=0; row--){
      for (int col=cols-1; col>=0; col--){
        int step=1;
        int number=NUMBER_BOARD[row][col];
        while(NUMBER_BOARD[row][col]!=0 && movingIsLegal(NUMBER_BOARD,row+step,col)){
          step++;
        }
        step--;
        NUMBER_BOARD[row+step][col]=number;
        NUMBER_BOARD[row][col]=0;
        if(step==0){
          NUMBER_BOARD[row][col]=number;
        }
      }
    }
    for (int row=rows-2; row>-1; row--){
      for (int col=cols-1; col>-1; col--){
        int number=NUMBER_BOARD[row][col];
        if(NUMBER_BOARD[row][col]!=0 && NUMBER_BOARD[row+1][col]==number){
          NUMBER_BOARD[row+1][col]=2*number;
          NUMBER_BOARD[row][col]=0;
          score = score+2*number;
        }
      }
    }
    for (int row=rows-1; row>=0; row--){
      for (int col=cols-1; col>=0; col--){
        int step=1;
        int number=NUMBER_BOARD[row][col];
        while(NUMBER_BOARD[row][col]!=0 && movingIsLegal(NUMBER_BOARD,row+step,col)){
          step++;
        }
        step--;
        NUMBER_BOARD[row+step][col]=number;
        NUMBER_BOARD[row][col]=0;
        if(step==0){
          NUMBER_BOARD[row][col]=number;
        }
      }
    }
  }
  
  public void moveLeft(){
    for (int row=0; row<rows; row++){
      for (int col=0; col<cols; col++){
        int step=1;
        int number=NUMBER_BOARD[row][col];
        while(NUMBER_BOARD[row][col]!=0 && movingIsLegal(NUMBER_BOARD,row,col-step)){
          step++;
        }
        step--;
        NUMBER_BOARD[row][col-step]=number;
        NUMBER_BOARD[row][col]=0;
        if(step==0){
          NUMBER_BOARD[row][col]=number;
        }
      }
    }
    for (int row=0; row<rows; row++){
      for (int col=1; col<cols; col++){
        int number=NUMBER_BOARD[row][col];
        if(NUMBER_BOARD[row][col]!=0 && NUMBER_BOARD[row][col-1]==number){
          NUMBER_BOARD[row][col-1]=2*number;
          NUMBER_BOARD[row][col]=0;
          score = score+2*number;
        }
      }
    }
    for (int row=0; row<rows; row++){
      for (int col=0; col<cols; col++){
        int step=1;
        int number=NUMBER_BOARD[row][col];
        while(NUMBER_BOARD[row][col]!=0 && movingIsLegal(NUMBER_BOARD,row,col-step)){
          step++;
        }
        step--;
        NUMBER_BOARD[row][col-step]=number;
        NUMBER_BOARD[row][col]=0;
        if(step==0){
          NUMBER_BOARD[row][col]=number;
        }
      }
    }
  }
  
  public void moveRight(){
    for (int row=rows-1; row>=0; row--){
      for (int col=cols-1; col>=0; col--){
        int step=1;
        int number=NUMBER_BOARD[row][col];
        while(NUMBER_BOARD[row][col]!=0 && movingIsLegal(NUMBER_BOARD,row,col+step)){
          step++;
        }
        step--;
        NUMBER_BOARD[row][col+step]=number;
        NUMBER_BOARD[row][col]=0;
        if(step==0){
          NUMBER_BOARD[row][col]=number;
        }
      }
    }
    for (int row=rows-1; row>-1; row--){
      for (int col=cols-2; col>-1; col--){
        int number=NUMBER_BOARD[row][col];
        if(NUMBER_BOARD[row][col]!=0 && NUMBER_BOARD[row][col+1]==number){
          NUMBER_BOARD[row][col+1]=2*number;
          NUMBER_BOARD[row][col]=0;
          score = score+2*number;
        }
      }
    }
    for (int row=rows-1; row>=0; row--){
      for (int col=cols-1; col>=0; col--){
        int step=1;
        int number=NUMBER_BOARD[row][col];
        while(NUMBER_BOARD[row][col]!=0 && movingIsLegal(NUMBER_BOARD,row,col+step)){
          step++;
        }
        step--;
        NUMBER_BOARD[row][col+step]=number;
        NUMBER_BOARD[row][col]=0;
        if(step==0){
          NUMBER_BOARD[row][col]=number;
        }
      }
    }
  }
  
  public void keyPressed(char key){
    if(!gameOver){
      if(key==UP){
        gameOver();
        moveUp();
        placeTwo=true;
      }
      else if(key==DOWN){
        gameOver();
        moveDown();
        placeTwo=true;
      }
      else if(key==LEFT){
        gameOver();
        moveLeft();
        placeTwo=true;
      }
      else if(key==RIGHT){
        gameOver();
        moveRight();
        placeTwo=true;
      }
    }else if(key==SPACE){
      reset();
    }
    
  }
  
  
  
  
  public void paintBoard(Graphics2D page, int row, int col) {
    int left = col * width / cols;
    int right = (col + 1) * width / cols; 
    int top  = row * height / rows;
    int bottom = (row + 1) * height / rows;
    page.setColor(Color.black);
    page.drawRect(left, top, right-left, bottom-top);
  }
  
  public void paintNumber(Graphics2D page,int row, int col){
    int centerY=row * height / rows + height / (2*rows);
    int centerX=col * width / cols + width / (2*cols);
    int number=NUMBER_BOARD[row][col];
    if(number!=0){
      page.setColor(Color.white);
      if(number<10){
        page.setFont(new Font("Ariel", Font.BOLD, 50));
        page.drawString(""+number, centerX-18, centerY+18);
      } else if(number>9&&number<100){
        page.setFont(new Font("Ariel", Font.BOLD, 45));
        page.drawString(""+number, centerX-28, centerY+17);
      } else if(number>99&&number<1000){
        page.setFont(new Font("Ariel", Font.BOLD, 40));
        page.drawString(""+number, centerX-38, centerY+16);
      }else if(number>999){
        page.setFont(new Font("Ariel", Font.BOLD, 35));
        page.drawString(""+number, centerX-45, centerY+15);
      }
    }
  }
  
  public void paintColor(Graphics2D page,int row, int col){
    int left = col * width / cols;
    int right = (col + 1) * width / cols; 
    int top  = row * height / rows;
    int bottom = (row + 1) * height / rows;
    int number=NUMBER_BOARD[row][col];
    if(number==2){
      page.setColor(Color.pink);
      page.fillRect(left, top, right-left, bottom-top);
    }else if(number==4){
      page.setColor(Color.orange);
      page.fillRect(left, top, right-left, bottom-top);
    }else if(number==8){
      page.setColor(Color.magenta);
      page.fillRect(left, top, right-left, bottom-top);
    }else if(number==16){
      page.setColor(Color.cyan);
      page.fillRect(left, top, right-left, bottom-top);
    }else if(number==32){
      page.setColor(Color.green);
      page.fillRect(left, top, right-left, bottom-top);
    }else if(number==64){
      page.setColor(Color.red);
      page.fillRect(left, top, right-left, bottom-top);
    }else if(number==128){
      page.setColor(Color.blue);
      page.fillRect(left, top, right-left, bottom-top);
    }else if(number==256){
      page.setColor(Color.gray);
      page.fillRect(left, top, right-left, bottom-top);
    }else if(number==512){
      page.setColor(Color.darkGray);
      page.fillRect(left, top, right-left, bottom-top);
    }else if(number==1024){
      page.setColor(Color.black);
      page.fillRect(left, top, right-left, bottom-top);
    }else if(number==2048){
      page.setColor(Color.pink);
      page.fillRect(left, top, right-left, bottom-top);
    }
  }
  public void paint(Graphics2D page){
    page.setColor(Color.lightGray);
    page.fillRect(0,0,getWidth(),getHeight());
    for (int row=0; row<rows; row++){
      for (int col=0; col<cols; col++){
        paintBoard(page,row,col); 
        paintColor(page,row,col);
        paintNumber(page,row,col);
        
      }
    }
    page.setColor(Color.black);
    page.setFont(new Font("Ariel", Font.BOLD, 20));
    page.drawString("SCORE",70,445);
    page.drawString(""+score,80,470);
    
    if(gameOver){
      page.setFont(new Font("Ariel", Font.BOLD, 100));
      page.drawString("GAME OVER",200,300);
    }
  }
  
  public static void main(String[] args) { launch(400, 500);}
}