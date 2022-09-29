package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.CommonFunc;
import uet.oop.bomberman.EList;

public abstract class DynamicEntity extends Entity{

    protected boolean moving = false;
    protected int speed = 3;
    protected int dir = 0; // 0: STAND, 1: UP, 2: DOWN, 3: LEFT, 4: RIGHT
    protected int animation = 0;


    public DynamicEntity(int x, int y, Image img) {
        super( x, y, img);
        animation = 0;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
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

    public boolean isMoving() {
        return moving;
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

    public abstract void change_img();

    public abstract boolean isDone();

    @Override
    public void update() {
    }
}
