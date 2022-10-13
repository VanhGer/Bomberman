package uet.oop.bomberman.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.AutoFindPath;
import uet.oop.bomberman.CommonFunc;
import uet.oop.bomberman.EList;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;


public class Kondoria extends Enemies {
    public Kondoria(int x, int y, Image img) {
        super(x, y, img);
        setDir(3);
        setMoving(true);
        speed = 1;
    }

    @Override
    public boolean check_colliding(int nxt_x, int nxt_y) {
        for (Entity ent: EList.walls) {
            if (CommonFunc.Collide(nxt_x, nxt_y, ent.getX(), ent.getY())) {
                return true;
            }
        }

        for (Entity ent: EList.bombs) {
            if (CommonFunc.Collide(nxt_x, nxt_y, ent.getX(), ent.getY())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void change_img() {
        if (isDead()) {
            setImg(
                    Sprite.movingSprite(
                                    Sprite.kondoria_dead,
                                    Sprite.mob_dead1,
                                    Sprite.mob_dead2,
                                    Sprite.mob_dead3,
                                    deadAnimation,
                                    DeadPeriod)
                            .getFxImage());
            return;
        }

        /** left. */
        if (getDir() == 1 || getDir() == 3) {
            setImg(
                    Sprite.movingSprite(
                                    Sprite.kondoria_left1,
                                    Sprite.kondoria_left2,
                                    Sprite.kondoria_left3,
                                    animation,
                                    CommonFunc.Period)
                            .getFxImage());
        }

        /** right. */
        if (getDir() == 2 || getDir() == 4) {
            setImg(
                    Sprite.movingSprite(
                                    Sprite.kondoria_right1,
                                    Sprite.kondoria_right2,
                                    Sprite.kondoria_right3,
                                    animation,
                                    CommonFunc.Period)
                            .getFxImage());
        }
    }
}
