package uet.oop.bomberman.other;

import javafx.scene.image.Image;
import uet.oop.bomberman.CommonFunc;
import uet.oop.bomberman.EList;
import uet.oop.bomberman.PlaySound;
import uet.oop.bomberman.entities.DynamicEntity;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends DynamicEntity {
  public static int bombLimit = 1;
  public static final int timeExploding = 90;
  private boolean exploding;

  public Bomb(int x, int y, Image img) {
    super(x, y, img);
    animation = 0;
    exploding = false;
  }

  public boolean isExploding() {
    return exploding;
  }

  public void setExploding(boolean Exploding) {
    this.exploding = Exploding;
  }

  public int getAnimation() {
    return animation;
  }

  public void setAnimation(int animation) {
    this.animation = animation;
  }

  public boolean isDone() {
    return (isExploding() && animation == CommonFunc.Period);
  }

  @Override
  public void update() {
    if (!isExploding()) {
      if (animation < timeExploding) {
        setImg(
            Sprite.movingSprite(
                    Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animation, CommonFunc.Period)
                .getFxImage());
        animation++;
      } else {
        /** bomb starts to explode. */
        PlaySound.play("res/Sound/boom.wav", 0);
        setBombExploding();
      }
    } else {
      if (animation == CommonFunc.Period) {
        return;
      }
      /** bomb is exploding. */
      setImg(
          Sprite.movingSprite(
                  Sprite.bomb_exploded,
                  Sprite.bomb_exploded1,
                  Sprite.bomb_exploded2,
                  animation,
                  CommonFunc.Period)
              .getFxImage());
      animation++;
    }
  }
  @Override
  public void change_img() {}
  public void setBombExploding() {
    setExploding(true);
    /** set Bomb status = exploding. */
    animation = 0;

    int ux = getxUnit(), uy = getyUnit();
    boolean up = true, down = true, left = true, right = true;
    Explosion ex = null;

    /** set explosion range. */
    for (int i = 1; i <= Explosion.Explosionrange; i++) {
      /** up. */
      if (up && CommonFunc.checkValidCoor(ux, uy - i) && checkStableEntity(ux, uy - i)) {
        if (i != Explosion.Explosionrange) {
          ex =
              new Explosion(
                  ux, uy - i, Sprite.explosion_vertical.getFxImage(), Explosion.Dir.VERTICAL);
        } else {
          ex =
              new Explosion(
                  ux, uy - i, Sprite.explosion_vertical_top_last.getFxImage(), Explosion.Dir.UP);
        }
        EList.explosions.add(ex);
      } else up = false;

      /** down. */
      if (down && CommonFunc.checkValidCoor(ux, uy + i) && checkStableEntity(ux, uy + i)) {
        if (i != Explosion.Explosionrange) {
          ex =
              new Explosion(
                  ux, uy + i, Sprite.explosion_vertical.getFxImage(), Explosion.Dir.VERTICAL);
        } else {
          ex =
              new Explosion(
                  ux, uy + i, Sprite.explosion_vertical_down_last.getFxImage(), Explosion.Dir.DOWN);
        }
        EList.explosions.add(ex);
      } else down = false;

      /** left. */
      if (left && CommonFunc.checkValidCoor(ux - i, uy) && checkStableEntity(ux - i, uy)) {
        if (i != Explosion.Explosionrange) {
          ex =
              new Explosion(
                  ux - i, uy, Sprite.explosion_horizontal.getFxImage(), Explosion.Dir.HORIZONTAL);
        } else {
          ex =
              new Explosion(
                  ux - i,
                  uy,
                  Sprite.explosion_horizontal_left_last.getFxImage(),
                  Explosion.Dir.LEFT);
        }
        EList.explosions.add(ex);
      } else left = false;

      /** right. */
      if (right && CommonFunc.checkValidCoor(ux + i, uy) && checkStableEntity(ux + i, uy)) {
        if (i != Explosion.Explosionrange) {
          ex =
              new Explosion(
                  ux + i, uy, Sprite.explosion_horizontal.getFxImage(), Explosion.Dir.HORIZONTAL);
        } else {
          ex =
              new Explosion(
                  ux + i,
                  uy,
                  Sprite.explosion_horizontal_right_last.getFxImage(),
                  Explosion.Dir.RIGHT);
        }
        EList.explosions.add(ex);
      } else right = false;
    }
  }

  public boolean checkStableEntity(int x, int y) {
    for (int i = 0; i < EList.bricks.size(); i++) {
      if (x == EList.bricks.get(i).getxUnit() && y == EList.bricks.get(i).getyUnit()) {
        EList.bricks.get(i).setDestroyed(true);
        return false;
      }
    }
    for (int i = 0; i < EList.walls.size(); i++) {
      if (x == EList.walls.get(i).getxUnit() && y == EList.walls.get(i).getyUnit()) {
        return false;
      }
    }

    for (int i = 0; i < EList.bombs.size(); i++) {
      if (x == EList.bombs.get(i).getxUnit() && y == EList.bombs.get(i).getyUnit()) {
        if (!EList.bombs.get(i).isExploding()) {
          EList.bombs.get(i).setAnimation(timeExploding);
        }
        return false;
      }
    }
    return true;
  }
}
