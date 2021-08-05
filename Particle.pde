
class Particle {
  private int x; 
  private int y; 

  public Particle(int x, int y) {
    setX(x);
    setY(y);
  }

  private void moveDown(color type) {
    set(x, y + 1, type);
    set(x, y, EMPTY);
  }

  private void moveDownLeft(color type) {
    set(x - 1, y + 1, type);
    set(x, y, EMPTY);
  }

  private void moveDownRight(color type) {
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
