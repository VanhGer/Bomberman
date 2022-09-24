package uet.oop.bomberman.other;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;


public class Bomb extends Entity {

    public Bomb(int x, int y, Image img) {
        super(x, y, img);
    }

    @Override
    public void update() {
        if (super.getImg() == Sprite.bomb.getFxImage()) {
            setImg(Sprite.bomb_1.getFxImage());
            System.out.println(1);
        } else if (super.getImg() == Sprite.bomb_1.getFxImage()) {
            setImg(Sprite.bomb_2.getFxImage());
            System.out.println(2);
        } else if (super.getImg() == Sprite.bomb_2.getFxImage()) {
            setImg(Sprite.bomb.getFxImage());
            System.out.println(3);
        } else {
            System.out.println(4);
        }
    }
}
