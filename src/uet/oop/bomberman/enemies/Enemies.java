package uet.oop.bomberman.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.AutoFindPath;
import uet.oop.bomberman.CommonFunc;
import uet.oop.bomberman.entities.DynamicEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class Enemies extends DynamicEntity {

    public boolean Dead;
    public int deadAnimation;
    public static final int DeadPeriod = 40;

    public Enemies(int x, int y, Image img) {
        super(x, y, img);
        deadAnimation = 0;
        Dead = false;
    }

    public boolean isDead() {
        return Dead;
    }

    public void setDead(boolean dead) {
        Dead = dead;
    }

    public boolean isDone() {
        return isDead() && deadAnimation >= DeadPeriod;
    }

    public void check_Dead() {
        if (check_explosing()) {
            setDead(true);
            deadAnimation = 0;
            return;
        }
    }

    public List<Integer> moveableDir(int nxt_x, int nxt_y) {
        List<Integer> list = new ArrayList<>();
        if (!check_colliding(nxt_x, nxt_y - getSpeed()))
            list.add(1); // can move up.

        if (!check_colliding(nxt_x, nxt_y + getSpeed()))
            list.add(2); // can move down;

        if (!check_colliding(nxt_x - getSpeed(), nxt_y))
            list.add(3); // can move left;

        if (!check_colliding(nxt_x + getSpeed(), nxt_y))
            list.add(4); // can move right;
        return list;
    }

    @Override
    public void update() {
        if (isMoving() && !isDead()) {
            int nxt_x = getX();
            int nxt_y = getY();

            if (this instanceof Minvo) {
                int dir = AutoFindPath.getAutoDir1(getxUnit(), getyUnit());
                List<Integer> list = moveableDir(nxt_x, nxt_y);
                /** change dir. */
                if (list.contains(dir)){
                    setDir(dir);
                }
            }
            if (this instanceof Kondoria) {
                int dir = AutoFindPath.getAutoDir2(getxUnit(), getyUnit());
                List<Integer> list = moveableDir(nxt_x, nxt_y);
                /** change dir. */
                if (list.contains(dir)){
                    setDir(dir);
                }
            }
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
                if (this instanceof Oneal || this instanceof Minvo) {
                    Random r = new Random();
                    int newSpeed = r.nextInt(2) + 1;
                    setSpeed(newSpeed);
                }
                autoChangeDir(getX(), getY());
            }
            animation++;
            if (animation == CommonFunc.Period) animation = 0;
        }
        if (!isDead()) {
            check_Dead();
        }  else {
            deadAnimation++;
        }
        if (isDead() || isMoving()) {
            change_img();
        }
    }

    public void autoChangeDir(int x, int y) {
        int preDir = super.getDir();
        int preX = super.getX();
        int preY = super.getY();
        //System.out.println(preDir);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        Collections.shuffle(list);
        //System.out.println(list.get(0) +  " " + list.get(1) + " " + list.get(2) + " " + list.get(3));
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == preDir) continue;
            if (list.get(i) == 1) { // UP
                if (check_colliding(preX, preY - getSpeed())) continue;
            } else if (list.get(i) == 2) { // DOWN
                if (check_colliding(preX, preY + getSpeed())) continue;
            } else if (list.get(i) == 3) { //LEFT
                if (check_colliding(preX - getSpeed(), preY)) continue;
            } else if (check_colliding(preX + getSpeed(), preY)) continue;
            // found new satisfied Dir
            setDir(list.get(i));
            break;
        }

    }
}