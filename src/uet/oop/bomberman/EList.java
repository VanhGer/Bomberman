package uet.oop.bomberman;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import uet.oop.bomberman.entities.Brick;
import uet.oop.bomberman.entities.Entity;

import java.util.ArrayList;
import java.util.List;

public class EList {
    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Entity> bricks = new ArrayList<>();
    public static List<Entity> enemies = new ArrayList<>();
    public static List<Entity> items = new ArrayList<>();

    public static void update() {
        entities.forEach(Entity::update);
        bricks.forEach(Entity::update);
        items.forEach(Entity::update);
        entities.forEach(Entity::update);
    }

    public static void render(GraphicsContext gc, Canvas canvas) {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        enemies.forEach(g -> g.render(gc));
        bricks.forEach(g -> g.render(gc));
        items.forEach(g -> g.render(gc));


    }
}
