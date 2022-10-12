package uet.oop.bomberman.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.CommonFunc;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Oneal extends Enemies  {
    public Oneal(int x, int y, Image img) {
        super(x, y, img);
        setDir(3);
        setMoving(true);
        setSpeed(1);
    }


    @Override
    public void change_img() {
        if (isDead()) {
            setImg(
                    Sprite.movingSprite(
                                    Sprite.oneal_dead,
                                    Sprite.oneal_dead,
                                    Sprite.oneal_dead,
                                    deadAnimation,
                                    CommonFunc.Period)
                            .getFxImage());
            return;
        }

        /** left. */
        if (getDir() == 1 || getDir() == 3) {
            setImg(
                    Sprite.movingSprite(
                                    Sprite.oneal_left1,
                                    Sprite.oneal_left2,
                                    Sprite.oneal_left3,
                                    animation,
                                    CommonFunc.Period)
                            .getFxImage());
        }

        /** right. */
        if (getDir() == 2 || getDir() == 4) {
            setImg(
                    Sprite.movingSprite(
                                    Sprite.oneal_right1,
                                    Sprite.oneal_right2,
                                    Sprite.oneal_right3,
                                    animation,
                                    CommonFunc.Period)
                            .getFxImage());
        }
    }

}
