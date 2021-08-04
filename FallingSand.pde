final color EMPTY = color(0); 
final color SAND = color(237, 201, 175); 
final int CURSOR_SIZE = 10; 

void setup() {
  size(800, 800);
  background(EMPTY);
  frameRate(1000);
}

void draw() {
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
      color cell = getCell(x, y);

      // Fall sand
      if (cell == SAND) {
        boolean down = isEmpty(x, y + 1); 
        boolean downLeft = isEmpty(x-1, y+1); 
        boolean downRight = isEmpty(x+1, y+1); 

        //Randomize downLeft and downRight
        if (downLeft && downRight) {
          boolean coinFlip = random(1) > 0.5;
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

boolean inBounds(int x, int y) {
  return (x >= 0) && (y >= 0) && (x < width) && (y < height);
}

boolean isEmpty(int x, int y) {
  return inBounds(x, y) && (getCell(x, y) == EMPTY);
}

color getCell(int x, int y) {
  return pixels[x + (y * width)];
}
