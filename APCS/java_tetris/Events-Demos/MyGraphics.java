// MyGraphics.java
// Test code...

import javax.swing.*;
import java.awt.*;

class MyGraphics extends JComponentWithEvents {

  // these instance variables persists between method calls!
  private int rectX = 50;
  private int rectY = 50;

  public void start() { System.out.println("start"); setTimerDelay(250); }

  public void keyPressed(char c) {
    System.out.println((int)c);
  }

  public void componentResized() {
    System.out.println("component resized, dims = " + getWidth() + "x" + getHeight());
  }

  public void timerFired() {
  }

  public void paint(Graphics2D page) {
    page.fillRect(rectX, rectY, 50, 50);
  }

  // Standard main method:
  public static void main(String[] args) { launch(500, 400); }
}