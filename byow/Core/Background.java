package byow.Core;

import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;
import ucb.gui.Widget;

import static byow.Core.Engine.WIDTH;

public class Background {
    public Background(){

    }

    public TETile[][] makeBarbieBackground(TETile[][] world){
        //row 0
        world[36][0] = Tileset.MAGENTA;
        world[37][0] = Tileset.MAGENTA;
        world[38][0] = Tileset.MAGENTA;
        world[43][0] = Tileset.MAGENTA;
        world[44][0] = Tileset.MAGENTA;

        world[39][0]= Tileset.PURPLE;
        world[40][0]= Tileset.PURPLE;
        world[41][0]= Tileset.PURPLE;
        world[42][0]= Tileset.PURPLE;

        //row 1
        for (int x = 38; x <= 43; x++) {
            world[x][1] = Tileset.MAGENTA;
        }

        //row 2 - none

        //row 3
        world[0][3] = Tileset.MAGENTA;
        for (int x = 4; x <= 11; x++) {
            world[x][3] = Tileset.MAGENTA;
        }

        //row 4
        for (int x = 0; x <= 4; x++) {
            world[x][4] = Tileset.MAGENTA;
        }
        for (int x = 11; x <= 17; x++) {
            world[x][4] = Tileset.MAGENTA;
        }
        for (int x = 5; x <= 9; x++) {
            world[x][4] = Tileset.YELLOW;
        }
        world[10][4] = Tileset.LIGHTBLUE;
        world[32][4] = Tileset.LIGHTYELLOW;

        //row 5
        for (int x = 0; x <= 16; x++) {
            world[x][5] = Tileset.YELLOW;
        }
        for (int x = 17; x <= 19; x++) {
            world[x][5] = Tileset.MAGENTA;
        }
        world[32][5] = Tileset.YELLOW;
        world[31][5] = Tileset.ORANGE;
        world[33][5] = Tileset.ORANGE;
        //row 6
        for (int x = 0; x <= 17; x++) {
            world[x][6] = Tileset.YELLOW;
        }
        world[18][6] = Tileset.MAGENTA;
        world[19][6] = Tileset.MAGENTA;
        for (int x = 58; x <= 70; x++) {
            world[x][6] = Tileset.MAGENTA;
        }
        world[30][6] = Tileset.LIGHTYELLOW;
        world[34][6] = Tileset.LIGHTYELLOW;
        world[33][6] = Tileset.YELLOW;
        world[32][6] = Tileset.ORANGE;
        world[31][6] = Tileset.YELLOW;

        //row 7
        for (int x = 0; x <= 11; x++) {
            world[x][7] = Tileset.YELLOW;
        }
        for (int x = 59; x <= 69; x++) {
            world[x][7] = Tileset.YELLOW;
        }
        world[32][7] = Tileset.YELLOW;
        world[15][7] = Tileset.YELLOW;
        world[16][7] = Tileset.YELLOW;

        for (int x = 49; x <= 58; x++) {
            world[x][7] = Tileset.MAGENTA;
        }
        world[12][7] = Tileset.MAGENTA;
        world[13][7] = Tileset.MAGENTA;
        world[14][7] = Tileset.MAGENTA;
        world[17][7] = Tileset.MAGENTA;
        world[18][7] = Tileset.MAGENTA;
        world[19][7] = Tileset.MAGENTA;
        world[70][7] = Tileset.MAGENTA;
        world[71][7] = Tileset.MAGENTA;
        world[72][7] = Tileset.MAGENTA;
        world[73][7] = Tileset.MAGENTA;
        world[31][7] = Tileset.ORANGE;
        world[33][7] = Tileset.ORANGE;

        //row 8
        for (int x = 0; x <= 3; x++) {
            world[x][8] = Tileset.YELLOW;
        }
        for (int x = 6; x <= 9; x++) {
            world[x][8] = Tileset.YELLOW;
        }
        for (int x = 50; x <= 72; x++) {
            world[x][8] = Tileset.YELLOW;
        }
        for (int x = 4; x <= 5; x++) {
            world[x][8] = Tileset.MAGENTA;
        }
        for (int x = 10; x <= 12; x++) {
            world[x][8] = Tileset.MAGENTA;
        }
        for (int x = 15; x <= 16; x++) {
            world[x][8] = Tileset.MAGENTA;
        }
        for (int x = 18; x <= 19; x++) {
            world[x][8] = Tileset.MAGENTA;
        }
        for (int x = 48; x <= 49; x++) {
            world[x][8] = Tileset.MAGENTA;
        }
        for (int x = 73; x <= 74; x++) {
            world[x][8] = Tileset.MAGENTA;
        }
        for (int x = 13; x <= 14; x++) {
            world[x][8] = Tileset.PURPLE;
        }
        world[17][8] = Tileset.PURPLE;
        world[32][8] = Tileset.LIGHTYELLOW;

        //row 9
        for (int x = 1; x <= 4; x++) {
            world[x][9] = Tileset.YELLOW;
        }
        for (int x = 5; x <= 8; x++) {
            world[x][9] = Tileset.YELLOW;
        }
        for (int x = 50; x <= 64; x++) {
            world[x][9] = Tileset.YELLOW;
        }
        for (int x = 67; x <= 73; x++) {
            world[x][9] = Tileset.YELLOW;
        }

        for (int x = 11; x <= 16; x++) {
            world[x][9] = Tileset.PURPLE;
        }
        for (int x = 2; x <= 4; x++) {
            world[x][9] = Tileset.MAGENTA;
        }
        world[0][9] = Tileset.MAGENTA;
        world[10][9] = Tileset.MAGENTA;
        world[9][9] = Tileset.MAGENTA;
        world[17][9] = Tileset.MAGENTA;
        world[18][9] = Tileset.MAGENTA;
        world[48][9] = Tileset.MAGENTA;
        world[49][9] = Tileset.MAGENTA;
        world[65][9] = Tileset.MAGENTA;
        world[66][9] = Tileset.MAGENTA;
        world[74][9] = Tileset.MAGENTA;

        //row 10
        for (int x = 0; x <= 2; x++) {
            world[x][10] = Tileset.MAGENTA;
        }
        for (int x = 4; x <= 9; x++) {
            world[x][10] = Tileset.MAGENTA;
        }
        for (int x = 13; x <= 17; x++) {
            world[x][10] = Tileset.MAGENTA;
        }
        for (int x = 49; x <= 50; x++) {
            world[x][10] = Tileset.MAGENTA;
        }
        for (int x = 64; x <= 70; x++) {
            world[x][10] = Tileset.MAGENTA;
        }
        world[74][10] = Tileset.MAGENTA;

        for (int x = 10; x <= 12; x++) {
            world[x][10] = Tileset.PURPLE;
        }
        world[3][10] = Tileset.PURPLE;

        for (int x = 51; x <= 63; x++) {
            world[x][10] = Tileset.YELLOW;
        }
        for (int x = 51; x <= 63; x++) {
            world[x][10] = Tileset.YELLOW;
        }
        for (int x = 71; x <= 73; x++) {
            world[x][10] = Tileset.YELLOW;
        }
        world[24][10] = Tileset.LIGHTBLUE;

        //row 11
        world[0][11] = Tileset.MAGENTA;
        world[3][11] = Tileset.MAGENTA;
        for (int x = 11; x <= 13; x++) {
            world[x][11] = Tileset.MAGENTA;
        }
        for (int x = 50; x <= 51; x++) {
            world[x][11] = Tileset.MAGENTA;
        }
        world[54][11] = Tileset.MAGENTA;
        for (int x = 62; x <= 64; x++) {
            world[x][11] = Tileset.MAGENTA;
        }
        for (int x = 68; x <= 74; x++) {
            world[x][11] = Tileset.MAGENTA;
        }

        for (int x = 1; x <= 2; x++) {
            world[x][11] = Tileset.PURPLE;
        }
        for (int x = 4; x <= 10; x++) {
            world[x][11] = Tileset.PURPLE;
        }
        for (int x = 65; x <= 67; x++) {
            world[x][11] = Tileset.PURPLE;
        }
        for (int x = 52; x <= 53; x++) {
            world[x][11] = Tileset.YELLOW;
        }
        for (int x = 55; x <= 61; x++) {
            world[x][11] = Tileset.YELLOW;
        }

        //row 12

        for (int x = 9; x <= 10; x++) {
            world[x][12] = Tileset.MAGENTA;
        }
        for (int x = 50; x <= 57; x++) {
            world[x][12] = Tileset.MAGENTA;
        }
        for (int x = 61; x <= 62; x++) {
            world[x][12] = Tileset.MAGENTA;
        }
        for (int x = 69; x <= 71; x++) {
            world[x][12] = Tileset.MAGENTA;
        }
        for (int x = 0; x <= 8; x++) {
            world[x][12] = Tileset.PURPLE;
        }
        for (int x = 63; x <= 68; x++) {
            world[x][12] = Tileset.PURPLE;
        }
        for (int x = 58; x <= 60; x++) {
            world[x][12] = Tileset.YELLOW;
        }

        //row 13
        for (int x = 0; x <= 1; x++) {
            world[x][13] = Tileset.MAGENTA;
        }
        world[9][13] = Tileset.MAGENTA;
        for (int x = 51; x <= 53; x++) {
            world[x][13] = Tileset.MAGENTA;
        }
        for (int x = 57; x <= 61; x++) {
            world[x][13] = Tileset.MAGENTA;
        }
        world[63][13] = Tileset.MAGENTA;
        for (int x = 66; x <= 69; x++) {
            world[x][13] = Tileset.MAGENTA;
        }
        for (int x = 2; x <= 8; x++) {
            world[x][13] = Tileset.PURPLE;
        }
        for (int x = 54; x <= 56; x++) {
            world[x][13] = Tileset.PURPLE;
        }
        for (int x = 64; x <= 65; x++) {
            world[x][13] = Tileset.PURPLE;
        }
        world[62][13] = Tileset.PURPLE;

        //row 14
        for (int x = 2; x <= 3; x++) {
            world[x][14] = Tileset.MAGENTA;
        }
        for (int x = 8; x <= 9; x++) {
            world[x][14] = Tileset.MAGENTA;
        }
        for (int x = 54; x <= 55; x++) {
            world[x][14] = Tileset.MAGENTA;
        }
        for (int x = 65; x <= 66; x++) {
            world[x][14] = Tileset.MAGENTA;
        }
        for (int x = 4; x <= 7; x++) {
            world[x][14] = Tileset.PURPLE;
        }
        for (int x = 56; x <= 64; x++) {
            world[x][14] = Tileset.MAGENTA;
        }

        //row 15
        for (int x = 3; x <= 4; x++) {
            world[x][15] = Tileset.MAGENTA;
        }
        for (int x = 7; x <= 8; x++) {
            world[x][15] = Tileset.MAGENTA;
        }
        for (int x = 55; x <= 57; x++) {
            world[x][15] = Tileset.MAGENTA;
        }
        for (int x = 63; x <= 64; x++) {
            world[x][15] = Tileset.MAGENTA;
        }
        for (int x = 5; x <= 6; x++) {
            world[x][15] = Tileset.PURPLE;
        }
        for (int x = 58; x <= 62; x++) {
            world[x][15] = Tileset.PURPLE;
        }

        //row 16
        for (int x = 4; x <= 6; x++) {
            world[x][16] = Tileset.MAGENTA;
        }
        for (int x = 57; x <= 63; x++) {
            world[x][16] = Tileset.MAGENTA;
        }

        //row 17
        world[78][17] = Tileset.YELLOW;

        //row 18
        world[77][18] = Tileset.LIGHTYELLOW;
        world[78][18] = Tileset.ORANGE;
        world[79][18] = Tileset.YELLOW;

        //row 19
        world[78][19] = Tileset.LIGHTYELLOW;
        world[2][19] = Tileset.LIGHTBLUE;
        //row 20 - none

        return world;
    }

