package uet.oop.bomberman.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.CommonFunc;
import uet.oop.bomberman.EList;
import uet.oop.bomberman.entities.DynamicEntity;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Balloon extends DynamicEntity {
    public Balloon(int x, int y, Image img) {
        super(x, y, img);
        super.setDir(3);
        super.setMoving(true);
        super.speed = 2;
    }
    public boolean check_colliding(int nxt_x, int nxt_y) {
        for (Entity ent : EList.walls) {
            if (CommonFunc.Bomber_Collide(nxt_x, nxt_y, ent.getX(), ent.getY())) {
                return true;
            }
        }

        for (Entity ent : EList.bricks) {
            if (CommonFunc.Bomber_Collide(nxt_x, nxt_y, ent.getX(), ent.getY())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void update() {
        if (isMoving()) {
            int nxt_x = getX();
            int nxt_y = getY();

            if (getDir() == 3) { // LEFT
                // check collide
                nxt_x -= getSpeed();
                // change Img
            } else if (getDir() == 4) { // RIGHT
                // check collide
                nxt_x += getSpeed();
                // change Img
            }
            if (!check_colliding(nxt_x, nxt_y)) {
                change_coordinates(nxt_x, nxt_y);
            }
            else {
                if(getDir() == 3) {
                    setDir(4);
                }
                else {
                    setDir(3);
                }
            }
            animation++;
            if (animation == CommonFunc.Period) animation = 0;
            change_img();
        }
    }

    @Override
    public void change_img() {
        /** left. */
        if (getDir() == 3) {
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
        if (getDir() == 4) {
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
