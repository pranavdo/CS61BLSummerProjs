package byow.Core;

import byow.InputDemo.KeyboardInputSource;
import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;
import java.util.Random;
import java.io.File;

import static byow.Core.Game.*;
import static byow.Core.RWGame.*;
import static byow.Core.Utils.*;

public class Engine {
    private TERenderer ter = new TERenderer();
    public static final int WIDTH = 80;
    public static final int HEIGHT = 45;
    public static final int BOUND = 50;
    public static final int NUM = 20;
    private TETile[][] world = new TETile[0][0];
    private TETile[][] realWorld = new TETile[0][0];
    private TETile[][] beachWorld = new TETile[0][0];
    private TETile[][] keyWorld = new TETile[0][0];
    private Game game = new Game();
    private RWGame rwgame = new RWGame();
    private Random r;
    private boolean pinkMode = false;
    public static boolean jpn = false;
    public static boolean it = false;
    private boolean gameStarted =  false;
    public static final File CWD = new File(System.getProperty("user.dir"));
    private int count = 0;
    private int count1 = 0;
    private int count2 = 0;
    public static boolean ken = false;
    public static boolean barbie = true;
    public static boolean playAgain = false;
    private TERenderer ter2 = new TERenderer();
    private boolean realWorldMade = false;
    public BeachOff bOff  = new BeachOff();
    public KeyChallenge kc = new KeyChallenge();


