package uet.oop.bomberman.enemies;

import javafx.scene.image.Image;
import uet.oop.bomberman.CommonFunc;
import uet.oop.bomberman.graphics.Sprite;


public class Minvo extends Enemies {
  public Minvo(int x, int y, Image img) {
    super(x, y, img);
    setDir(3);
    setMoving(true);
    speed = 1;
  }


  @Override
  public void change_img() {
    if (isDead()) {
      setImg(
          Sprite.movingSprite(
                  Sprite.minvo_dead,
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
                  Sprite.minvo_left1,
                  Sprite.minvo_left2,
                  Sprite.minvo_left3,
                  animation,
                  CommonFunc.Period)
              .getFxImage());
    }

    /** right. */
    if (getDir() == 2 || getDir() == 4) {
      setImg(
          Sprite.movingSprite(
                  Sprite.minvo_right1,
                  Sprite.minvo_right2,
                  Sprite.minvo_right3,
                  animation,
                  CommonFunc.Period)
              .getFxImage());
    }
  }
}
