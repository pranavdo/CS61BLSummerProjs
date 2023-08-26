package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.util.Random;

import static byow.Core.Engine.*;
import static byow.Core.Engine.it;
import static byow.Core.Game.score;
import static byow.Core.RWGame.keyChallenge;
import static byow.Core.RWGame.keyObtained;

public class KeyChallenge {

    public static boolean won = false;
    public int agentCordX;
    public int agentCordY;

    public KeyChallenge(){

    }

    public void gameStart(){
        StdDraw.setPenColor(new Color(236, 220, 178));
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);
        StdDraw.text(WIDTH/2, 5, "Try to find the meaning of life, it is hidden within the walls..");
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

    public TETile[][] createKeyChallenge(TETile[][] world){
        addRooms(world);
        addHall(world);
        addPpl(world);
        addSecret(world);
        return world;
    }


    public void addRooms(TETile[][] world) {
        createRooms(5, 13, 20, 20, world);
        createRooms(51, 13, 20, 20, world);
    }

    public void addHall(TETile[][] world) {
        createRooms(5, 13, 20, 20, world);
        createRooms(51, 13, 20, 20, world);
        for (int x = 7; x <= 54; x++) {
            if (world[x][20] == Tileset.RWFLOOR) {
                continue;
            }
            if (world[x][20] == Tileset.RWNOTHING || world[x][20] == Tileset.RWWALL) {
                world[x][18] = Tileset.RWWALL;
                for (int y = 19; y <=30; y++) {
                    world[x][y] = Tileset.RWFLOOR;
                }
                world[x][31] = Tileset.RWWALL;
            }
        }
    }

    public void drawMeaningOfLife(){
        StdDraw.pause(1300);
        StdDraw.clear(new Color(17, 23, 31));
        StdDraw.setPenColor(new Color(236, 220, 178));
        Font fontSmall = new Font("Monaco", Font.PLAIN, 20);
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);
        if (jpn) {
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "人生の意味は42です");
        }
        if (it) {
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "Il senso della vita è godersela");
        }
        if (!it && !jpn) {
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "The meaning of life is to be yourself");
        }
        StdDraw.show();
        StdDraw.pause(3000);
    }

    public void addDoor(TETile[][] world){
        int x = WIDTH - 1;
        int y = HEIGHT - 1;
        while (world[x][y] != Tileset.RWWALL) {
            x--;
            y--;
        }
        if (x > 2 && y > 2) {
            world[x][y] = Tileset.RWUNLOCKED_DOOR;
        }
    }


    public void addPpl(TETile[][] world) {
        world[18][23] = Tileset.BARBIE;
        world[57][23] = Tileset.NPC2;
        agentCordX = 18;
        agentCordY = 23;
    }

    public void addSecret(TETile[][] world){
        int x = 0;
        int y = 0;
        while (world[x][y] != Tileset.RWWALL) {
            x++;
            y++;
        }
        world[x][y] = Tileset.SECRETCHERRY;
    }

    public TETile[][] moveAgent(Character inp, TETile[][] world, Boolean b) {
        if (inp == 'S') {
            moveDown(world, b);
        }
        if (inp == 'D') {
            moveRight(world, b);
        }
        if (inp == 'A') {
            moveLeft(world, b);
        }
        if (inp == 'W') {
            moveUp(world, b);
        }
        return world;
    }

    public void moveLeft(TETile[][] world, Boolean b){
        int startx = agentCordX;
        int starty = agentCordY;
        if (startx - 1 > 0) {
            if (world[startx - 1][starty] == Tileset.RWLOCKED_DOOR && keyObtained) {
                world[startx - 1][starty] = Tileset.RWUNLOCKED_DOOR;
            }
            if (world[startx - 1][starty] == Tileset.UNLOCKED_DOOR) {
                //return to barbie world
                keyChallenge = false;
            }
            if (world[startx - 1][starty] == Tileset.SECRETCHERRY) {
                world[startx - 1][starty] = Tileset.CRYSTALBALL;
                keyObtained = true;
                score += 200;
                addDoor(world);
                drawMeaningOfLife();
            }
            if (world[startx - 1][starty] == Tileset.RWFLOOR) {
                world[startx - 1][starty] = Tileset.BARBIE;
                world[startx][starty] = Tileset.RWFLOOR;
                agentCordX = startx - 1;
            }
        }
        return;
    }

    public void moveUp(TETile[][] world, Boolean b){
        int startx = agentCordX;
        int starty = agentCordY;
        if (starty + 1 < HEIGHT) {
            if (world[startx][starty + 1] == Tileset.RWLOCKED_DOOR && keyObtained) {
                world[startx][starty + 1] = Tileset.RWUNLOCKED_DOOR;
            }
            if (world[startx][starty + 1] == Tileset.RWUNLOCKED_DOOR) {
                //return to real world
                keyChallenge = false;
            }
            if (world[startx][starty + 1] == Tileset.SECRETCHERRY) {
                world[startx][starty + 1] = Tileset.CRYSTALBALL;
                keyObtained = true;
                score += 200;
                addDoor(world);
                drawMeaningOfLife();
            }
            if (world[startx][starty + 1] == Tileset.RWFLOOR) {
                world[startx][starty + 1] = Tileset.BARBIE;
                world[startx][starty] = Tileset.RWFLOOR;
                agentCordY = starty + 1;
            }
        }
    }

    public void moveDown(TETile[][] world, Boolean b){
        int startx = agentCordX;
        int starty = agentCordY;
        if (starty - 1 > 0) {
            if (world[startx][starty - 1] == Tileset.RWLOCKED_DOOR && keyObtained) {
                world[startx][starty - 1] = Tileset.RWUNLOCKED_DOOR;
            }
            if (world[startx][starty - 1] == Tileset.RWUNLOCKED_DOOR) {
                keyChallenge = false;
                //return to  barbie world
            }
            if (world[startx][starty - 1] == Tileset.SECRETCHERRY) {
                world[startx][starty - 1] = Tileset.CRYSTALBALL;
                keyObtained = true;
                score += 200;
                addDoor(world);
                drawMeaningOfLife();
            }
            if (world[startx][starty - 1] == Tileset.RWFLOOR) {
                world[startx][starty - 1] = Tileset.BARBIE;
                world[startx][starty] = Tileset.RWFLOOR;
                agentCordY = starty - 1;
            }
        }
    }

    public void moveRight(TETile[][] world, Boolean b){
        int startx = agentCordX;
        int starty = agentCordY;
        if (startx + 1 < WIDTH - 1) {
            if (world[startx + 1][starty] == Tileset.RWLOCKED_DOOR && keyObtained) {
                world[startx + 1][starty] = Tileset.RWUNLOCKED_DOOR;
                //return  toreal world, with new door
            }
            if (world[startx + 1][starty] == Tileset.UNLOCKED_DOOR) {
                keyChallenge = false;
                //return  toreal world, with new door
            }
            if (world[startx + 1][starty] == Tileset.SECRETCHERRY) {
                world[startx + 1][starty] = Tileset.CRYSTALBALL;
                keyObtained = true;
                score += 200;
                addDoor(world);
                drawMeaningOfLife();
            }
            if (world[startx + 1][starty] == Tileset.RWFLOOR) {
                world[startx + 1][starty] = Tileset.BARBIE;
                world[startx][starty] = Tileset.RWFLOOR;
                agentCordX = startx + 1;
            }
        }
    }
}
