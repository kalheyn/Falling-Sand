final color SAND = #EDC9AF;

class Sand extends Particle {
  final color type = SAND;

  public Sand(int x, int y) {
   super(x, y);
  }

  public void move() {
    // Check area around sand
    boolean down = super.checkDown();
    boolean downLeft = super.checkDownLeft();
    boolean downRight = super.checkDownRight();

    //Randomize downLeft and downRight
    if (downLeft && downRight) {
      boolean coinFlip = random(1) > 0.5;
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
