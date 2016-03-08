// EventsPractice2.java

import javax.swing.*;
import java.awt.*;

public class EventsPractice2 extends JComponentWithEvents {

  // instance variables
  private int rectX = 50;
  private int rectWidth = 30;
  private boolean moveRight = true;

  // start method
  public void start() {
    setTimerDelay(15);
  }

  // timerFired event handler
  public void timerFired() {
    if (moveRight) {
      rectX += 5;
      int maxRight = getWidth() - rectWidth;
      if (rectX > maxRight) {
        rectX = maxRight;
        moveRight = false;
      }
    }
    else {
      rectX -= 5;
      if (rectX < 0) {
        rectX = 0;
        moveRight = true;
      }
    }
  }

  // paint method
  public void paint(Graphics2D page) {
    page.fillRect(rectX, 20, rectWidth, 30);

    page.setColor(Color.blue);
    page.setFont(new Font("SansSerif",Font.BOLD,16));
    page.drawString("Make the box bounce right and left!",10,80);
  }

  // Standard main method:
  public static void main(String[] args) { launch(400, 100); }
}