package uet.oop.bomberman;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.other.Bomb;
import uet.oop.bomberman.other.Explosion;

import java.util.ArrayList;
import java.util.List;

public class EList {
  public static List<Grass> grasses = new ArrayList<>();
  public static List<Wall> walls = new ArrayList<>();
  public static List<Brick> bricks = new ArrayList<>();
  public static List<Entity> enemies = new ArrayList<>();
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
    BombermanGame.bomber = null;
  }

  public static void update() {
    /** update. */
    bricks.forEach(Entity::update);
    items.forEach(Entity::update);
    enemies.forEach(Entity::update);
    bombs.forEach(Entity::update);
    explosions.forEach(Entity::update);
    if (BombermanGame.bomber != null) {
      BombermanGame.bomber.update();
    }


    /** remove. */
    bombs.removeIf(b -> b.isDone());
    explosions.removeIf(e -> e.isDone());
    bricks.removeIf(b -> b.isDone());
    items.removeIf(i -> i.isUsed());
    if (BombermanGame.bomber.isDone()) {
      BombermanGame.bomber = null;
      System.exit(0);
    }
  }

  public static void render(GraphicsContext gc, Canvas canvas) {
    /** render. */
    gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
    grasses.forEach(g -> g.render(gc));
    walls.forEach(g -> g.render(gc));
    enemies.forEach(g -> g.render(gc));
    items.forEach(g -> g.render(gc));
    bricks.forEach(g -> g.render(gc));
    bombs.forEach(g -> g.render(gc));
    explosions.forEach(g -> g.render(gc));
    if (BombermanGame.bomber != null) {
        BombermanGame.bomber.render(gc);
    }
  }
}
