//AP CS
//Simple two player game with key and mouse events


import javax.swing.*;
import java.awt.*;

public class ClickerGame extends JComponentWithEvents{


    private int width = getWidth();
    private int height = getHeight();

    //set up circle's coordinates
    private int cx = width/2;
    private int cy = height/2;
    private int r = 20;
    private int speed = 40;
    private boolean gameOver = false;
    private int timer = 10;
    private String winner;
    
    private static Color cirColor = Color.green;

    public void start(){
        setTimerDelay(1000);
        gameOver = false;
        cx = width/2;
        cy = height/2;
    }

    public void timerFired(){
        if(!gameOver){
            timer -= 1;
            if(timer == 0){
                gameOver = true;
                winner = "Circle Mover!";
            }
        }
    }

    

    public void keyPressed(char key){
    if(key == LEFT){
        moveCircle(-speed, 0);
    }
    if(key == RIGHT){
        moveCircle(speed, 0);
    }
    if(key == UP){
        moveCircle(0, -speed);
    }
    if(key == DOWN){
        moveCircle(0, speed);
    }
    }

    public void moveCircle(int dx, int dy){
        int oldCx = cx;
        int oldCy = cy;
        cx += dx;
        cy += dy;
        int left = cx - r;
        int right = cx + r;
        int top = cy - r;
        int bottom = cy + r;
        if(left < 0 || right > width || top < 0 || bottom > height){
            cx = oldCx;
            cy = oldCy;
        }
    }

    public void mousePressed(int x, int y){
        if(contains(x,y)){
            gameOver = true;
            winner = "Rick";
        }
    }
    
    public boolean contains(int x, int y){
        int dx = x - cx;
        int dy = y - cy;
        double distance = Math.sqrt(Math.pow(dx,2) + Math.pow(dy, 2));
        return distance <= r;

    }


        

    public static void main(String[] args) { launch(400, 400);}




}
