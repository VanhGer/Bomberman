package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.CommonFunc;
import uet.oop.bomberman.graphics.Sprite;

public class Brick extends DynamicEntity {
  protected boolean destroyed;

  public Brick(int x, int y, Image img) {
    super(x, y, img);
    destroyed = false;
    animation = 0;
  }

  public void setDestroyed(boolean destroyed) {
    this.destroyed = destroyed;
  }

  public boolean isDestroyed() {
    return this.destroyed;
  }

  @Override
  public boolean isDone() {
    return isDestroyed() && animation >= CommonFunc.Period;
  }

  @Override
  public void update() {
    if (isDestroyed()) {
      animation++;
      change_img();
    }
  }

  @Override
  public void change_img() {
    setImg(
        Sprite.movingSprite(
                Sprite.brick_exploded,
                Sprite.brick_exploded1,
                Sprite.brick_exploded2,
                animation,
                CommonFunc.Period)
            .getFxImage());
  }
}
