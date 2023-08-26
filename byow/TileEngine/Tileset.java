package byow.TileEngine;

import java.awt.Color;

/**
 * Contains constant tile objects, to avoid having to remake the same tiles in different parts of
 * the code.
 *
 * You are free to (and encouraged to) create and add your own tiles to this file. This file will
 * be turned in with the rest of your code.
 *
 * Ex:
 *      world[x][y] = Tileset.FLOOR;
 *
 * The style checker may crash when you try to style check this file due to use of unicode
 * characters. This is OK.
 */

public class Tileset {


    public static final TETile AVATAR = new TETile('@', Color.white, Color.black, "you");

    public static final TETile WALL = new TETile('#', new Color(216, 128, 128), Color.darkGray,
            "wall", "byow/TileEngine/tiles/squarewall.png");

    public static final TETile RWWALL = new TETile('#', new Color(216, 128, 128), Color.darkGray,
            "wall", "byow/TileEngine/tiles/realworldsquare.png");
    public static final TETile RWFLOOR = new TETile('f', new Color(216, 128, 128), Color.darkGray,
            "wall", "byow/TileEngine/tiles/realworldfloor.png");
    public static final TETile RWNOTHING = new TETile(' ', new Color(115, 141, 180), new Color(115, 141, 180), "nothing");

    public static final TETile NOTHING = new TETile(' ', new Color(249, 202, 250), new Color(249, 202, 250), "background pink");
    public static final TETile SHADOWPINK = new TETile(' ', new Color(245, 193, 235), new Color(245, 193, 235), "shadow pink");
    public static final TETile ORANGE = new TETile(' ', new Color(241, 164, 92), new Color(241, 164, 92), "nothing");
    public static final TETile MAGENTA = new TETile(' ', new Color(247, 154, 242), new Color(247, 154, 242), "nothing");
    public static final TETile LIGHTYELLOW = new TETile(' ', new Color(250, 241, 201), new Color(250, 241, 201), "nothing");
    public static final TETile YELLOW = new TETile(' ', new Color(247, 231, 179), new Color(247, 231, 179), "nothing");
    public static final TETile PURPLE = new TETile(' ', new Color(198, 136, 254), new Color(198, 136, 254), "nothing");
    public static final TETile LIGHTBLUE = new TETile(' ', new Color(186, 217, 255), new Color(186, 217, 255), "nothing");

    public static final TETile BONOTHING = new TETile(' ', new Color(186, 235, 255), new Color(186, 235, 255), "nothing");



    public static final TETile TAN = new TETile(' ', new Color(255, 228, 171), new Color(255, 228, 171), "nothing");
    public static final TETile BLUE = new TETile(' ', new Color(64, 197, 250), new Color(64, 197, 250), "nothing");
    public static final TETile WHITE = new TETile(' ', Color.white, Color.white, "nothing");


    public static final TETile GRASS = new TETile('"', Color.green, Color.black, "grass");
    public static final TETile WATER = new TETile('≈', Color.blue, Color.black, "water");
    public static final TETile FLOWER = new TETile('❀', Color.magenta, Color.pink, "flower");
    public static final TETile LOCKED_DOOR = new TETile('█', Color.magenta, new Color(249, 202, 250),
            "locked door");
    public static final TETile UNLOCKED_DOOR = new TETile('▢', Color.magenta, new Color(249, 202, 250),
            "unlocked door");
    public static final TETile RWLOCKED_DOOR = new TETile('█', Color.lightGray, Color.darkGray,
            "locked door");
    public static final TETile RWUNLOCKED_DOOR = new TETile('▢', Color.lightGray, Color.darkGray,
            "unlocked door");
    public static final TETile SAND = new TETile('▒', Color.yellow, Color.black, "sand", "byow/TileEngine/tiles/sand.png");
    public static final TETile MOUNTAIN = new TETile('▲', Color.gray, Color.black, "mountain");
    public static final TETile TREE = new TETile('♠', Color.green, Color.black, "tree");



    public static final TETile SMILEYDOWN = new TETile('s', Color.white, Color.yellow, "smiley", "byow/TileEngine/characters/smileyDOWN.png");
    public static final TETile SMILEYRIGHT = new TETile('s', Color.white, Color.yellow, "smiley", "byow/TileEngine/characters/SMILEYright.png");
    public static final TETile SMILEYUP = new TETile('s', Color.white, Color.yellow, "smiley", "byow/TileEngine/characters/smileyUP.png");
    public static final TETile SMILEYLEFT = new TETile('s', Color.white, Color.yellow, "smiley", "byow/TileEngine/characters/smileyUPl.png");

    public static final TETile BARBIE = new TETile('s', Color.white, Color.yellow, "barbie", "byow/TileEngine/characters/barbie.png");
    public static final TETile KEN = new TETile('s', Color.white, Color.yellow, "ken", "byow/TileEngine/characters/ken.png");
    public static final TETile KEN2 = new TETile('s', Color.white, Color.yellow, "ken", "byow/TileEngine/characters/ken2.png");
    public static final TETile NPC = new TETile('s', Color.white, Color.yellow, "npc", "byow/TileEngine/characters/npc.png");
    public static final TETile NPC2 = new TETile('s', Color.white, Color.yellow, "npc", "byow/TileEngine/characters/npc2.png");



    public static final TETile SECRETBARBIE = new TETile('b', Color.white, Color.magenta, "secret barbie", "byow/TileEngine/tiles/squarewall.png");
    public static final TETile SECRETCHERRY = new TETile('#', new Color(216, 128, 128), Color.darkGray,
            "wall", "byow/TileEngine/tiles/realworldsquare.png");

    public static final TETile FLOOR = new TETile('f', Color.white, Color.yellow, "floor", "byow/TileEngine/tiles/pinkmarble.png");

    public static final TETile CRYSTALBALL = new TETile('c', Color.white, Color.white, "crystal ball", "byow/TileEngine/assets/icons8-crystal-ball-16.png");
    public static final TETile CHERRY = new TETile('c', Color.white, Color.white, "cherry", "byow/TileEngine/assets/icons8-cherry-16.png");
    public static final TETile HORSE = new TETile('c', Color.white, Color.white, "cherry", "byow/TileEngine/assets/horse.png");


}


