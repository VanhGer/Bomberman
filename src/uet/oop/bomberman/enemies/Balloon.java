package uet.oop.bomberman.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.CommonFunc;
import uet.oop.bomberman.graphics.Sprite;

public class Balloon extends Enemies  {
    public Balloon(int x, int y, Image img) {
        super(x, y, img);
        setDir(3);
        setMoving(true);
        speed = 1;
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
                                    Sprite.balloom_left1,
                                    Sprite.balloom_left2,
                                    Sprite.balloom_left3,
                                    animation,
                                    CommonFunc.Period)
                            .getFxImage());
        }

        /** right. */
        if (getDir() == 2 || getDir() == 4) {
            setImg(
                    Sprite.movingSprite(
                                    Sprite.balloom_right1,
                                    Sprite.balloom_right2,
                                    Sprite.balloom_right3,
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
