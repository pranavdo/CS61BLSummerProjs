package byow.Core;


import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.algs4.StdDraw;


import static byow.Core.Engine.*;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;


public class RWGame extends Game {
    /** game instance variables*/
    private boolean gameOver = true;
    private int score = 0;
    private int level = 1;
    private String seed = "";
    private SimpleDateFormat timeDate = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy");
    private SimpleDateFormat timeDateIT = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy", Locale.ITALIAN);
    private SimpleDateFormat timeDateJPN = new SimpleDateFormat("EEE MMM d HH:mm:ss yyyy", Locale.JAPANESE);
    private boolean kenReached = false;
    private int health = 500;
    private boolean hasKen = false;
    private boolean doorReached  = false;
    private Random r;
//    public static boolean level2 = false;
    public static boolean keyObtained = false;
    private boolean npc = false;
    public static boolean keyChallenge = false;

    /** agent instance variables*/
    private TETile agentCord;
    private int agentCordX;
    private int agentCordY;
    private TETile door1Cord;
    private TETile door2Cord;

    /** constructor*/
    public RWGame(){
        StdDraw.setCanvasSize(WIDTH*16, HEIGHT*16);
        Font font = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(font);
        StdDraw.setXscale(0, WIDTH);
        StdDraw.setYscale(0, HEIGHT);
        StdDraw.clear(Color.BLACK);
        StdDraw.enableDoubleBuffering();
    }

    public void GameStart() {
        gameOver = false;
        while (!gameOver) {
            if (hasKen) {
                StdDraw.pause(1000);
            }
            if (kenReached) {
                gameOver = true;
            }
        }
    }

    public TETile[][] createRealWorld(TETile[][] world) {
        int numRooms = 6;
        RealWorld rw = new RealWorld();
        rw.addRooms(numRooms, world);
        rw.createHalls(world);
        addAgent(0, 0, world, barbie);
        addDoor(world, barbie);
        addCherry(world);
        addCherry(world);
        addCherry(world);
        return world;
    }

    public void pinkOutMode() {
        double[] rec1x = new double[]{agentCordX+3, agentCordX - 3, agentCordX - 3, agentCordX + 3};
        double[] rec1y = new double[]{45, 45, agentCordY + 3, agentCordY + 3};
        double[] rec2x = new double[]{agentCordX-3, agentCordX+3, agentCordX+3, agentCordX-3};
        double[] rec2y = new double[]{agentCordY-3, agentCordY-3, 0, 0};
        double[] rec3x = new double[]{0, agentCordX-3, agentCordX-3, 0};
        double[] rec3y = new double[]{0, 0, 45, 45};
        double[] rec4x = new double[]{agentCordX+3,agentCordX+3,80,80};
        double[] rec4y = new double[]{0,45,45,0};

        StdDraw.setPenColor(new Color(0, 0, 0));
        StdDraw.filledPolygon(rec1x, rec1y);
        StdDraw.filledPolygon(rec2x, rec2y);
        StdDraw.filledPolygon(rec3x, rec3y);
        StdDraw.filledPolygon(rec4x, rec4y);
        StdDraw.show();
    }


