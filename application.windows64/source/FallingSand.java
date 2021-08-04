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

// TEST GITHUB
public class FallingSand extends PApplet {

final int EMPTY = color(0); 
final int SAND = color(237, 201, 175); 
final int CURSOR_SIZE = 10; 

public void setup() {
  
  background(EMPTY);
  frameRate(1000);
}

public void draw() {
  loadPixels();

  // Draw on mouse press
  if (mousePressed) {
    for (int x = -1 * (CURSOR_SIZE / 2); x < (CURSOR_SIZE / 2); x++)
      for (int y = -1 * (CURSOR_SIZE / 2); y < (CURSOR_SIZE / 2); y++ ) {
        set(mouseX + x, mouseY + y, SAND);
      }
  }
  
  // Loop through display to get cells
  for (int x = 0; x < width; x++) 
    for (int y = 0; y < height; y++) {
      int cell = getCell(x, y);

      // Fall sand
      if (cell == SAND) {
        boolean down = isEmpty(x, y + 1); 
        boolean downLeft = isEmpty(x-1, y+1); 
        boolean downRight = isEmpty(x+1, y+1); 

        //Randomize downLeft and downRight
        if (downLeft && downRight) {
          boolean coinFlip = random(1) > 0.5f;
          downLeft = coinFlip;
          downRight = !coinFlip;
        }        

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
    }
}

public boolean inBounds(int x, int y) {
  return (x >= 0) && (y >= 0) && (x < width) && (y < height);
}

public boolean isEmpty(int x, int y) {
  return inBounds(x, y) && (getCell(x, y) == EMPTY);
}

public int getCell(int x, int y) {
  return pixels[x + (y * width)];
}
  public void settings() {  size(800, 800); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "FallingSand" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
