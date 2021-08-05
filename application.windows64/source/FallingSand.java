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
  Cursor sandCursor = new Cursor(SAND);
  sandCursor.draw();
  
  // Loop through display to get cells
  for (int x = 0; x < width; x++) 
    for (int y = 0; y < height; y++) {
      int cell = pixels[x + (y * width)];

      // Fall sand
      if (cell == SAND) {
        Sand sand = new Sand(x, y);
        sand.move();
      }
    }
}


class Cursor {
  private int size = 10;
  private int type;

  public Cursor(int type) {
    setSize(size);
    setType(type);
  }

  public void draw() {
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

final int SAND = 0xffEDC9AF;

class Sand {
  private int x; 
  private int y; 

  public Sand(int x, int y) {
    setX(x);
    setY(y);
  }
 
  public void move() {
    // Check area around sand
    boolean down = isEmpty(x, y + 1); 
    boolean downLeft = isEmpty(x-1, y+1); 
    boolean downRight = isEmpty(x+1, y+1);

    //Randomize downLeft and downRight
    if (downLeft && downRight) {
      boolean coinFlip = random(1) > 0.5f;
      downLeft = coinFlip;
      downRight = !coinFlip;
    }

    // Move sand and replace with emptiness
    if (down) {
      set(x, y+1, SAND); 
      set(x, y, EMPTY);
    } else if (downLeft) {
      set(x-1, y+1, SAND);
      set(x, y, EMPTY);
    } else if (downRight) {
      set(x+1, y+1, SAND);
      set(x, y, EMPTY);
    }
  }

  private boolean inBounds(int x, int y) {
    return (x >= 0) && (y >= 0) && (x < width) && (y < height);
  }

  private boolean isEmpty(int x, int y) {
    return inBounds(x, y) && (getCell(x, y) == EMPTY);
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