    public void drawYouWon(Boolean j, Boolean s) {
        StdDraw.clear(new Color(192, 21, 185));
        StdDraw.setPenColor(new Color(236, 220, 178));
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);
        if (j) {
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "優勝おめでとうございます！");
        }
        if (s) {
            StdDraw.text(WIDTH / 2, HEIGHT / 2 + 5, "Congratulazioni!");
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "Hai vinto!");
        }
        if (!s && !j) {
            StdDraw.text(WIDTH / 2, HEIGHT / 2 + 5, "Congratulations!");
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "You won!");
        }
        StdDraw.show();
        StdDraw.pause(3000);
    }

    public void drawHUD(Boolean j , Boolean s) {
        if (!gameOver) {
            StdDraw.setPenColor(new Color(53, 57, 61));
            double[] recx = new double[]{0, 0, WIDTH, WIDTH};
            double[] recy = new double[]{ HEIGHT-2, HEIGHT, HEIGHT, HEIGHT-2};
            StdDraw.filledPolygon(recx, recy);

            StdDraw.setPenColor(new Color(236, 220, 178));
            Font fontBig = new Font("Monaco", Font.BOLD, 30);
            StdDraw.setFont(fontBig);
            StdDraw.line(0, HEIGHT - 2, WIDTH, HEIGHT - 2);

            String currDate = timeDate.format(new Date());
            Font fontSmall = new Font("Monaco", Font.BOLD, 20);
            StdDraw.setFont(fontSmall);
            StdDraw.line(0, HEIGHT - 2, WIDTH, HEIGHT - 2);
            if (j) {
                StdDraw.textLeft(0, HEIGHT - 1, "ヘルス: " + health);
                StdDraw.text(WIDTH / 2, HEIGHT - 1, timeDateJPN.format(new Date()));
                StdDraw.textRight(WIDTH, HEIGHT - 1, "スコア: " + score);

            }
            if (s) {
                StdDraw.textLeft(0, HEIGHT - 1, "Salute: " + health);
                StdDraw.text(WIDTH / 2, HEIGHT - 1, timeDateIT.format(new Date()));
                StdDraw.textRight(WIDTH, HEIGHT - 1, "Puntaggio: " + score);
            }
            if (!s&&!j) {
                StdDraw.textLeft(0, HEIGHT - 1, "Health: " + health);
                StdDraw.text(WIDTH / 2, HEIGHT - 1, currDate);
                StdDraw.textRight(WIDTH, HEIGHT - 1, "Score: " + score);
            }
        }
    }

    public void drawPlayAgain(Boolean j, Boolean s){
        StdDraw.clear(new Color(192, 21, 185));
        StdDraw.setPenColor(new Color(236, 220, 178));
        Font fontSmall = new Font("Monaco", Font.PLAIN, 20);
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);
        if (j) {
            StdDraw.text(WIDTH / 2, HEIGHT / 2 + 5, "バービーを見つかった！");
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "優勝おめでとうございます！");
            StdDraw.text(WIDTH / 2, HEIGHT / 2 - 5, "再びプレー？");
            StdDraw.textRight(20, HEIGHT / 2  - 10, "Y: はい");
            StdDraw.textLeft(WIDTH - 20, HEIGHT / 2  - 10, "N: いいえ");
        }
        if (s) {
            StdDraw.text(WIDTH / 2, HEIGHT / 2 + 5, "Congratulazioni, l'hai incontrata!");
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "Hai vinto!");
            StdDraw.text(WIDTH / 2, HEIGHT / 2 - 5, "Un'altra partita?");
            StdDraw.textRight(20, HEIGHT / 2  - 10, "Y: Si");
            StdDraw.textLeft(WIDTH - 20, HEIGHT / 2  - 10, "N: No");
        }
        if (!s && !j) {
            StdDraw.text(WIDTH / 2, HEIGHT / 2 + 5, "Congratulations, you found Barbie!");
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "You won!");
            StdDraw.text(WIDTH / 2, HEIGHT / 2 - 5, "Play again?");
            StdDraw.textRight(20, HEIGHT / 2  - 10, "Y: Yes");
            StdDraw.textLeft(WIDTH - 20, HEIGHT / 2  - 10, "N: No");
        }
        StdDraw.show();
    }

    public void newKeyChallenge(){
        StdDraw.clear(new Color(17, 23, 31));
        StdDraw.setPenColor(new Color(236, 220, 178));
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);
        if (jpn) {
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "バービーランドへの行き方を知っている！");
        }
        if (it) {
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "Io so come arrivare a Barbie Land!");
        }
        if (!jpn && !it) {
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "I know how to get to Barbie Land!");
        }
        StdDraw.show();
        StdDraw.pause(2000);
        StdDraw.clear(new Color(17, 23, 31));
        if (jpn) {
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "でもまず人生の意味を見つけなければならない。。。");
            StdDraw.text(WIDTH / 2, HEIGHT / 2 - 5, "壁の中であるよ。");
        }
        if (it) {
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "Ma prima devi trovare il senso della vita");
            StdDraw.text(WIDTH / 2, HEIGHT / 2 - 5, "È nelle parete...");
        }
        if (!jpn && !it) {
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "But first you have to find the meaning of life.");
            StdDraw.text(WIDTH / 2, HEIGHT / 2 - 5, "It's somewhere in the walls...");
        }
        StdDraw.show();
        StdDraw.pause(2000);
        keyChallenge = true;
    }

    public void drawGameOver(Boolean j, Boolean s){
        StdDraw.clear(new Color(192, 21, 185));
        StdDraw.setPenColor(new Color(236, 220, 178));
        Font fontSmall = new Font("Monaco", Font.PLAIN, 20);
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);
        if (j) {
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "ゲームオーバー");
        }
        if (s) {
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "fine dei gioci :(");
        }
        if (!s && !j) {
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "game over :(");
        }
        StdDraw.show();
    }

    public void addDoor(TETile[][] world, Boolean  b){
        Random r = new Random(5678);
        int x = r.nextInt(40,75);
        int y = r.nextInt(30,45);
        while (world[x][y] != Tileset.RWWALL || world[x-1][y] != Tileset.RWFLOOR) {
            x--;
            y--;
        }
        if (b && x > 2 && y > 2) {
            if (!keyObtained) {
                world[x][y] = Tileset.RWLOCKED_DOOR;
                door1Cord = world[x][y];
            }
            if (keyObtained) {
                world[x][y] = Tileset.RWUNLOCKED_DOOR;
                door1Cord = world[x][y];
            }
        }
    }

    public void addNewDoor(TETile[][] world) {
        doorReached = false;
        Random r = new Random(5678);
        int x = 0;
        int y = 0;
        while (world[x][y] != Tileset.RWWALL) {
            x++;
            y++;
        }
        world[x][y] = Tileset.LOCKED_DOOR;
        door2Cord = world[x][y];
    }

    public void addCherry(TETile[][] world) {
        Random r = new Random(98765432);
        int x = r.nextInt(25,75);
        int y = r.nextInt(25,45);
        while (world[x][y] != Tileset.RWFLOOR) {
            x--;
            y--;
        }
        if (x > 3 && y > 3) {
            world[x][y] = Tileset.CHERRY;
        }
    }

    public void addKen(TETile[][] world) {
        int x = 0;
        int y = HEIGHT-1;
        while (world[x][y] != Tileset.RWFLOOR) {
            x++;
            y--;
        }
        world[x][y] = Tileset.KEN;
    }


    public void addNPC(TETile[][] world){
        int x = WIDTH - 1;
        int y = HEIGHT - 1;
        while (world[x][y] != Tileset.RWFLOOR) {
                x--;
                y--;
        }
        world[x][y] = Tileset.NPC;
        addNPC2(world);
        score+=100;
    }
    public void addNPC2(TETile[][] world){
        int x = 0;
        int y = 0;
        while (world[x][y] != Tileset.RWFLOOR) {
            x++;
            y++;
        }
        world[x][y] = Tileset.NPC2;
    }

    public TETile[][] addAgent(int x, int y, TETile[][] world, Boolean b) {
        gameOver = false;
        while (world[x][y] != Tileset.RWFLOOR) {
            x++;
            y++;
        }
        agentCord = world[x][y];
        agentCordX = x;
        agentCordY = y;
        world[x][y] = Tileset.BARBIE;
        return world;
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
        int startx = getAgentCordX();
        int starty = getAgentCordY();
        if (startx - 1 > 0) {
            if (world[startx - 1][starty] == Tileset.RWLOCKED_DOOR && keyObtained) {
                world[startx - 1][starty] = Tileset.RWUNLOCKED_DOOR;
                doorReached = true;
                score += 50;
            }
            if (world[startx - 1][starty] == Tileset.NPC2) {
                score+=50;
                newKeyChallenge();
            }
            if (world[startx - 1][starty] == Tileset.RWUNLOCKED_DOOR) {
                level++;
                drawYouWon(jpn, it);
                level2 = false;
                //return to barbie world
            }
            if (world[startx - 1][starty] == Tileset.CHERRY) {
                world[startx - 1][starty] = Tileset.RWFLOOR;
                score += 30;
                health += 100;
            }
            else if (world[startx - 1][starty] == Tileset.RWFLOOR) {
                if (b) {
                    world[startx - 1][starty] = Tileset.BARBIE;
                    world[startx][starty] = Tileset.RWFLOOR;
                }
                if (!b) {
                    world[startx - 1][starty] = Tileset.KEN;
                    world[startx][starty] = Tileset.RWFLOOR;
                }
                agentCordX = startx - 1;
            }
        }
        return;
    }

    public void moveUp(TETile[][] world, Boolean b){
        int startx = getAgentCordX();
        int starty = getAgentCordY();
        if(agentCordX > 45 && agentCordY > 30 && npc == false) {
            addNPC(world);
            npc = true;
        }
        if (starty + 1 < HEIGHT) {
            if (world[startx][starty + 1] == Tileset.RWLOCKED_DOOR && keyObtained) {
                world[startx][starty + 1] = Tileset.RWUNLOCKED_DOOR;
                doorReached  = true;
                score+=50;
            }
            if (world[startx][starty + 1] == Tileset.KEN) {
                doorReached  = true;
                score+=50;
            }
            if (world[startx][starty + 1] == Tileset.RWUNLOCKED_DOOR) {
                level++;
                drawYouWon(jpn, it);
                level2 = false;
                //return to barbie world
            }
            if (world[startx][starty + 1] == Tileset.CHERRY) {
                world[startx][starty + 1] = Tileset.RWFLOOR;
                score+=30;
                health+=100;
            }
            if (world[startx][starty + 1] == Tileset.RWFLOOR) {
                if (b) {
                    world[startx][starty + 1] = Tileset.BARBIE;
                    world[startx][starty] = Tileset.RWFLOOR;
                }
                if (!b) {
                    world[startx][starty + 1] = Tileset.KEN;
                    world[startx][starty] = Tileset.RWFLOOR;
                }
                agentCordY = starty + 1;
            }
        }
    }

    public void moveDown(TETile[][] world, Boolean b){
        int startx = getAgentCordX();
        int starty = getAgentCordY();
        if (starty - 1 > 0) {
            if (world[startx][starty - 1] == Tileset.RWLOCKED_DOOR && keyObtained) {
                world[startx][starty - 1] = Tileset.RWUNLOCKED_DOOR;
                doorReached  = true;
                score+=50;
            }
            if (world[startx][starty - 1] == Tileset.NPC2) {
                score+=50;
                newKeyChallenge();
            }
            if (world[startx][starty - 1] == Tileset.RWUNLOCKED_DOOR) {
                level++;
                drawYouWon(jpn, it);
                level2 = false;
                //return to  barbie world
            }
            if (world[startx][starty - 1] == Tileset.CHERRY) {
                world[startx][starty - 1] = Tileset.RWFLOOR;
                score += 30;
                health += 100;
            }
            if (world[startx][starty - 1] == Tileset.RWFLOOR) {
                if (b) {
                    world[startx][starty - 1] = Tileset.BARBIE;
                    world[startx][starty] = Tileset.RWFLOOR;
                }
                if (!b){
                    world[startx][starty - 1] = Tileset.KEN;
                    world[startx][starty] = Tileset.RWFLOOR;
                }
                agentCordY = starty - 1;
            }
        }
    }

    public void moveRight(TETile[][] world, Boolean b){
        int startx = getAgentCordX();
        int starty = getAgentCordY();
        if (startx + 1 < WIDTH - 1) {
            if (world[startx + 1][starty] == Tileset.RWLOCKED_DOOR && keyObtained) {
                world[startx + 1][starty] = Tileset.RWUNLOCKED_DOOR;
                doorReached = true;
                score += 50;
            }
            if (world[startx + 1][starty] == Tileset.RWUNLOCKED_DOOR) {
                level++;
                drawYouWon(jpn, it);
                level2 = false;
                //return to barbie world
            }
            if (world[startx + 1][starty] == Tileset.CHERRY) {
                world[startx + 1][starty] = Tileset.RWFLOOR;
                score += 30;
                health += 100;
            }
            if (world[startx + 1][starty] == Tileset.RWFLOOR) {
                if (b) {
                    world[startx + 1][starty] = Tileset.BARBIE;
                    world[startx][starty] = Tileset.RWFLOOR;
                }
                if (!b) {
                    world[startx + 1][starty] = Tileset.KEN;
                    world[startx][starty] = Tileset.RWFLOOR;
                }
                agentCordX = startx + 1;
            }
        }
    }

    public int getAgentCordX(){
        return agentCordX;
    }

    public int getAgentCordY(){
        return agentCordY;
    }

    public void quitGame(){
        System.exit(0);
    }

    public boolean getDoorReached(){
        return doorReached;
    }

    public boolean getKenReached(){
        return kenReached;
    }
}
