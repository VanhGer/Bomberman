package uet.oop.bomberman;

import uet.oop.bomberman.enemies.*;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map {
  public static int level = 1;
  public static final int maxLevel = 3;

  public static void levelUp() {
    level++;
    Read();
  }

  public static void Read() {
    if (level == maxLevel) {
      // win -> in ra mh chien thang.
      BombermanGame.wonScence();
    }
    EList.clear();
    String tmp = "level" + level + ".txt";
    Scanner scanner = null;
    try {
      scanner = new Scanner(new File(String.format("res/levels/" + tmp)));
    } catch (FileNotFoundException e) {
      return;
    }

    int lv = scanner.nextInt();
    int row = scanner.nextInt();
    int col = scanner.nextInt();
    tmp = scanner.nextLine();
    Entity ent;
    for (int j = 0; j < row; j++) {
      tmp = scanner.nextLine();
      for (int i = 0; i < col; i++) {
        char c = tmp.charAt(i);
        switch (c) {
          case '#':
          {
            ent = new Wall(i, j, Sprite.wall.getFxImage());
            EList.walls.add((Wall) ent);
            break;
          }

          case '*':
          case 'x':
          case 'b':
          case 'f':
          case 's':
          {
            ent = new Brick(i, j, Sprite.brick.getFxImage());
            EList.bricks.add((Brick) ent);
            ent = new Grass(i, j, Sprite.grass.getFxImage());
            EList.grasses.add((Grass) ent);
            // xu ly th ben trong co item???
            if (c == 'x') {
              ent = new Item(i, j, Sprite.portal.getFxImage(), Item.type.Portal);
            } else if (c == 'b') {
              ent = new Item(i, j, Sprite.powerup_bombs.getFxImage(), Item.type.Bomb);
            } else if (c == 'f') {
              ent = new Item(i, j, Sprite.powerup_flames.getFxImage(), Item.type.Flame);
            } else if (c == 's') {
              ent = new Item(i, j, Sprite.powerup_speed.getFxImage(), Item.type.Speed);
            }
            if (c != '*') {
              EList.items.add((Item) ent);
            }
            break;
          }

          case 'p':
          {
            EList.bomberman = new Bomber(i, j, Sprite.player_down.getFxImage());
            ent = new Grass(i, j, Sprite.grass.getFxImage());
            EList.grasses.add((Grass) ent);
            break;
          }

          case '1':
          {
            ent = new Balloon(i, j, Sprite.balloom_right1.getFxImage());
            EList.enemies.add((Enemies) ent);
            ent = new Grass(i, j, Sprite.grass.getFxImage());
            EList.grasses.add((Grass) ent);
            break;
          }

          case '2':
          {
            ent = new Oneal(i, j, Sprite.oneal_right1.getFxImage());
            EList.enemies.add((Enemies) ent);
            ent = new Grass(i, j, Sprite.grass.getFxImage());
            EList.grasses.add((Grass) ent);
            break;
          }
          case '3':
          {
            ent = new Doll(i, j, Sprite.doll_right1.getFxImage());
            EList.enemies.add((Enemies) ent);
            ent = new Grass(i, j, Sprite.grass.getFxImage());
            EList.grasses.add((Grass) ent);
            break;
          }

          case '4':
          {
            ent = new Minvo(i, j, Sprite.doll_right1.getFxImage());
            EList.enemies.add((Enemies) ent);
            ent = new Grass(i, j, Sprite.grass.getFxImage());
            EList.grasses.add((Grass) ent);
            break;
          }

          case '5':
          {
            ent = new Kondoria(i, j, Sprite.doll_right1.getFxImage());
            EList.enemies.add((Enemies) ent);
            ent = new Grass(i, j, Sprite.grass.getFxImage());
            EList.grasses.add((Grass) ent);
            break;
          }

          default:
          {
            ent = new Grass(i, j, Sprite.grass.getFxImage());
            EList.grasses.add((Grass) ent);
          }
        }
      }
    }
  }
}