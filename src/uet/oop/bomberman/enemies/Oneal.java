package uet.oop.bomberman.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.DynamicEntity;
import uet.oop.bomberman.entities.Entity;

public class Oneal extends DynamicEntity {
    public Oneal(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {

    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public void change_img() {

    }
}