    public TETile[][] makeBeachOffBackground(TETile[][] world) {
        for (int x = 0; x <= 79; x++) {
            for (int y = 0; y <= 3; y++) {
                world[x][y] = Tileset.BLUE;
            }
        }
        for (int x = 0; x <= 7; x++) {
            world[x][4] = Tileset.BLUE;
        }
        for (int x = 14; x <= 79; x++) {
            world[x][4] = Tileset.BLUE;
        }
        for (int x = 8; x <= 13; x++) {
            world[x][4] = Tileset.WHITE;
        }

        //row5
        for (int x = 0; x <= 19; x++) {
            world[x][5] = Tileset.BLUE;
        }
        for (int x = 25; x <= 48; x++) {
            world[x][5] = Tileset.BLUE;
        }
        for (int x = 53; x <= 68; x++) {
            world[x][5] = Tileset.BLUE;
        }
        for (int x = 75; x <= 79; x++) {
            world[x][5] = Tileset.BLUE;
        }

        for (int x = 20; x <= 24; x++) {
            world[x][5] = Tileset.WHITE;
        }
        for (int x = 49; x <= 52; x++) {
            world[x][5] = Tileset.WHITE;
        }
        for (int x = 69; x <= 74; x++) {
            world[x][5] = Tileset.WHITE;
        }

       //row6
        for (int x = 0; x <= 7; x++) {
            world[x][6] = Tileset.WHITE;
        }
        for (int x = 30; x <= 37; x++) {
            world[x][6] = Tileset.WHITE;
        }
        for (int x = 8; x <= 10; x++) {
            world[x][6] = Tileset.BLUE;
        }
        for (int x = 38; x <= 79; x++) {
            world[x][6] = Tileset.BLUE;
        }
        for (int x = 11; x <= 29; x++) {
            world[x][6] = Tileset.TAN;
        }

        //row 7
        for (int x = 0; x <= 61; x++) {
            world[x][7] = Tileset.TAN;
        }
        for (int x = 71; x <= 79; x++) {
            world[x][7] = Tileset.BLUE;
        }
        for (int x = 62; x <= 70; x++) {
            world[x][7] = Tileset.WHITE;
        }

        //row8-11
        for (int x = 0; x <= 79; x++) {
            for (int y = 8; y <= 11; y++) {
                world[x][y] = Tileset.TAN;
            }
        }
        //row 12
        for (int x = 0; x <= 11; x++) {
            world[x][12] = Tileset.TAN;
        }
        for (int x = 35; x <= 65; x++) {
            world[x][12] = Tileset.TAN;
        }
        for (int x = 77; x <= 79; x++) {
            world[x][12] = Tileset.TAN;
        }

        //row 13
        for (int x = 0; x <= 1; x++) {
            world[x][13] = Tileset.TAN;
        }
        for (int x = 41; x <= 48; x++) {
            world[x][13] = Tileset.TAN;
        }
        world[79][13] = Tileset.TAN;

        return world;
    }


}
