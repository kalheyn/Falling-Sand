
class Cursor implements Painter {
  private int size = 10;
  private color type;

  public Cursor(color type) {
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

  private color getType() {
    return this.type;
  }

  private void setSize(int size) {
    this.size = size;
  }

  private void setType(color type) {
    this.type = type;
  }
}
