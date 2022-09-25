package uet.oop.bomberman;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.other.Bomb;

import java.util.ArrayList;
import java.util.List;

public class EList {
    public static List<Entity> grasses = new ArrayList<>();
    public static List<Entity> walls = new ArrayList<>();
    public static List<Entity> bricks = new ArrayList<>();
    public static List<Entity> enemies = new ArrayList<>();
    public static List<Entity> items = new ArrayList<>();
    public static List<Bomb> bombs = new ArrayList<>();

    public static void update() {
        /** update. */
        bricks.forEach(Entity::update);
        items.forEach(Entity::update);
        enemies.forEach(Entity::update);
        bombs.forEach(Entity::update);
        BombermanGame.bomber.update();

        /** remove. */
       bombs.removeIf(b -> b.isDone());


    }

    public static void render(GraphicsContext gc, Canvas canvas) {
        /** render. */
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        grasses.forEach(g -> g.render(gc));
        walls.forEach(g -> g.render(gc));
        bricks.forEach(g -> g.render(gc));
        enemies.forEach(g -> g.render(gc));
        items.forEach(g -> g.render(gc));
        bombs.forEach(g -> g.render(gc));
        BombermanGame.bomber.render(gc);
    }
}
