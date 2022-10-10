package uet.oop.bomberman.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.DynamicEntity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Enemies extends DynamicEntity {
    public Enemies(int x, int y, Image img) {
        super(x, y, img);
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