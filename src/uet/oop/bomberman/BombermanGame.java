package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.other.Bomb;

import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    
    public static GraphicsContext gc;
    public static Canvas canvas;

    public static Bomber bomber;

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        // Di chuyen Bomber
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case SPACE: {
                        // set bomb
                        if (EList.bombs.size() < Bomb.bombLimit) {
                            Bomb bomb = new Bomb(bomber.getxUnit(), bomber.getyUnit(), Sprite.bomb.getFxImage());
                            EList.bombs.add(bomb);
                        }
                        break;
                    }
                    case UP: {
                        // Move up
                        bomber.setMoving(true);
                        bomber.setDir(1);
                        break;
                    }

                    case DOWN: {
                        // Move down
                        bomber.setMoving(true);
                        bomber.setDir(2);
                        break;
                    }

                    case LEFT: {
                        //Move left
                        bomber.setMoving(true);
                        bomber.setDir(3);
                        break;
                    }

                    case RIGHT: {
                        //Move right
                        bomber.setMoving(true);
                        bomber.setDir(4);
                        break;
                    }
                    default:
                        break;
                }
            }
        });

        scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                switch (keyEvent.getCode()) {
                    case UP:
                    case DOWN:
                    case LEFT:
                    case RIGHT:
                        bomber.setMoving(false);
                }
            }
        });


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                EList.render(gc, canvas);
                EList.update();
            }
        };
        timer.start();
    }


}
