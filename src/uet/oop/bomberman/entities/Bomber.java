package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.EList;

public class Bomber extends DynamicEntity {

    public Bomber(int x, int y, Image img) {
        super( x, y, img);
    }

    @Override
    public void update() {
        if (getIsMoving()) {
            int nxt_x =  getX();
            int nxt_y = getY();

            if (getDir() == 1) { // UP
                // check collide
                nxt_y -= getSpeed();
                // change Img
            } else if (getDir() == 2) { // DOWN
                // check collide
                nxt_y += getSpeed();
                // change Img
            } else if (getDir() == 3) { // LEFT
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
        }
    }
}
