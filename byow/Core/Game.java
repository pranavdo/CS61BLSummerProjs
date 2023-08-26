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


public class Game {
    /** game instance variables*/
    private boolean gameOver = true;
    public static int score = 0;
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
    public static boolean level2 = false;
    public static boolean beachOff = false;

    /** agent instance variables*/
    private TETile agentCord;
    private int agentCordX;
    private int agentCordY;
    private TETile door1Cord;
    private TETile door2Cord;

    /** constructor*/
    public Game(){
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

    public TETile[][] createGame(String s, TETile[][] world, Boolean barbie, Boolean j, Boolean it) {
        long seed = Long.parseLong(s);
        r = new Random(seed);
        int numRooms = NUM + r.nextInt(BOUND);
        Room room = new Room();
        room.addRooms(r, numRooms, world);
        room.createHalls(world);
        addAgent(0, 0, world,  barbie);
        addKen(world);
        addDoor(world,  barbie);
        if (barbie) {
            addCherry(world);
            addCherry(world);
            addCherry(world);
        }
        if (!barbie) {
            addOtherKen(world);
        }

        return world;
    }


    public void drawMenu(Boolean j, Boolean s){
        StdDraw.clear(new Color(192, 21, 185));
        StdDraw.setPenColor(new Color(236, 220, 178));
        Font fontSmall = new Font("Monaco", Font.PLAIN, 20);
        StdDraw.setFont(fontSmall);
        StdDraw.textLeft(0, 1, "E: English | I: Italiano | J:日本語");
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        Font fontBigger = new Font("Monaco", Font.BOLD, 35);
        if (j) {
            StdDraw.setFont(fontBigger);
            StdDraw.text(WIDTH / 2, HEIGHT / 2 + 10, "メーニュー");
            StdDraw.setFont(fontBig);
            StdDraw.textLeft(20, HEIGHT / 2 - 12, "B: バービー");
            StdDraw.textRight(WIDTH - 20, HEIGHT/ 2 - 12, "K: ケン");
            StdDraw.text(WIDTH / 2, HEIGHT / 2+5, "N: スタート");
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "L: ロードします");
            StdDraw.text(WIDTH / 2, HEIGHT / 2 - 5, "Q: クイットします");
            StdDraw.setFont(fontSmall);
            StdDraw.textRight(WIDTH, 1, "P: ピンクモード");
        }
        if (s) {
            StdDraw.setFont(fontBigger);
            StdDraw.text(WIDTH / 2, HEIGHT / 2 + 10, "Menu");
            StdDraw.setFont(fontBig);
            StdDraw.textLeft(20, HEIGHT / 2 - 12, "B: Barbie");
            StdDraw.textRight(WIDTH - 20, HEIGHT/ 2 - 12, "K: Ken");
            StdDraw.text(WIDTH / 2, HEIGHT / 2 + 5, "N: Nuovo gioco");
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "L: Caricare gioco");
            StdDraw.text(WIDTH / 2, HEIGHT / 2 - 5, "Q: Esci e guardare");
            StdDraw.setFont(fontSmall);
            StdDraw.textRight(WIDTH, 1, "P: Pinkmode");
        }
        if (!s && !j) {
            StdDraw.setFont(fontBigger);
            StdDraw.text(WIDTH / 2, HEIGHT / 2 + 10, "Menu");
            StdDraw.setFont(fontBig);
            StdDraw.textLeft(20, HEIGHT / 2 - 12, "B: Barbie");
            StdDraw.textRight(WIDTH - 20, HEIGHT/ 2 - 12, "K: Ken");
            StdDraw.text(WIDTH / 2, HEIGHT / 2 + 5, "N: New Game");
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "L: Load Game");
            StdDraw.text(WIDTH / 2, HEIGHT / 2 - 5, "Q: Quit");
            StdDraw.setFont(fontSmall);
            StdDraw.textRight(WIDTH, 1, "P: Pinkmode");
        }
        StdDraw.show();
    }


    public void drawSeedPrompt(Boolean j, Boolean s, Boolean b, Boolean k){
        StdDraw.clear(new Color(192, 21, 185));
        StdDraw.setPenColor(new Color(236, 220, 178));
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);
        if (j) {
            if (b) {
                StdDraw.text(WIDTH/2, HEIGHT/2 + 10, "ハイ, バービ！");
            }
            if (k) {
                StdDraw.text(WIDTH/2, HEIGHT/2 + 10, "ハイ, ケン！");
            }
            StdDraw.text(WIDTH/2, HEIGHT/2, "数字を入力して, 'S' を押してください");
        }
        if (s) {
            if (b) {
                StdDraw.text(WIDTH/2, HEIGHT/2 + 10, "Ciao, barbie！");
            }
            if (k) {
                StdDraw.text(WIDTH/2, HEIGHT/2 + 10, "Ciao, Ken！");
            }
            StdDraw.text(WIDTH/2, HEIGHT/2, "Inserire un numero e premere S.");
        } if(!s&&!j) {
            if (b) {
                StdDraw.text(WIDTH/2, HEIGHT/2 + 10, "Hi, Barbie！");
            }
            if (k) {
                StdDraw.text(WIDTH/2, HEIGHT/2 + 10, "Hi, Ken！");
            }
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "Please enter a number, and press S.");
        }
        StdDraw.show();
    }

    public void drawSeed(Character see, Boolean j, Boolean it, Boolean b){
        String s = String.valueOf(see);
        seed += s;
        StdDraw.clear(new Color(192, 21, 185));
        StdDraw.setPenColor(new Color(236, 220, 178));
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);
        if (j) {
            if (b) {
                StdDraw.text(WIDTH/2, HEIGHT/2 + 10, "ハイ, バービ！");
                StdDraw.text(WIDTH / 2, HEIGHT / 2, "数字を入力して, 'S' を押してください");
                StdDraw.text(WIDTH / 2, HEIGHT / 2 - 10, seed);
            }
            if (!b) {
                StdDraw.text(WIDTH/2, HEIGHT/2 + 10, "ハイ, ケン！");
                StdDraw.text(WIDTH / 2, HEIGHT / 2, "数字を入力して, 'S' を押してください");
                StdDraw.text(WIDTH / 2, HEIGHT / 2 - 10, seed);
            }
        }
        if(it) {
            if (b) {
                StdDraw.text(WIDTH/2, HEIGHT/2 + 10, "Ciao, Barbie！");
                StdDraw.text(WIDTH / 2, HEIGHT / 2, "Inserire un numero e premere S.");
                StdDraw.text(WIDTH / 2, HEIGHT / 2 - 10, seed);
            }
            if (!b) {
                StdDraw.text(WIDTH/2, HEIGHT/2 + 10, "Ciao, Ken！");
                StdDraw.text(WIDTH / 2, HEIGHT / 2, "Inserire un numero e premere S.");
                StdDraw.text(WIDTH / 2, HEIGHT / 2 - 10, seed);
            }
        }
        if(!j&&!it){
            if (b) {
                StdDraw.text(WIDTH/2, HEIGHT/2 + 10, "Hi, Barbie！");
                StdDraw.text(WIDTH / 2, HEIGHT / 2, "Please enter a number, and press S.");
                StdDraw.text(WIDTH / 2, HEIGHT / 2 - 10, seed);
            }
            if (!b) {
                StdDraw.text(WIDTH/2, HEIGHT/2 + 10, "Hi, Ken！");
                StdDraw.text(WIDTH / 2, HEIGHT / 2, "Please enter a number, and press S.");
                StdDraw.text(WIDTH / 2, HEIGHT / 2 - 10, seed);
            }
        }
        StdDraw.show();
    }

    public void drawYouWon(Boolean j, Boolean s) {
        StdDraw.clear(new Color(192, 21, 185));
        StdDraw.setPenColor(new Color(236, 220, 178));
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);
        if  (!barbie) {
            if (j) {
                StdDraw.text(WIDTH / 2, HEIGHT / 2 + 5, "バービーを見つかった！");
                StdDraw.text(WIDTH / 2, HEIGHT / 2, "優勝おめでとうございます！");
            }
            if (s) {
                StdDraw.text(WIDTH / 2, HEIGHT / 2 + 5, "Congratulazioni, l'hai incontrata!");
                StdDraw.text(WIDTH / 2, HEIGHT / 2, "Hai vinto!");
            }
            if (!s && !j) {
                StdDraw.text(WIDTH / 2, HEIGHT / 2 + 5, "Congratulations, you found Barbie!");
                StdDraw.text(WIDTH / 2, HEIGHT / 2, "You won!");
            }
            StdDraw.show();
            StdDraw.pause(3000);
            drawPlayAgain(j, s);
            StdDraw.pause(5000);
        }
        if (barbie) {
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

    }

    public void drawMission(Boolean j, Boolean s, Boolean b, Boolean k){
        StdDraw.clear(new Color(192, 21, 185));
        StdDraw.setPenColor(new Color(236, 220, 178));
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);
        if (j) {
            if (b) {
                StdDraw.text(WIDTH/2, HEIGHT/2, "現実世界へのポータルを探して！");
            }
            if (k) {
                StdDraw.text(WIDTH/2, HEIGHT/2, "バービーを探して！");
            }
        }
        if (s) {
            if (b) {
                StdDraw.text(WIDTH/2, HEIGHT/2, "Cerca il portale per il mondo reale！");
            }
            if (k) {
                StdDraw.text(WIDTH/2, HEIGHT/2, "Cerca Barbie！");
            }
        } if(!s&&!j) {
            if (b) {
                StdDraw.text(WIDTH/2, HEIGHT/2, "Find the portal to the real world！");
            }
            if (k) {
                StdDraw.text(WIDTH/2, HEIGHT/2, "Find barbie！");
            }
        }
        StdDraw.show();
        StdDraw.pause(2500);
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

    public void drawWelcomeToTheRealWorld(TETile[][] world){
        StdDraw.clear(new Color(192, 21, 185));
        StdDraw.setPenColor(new Color(236, 220, 178));
        Font fontSmall = new Font("Monaco", Font.PLAIN, 20);
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);
        StdDraw.pause(1500);
        if (jpn) {
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "現実世界へようこそ、バービー。。。");
        }
        if (it) {
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "Benvenuta al mondo reale, Barbie...");
        }
        if (!it && !jpn) {
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "Welcome to the real world, Barbie...");
        }
        StdDraw.show();
        StdDraw.pause(500);
        StdDraw.clear(new Color(141, 24, 136));
        StdDraw.pause(500);
        StdDraw.clear(new Color(82, 18, 80));
        StdDraw.pause(500);
        StdDraw.clear(new Color(58, 11, 56));
        StdDraw.pause(500);
        StdDraw.clear(new Color(38, 9, 37));
        StdDraw.pause(500);
        if (jpn) {
            StdDraw.clear(new Color(7, 2, 7));
            StdDraw.setPenColor(new Color(201, 187, 146));
            StdDraw.text(WIDTH / 2, HEIGHT / 2  + 5, "ここの状況はあなたが思っているほど");
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "素晴らしいものではない。。。");
            StdDraw.text(WIDTH / 2, HEIGHT / 2 - 5, "注意していれば乗り越えることができます。。。");
        }
        if (it) {
            StdDraw.clear(new Color(5, 1, 5));
            StdDraw.setPenColor(new Color(201, 187, 146));
            StdDraw.text(WIDTH / 2, HEIGHT / 2 + 5, "Le cose qui non sono così belle come avresti potuto pensare...");
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "Puoi sopravvivere, se stai attento...");
        }
        if (!it && !jpn) {
            StdDraw.clear(new Color(12, 3, 12));
            StdDraw.setPenColor(new Color(201, 187, 146));
            StdDraw.text(WIDTH / 2, HEIGHT / 2 + 5, "Things here aren't as great as you might think.");
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "But you can make it through, if you are careful...");
        }
        StdDraw.show();
        StdDraw.pause(3500);
        level2 = true;
    }

    public void drawBeachOff(TETile[][] world){
        StdDraw.clear(new Color(192, 21, 185));
        StdDraw.setPenColor(new Color(236, 220, 178));
        Font fontBig = new Font("Monaco", Font.BOLD, 30);
        StdDraw.setFont(fontBig);
        if (jpn) {
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "ビーチオフをしよう！");
        }
        if (it) {
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "Facciamo un beach off!");
        }
        if (!jpn && !it) {
            StdDraw.text(WIDTH / 2, HEIGHT / 2, "Let's beach off!");
        }
        StdDraw.show();
        StdDraw.pause(1500);
        beachOff = true;
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

    public void pinkOutMode() {
        double[] rec1x = new double[]{agentCordX+3, agentCordX - 3, agentCordX - 3, agentCordX + 3};
        double[] rec1y = new double[]{45, 45, agentCordY + 3, agentCordY + 3};
        double[] rec2x = new double[]{agentCordX-3, agentCordX+3, agentCordX+3, agentCordX-3};
        double[] rec2y = new double[]{agentCordY-3, agentCordY-3, 0, 0};
        double[] rec3x = new double[]{0, agentCordX-3, agentCordX-3, 0};
        double[] rec3y = new double[]{0, 0, 45, 45};
        double[] rec4x = new double[]{agentCordX+3,agentCordX+3,80,80};
        double[] rec4y = new double[]{0,45,45,0};

        StdDraw.setPenColor(new Color(192, 21, 185));
        StdDraw.filledPolygon(rec1x, rec1y);
        StdDraw.filledPolygon(rec2x, rec2y);
        StdDraw.filledPolygon(rec3x, rec3y);
        StdDraw.filledPolygon(rec4x, rec4y);
        StdDraw.show();
    }

    public void addDoor(TETile[][] world, Boolean  b){
        Random r = new Random(5678);
        int x = r.nextInt(40,75);
        int y = r.nextInt(30,45);
        while (world[x][y] != Tileset.WALL) {
            x--;
            y--;
        }
        if (b && x > 2 && y > 2) {
            world[x][y] = Tileset.LOCKED_DOOR;
            door1Cord = world[x][y];
        }
        if (!b && x > 2 && y > 2) {
            world[x][y] = Tileset.SECRETBARBIE;
        }
    }

    public void addNewDoor(TETile[][] world) {
        doorReached = false;
        Random r = new Random(5678);
        int x = 0;
        int y = 0;
        while (world[x][y] != Tileset.WALL) {
            x++;
            y++;
        }
        world[x][y] = Tileset.LOCKED_DOOR;
        door2Cord = world[x][y];
    }

    public void addCherry(TETile[][] world){
        Random r = new Random(98765432);
        int x = r.nextInt(25,75);
        int y = r.nextInt(25,45);
        while (world[x][y] != Tileset.FLOOR) {
            x--;
            y--;
        }
        if (x > 3 && y > 3) {
            world[x][y] = Tileset.CHERRY;
        }
    }

    public void addOtherKen(TETile[][] world){
        Random r = new Random(98765432);
        int x = r.nextInt(25,75);
        int y = r.nextInt(25,45);
        while (world[x][y] != Tileset.FLOOR) {
            x--;
            y--;
        }
        if (x > 3 && y > 3) {
            world[x][y] = Tileset.KEN2;
        }
    }

    public void addKen(TETile[][] world) {
        int x = 0;
        int y = HEIGHT-1;
        while (world[x][y] != Tileset.FLOOR) {
            x++;
            y--;
        }
        if (barbie) {
            world[x][y] = Tileset.KEN;
        }
        if (!barbie) {
            world[x][y] = Tileset.HORSE;
        }

    }


    public void drawHUD(Boolean j , Boolean s) {
        if (!gameOver) {
            StdDraw.setPenColor(new Color(224, 95, 218));
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


    public TETile[][] addAgent(int x, int y, TETile[][] world, Boolean b) {
        gameOver = false;
        while (world[x][y] != Tileset.FLOOR) {
            x++;
            y++;
        }
        agentCord = world[x][y];
        agentCordX = x;
        agentCordY = y;
        if (b) {
            world[x][y] = Tileset.BARBIE;
        } else {
            world[x][y] = Tileset.KEN;
        }
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
            if (world[startx - 1][starty] == Tileset.LOCKED_DOOR) {
                world[startx - 1][starty] = Tileset.UNLOCKED_DOOR;
                doorReached = true;
                score += 50;
            }
            if (world[startx - 1][starty] == Tileset.UNLOCKED_DOOR) {
                level++;
                drawWelcomeToTheRealWorld(world);
            }
            if (world[startx - 1][starty] == Tileset.KEN2) {
                drawBeachOff(world);
            }
            if (world[startx + 1][starty] == Tileset.SECRETBARBIE) {
                world[startx + 1][starty] = Tileset.BARBIE;
                kenReached = true;
                score += 500;
            }
            if (world[startx - 1][starty] == Tileset.CHERRY) {
                world[startx - 1][starty] = Tileset.FLOOR;
                score += 30;
                health += 100;
            }
            else if (world[startx - 1][starty] == Tileset.FLOOR) {
                if (b) {
                    world[startx - 1][starty] = Tileset.BARBIE;
                    world[startx][starty] = Tileset.FLOOR;
                }
                if (!b) {
                    world[startx - 1][starty] = Tileset.KEN;
                    world[startx][starty] = Tileset.FLOOR;
                }
                agentCordX = startx - 1;
            }
        }
        return;
    }

    public void moveUp(TETile[][] world, Boolean b){
        int startx = getAgentCordX();
        int starty = getAgentCordY();

        if (starty + 1 < HEIGHT) {
            if (world[startx][starty + 1] == Tileset.LOCKED_DOOR) {
                world[startx][starty + 1] = Tileset.UNLOCKED_DOOR;
                doorReached  = true;
                score+=50;
            }
            if (world[startx][starty + 1] == Tileset.KEN2) {
                drawBeachOff(world);
            }
            if (world[startx][starty + 1] == Tileset.UNLOCKED_DOOR) {
                level++;
                drawWelcomeToTheRealWorld(world);
            }
            if (world[startx][starty + 1] == Tileset.SECRETBARBIE) {
                world[startx][starty + 1] = Tileset.BARBIE;
                kenReached = true;
                score+=500;
            }
            if (world[startx][starty + 1] == Tileset.CHERRY) {
                world[startx][starty + 1] = Tileset.FLOOR;
                score+=30;
                health+=100;
            }
            if (world[startx][starty + 1] == Tileset.FLOOR) {
                if (b) {
                    world[startx][starty + 1] = Tileset.BARBIE;
                    world[startx][starty] = Tileset.FLOOR;
                }
                if (!b) {
                    world[startx][starty + 1] = Tileset.KEN;
                    world[startx][starty] = Tileset.FLOOR;
                }
                agentCordY = starty + 1;
            }
        }
    }

    public void moveDown(TETile[][] world, Boolean b){
        int startx = getAgentCordX();
        int starty = getAgentCordY();
        if (starty - 1 > 0) {
            if (world[startx][starty - 1] == Tileset.LOCKED_DOOR) {
                world[startx][starty - 1] = Tileset.UNLOCKED_DOOR;
                doorReached  = true;
                score+=50;
            }
            if (world[startx][starty - 1] == Tileset.UNLOCKED_DOOR) {
                level++;
                drawWelcomeToTheRealWorld(world);
            }
            if (world[startx][starty - 1] == Tileset.KEN2) {
                drawBeachOff(world);
            }
            if (world[startx][starty - 1] == Tileset.SECRETBARBIE) {
                world[startx][starty - 1] = Tileset.BARBIE;
                kenReached = true;
                score+=500;
            }
            if (world[startx][starty - 1] == Tileset.CHERRY) {
                world[startx][starty - 1] = Tileset.FLOOR;
                score += 30;
                health += 100;
            }
            if (world[startx][starty - 1] == Tileset.FLOOR) {
                if (b) {
                    world[startx][starty - 1] = Tileset.BARBIE;
                    world[startx][starty] = Tileset.FLOOR;
                }
                if (!b){
                    world[startx][starty - 1] = Tileset.KEN;
                    world[startx][starty] = Tileset.FLOOR;
                }
                agentCordY = starty - 1;
            }
        }
    }

    public void moveRight(TETile[][] world, Boolean b){
        int startx = getAgentCordX();
        int starty = getAgentCordY();
        if (startx + 1 < WIDTH - 1) {
            if (world[startx + 1][starty] == Tileset.LOCKED_DOOR) {
                world[startx + 1][starty] = Tileset.UNLOCKED_DOOR;
                doorReached = true;
                score += 50;
            }
            if (world[startx + 1][starty] == Tileset.KEN2) {
                drawBeachOff(world);
            }
            if (world[startx + 1][starty] == Tileset.UNLOCKED_DOOR) {
                level++;
                drawWelcomeToTheRealWorld(world);
            }
            if (world[startx + 1][starty] == Tileset.SECRETBARBIE) {
                world[startx + 1][starty] = Tileset.BARBIE;
                kenReached = true;
                score += 500;
            }
            if (world[startx + 1][starty] == Tileset.CHERRY) {
                world[startx + 1][starty] = Tileset.FLOOR;
                score += 30;
                health += 100;
            }
            if (world[startx + 1][starty] == Tileset.FLOOR) {
                if (b) {
                    world[startx + 1][starty] = Tileset.BARBIE;
                    world[startx][starty] = Tileset.FLOOR;
                }
                if (!b) {
                    world[startx + 1][starty] = Tileset.KEN;
                    world[startx][starty] = Tileset.FLOOR;
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
