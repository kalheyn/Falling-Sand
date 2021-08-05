final color SAND = #EDC9AF;

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
      boolean coinFlip = random(1) > 0.5;
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

  private color getCell(int x, int y) {
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
