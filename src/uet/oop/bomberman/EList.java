package uet.oop.bomberman;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.enemies.Enemies;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.other.Bomb;
import uet.oop.bomberman.other.Explosion;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.List;

public class EList {
  public static Bomber bomberman;
  public static List<Grass> grasses = new ArrayList<>();
  public static List<Wall> walls = new ArrayList<>();
  public static List<Brick> bricks = new ArrayList<>();
  public static List<Enemies> enemies = new ArrayList<>();
  public static List<Item> items = new ArrayList<>();
  public static List<Bomb> bombs = new ArrayList<>();
  public static List<Explosion> explosions = new ArrayList<>();


  public static void clear() {
    grasses.clear();
    walls.clear();
    bricks.clear();
    enemies.clear();
    items.clear();
    bombs.clear();
    explosions.clear();
    //BombermanGame.bomber = null;
  }

  public static void update() {
    /** update. */

    try{
      bombs.forEach(Entity::update);
      bricks.forEach(Entity::update);
      enemies.forEach(Entity::update);
      explosions.forEach(Entity::update);
      bomberman.update();
      items.forEach(Entity::update);


      /** remove. */
      bombs.removeIf(Bomb::isDone);
      explosions.removeIf(Explosion::isDone);
      bricks.removeIf(Brick::isDone);
      items.removeIf(Item::isUsed);
      enemies.removeIf(Enemies::isDone);
    } catch (ConcurrentModificationException con) {
    }
  }

  public static boolean lose() {
    return bomberman.isDone();
  }

  public static void render(GraphicsContext gc, Canvas canvas) {
    /** render. */
    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    grasses.forEach(g -> g.render(gc));
    walls.forEach(g -> g.render(gc));
    items.forEach(g -> g.render(gc));
    bricks.forEach(g -> g.render(gc));
    enemies.forEach(g -> g.render(gc));
    bombs.forEach(g -> g.render(gc));
    explosions.forEach(g -> g.render(gc));
    if (bomberman != null) {
      bomberman.render(gc);
    }
  }
}