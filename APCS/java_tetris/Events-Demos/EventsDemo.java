// EventsDemo.java
// By David Kosbie

// A simple demo showing some of the functionality of JComponentWithEvents:
// Requires JComponentWithEvents.class

import java.awt.*;
import javax.swing.*;

public class EventsDemo extends JComponentWithEvents {

  private String song1 =  "brandenburg6.mid";
  // private String song1 =  "http://kosbie.net/cmu/sampleSounds/brandenburg6.mid";
  private String song2 =  "http://kosbie.net/cmu/sampleSounds/entertainer.mid";
  private String song3 =  "http://kosbie.net/cmu/sampleSounds/route66.mid";
  private String song4 =  "http://kosbie.net/cmu/sampleSounds/sabre.mid";
  private String sound1 = "http://kosbie.net/cmu/sampleSounds/elephant.wav";
  private String sound2 = "http://kosbie.net/cmu/sampleSounds/horse.wav";

  public void start() {
    setPauseKey('p');
    setTimerDelay(50);
    play(song1);
  }

  public void timerFired() {
    rectX += 20;
    ovalY += 20;
  }

  public void mousePressed(int x, int y) {
    mouseMessage = "mouse pressed at (" + x + ","  + y + ")";
    mouseX = x;
    mouseY = y;
    dotX = x;
    dotY = y;
    play(sound1);
  }

  public void mouseDragged(int x, int y) {
    mouseMessage = "mouse dragged at (" + x + ","  + y + ")";
    mouseX = x;
    mouseY = y;
    dotX = x;
    dotY = y;
  }

  public void mouseReleased(int x, int y) {
    mouseMessage = "mouse released at (" + x + ","  + y + ")";
    mouseX = x;
    mouseY = y;
    dotX = x;
    dotY = y;
    play(sound2);
  }

  public void mouseMoved(int x, int y) {
    mouseX = x;
    mouseY = y;
  }

  private void mouseEnteredOrExited(String msg, int x, int y) {
    mouseMessage = "mouse " + msg + " at (" + x + ","  + y + ")";
    mouseX = x;
    mouseY = y;
    int width = getWidth(), height = getHeight();
    if (x < 0) x = 0;
    else if (x > width) x = width;
    if (y < 0) y = 0;
    else if (y > height) y = height;
    inOutDotX = x;
    inOutDotY = y;
  }

  public void mouseEntered(int x, int y) {
    mouseEnteredOrExited("entered", x, y);
  }

  public void mouseExited(int x, int y) {
    mouseEnteredOrExited("exited", x, y);
  }

  public void keyPressed(char key) {
    if (key == 'x') exit();
    else if (key == '1') { stopSounds(); play(song1); }
    else if (key == '2') { stopSounds(); play(song2); }
    else if (key == '3') { stopSounds(); play(song3); }
    else if (key == '4') { stopSounds(); play(song4); }
    else if (key == UP)    pointY -= 5;
    else if (key == DOWN)  pointY += 5;
    else if (key == LEFT)  pointX -= 5;
    else if (key == RIGHT) pointX += 5;
    else beep();
  }

  private int rectX, ovalY, dotX, dotY, pointX = getWidth()-50, pointY = getHeight()-50;
  private int mouseX = -1, mouseY = -1;
  private int inOutDotX = -1, inOutDotY = -1;
  private String mouseMessage = "Mouse has not entered yet!";

  public void paint(Graphics2D page) {
    int width = getWidth(), height = getHeight();

    // CARPE DIEM
    double scale = 0.5;
    double rotation = 0.0;
    drawCenteredImage(page, "sampleImage.jpg", width/2, height/2, scale, rotation);

    // RECTS
    page.setColor(Color.blue);
    page.fillRect(rectX % width, 50, 50, 50);
    page.setColor(Color.red);
    page.fillRect(width-rectX % width,50,50,50);

    // OVALS
    page.setColor(Color.green);
    page.fillOval(50,ovalY % height,100,50);
    page.setColor(Color.orange);
    page.fillOval(50,height-ovalY % height,100,50);

    // CROSSHAIR
    page.setColor(Color.black);
    page.drawLine(pointX,0,pointX,height);
    page.drawLine(0,pointY,width,pointY);

    // DOTS
    page.setColor(new Color(255,0,255,128));
    page.fillOval(dotX-25,dotY-25,50,50);
    if (inOutDotX >= 0) {
      page.setColor(Color.cyan);
      page.fillOval(inOutDotX-25,inOutDotY-25,50,50);
    }

    // INSTRUCTIONS
    page.setFont(new Font("Arial",Font.BOLD,16));
    page.setColor(Color.magenta);
    String howTo1 = "Keys 1-4 play songs, arrows move crosshairs,";
    String howTo2 = "mouse moves dot, p pauses, x exits. Enjoy!";
    page.drawString(howTo1,10,height-30);
    page.drawString(howTo2,10,height-10);

    // ISAPPLET?
    page.setFont(new Font("Arial",Font.BOLD,16));
    page.setColor(Color.orange);
    page.drawString("isApplet() == " + isApplet(),10,30);

    // MOUSE MESSAGES
    Color amethyst = new Color(153, 102, 204);
    page.setColor(amethyst);
    page.drawString(mouseMessage,10,60);
    String mouseLocationMsg = "Current mouse location: (" + mouseX + "," + mouseY + ")";
    page.drawString(mouseLocationMsg, 10, 90);
  }

  // Standard main method:
  public static void main(String[] args) { launch(500, 400); }
}
