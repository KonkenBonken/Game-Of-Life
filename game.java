boolean[][] pixels;
int pxSize = 5;
int stillFrameRate = 120;
int FrameRate = 30;
boolean run = false;
boolean setTo = true;

public boolean inScreen() {
  return mouseX >= 0 && mouseY >= 0 && mouseX < width && mouseY < height;
}

void setup() {
  fullScreen();

  pixels = new boolean[width / pxSize][height / pxSize];

  background(0);
  frameRate(stillFrameRate);
  fill(255);
}

void mousePressed() {
  if (mousePressed && inScreen())
    setTo = !pixels[mouseX / pxSize][mouseY / pxSize];
}

void keyPressed() {
  if (key == 's') {
    run ^= true;
    if (run)
      frameRate(FrameRate);
    else
      frameRate(stillFrameRate);
  } else if (key == 'r') {
    for (int x = 1; x < pixels.length - 1; x++)
      for (int y = 1; y < pixels[x].length - 1; y++)
        pixels[x][y] = false;
  }
}

void mouseWheel(MouseEvent event) {
  FrameRate -= event.getCount();
  if (FrameRate < 0)
    FrameRate = 1;
  // System.out.println(FrameRate);
  frameRate(FrameRate);
}

void draw() {
  background(0);

  if (mousePressed && inScreen())
    pixels[mouseX / pxSize][mouseY / pxSize] = setTo;

  for (int x = 1; x < pixels.length - 1; x++) {
    for (int y = 1; y < pixels[x].length - 1; y++) {
      if (run) {
        boolean[] neighbours = {
          pixels[x - 1][y + 1],
          pixels[x][y + 1],
          pixels[x + 1][y + 1],
          pixels[x - 1][y],
          pixels[x + 1][y],
          pixels[x - 1][y - 1],
          pixels[x][y - 1],
          pixels[x + 1][y - 1]
        };
        int alive = 0;
        for (int i = 0; i < 8; i++)
          if (neighbours[i])
            alive++;

        pixels[x][y] =
          (pixels[x][y] && (alive == 2 || alive == 3)) ||
          (!pixels[x][y] && alive == 3);
      }
      if (pixels[x][y])
        square(x * pxSize, y * pxSize, pxSize);
    }
  }
}
