package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.Random;

import static byow.Core.Engine.*;
import static java.lang.Math.divideExact;

public class RealWorld {
    private int cordx;
    private int cordy;
    private RealWorld[] listOfRooms;

    public RealWorld() {

    }

    public RealWorld(Random r, int x, int y, int height, int length) {
        this.cordx = x + (r.nextInt(1, height - 1));
        this.cordy = y + (r.nextInt(1, length - 1));
    }

    public boolean roomExists(int x, int y, int l, int h, TETile[][] world) {
        for (int i = 0; i <= l; i++) {
            for (int j = 0; j <= h; j++) {
                if ((world[x + i][y + j] == Tileset.WALL) || (world[x + i][y + j] == Tileset.FLOOR)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isBackground(int x, int y, TETile[][] world) {
        if (world[x][y] == Tileset.RWNOTHING) {
            return true;
        }
        return false;
    }

    public void createRooms(int x, int y, int l, int h, TETile[][] world) {
        for (int i = 0; i <= l - 1; i++) {
            for (int j = 0; j <= h - 1; j++) {
                if ((i == 0) || (j == 0) || (i == l - 1) || (j == h - 1)) {
                    world[x + i][y + j] = Tileset.RWWALL;
                } else {
                    world[x + i][y + j] = Tileset.RWFLOOR;
                }
            }
        }
    }

    public void addRooms(int numRooms, TETile[][] world) {
        listOfRooms = new RealWorld[numRooms];
        Random r = new Random(2);
        RealWorld a = new RealWorld(r, 5, 6, 17, 10);
        createRooms(5, 6, 17, 10, world);
        listOfRooms[0] = a;

        RealWorld b = new RealWorld(r, 4, 24, 12, 18);
        createRooms(4, 24, 12, 18, world);
        listOfRooms[1] = b;

        RealWorld c = new RealWorld(r, 28, 22, 27, 11);
        createRooms(28, 22, 27, 11, world);
        listOfRooms[2] = c;

        RealWorld d = new RealWorld(r, 58, 24, 19, 18);
        createRooms(58, 24, 19, 18, world);
        listOfRooms[3] = d;

        RealWorld e = new RealWorld(r, 58, 3, 20, 12);
        createRooms(58, 3, 20, 12, world);
        listOfRooms[4] = e;
    }

    public int findSlope(RealWorld r1, RealWorld r2) {
        int slope = 0;
        int run = r2.cordx - r1.cordx;
        int rise = r2.cordy - r1.cordy;
        if (run != 0) {
            slope = divideExact(rise, run);
        }
        return slope;
    }

    public void createHalls(TETile[][] world) {
        int inc = 0;
        for (int i = 0; i < listOfRooms.length; i++) {
            if (listOfRooms[inc] == null) {
                inc++;
            }
            if ((listOfRooms[i]) != null && listOfRooms[inc] != null) {
                int slope = findSlope(listOfRooms[i], listOfRooms[inc]);
                if (slope == 0) {
                    buildHorizontalHalls(listOfRooms[i].cordx, listOfRooms[i].cordy,
                            listOfRooms[inc].cordx, listOfRooms[inc].cordy, world);

                }
                if (slope == 1) {
                    buildVerticalHalls(listOfRooms[i].cordx, listOfRooms[i].cordy,
                            listOfRooms[inc].cordx, listOfRooms[inc].cordy, world);

                } else {
                    if (slope > 0) {
                        if (listOfRooms[i].cordx > listOfRooms[inc].cordx) {

                        }

                    }
                    buildHorizontalHalls(listOfRooms[inc].cordx, listOfRooms[inc].cordy,
                            listOfRooms[i].cordx, listOfRooms[i].cordy, world);
                    buildVerticalHalls(listOfRooms[i].cordx, listOfRooms[i].cordy,
                            listOfRooms[inc].cordx, listOfRooms[inc].cordy, world);

                    buildHorizontalHalls(listOfRooms[i].cordx, listOfRooms[i].cordy,
                            listOfRooms[inc].cordx, listOfRooms[inc].cordy, world);
                    buildVerticalHalls(listOfRooms[inc].cordx, listOfRooms[inc].cordy,
                            listOfRooms[i].cordx, listOfRooms[i].cordy, world);
                }

            }
        }
    }

    public void addSecretCherries(TETile world) {

    }

    public void buildHorizontalHalls(int start_x, int start_y, int end_x, int end_y, TETile[][] world) {
        if (start_x < end_x) {
            for (int x_ax = start_x; x_ax <= end_x; x_ax++) {
                if (world[x_ax][start_y] == Tileset.RWFLOOR) {
                    continue;
                }
                if (x_ax == end_x &&
                        isBackground(x_ax + 1, start_y, world)) {
                    world[x_ax + 1][start_y] = Tileset.RWWALL;
                }
                world[x_ax][start_y] = Tileset.RWFLOOR;
                world[x_ax][start_y + 1] = Tileset.RWWALL;
                world[x_ax][start_y - 1] = Tileset.RWWALL;
                if (x_ax == end_x && end_y > start_y &&
                        isBackground(end_x + 1, start_y - 1, world)) {
                    world[end_x + 1][start_y - 1] = Tileset.RWWALL;
                }
                if (x_ax == end_x && end_y < start_y &&
                        isBackground(end_x + 1,  start_y + 1, world)) {
                    world[end_x + 1][start_y + 1] = Tileset.RWWALL;
                }
            }
        }
        if (end_x < start_x) {
            for (int x_ay = start_x; x_ay >= end_x; x_ay--) {
                if (world[x_ay][start_y] == Tileset.RWFLOOR) {
                    continue;
                }
                if (x_ay == end_x &&
                        isBackground(x_ay - 1, start_y, world)) {
                    world[x_ay - 1][start_y] = Tileset.RWWALL;
                }
                world[x_ay][start_y] = Tileset.RWFLOOR;
                world[x_ay][start_y + 1] = Tileset.RWWALL;
                world[x_ay][start_y - 1] = Tileset.RWWALL;
                if (x_ay == end_x && end_y > start_y &&
                        isBackground(end_x - 1, start_y - 1, world)) {
                    world[end_x - 1][start_y - 1] = Tileset.RWWALL;
                }
                if (x_ay == end_x && end_y < start_y &&
                        isBackground(end_x - 1, start_y + 1, world)) {
                    world[end_x - 1][start_y + 1] = Tileset.RWWALL;
                }

            }

        }

    }

    public void buildVerticalHalls(int start_x, int start_y, int end_x, int end_y, TETile[][] world) {
        if (end_y > start_y) {
            for (int y_ay = start_y; y_ay <= end_y; y_ay++) {
                if (world[start_x][y_ay] == Tileset.RWFLOOR) {
                    continue;
                }
                if (y_ay == end_y && (isBackground(start_x, y_ay + 1, world))) {
                    world[start_x][y_ay + 1] = Tileset.RWWALL;
                }
                world[start_x][y_ay] = Tileset.RWFLOOR;
                world[start_x + 1][y_ay] = Tileset.RWWALL;
                world[start_x - 1][y_ay] = Tileset.RWWALL;
                if (y_ay == end_y && end_x > start_x && isBackground(start_x - 1, end_y + 1, world)) {
                    world[start_x - 1][end_y + 1] = Tileset.RWWALL;
                }
                if (y_ay == end_y && end_x < start_x && isBackground(start_x + 1, end_y + 1, world)) {
                    world[start_x + 1][end_y + 1] = Tileset.RWWALL;
                }
            }
        }
        if (end_y < start_y) {
            for (int y_ay = start_y; y_ay >= end_y; y_ay--) {
                if (world[start_x][y_ay] == Tileset.RWFLOOR) {
                    continue;
                }
                if (y_ay == end_y &&
                        isBackground(start_x, y_ay - 1, world)) {
                    world[start_x][y_ay - 1] = Tileset.RWWALL;
                }
                world[start_x][y_ay] = Tileset.RWFLOOR;
                world[start_x + 1][y_ay] = Tileset.RWWALL;
                world[start_x - 1][y_ay] = Tileset.RWWALL;
                if (y_ay == end_y && end_x < start_x &&
                        isBackground(start_x + 1, end_y - 1, world)) {
                    world[start_x + 1][end_y - 1] = Tileset.RWWALL;
                }
                if (y_ay == end_y && end_x > start_x &&
                        isBackground(start_x - 1, end_y - 1, world)) {
                    world[start_x - 1][end_y - 1] = Tileset.RWWALL;
                }
            }
        }
    }

}
