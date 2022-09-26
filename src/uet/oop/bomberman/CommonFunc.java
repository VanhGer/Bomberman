package uet.oop.bomberman;

import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.other.Bomb;

public class CommonFunc {
    public static final int Period = 20;
    public static boolean Bomber_Collide(int x1, int y1, int x2, int y2) {
        if ((x1 <= x2 && x2 <= x1 + Sprite.SCALED_SIZE - 8)
                || (x2 <= x1 && x1 <= x2 + Sprite.SCALED_SIZE - 4)){
            if (y1 <= y2 && y2 <= y1 + Sprite.SCALED_SIZE - 6) {
                return true;
            }
            if (y2 < y1 && y2 + Sprite.SCALED_SIZE - 6 >= y1) {
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

    public static boolean checkValidCoor(int x, int y) {
        if (x <= 0 || y <= 0 || x > BombermanGame.WIDTH || y > BombermanGame.HEIGHT) {
            return false;
        }
        return true;
    }


}
