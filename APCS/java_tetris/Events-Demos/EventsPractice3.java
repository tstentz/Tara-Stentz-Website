// EventsPractice3.java

import javax.swing.*;
import java.awt.*;

public class EventsPractice3 extends JComponentWithEvents {

  // instance variables
  private int rectX = 50;
  private int rectY = 10;
  private int rectWidth = 30;
  private int rectHeight = 30;
  private int xSpeed = 5;
  private int ySpeed = 3;
  private boolean moveRight = true;
  private boolean moveDown = true;

  // start method
  public void start() {
    setTimerDelay(15);
  }

  // timerFired event handler
  public void timerFired() {
    if (moveRight) {
      rectX += xSpeed;
      int maxRight = getWidth() - rectWidth;
      if (rectX > maxRight) {
        rectX = maxRight;
        moveRight = false;
      }
    }
    else {
      rectX -= xSpeed;
      if (rectX < 0) {
        rectX = 0;
        moveRight = true;
      }
    }
    if (moveDown) {
      rectY += ySpeed;
      int maxTop = getHeight() - rectHeight;
      if (rectY > maxTop) {
        rectY = maxTop;
        moveDown = false;
      }
    }
    else {
      rectY -= ySpeed;
      if (rectY < 0) {
        rectY = 0;
        moveDown = true;
      }
    }
  }

  // paint method
  public void paint(Graphics2D page) {
    page.fillRect(rectX, rectY, rectWidth, rectHeight);

    page.setColor(Color.blue);
    page.setFont(new Font("SansSerif",Font.BOLD,16));
    page.drawString("Make the box bounce up, down, left, and right!",10,80);
  }

  // Standard main method:
  public static void main(String[] args) { launch(400, 100); }
}