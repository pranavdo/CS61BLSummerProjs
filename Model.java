package game2048;

import java.util.Formatter;
import java.util.Observable;


/** The state of a game of 2048.
 *  @author TODO: Pranav Dogra
 */
public class Model extends Observable {
    /** Current contents of the board. */
    private Board _board;
    /** Current score. */
    private int _score;
    /** Maximum score so far.  Updated when game ends. */
    private int _maxScore;
    /** True iff game is ended. */
    private boolean _gameOver;

    /* Coordinate System: column C, row R of the board (where row 0,
     * column 0 is the lower-left corner of the board) will correspond
     * to board.tile(c, r).  Be careful! It works like (x, y) coordinates.
     */

    /** Largest piece value. */
    public static final int MAX_PIECE = 2048;

    /** A new 2048 game on a board of size SIZE with no pieces
     *  and score 0. */
    public Model(int size) {
        this._board = new Board(size);

    }

    /** A new 2048 game where RAWVALUES contain the values of the tiles
     * (0 if null). VALUES is indexed by (row, col) with (0, 0) corresponding
     * to the bottom-left corner. Used for testing purposes. */
    public Model(int[][] rawValues, int score, int maxScore, boolean gameOver) {
        this._board = new Board(rawValues);
        this._score = score;
        this._maxScore = maxScore;
        this._gameOver = gameOver;

    }

    /** Return the current Tile at (COL, ROW), where 0 <= ROW < size(),
     *  0 <= COL < size(). Returns null if there is no tile there.
     *  Used for testing. Should be deprecated and removed.
     */
    public Tile tile(int col, int row) {
        return _board.tile(col, row);
    }

    /** Return the number of squares on one side of the board.
     *  Used for testing. Should be deprecated and removed. */
    public int size() {
        return _board.size();
    }

    /** Return true iff the game is over (there are no moves, or
     *  there is a tile with value 2048 on the board). */
    public boolean gameOver() {
        checkGameOver();
        if (_gameOver) {
            _maxScore = Math.max(_score, _maxScore);
        }
        return _gameOver;
    }

    /** Return the current score. */
    public int score() {
        return _score;
    }

    /** Return the current maximum game score (updated at end of game). */
    public int maxScore() {
        return _maxScore;
    }

    /** Clear the board to empty and reset the score. */
    public void clear() {
        _score = 0;
        _gameOver = false;
        _board.clear();
        setChanged();
    }

    /** Add TILE to the board. There must be no Tile currently at the
     *  same position. */
    public void addTile(Tile tile) {
        _board.addTile(tile);
        checkGameOver();
        setChanged();
    }

    /** Tilt the board toward SIDE. Return true iff this changes the board.
     *
     * 1. If two Tile objects are adjacent in the direction of motion and have
     *    the same value, they are merged into one Tile of twice the original
     *    value and that new value is added to the score instance variable
     * 2. A tile that is the result of a merge will not merge again on that
     *    tilt. So each move, every tile will only ever be part of at most one
     *    merge (perhaps zero).
     * 3. When three adjacent tiles in the direction of motion have the same
     *    value, then the leading two tiles in the direction of motion merge,
     *    and the trailing tile does not.
     */


    public Tile nextTilefinder(int currenttile_R,int currenttile_C) {
        Tile [] tile1_tile2 = new Tile[2];

        for (int i = currenttile_R; i <= 0; i--) {
            if (_board.tile(currenttile_C, i) != null) {
                return _board.tile(currenttile_C, i);
            }
        }
        return null;

    }
    
