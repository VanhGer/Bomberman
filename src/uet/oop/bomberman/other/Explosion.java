package uet.oop.bomberman.other;

import javafx.scene.image.Image;
import uet.oop.bomberman.CommonFunc;
import uet.oop.bomberman.entities.DynamicEntity;
import uet.oop.bomberman.graphics.Sprite;

public class Explosion extends DynamicEntity {
  public enum Dir {
    DOWN,
    UP,
    LEFT,
    RIGHT,
    HORIZONTAL,
    VERTICAL
  }

  public static int Explosionrange = 2;
  public Dir dir;

  public Explosion(int x, int y, Image img, Dir dir) {
    super(x, y, img);
    animation = 0;
    this.dir = dir;
  }


  @Override
  public boolean isDone() {
    return animation >= CommonFunc.Period;
  }

  @Override
  public void update() {
    animation++;
    change_img();
  }

  @Override
  public void change_img() {
    if (dir.equals(Dir.UP)) {
      setImg(
          Sprite.movingSprite(
                  Sprite.explosion_vertical_top_last,
                  Sprite.explosion_vertical_top_last1,
                  Sprite.explosion_vertical_top_last2,
                  animation,
                  CommonFunc.Period)
              .getFxImage());
    }

    if (dir.equals(Dir.DOWN)) {
      setImg(
          Sprite.movingSprite(
                  Sprite.explosion_vertical_down_last,
                  Sprite.explosion_vertical_down_last1,
                  Sprite.explosion_vertical_down_last2,
                  animation,
                  CommonFunc.Period)
              .getFxImage());
    }

    if (dir.equals(Dir.LEFT)) {
      setImg(
          Sprite.movingSprite(
                  Sprite.explosion_horizontal_left_last,
                  Sprite.explosion_horizontal_left_last1,
                  Sprite.explosion_horizontal_left_last2,
                  animation,
                  CommonFunc.Period)
              .getFxImage());
    }

    if (dir.equals(Dir.RIGHT)) {
      setImg(
          Sprite.movingSprite(
                  Sprite.explosion_horizontal_right_last,
                  Sprite.explosion_horizontal_right_last1,
                  Sprite.explosion_horizontal_right_last2,
                  animation,
                  CommonFunc.Period)
              .getFxImage());
    }

    if (dir.equals(Dir.HORIZONTAL)) {
      setImg(
          Sprite.movingSprite(
                  Sprite.explosion_horizontal,
                  Sprite.explosion_horizontal1,
                  Sprite.explosion_horizontal2,
                  animation,
                  CommonFunc.Period)
              .getFxImage());
    }

    if (dir.equals(Dir.VERTICAL)) {
      setImg(
          Sprite.movingSprite(
                  Sprite.explosion_vertical,
                  Sprite.explosion_vertical1,
                  Sprite.explosion_vertical2,
                  animation,
                  CommonFunc.Period)
              .getFxImage());
    }
  }
}
