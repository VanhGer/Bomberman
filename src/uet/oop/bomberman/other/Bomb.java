package uet.oop.bomberman.other;

import javafx.scene.image.Image;
import uet.oop.bomberman.entities.DynamicEntity;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class Bomb extends DynamicEntity {
  public static final int timeExploding = 90;
  public static final int bombTime = 20;
  private boolean Exploding;


  public Bomb(int x, int y, Image img) {
    super(x, y, img);
    animation = 0;
    Exploding = false;
  }

  public boolean isExploding() {
    return Exploding;
  }

  public void setExploding(boolean Exploding) {
    this.Exploding = Exploding;
  }

  public int getAnimation() {
    return animation;
  }

  public void setAnimation(int animation) {
    this.animation = animation;
  }

  public boolean isDone() {
    return (isExploding() && animation == bombTime);
  }

  @Override
  public void update() {

    if (!isExploding()) {
      if (animation != timeExploding) {
        setImg(
            Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, animation, bombTime)
                .getFxImage());
        animation++;
      } else {

        /** bomb starts to explode. */
        setExploding(true);
        animation = 0;
      }
    } else {
      if (animation == bombTime) {
        return;
      }

      setImg(
          Sprite.movingSprite(
                  Sprite.bomb_exploded,
                  Sprite.bomb_exploded1,
                  Sprite.bomb_exploded2,
                  animation,
                  bombTime)
              .getFxImage());
      animation++;
    }
  }
}
