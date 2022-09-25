package uet.oop.bomberman;

import uet.oop.bomberman.graphics.Sprite;

public class CommonFunc {
    public static boolean Bomber_Collide(int x1, int y1, int x2, int y2) {
        if ((x1 <= x2 && x2 <= x1 + Sprite.SCALED_SIZE - 8)
                || (x2 <= x1 && x1 <= x2 + Sprite.SCALED_SIZE - 2)){
            if (y1 <= y2 && y2 <= y1 + Sprite.SCALED_SIZE - 5) {
                return true;
            }
            if (y2 < y1 && y2 + Sprite.SCALED_SIZE - 5 >= y1) {
                return true;
            }
        }
        return  false;
    }

    public static boolean Collide(int x1, int y1, int x2, int y2) {
        if ((x1 <= x2 && x2 <= x1 + Sprite.SCALED_SIZE - 5)
                || (x2 <= x1 && x1 <= x2 + Sprite.SCALED_SIZE - 5)){
            if (y1 <= y2 && y2 <= y1 + Sprite.SCALED_SIZE - 5) {
                return true;
            }
            if (y2 < y1 && y2 + Sprite.SCALED_SIZE - 5 >= y1) {
                return true;
            }
        }
        return  false;

    }
}
