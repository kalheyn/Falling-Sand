import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class FallingSand extends PApplet {


public void setup() {
  
  background(EMPTY);
  frameRate(1000);
}

public void draw() {
  loadPixels();

  // Draw particles
  Painter sandPainter = new Cursor(SAND); // decouple from Cursor class using Painter interface
  sandPainter.paint();

  // Loop through display to get cells
  for (int x = 0; x < width; x++) 
    for (int y = 0; y < height; y++) {
      int cell = pixels[x + (y * width)];

      // Run logic for each type of cell
      if (cell == SAND) {

        // Simulate sand
        Simulator sandSimulator = new Sand(x, y); // decouple from Sand class using Simulator interface
        sandSimulator.move();
      }
    }
}

class Cursor implements Painter {
  private int size = 10;
  private int type;

  public Cursor(int type) {
    setSize(size);
    setType(type);
  }

  @Override
    public void paint() {
    if (mousePressed) {
      for (int x = -1 * (size / 2); x < (size / 2); x++)
        for (int y = -1 * (size / 2); y < (size / 2); y++ ) {
          set(mouseX + x, mouseY + y, type);
        }
    }
  }

  private int getSize() {
    return this.size;
  }

  private int getType() {
    return this.type;
  }

  private void setSize(int size) {
    this.size = size;
  }

  private void setType(int type) {
    this.type = type;
  }
}
final int EMPTY = color(0); 
interface Painter {
  public void paint();
}

class Particle {
  private int x; 
  private int y; 

  public Particle(int x, int y) {
    setX(x);
    setY(y);
  }

  private void moveDown(int type) {
    set(x, y + 1, type);
    set(x, y, EMPTY);
  }

  private void moveDownLeft(int type) {
    set(x - 1, y + 1, type);
    set(x, y, EMPTY);
  }

  private void moveDownRight(int type) {
    set(x + 1, y + 1, type);
    set(x, y, EMPTY);
  }

  private boolean checkDown() {
    return isEmpty(x, y + 1);
  }

  private boolean checkDownLeft() {
    return isEmpty(x - 1, y + 1);
  }

  private boolean checkDownRight() {
    return isEmpty(x + 1, y + 1);
  }

  private boolean isEmpty(int x, int y) {
    return inBounds(x, y) && (getCell(x, y) == EMPTY);
  }

  private boolean inBounds(int x, int y) {
    return (x >= 0) && (y >= 0) && (x < width) && (y < height);
  }

  private int getCell(int x, int y) {
    return pixels[x + (y * width)];
  }

  private int getX() {
    return this.x;
  }

  private int getY() {
    return this.y;
  }

  private void setX(int x) {
    this.x = x;
  }

  private void setY(int y) {
    this.y = y;
  }
}
final int SAND = 0xffEDC9AF;

class Sand extends Particle implements Simulator {
  final int type = SAND;

  public Sand(int x, int y) {
    super(x, y);
  }

  @Override
    public void move() {
    // Check area around sand
    boolean down = super.checkDown();
    boolean downLeft = super.checkDownLeft();
    boolean downRight = super.checkDownRight();

    //Randomize downLeft and downRight
    if (downLeft && downRight) {
      boolean coinFlip = random(1) > 0.5f;
      downLeft = coinFlip;
      downRight = !coinFlip;
    }

    // Move sand and replace with emptiness
    if (down) {
      super.moveDown(type);
    } else if (downLeft) {
      super.moveDownLeft(type);
    } else if (downRight) {
      super.moveDownRight(type);
    }
  }
}

interface Simulator {
  public void move();
}
  public void settings() {  size(500,500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "FallingSand" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
