
void setup() {
  size(500,500);
  background(EMPTY);
  frameRate(1000);
}

void draw() {
  loadPixels();

  // Draw particles
  Cursor sandCursor = new Cursor(SAND);
  sandCursor.draw();
  
  // Loop through display to get cells
  for (int x = 0; x < width; x++) 
    for (int y = 0; y < height; y++) {
      color cell = pixels[x + (y * width)];

      // Fall sand
      if (cell == SAND) {
        Sand sand = new Sand(x, y);
        sand.move();
      }
    }
}