    public boolean tilt(Side side) {
        boolean changed;
        changed = false;

        _board.setViewingPerspective(side);
        for (int col =0; col < _board.size(); col++){
           int previous_tile_c = col;
           int previous_tile_r = _board.size();
//
//            Tile topmosttile = _board.tile(0,0);
//            int topmosttile_c;
//            int topmosttile_r = _board.size();
//
//            for (int i = _board.size()-1; i >= 0;i--){
//                if (_board.tile(col,i) != null){
//                    topmosttile = _board.tile(col,i);
//                    topmosttile_c = col;
//                    topmosttile_r = i;
//                    previous_tile_c = col;
//                    previous_tile_r = i;
//
//                    break;
//                }
//
//            }
//
//
//            _board.move(col, 0, topmosttile);


            for (int row = _board.size()-1; row>=0; row-- ){
                Tile t = _board.tile(col,row);
                int current_c = col;
                int current_r = row;
                if (t!= null){

                    // simply brings the current tile all the way up
                    if (row != previous_tile_r-1){
                        _board.move(col,previous_tile_r-1,t);
                        current_c = col;
                        current_r = previous_tile_r-1;
                        changed = true;
                    }

                    //merge
                    Tile next_tile = nextTilefinder(row,col);
                    if (next_tile != null){
                        if (next_tile.value() == t.value()){
                            _board.move(current_c,current_r,next_tile);
                            _score += t.value();
                            changed = true;
                        }
                    }



                    previous_tile_c = col;
                    previous_tile_r = row;
                    //sets the previous tile to col and row
                }
            }
        }



        _board.setViewingPerspective(Side.NORTH);



//        if (side == Side.NORTH){
//            int toptile_r = 90;
//        //how top tile moves
//        //how literally every other tile moves
//
//            /**
//             * 1. move this tile to the topmost possible space (how? iterate upwards and find where the next tile is and move the current tile right below it)
//             * 1. get the tiles next to it by nexttilefinder ->
//             * 2. Get a function for this (mergerfunction) -> If the tile North to the tile aka (tile 1) is the same value then we merge and get the merged score on the "row" value and the value is added to the score
//             * 3. If the there are three adjacent tiles tile, tile 1 , tile 2, then you call mergerfunctinon on row, row-1 and move tile 2 to the tile's row - 1 position
//             *
//              */
//            for (int c = 0; c < _board.size(); c++){
//                for (int r = toptile_r; r >= 0; r--){
//                    Tile current_tile = _board.tile(c,r);
//                    _board.move(c,toptile_r-1,current_tile);
//                    Tile next_tile = nextTilefinder(r, c,)
//                }
//
//            }
//
//        }


        checkGameOver();
        if (changed) {
            setChanged();
        }
        return changed;
    }

    /** Checks if the game is over and sets the gameOver variable
     *  appropriately.
     */
    private void checkGameOver() {
        _gameOver = checkGameOver(_board);
    }

    /** Determine whether game is over. */
    private static boolean checkGameOver(Board b) {
        return maxTileExists(b) || !atLeastOneMoveExists(b);
    }

    /** Returns true if at least one space on the Board is empty.
     *  Empty spaces are stored as null.
     */
    public static boolean emptySpaceExists(Board b) {
        for (int col = 0; col < b.size(); col += 1) {
            for (int row = 0; row < b.size(); row += 1) {
                if (b.tile(col,row) == null){
                    return true;

                }
            }
        }
        return false;

    }

    /**
     * Returns true if any tile is equal to the maximum valid value.
     * Maximum valid value is given by MAX_PIECE. Note that
     * given a Tile object t, we get its value with t.value().
     */
    public static boolean maxTileExists(Board b) {
        for (int col = 0; col < b.size(); col += 1) {
            for (int row = 0; row < b.size(); row += 1) {
                if (b.tile(col,row) != null){
                if (b.tile(col,row).value() == MAX_PIECE){
                    return true;

                }}
            }
        }
        return false;
    }

    /**
     * Returns true if there are any valid moves on the board.
     * There are two ways that there can be valid moves:
     * 1. There is at least one empty space on the board.
     * 2. There are two adjacent tiles with the same value.
     */
    public static boolean atLeastOneMoveExists(Board b) {

        if (emptySpaceExists(b) == true){
        return true;}

        for (int col = 0; col < b.size(); col += 1) {
            for (int row = 0; row < b.size(); row += 1) {


                    if (col+1 < b.size()){
                    if (b.tile(col+1,row).value() == b.tile(col,row).value()){
                        return true;
                    }}


                    if (row+1<b.size()){
                    if (b.tile(col,row+1).value() == b.tile(col,row).value()){
                        return true;

                    }}

                    if (col-1>=0){
                    if (b.tile(col-1,row).value() == b.tile(col,row).value()){
                        return true;

                    }}
                    if (row-1>=0){
                    if (b.tile(col,row-1).value() == b.tile(col,row).value()){
                        return true;

                    }}
            }
        }
        return false;
    }

    /** Returns the model as a string, used for debugging. */
    @Override
    public String toString() {
        Formatter out = new Formatter();
        out.format("%n[%n");
        for (int row = size() - 1; row >= 0; row -= 1) {
            for (int col = 0; col < size(); col += 1) {
                if (tile(col, row) == null) {
                    out.format("|    ");
                } else {
                    out.format("|%4d", tile(col, row).value());
                }
            }
            out.format("|%n");
        }
        String over = gameOver() ? "over" : "not over";
        out.format("] %d (max: %d) (game is %s) %n", score(), maxScore(), over);
        return out.toString();
    }

    /** Returns whether two models are equal. */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            return toString().equals(o.toString());
        }
    }

    /** Returns hash code of Model’s string. */
    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
