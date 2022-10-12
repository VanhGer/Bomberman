package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.EList;
import uet.oop.bomberman.Map;
import uet.oop.bomberman.PlaySound;
import uet.oop.bomberman.other.Bomb;
import uet.oop.bomberman.other.Explosion;

public class Item extends Entity {
  public enum type {
    Bomb,
    Flame,
    Speed,
    Portal
  }

  private type t;
  private boolean used;

  public Item(int x, int y, Image img, type t) {
    super(x, y, img);
    used = false;
    this.t = t;
  }

  public void setUsed(boolean used) {
    this.used = used;
  }

  public boolean isUsed() {
    return used;
  }

  @Override
  public void update() {
    if (getxUnit() == EList.bomberman.getxUnit()
        && getyUnit() == EList.bomberman.getyUnit()) {
      setUsed(true);
      PlaySound.play("res/Sound/ting.wav", 0);
      switch (t) {
        case Bomb:
          {
            Bomb.bombLimit++;
            break;
          }

        case Flame:
          {
            Explosion.Explosionrange++;
            break;
          }

        case Speed:
          {
            EList.bomberman.speed++;
            break;
          }

        case Portal:
          {
            Map.levelUp();
            return;
          }

        default:
          break;
      }
    }
  }
}
