//Tara Stentz
//tstentz
//Simple two player clicking game

import javax.swing.*;
import java.awt.*;


public class ClickGame extends JComponentWithEvents{
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
    
    private static Color cirColor = Color.pink;

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
        if(left < 0 || right > width){
            cx = oldCx;
            cy = oldCy;
        }
        if(top < 0 || bottom > height){
            cx = oldCx;
            cy = oldCy;
        }
    }

    public void mousePressed(int x, int y){
        if(contains(x,y)){
            gameOver = true;
            winner = "Clicker!";
            
        } 
    
    }

    public boolean contains(int x, int y){
        int dx = x - cx;
        int dy = y - cy;
        double distance = (int) Math.sqrt((Math.pow(dx, 2) + Math.pow(dy, 2)));
        return distance <= r;
    
    
    }

    public void paint(Graphics2D page){
        if(!gameOver){
            page.setColor(cirColor);
            page.fillOval(cx - r, cy - r, 2*r, 2*r);
        }
        else{
            page.setColor(Color.black);
            page.drawString("The winner is " + winner, 100, height/2);
        }
        page.setColor(Color.black);
        page.drawString("Timer: " + Integer.toString(timer), 20, 20);
    }

    public static void main(String[] args){ launch(400, 400);}



    






}
