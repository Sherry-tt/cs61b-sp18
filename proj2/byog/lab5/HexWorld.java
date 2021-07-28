package byog.lab5;
import org.junit.Test;
import static org.junit.Assert.*;

import byog.TileEngine.TERenderer;
import byog.TileEngine.TETile;
import byog.TileEngine.Tileset;

import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    private static final int WIDTH = 50;
    private static final int HEIGHT = 50;

    /**
     * Calculate the x offset of each row compared to leftest point of 1st row.
     */
    private static int xOffset(int n, int size) {
        if (n < size) {
            return (size - n - 1);
        }
        return n - size;
    }

    /**
     * Calculate the y offset of each row compared to leftest point of 1st row.
     */
    private static int yOffset(int n, int size) {
        if (n < size) {
            return (size - n - 1);
        }
        return -(n - size + 1);
    }

    /** n = 0, top*/
    public static int calRow(int n, int size) {
        if (n >= size) return -2 * n + 5 * size - 2;
        return 2 * n + size;
    }

    /** Add a row */
    public static void addRow(TETile[][] world, TETile bg, int xP, int yP, int width){
        for (int i = 0; i < width; i++){
            world[xP + i][yP] = bg;
        }
    }

    /**
     * xP, yP is the leftest point of a hex
     */
    public static void addHexagon(TETile[][] world, TETile tile, int size, int xP, int yP) {
        for (int i = 0; i < size * 2; i++) {
            int xStart = xP + xOffset(i, size);
            int yStart = yP + yOffset(i, size);
            addRow(world, tile, xStart, yStart, calRow(i, size));
        }
    }

    public static void main (String[] args){
        TERenderer ter = new TERenderer();
        ter.initialize(WIDTH, HEIGHT);

        // initialize tiles
        TETile[][] world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x += 1) {
           for (int y = 0; y < HEIGHT; y += 1) {
                 world[x][y] = Tileset.WALL;
           }
       }

       addHexagon(world, Tileset.FLOWER, 6, 25, 25);
       ter.renderFrame(world);
    }

}