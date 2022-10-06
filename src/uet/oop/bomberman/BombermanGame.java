package uet.oop.bomberman;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.entities.Bomber;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Grass;
import uet.oop.bomberman.entities.Wall;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.other.Bomb;

import java.awt.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BombermanGame extends Application {
    
    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;
    
    public static GraphicsContext gc;
    public static Canvas canvas;
    public static AnimationTimer timer;
    public static Stage mainStage;

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        mainStage = stage;
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
                            Bomb bomb = new Bomb(EList.bomberman.getxUnit(), EList.bomberman.getyUnit(), Sprite.bomb.getFxImage());
                            EList.bombs.add(bomb);
                        }
                        break;
                    }
                    case UP: {
                        // Move up
                        EList.bomberman.setMoving(true);
                        EList.bomberman.setDir(1);
                        break;
                    }

                    case DOWN: {
                        // Move down
                        EList.bomberman.setMoving(true);
                        EList.bomberman.setDir(2);
                        break;
                    }

                    case LEFT: {
                        //Move left
                        EList.bomberman.setMoving(true);
                        EList.bomberman.setDir(3);
                        break;
                    }

                    case RIGHT: {
                        //Move right
                        EList.bomberman.setMoving(true);
                        EList.bomberman.setDir(4);
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
                        EList.bomberman.setMoving(false);
                }
            }
        });


        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                EList.render(gc, canvas);
                EList.update();
                if (EList.lose()) {
                    lostScence();
                }
            }
        };
        timer.start();
    }

    public static void lostScence() {
        displayImage("res/scene/gameover.png");
    }

    public static void wonScence() {
        displayImage("res/scene/victory.png");
    }

    private static void displayImage(String nameOfFile) {
        timer.stop();
        try {
            InputStream ip = new FileInputStream(nameOfFile);
            Image image = new Image(ip);
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(Sprite.SCALED_SIZE * HEIGHT);
            imageView.setFitWidth(Sprite.SCALED_SIZE * WIDTH);
            Group root = new Group(imageView);
            Scene scene = new Scene(root);
            scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent keyEvent) {
                    System.exit(0);
                }
            });
            mainStage.setScene(scene);
            mainStage.show();

        } catch (FileNotFoundException e) {
            System.exit(0);

        }

    }


}
