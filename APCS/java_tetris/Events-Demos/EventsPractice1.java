// EventsPractice1.java

import javax.swing.*;
import java.awt.*;

public class EventsPractice1 extends JComponentWithEvents {

  // instance variables
  private int rectX = 50;

  // start method
  public void start() {
    setTimerDelay(15);
  }

  // timerFired event handler
  public void timerFired() {
    rectX += 5;
    if (rectX > getWidth())
      rectX = 0;
  }

  // paint method
  public void paint(Graphics2D page) {
    page.fillRect(rectX, 20, 30, 30);

    page.setColor(Color.blue);
    page.setFont(new Font("SansSerif",Font.BOLD,16));
    page.drawString("Make the box move right and wraparound!",10,80);
  }

  // Standard main method:
  public static void main(String[] args) { launch(400, 100); }
}
