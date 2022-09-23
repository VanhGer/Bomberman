package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.CommonFunc;
import uet.oop.bomberman.EList;

public class DynamicEntity extends Entity{

    private boolean isMoving = false;
    private int speed = 3;
    private int dir = 0; // 0: STAND, 1: UP, 2: DOWN, 3: LEFT, 4: RIGHT


    public DynamicEntity(int x, int y, Image img) {
        super( x, y, img);
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public void setDir(int dir) {
        this.dir = dir;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDir() {
        return dir;
    }

    public int getSpeed() {
        return speed;
    }

    public boolean getIsMoving() {
        return isMoving;
    }

    public boolean check_colliding(int nxt_x, int nxt_y) {
        for (Entity ent: EList.walls) {
            if (CommonFunc.Collide(nxt_x, nxt_y, ent.getX(), ent.getY())) {
                return true;
            }
        }

        for (Entity ent: EList.bricks) {
            if (CommonFunc.Collide(nxt_x, nxt_y, ent.getX(), ent.getY())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void update() {
    }
}
