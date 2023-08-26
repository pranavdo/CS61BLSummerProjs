package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.util.Random;

import static byow.Core.Engine.*;
import static byow.Core.Game.beachOff;

public class BeachOff {
    private String[] answers = new String[]{"rock", "paper", "scissors"};
    public BeachOff(){

    }

    public void drawYouWon() {
        StdDraw.clear(new Color(192, 21, 185));
        StdDraw.setPenColor(new Color(236, 220, 178));
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);
        if (jpn) {
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "優勝おめでとうございます！");
        }
        if (it) {
            StdDraw.text(WIDTH / 2, HEIGHT / 2 + 5, "Congratulazioni!");
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "Hai vinto!");
        }
        if (!it && !jpn) {
            StdDraw.text(WIDTH / 2, HEIGHT / 2 + 5, "Congratulations!");
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "You won!");
        }
        StdDraw.show();
        StdDraw.pause(3000);
    }

    public void drawPlayAgain(){
        playAgain = true;
        StdDraw.clear(new Color(192, 21, 185));
        StdDraw.setPenColor(new Color(236, 220, 178));
        Font fontSmall = new Font("Monaco", Font.PLAIN, 20);
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);
        if (jpn) {
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "再びプレー？");
            StdDraw.textRight(20, HEIGHT / 2  - 5, "Y: はい");
            StdDraw.textLeft(WIDTH - 20, HEIGHT / 2  - 5, "N: いいえ");
        }
        if (it) {
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "Un'altra partita?");
            StdDraw.textRight(20, HEIGHT / 2  - 5, "Y: Si");
            StdDraw.textLeft(WIDTH - 20, HEIGHT / 2  - 5, "N: No");
        }
        if (!it && !jpn) {
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "Play again?");
            StdDraw.textRight(20, HEIGHT / 2  - 5, "Y: Yes");
            StdDraw.textLeft(WIDTH - 20, HEIGHT / 2  - 5, "N: No");
        }
        StdDraw.show();
    }

    public void drawGameOver(){
        StdDraw.clear(new Color(192, 21, 185));
        StdDraw.setPenColor(new Color(236, 220, 178));
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);
        if (jpn) {
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "負けた! ゲームオーバー");
        }
        if (it) {
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "hai perso! fine dei gioci :(");
        }
        if (!it && !jpn) {
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "you lost! game over :(");
        }
        StdDraw.show();
    }

    public void rockPaperScissors(Character input){
        String answer = returnString();
        if(input == 'S' && answer == "scissors") {
            drawGameOver();
            StdDraw.pause(3000);
            drawPlayAgain();
            StdDraw.pause(5000);

        }
        if(input == 'S' && answer == "paper") {
            drawYouWon();
            beachOff = false;
        }
        if(input == 'S' && answer == "rock") {
            drawGameOver();
            StdDraw.pause(3000);
            drawPlayAgain();
            StdDraw.pause(5000);
        }

        if (input == 'P' && answer == "scissors") {
            drawGameOver();
            StdDraw.pause(3000);
            drawPlayAgain();
            StdDraw.pause(5000);

        }
        if (input == 'P' && answer == "rock") {
            drawYouWon();
            beachOff = false;
        }
        if (input == 'P' && answer == "paper") {
            drawGameOver();
            StdDraw.pause(3000);
            drawPlayAgain();
            StdDraw.pause(5000);
        }

        if (input == 'R' && answer == "rock") {
            drawGameOver();
            StdDraw.pause(3000);
            drawPlayAgain();
            StdDraw.pause(5000);
        }

        if (input == 'R' && answer == "paper") {
            drawGameOver();
            StdDraw.pause(3000);
            drawPlayAgain();
            StdDraw.pause(5000);
        }
        if (input == 'R' && answer == "scissors") {
            drawYouWon();
            beachOff = false;
        }
    }

    public String returnString() {
        Random r = new Random();
        int index = r.nextInt(0, 2);
        return answers[index];
    }

    public void createRooms(int x, int y, int l, int h, TETile[][] world) {
        for (int i = 0; i <= l - 1; i++) {
            for (int j = 0; j <= h - 1; j++) {
                if ((i == 0) || (j == 0) || (i == l - 1) || (j == h - 1)) {
                    world[x + i][y + j] = Tileset.WALL;
                } else {
                    world[x + i][y + j] = Tileset.SAND;
                }
            }
        }
    }

    public TETile[][] createBeachOff(TETile[][] world){
        BeachOff bo = new BeachOff();
        bo.addRooms(world);
        bo.addHall(world);
        bo.addKens(world);
        return world;
    }

    public  boolean isNothing(int x, int y, TETile[][] world){
        if (world[x][y] == Tileset.BONOTHING){
            return true;
        }
        if (world[x][y] == Tileset.TAN){
            return true;
        }
        if (world[x][y] == Tileset.BLUE){
            return true;
        }
        if (world[x][y] == Tileset.WHITE){
            return true;
        }
        return false;
    }

    public void addRooms(TETile[][] world) {
        createRooms(5, 13, 20, 20, world);
        createRooms(51, 13, 20, 20, world);
    }
    public void addHall(TETile[][] world) {
        createRooms(5, 13, 20, 20, world);
        createRooms(51, 13, 20, 20, world);
        for (int x = 7; x <= 54; x++) {
            if (world[x][20] == Tileset.SAND) {
                continue;
            }
            if (isNothing(x, 20, world) || world[x][20] == Tileset.WALL) {
                world[x][18] = Tileset.WALL;
                for (int y = 19; y <=30; y++) {
                    world[x][y] = Tileset.SAND;
                }
                world[x][31] = Tileset.WALL;
            }
        }
    }
    public void addKens(TETile[][] world) {
        world[18][23] = Tileset.KEN2;
        world[57][23] = Tileset.KEN;
    }

}
