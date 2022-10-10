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
    public void update() {
        if (isMoving()) {
            int nxt_x = getX();
            int nxt_y = getY();
            if (getDir() == 1) { // UP
                nxt_y -= getSpeed();
            } else if (getDir() == 2) { // DOWN
                nxt_y += getSpeed();
            }else if (getDir() == 3) { // LEFT
                nxt_x -= getSpeed();
            } else if (getDir() == 4) { // RIGHT
                nxt_x += getSpeed();
            }
            if (!check_colliding(nxt_x, nxt_y)) {
                change_coordinates(nxt_x, nxt_y);
            }
            else {
                // change speed when colliding.
                Random d = new Random();
                setSpeed(d.nextInt(2) + 1);
                autoChangeDir(getX(), getY());
            }
            animation++;
            if (animation == CommonFunc.Period) animation = 0;
            change_img();
        }
    }

    @Override
    public void change_img() {
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

    @Override
    public boolean isDone() {
        return false;
    }
}
