package byow.Core;

import byow.InputDemo.InputSource;
import edu.princeton.cs.algs4.StdDraw;

public class KeyboardInputSource2 implements InputSource {
    private static final boolean PRINT_TYPED_KEYS = false;

    @Override
    public char getNextKey() {
        while (true) {
            if (StdDraw.hasNextKeyTyped()) {
                char c = Character.toUpperCase(StdDraw.nextKeyTyped());
                if (PRINT_TYPED_KEYS) {
                    System.out.print(c);
                }
                return c;
            }
        }
    }

    @Override
    public boolean possibleNextInput() {
        return true;
    }

//    public KeyboardInputSource(String s) {
//        index = 0;
//        input = s;
//    }
//
//    public char getNextKey() {
//        char returnChar = input.charAt(index);
//        index += 1;
//        return returnChar;
//    }
}
