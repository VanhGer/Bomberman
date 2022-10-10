package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.CommonFunc;
import uet.oop.bomberman.EList;
import uet.oop.bomberman.graphics.Sprite;

public class Bomber extends DynamicEntity {

  public boolean Dead;
  public int deadAnimation;
  public Bomber(int x, int y, Image img) {
    super(x, y, img);
    Dead = false;
    deadAnimation = 0;
  }

  public boolean isDead() {
    return Dead;
  }

  public void setDead(boolean dead) {
    Dead = dead;
  }

  @Override
  public boolean isDone() {
    return isDead() && deadAnimation >= CommonFunc.Period;
  }

  @Override
  public boolean check_colliding(int nxt_x, int nxt_y) {
    for (Entity ent : EList.walls) {
      if (CommonFunc.Bomber_Collide(nxt_x, nxt_y, ent.getX(), ent.getY())) {
        return true;
      }
    }

    for (Entity ent : EList.bricks) {
      if (CommonFunc.Bomber_Collide(nxt_x, nxt_y, ent.getX(), ent.getY())) {
        return true;
      }
    }
    return false;
  }

  @Override
  public void update() {
    if (isMoving() && ! isDead()) {
      int nxt_x = getX();
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
      animation++;
      if (animation == CommonFunc.Period) animation = 0;
    }
    if (!isDead()) {
      check_Dead();
    } else {
      deadAnimation++;
    }
    if (isDead() || isMoving()) {
      change_img();
    }

  }
  /** check if player is dead. */
  public void check_Dead() {
    for (int i = 0; i < EList.explosions.size(); i++) {
      if (CommonFunc.Collide(getX(), getY(), EList.explosions.get(i).getX(), EList.explosions.get(i).getY())) {
        setDead(true);
        deadAnimation = 0;
        return;
      }
    }
  }

  @Override
  public void change_img() {
    if (isDead()) {
      setImg(Sprite.movingSprite(Sprite.player_dead1, Sprite.player_dead2, Sprite.player_dead3, deadAnimation,
              CommonFunc.Period).getFxImage());
      return;
    }

    /** up. */
    if (getDir() == 1) {
      setImg(
          Sprite.movingSprite(
                  Sprite.player_up,
                  Sprite.player_up_1,
                  Sprite.player_up_2,
                  animation,
                  CommonFunc.Period)
              .getFxImage());
    }

    /** down. */
    if (getDir() == 2) {
      setImg(
          Sprite.movingSprite(
                  Sprite.player_down,
                  Sprite.player_down_1,
                  Sprite.player_down_2,
                  animation,
                  CommonFunc.Period)
              .getFxImage());
    }

    /** left. */
    if (getDir() == 3) {
      setImg(
          Sprite.movingSprite(
                  Sprite.player_left,
                  Sprite.player_left_1,
                  Sprite.player_left_2,
                  animation,
                  CommonFunc.Period)
              .getFxImage());
    }

    /** right. */
    if (getDir() == 4) {
      setImg(
          Sprite.movingSprite(
                  Sprite.player_right,
                  Sprite.player_right_1,
                  Sprite.player_right_2,
                  animation,
                  CommonFunc.Period)
              .getFxImage());
    }
  }

}
