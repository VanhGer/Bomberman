package uet.oop.bomberman;

import uet.oop.bomberman.graphics.Sprite;

public class CommonFunc {
    public static boolean Collide(int x1, int y1, int x2, int y2) {
        if (x1 <= x2 && x2 <= x1 + Sprite.SCALED_SIZE - 8) {
            if (y1 + 2 <= y2 && y2 <= y1 + Sprite.SCALED_SIZE - 2) {
                return true;
            }
            if (y1 + 2 <= y2 + Sprite.SCALED_SIZE && y2 + Sprite.SCALED_SIZE <= y1 + Sprite.SCALED_SIZE - 2) {
                return true;
            }
        }

        if (x2 + 2 <= x1 && x1 <= x2 + Sprite.SCALED_SIZE - 2) {
            if (y1 + 2 <= y2 && y2 <= y1 + Sprite.SCALED_SIZE - 2) {
                return true;
            }
            if (y1 + 2 <= y2 + Sprite.SCALED_SIZE && y2 + Sprite.SCALED_SIZE <= y1 + Sprite.SCALED_SIZE - 2) {
                return true;
            }
        }
        return false;
    }
}