    public void interactWithKeyboard() {
        game.drawMenu(jpn, it);
        KeyboardInputSource keyboardInput = new KeyboardInputSource();
        String seed = "";
        String charsTyped = "";
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = keyboardInput.getNextKey();
                if (c == 'B' && !gameStarted) {
                    barbie = true;
                    ken = false;
                }
                if (c == 'K' && !gameStarted) {
                    ken = true;
                    barbie = false;
                }
                if (c == 'J') {
                    jpn = !jpn;
                    it = false;
                    if(!gameStarted) {
                        game.drawMenu(jpn, it);
                    }
                }
                if (c == 'I') {
                    it = !it;
                    jpn = false;
                    if(!gameStarted) {
                        game.drawMenu(jpn, it);
                    }
                }
                if (c == 'E') {
                    jpn = false;
                    it = false;
                    if(!gameStarted) {
                        game.drawMenu(jpn, it);
                    }
                }
                if (c == 'Y' && playAgain) {
                    game.drawMenu(jpn, it);
                    count1++;
                    playAgain = false;
                    barbie = true;
                    ken = false;
                }
                if (c == 'N' && playAgain) {
                    count1++;
                    game.quitGame();
                }
                if (c == 'S' && beachOff) {
                    bOff.rockPaperScissors('S');
                }
                if (c == 'R' && beachOff) {
                    bOff.rockPaperScissors('R');
                }
                if (c == 'P' && beachOff) {
                    bOff.rockPaperScissors('P');
                }
                if (c == 'N' && !playAgain) {
                    gameStarted = true;
                    charsTyped += c;
                    game.drawSeedPrompt(jpn, it, barbie, ken);
                    char n = keyboardInput.getNextKey();
                    while (n != 'S') {
                        seed += n;
                        charsTyped += n;
                        game.drawSeed(n, jpn, it, barbie);
                        n = keyboardInput.getNextKey();
                    }
                    if (n == 'S') {
                        world = initBarbieWorld();
                        game.createGame(seed, world, barbie, jpn, it);
                        charsTyped += n;
                        game.drawMission(jpn, it, barbie, ken);
                    }
                    charsTyped += c;
                }

                if (c == 'A' || c == 'S' || c == 'W' || c == 'D') {
                    if (!level2 && !keyChallenge) {
                        game.moveAgent(c, world, barbie);
                    }
                    if (level2 && realWorldMade && !keyChallenge) {
                        rwgame.moveAgent(c, realWorld, barbie);
                    }
                    if (keyChallenge) {
                        kc.moveAgent(c, keyWorld, barbie);
                    }
                    charsTyped += c;
                }
                if (c == 'P' && !beachOff) {
                    pinkMode = !pinkMode;
                    charsTyped += c;
                }
                if (c == 'L') {
                    charsTyped = loadGame();
                }
                if (c == ':') {
                    char q = keyboardInput.getNextKey();
                    if (q == 'Q') {
                        saveGame(charsTyped);
                        game.quitGame();
                        break;
                    }
                }
            }
            if (gameStarted) {
                if (!level2 && !beachOff) {
                    ter.renderFrame(world);
                    if (game.getKenReached() && count < 1) {
                        game.drawYouWon(jpn, it);
                        playAgain = true;
                        gameStarted = false;
                        count++;
                    }
                    if (game.getDoorReached() && count < 1) {
                        game.addNewDoor(world);
                        game.addCherry(world);
                        game.addCherry(world);
                        game.addCherry(world);
                        count++;
                    }
                    if (pinkMode) {
                        game.pinkOutMode();
                        StdDraw.pause(100);
                    } else {
                        game.drawHUD(jpn, it);
                        mouseOver(jpn, it);
                    }
                }
                if (beachOff) {
                    while(count1 < 1) {
                        beachWorld = initBeachOff();
                        bOff.createBeachOff(beachWorld);
                        count1++;
                    }
                    ter.renderFrame(beachWorld);
                    game.drawHUD(jpn, it);
                    mouseOver(jpn, it);
                    rockPaperScissors();

                }
                if (level2) {
                    while (count1 < 1) {
                        realWorld = initRealWorld();
                        rwgame.createRealWorld(realWorld);
                        realWorldMade = true;
                        count1++;
                    }
                    if (keyChallenge) {
                        while(count2 < 1) {
                            keyWorld = initKeyChallenge();
                            kc.createKeyChallenge(keyWorld);
                            count2++;
                            kc.gameStart();
                        }
                        ter.renderFrame(keyWorld);
                    }
                    if(!keyChallenge) {
                        ter.renderFrame(realWorld);
                    }
                    if (pinkMode) {
                        rwgame.pinkOutMode();
                        StdDraw.pause(100);
                    }
                    rwgame.drawHUD(jpn, it);
                    mouseOver(jpn, it);
                }
                StdDraw.show();
            }
        }
    }


    public TETile[][] interactWithInputString(String input) {
        game.drawMenu(jpn, it);
        String inpu = input.toUpperCase();
        char[] inp = inpu.toCharArray();
        for (int j = 0; j < inp.length; j++) {
            if (inp[j] == 'B' && !gameStarted) {
                barbie = true;
                ken = false;
            }
            if (inp[j] == 'K' && !gameStarted) {
                ken = true;
                barbie = false;
            }
            if (inp[j] == 'E') {
                jpn = false;
                it = false;
            }
            if (inp[j] == 'I') {
                it = !it;
                jpn = false;
            }
            if (inp[j] == 'J') {
                jpn = !jpn;
                it = false;
            }
            if (inp[j] == 'Y' && playAgain) {
                game.drawMenu(jpn, it);
                count1++;
                playAgain = false;
                barbie = true;
                ken = false;
            }
            if (inp[j] == 'N' && playAgain) {
                count1++;
                game.quitGame();
            }
            if (inp[j] == 'N') {
                gameStarted = true;
                String numString = "";
                int i = 1;
                while (inp[i] != 'S') {
                    numString += inp[i];
                    i++;
                }
                if (inp[i]  == 'S') {
                    world = initBarbieWorld();
                    game.createGame(numString, world, barbie, jpn, it);
                }
            } else if (inp[j] == 'A' || inp[j] == 'S' || inp[j] == 'W' || inp[j] == 'D') {
                if (!level2) {
                    game.moveAgent(inp[j], world,  barbie);
                }
                if (level2 && realWorldMade) {
                    rwgame.moveAgent(inp[j], realWorld, barbie);
                }
            } else if (inp[j] == 'P') {
                pinkMode = !pinkMode;
            } else if (inp[j] == 'L') {
                inpu = loadGame();
            } else if (inp[j] == ':' && (j + 1 < inp.length) && inp[j + 1] == 'Q') {
                saveGame(inpu);
                break;
            }
        }
        if (gameStarted) {
            ter.renderFrame(world);
            if (game.getKenReached() && count < 1) {
                game.drawYouWon(jpn, it);
                playAgain = true;
                gameStarted = false;
                count++;
            }
            if (game.getDoorReached() && count < 1) {
                game.addNewDoor(world);
                game.addCherry(world);
                game.addCherry(world);
                game.addCherry(world);
                count++;
            }
            if (pinkMode) {
                game.pinkOutMode();
                StdDraw.pause(100);
            } else {
                game.drawHUD(jpn, it);
                mouseOver(jpn, it);
            }
            if (level2) {
                while (count1 < 1) {
                    realWorld = initRealWorld();
                    rwgame.createRealWorld(realWorld);
                    realWorldMade = true;
                    count1++;
                }
                if (realWorldMade) {
                    ter.renderFrame(realWorld);
                    if (pinkMode) {
                        rwgame.pinkOutMode();
                        StdDraw.pause(100);
                    } else {
                        rwgame.drawHUD(jpn, it);
                        mouseOver(jpn, it);
                    }
                }
            }
            StdDraw.show();
        }
        return world;
    }


    public String loadGame() {
        File gameFile = join(CWD, "gameFile.txt");
        interactWithInputString(readObject(gameFile, String.class));
        return readObject(gameFile, String.class);
    }

    public void saveGame(String s) {
        File gameFile = join(CWD, "gameFile.txt");
        writeObject(gameFile, s);
    }

    public TETile[][] initBarbieWorld() {
        ter.initialize(WIDTH, HEIGHT);
        world = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                world[x][y] = Tileset.NOTHING;
            }
        }
        Background b = new Background();
        b.makeBarbieBackground(world);
        return world;
    }

    public TETile[][] initRealWorld() {
        realWorld = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                realWorld[x][y] = Tileset.RWNOTHING;
            }
        }
        return realWorld;
    }

    public TETile[][] initKeyChallenge() {
        keyWorld = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                keyWorld[x][y] = Tileset.RWNOTHING;
            }
        }
        return keyWorld;
    }

    public TETile[][] initBeachOff() {
        beachWorld = new TETile[WIDTH][HEIGHT];
        for (int x = 0; x < WIDTH; x++) {
            for (int y = 0; y < HEIGHT; y++) {
                beachWorld[x][y] = Tileset.BONOTHING;
            }
        }
        Background b = new Background();
        b.makeBeachOffBackground(beachWorld);
        return beachWorld;
    }

    public void rockPaperScissors(){
        StdDraw.setPenColor(new Color(192, 21, 185));
        Font fontSmall = new Font("Monaco", Font.BOLD, 20);
        StdDraw.setFont(fontSmall);
        if (it){
            StdDraw.text(WIDTH/2, HEIGHT - 6, "Giochiamo a sasso carta forbici.");
            StdDraw.text(WIDTH/2, HEIGHT - 8, "R: Roccia, P: Carta, S: Forbici.");
        }
        if (jpn) {
            StdDraw.text(WIDTH/2, HEIGHT - 6, "じゃんけんをしましょう。");
            StdDraw.text(WIDTH/2, HEIGHT - 8, "R: 石, P: 紙, S: はさみ.");
        }
        if (!jpn&&!it) {
            StdDraw.text(WIDTH/2, HEIGHT - 6, "Let's play rock paper scissors.");
            StdDraw.text(WIDTH/2, HEIGHT - 8, "R: Rock    P: Paper    S: Scissors.");
        }
        StdDraw.show();
        StdDraw.pause(200);
    }


    public void mouseOver(Boolean j, Boolean i){
        StdDraw.setPenColor(new Color(192, 21, 185));
        Font fontSmall = new Font("Monaco", Font.BOLD, 20);
        StdDraw.setFont(fontSmall);

        int x = (int) Math.round(StdDraw.mouseX());
        int y = (int) Math.round(StdDraw.mouseY());
        if (!level2) {
        if (x < WIDTH && y < HEIGHT) {
            if (world[x][y] == Tileset.WALL) {
                if (i) {
                    StdDraw.textRight(WIDTH, 2, "parete");
                }
                if (j) {
                    StdDraw.textRight(WIDTH, 2, "壁");
                }
                if (!i && !j) {
                    StdDraw.textRight(WIDTH, 2, "wall");
                }
            }
            if (world[x][y] == Tileset.HORSE) {
                if (i) {
                    StdDraw.textRight(WIDTH, 2, "cavallino");
                }
                if (j) {
                    StdDraw.textRight(WIDTH, 2, "うま！");
                }
                if (!i && !j) {
                    StdDraw.textRight(WIDTH, 2, "you're kenough");
                }
            }
            if (world[x][y] == Tileset.FLOOR) {
                if (i) {
                    StdDraw.textRight(WIDTH, 2, "pavimento");
                }
                if (j) {
                    StdDraw.textRight(WIDTH, 2, "床");
                }
                if (!i && !j) {
                    StdDraw.textRight(WIDTH, 2, "floor");
                }
            }

            if (world[x][y] == Tileset.UNLOCKED_DOOR) {
                if (i) {
                    StdDraw.textRight(WIDTH, 2, "porto aperto");
                }
                if (j) {
                    StdDraw.textRight(WIDTH, 2, "鍵なしドア");
                }
                if (!i && !j) {
                    StdDraw.textRight(WIDTH, 2, "unlocked door");
                }
            }
            if (world[x][y] == Tileset.LOCKED_DOOR) {
                if (i) {
                    StdDraw.textRight(WIDTH, 2, "porto chiuso");
                }
                if (j) {
                    StdDraw.textRight(WIDTH, 2, "鍵付きドア");
                }
                if (!i && !j) {
                    StdDraw.textRight(WIDTH, 2, "locked door");
                }
            }
            if (world[x][y] == Tileset.BARBIE) {
                if (i) {
                    StdDraw.textRight(WIDTH, 2, "ciao, barbie");
                }
                if (j) {
                    StdDraw.textRight(WIDTH, 2, "ハイ、バービー！");
                }
                if (!i && !j) {
                    StdDraw.textRight(WIDTH, 2, "hi, barbie");
                }
            }
            if (world[x][y] == Tileset.KEN) {
                if (i) {
                    StdDraw.textRight(WIDTH, 2, "ciao, ken");
                }
                if (j) {
                    StdDraw.textRight(WIDTH, 2, "ハイ、ケン！");
                }
                if (!i && !j) {
                    StdDraw.textRight(WIDTH, 2, "hi, ken");
                }
            }
            if (world[x][y] == Tileset.SECRETBARBIE) {
                if (i) {
                    StdDraw.textRight(WIDTH, 2, "ciao ken");
                }
                if (j) {
                    StdDraw.textRight(WIDTH, 2, "ハイ、ケン");
                }
                if (!i && !j) {
                    StdDraw.textRight(WIDTH, 2, "hi ken");
                }
            }
            if (world[x][y] == Tileset.CHERRY) {
                if (i) {
                    StdDraw.textRight(WIDTH, 2, "ciliegia. mmm");
                }
                if (j) {
                    StdDraw.textRight(WIDTH, 2, "チェリー。美味しそう");
                }
                if (!i && !j) {
                    StdDraw.textRight(WIDTH, 2, "cherry. yum");
                }
            }
            if (keyObtained) {
                if (keyWorld[x][y] == Tileset.SECRETCHERRY) {
                    if (i) {
                        StdDraw.textRight(WIDTH, 2, "mmm... cos'è questo?");
                    }
                    if (j) {
                        StdDraw.textRight(WIDTH, 2, "あー…これは何？");
                    }
                    if (!i && !j) {
                        StdDraw.textRight(WIDTH, 2, "hmm... what's this?");
                    }
                }
            }
        }
            StdDraw.show();
        }
    }
}
