boolean[][] pixels;
int pxSize = 5;

void setup() {
  size(600, 250);

  pixels = new boolean[width / pxSize][height / pxSize];

  background(0);
  frameRate(30);
  fill(255);

  for (int x = 0; x < pixels.length; x++)
    for (int y = 0; y < pixels[x].length; y++)
      pixels[x][y] = Math.random() < 0.1;

}

void draw() {
  background(0);

  for (int x = 1; x < pixels.length - 1; x++) {
    for (int y = 1; y < pixels[x].length - 1; y++) {
      boolean[] neighbours = {
        pixels[x-1][y+1],
        pixels[ x ][y+1],
        pixels[x+1][y+1],
        pixels[x-1][ y ],
        pixels[x+1][ y ],
        pixels[x-1][y-1],
        pixels[ x ][y-1],
        pixels[x+1][y-1]
      };
      int alive = 0;
      for (int i = 0; i < 8; i++)
        if (neighbours[i])
          alive++;

      pixels[x][y] =
        (pixels[x][y] && (alive == 2 || alive == 3)) ||
        (!pixels[x][y] && alive == 3);

      if (pixels[x][y])
        square(x * pxSize, y * pxSize, pxSize);
    }
  }
}
