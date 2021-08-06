
void setup() {
  size(500, 500);
  background(EMPTY);
  frameRate(1000);
}

void draw() {
  loadPixels();

  // Draw particles
  Painter sandPainter = new Cursor(SAND); // decouple from Cursor class using Painter interface
  sandPainter.paint();

  // Loop through display to get cells
  for (int x = 0; x < width; x++) 
    for (int y = 0; y < height; y++) {
      color cell = pixels[x + (y * width)];

      // Run logic for each type of cell
      if (cell == SAND) {

        // Simulate sand
        Simulator sandSimulator = new Sand(x, y); // decouple from Sand class using Simulator interface
        sandSimulator.move();
      }
    }
}
