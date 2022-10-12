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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.other.Bomb;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class BombermanGame extends Application {

  public static final int WIDTH = 31;
  public static final int HEIGHT = 13;

  public static GraphicsContext gc;
  public static Canvas canvas;
  public static AnimationTimer timer;
  public static Stage mainStage;
  public static Group root;
  public static Scene scene;
  @Override
  public void start(Stage stage) {
    // Tao Canvas
    mainStage = stage;
    canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
    gc = canvas.getGraphicsContext2D();

    // Tao root container
    root = new Group();
    root.getChildren().add(canvas);

    // Tao scene
    scene = new Scene(root);

    // Them scene vao stage
    stage.setScene(scene);
    stage.show();

    PlaySound.play("D:/Yatogami/Code/Java/Bomberman-main(V2)/res/Sound/theme.wav", -1);

    // Di chuyen Bomber
    scene.setOnKeyPressed(
        new EventHandler<KeyEvent>() {
          @Override
          public void handle(KeyEvent keyEvent) {
            switch (keyEvent.getCode()) {
              case SPACE:
                {
                  // set bomb
                  if (EList.bombs.size() < Bomb.bombLimit) {
                    Bomb bomb =
                        new Bomb(
                            EList.bomberman.getxUnit(),
                            EList.bomberman.getyUnit(),
                            Sprite.bomb.getFxImage());
                    EList.bombs.add(bomb);
                  }
                  break;
                }
              case UP:
                {
                  // Move up
                  EList.bomberman.setMoving(true);
                  EList.bomberman.setDir(1);
                  break;
                }

              case DOWN:
                {
                  // Move down
                  EList.bomberman.setMoving(true);
                  EList.bomberman.setDir(2);
                  break;
                }

              case LEFT:
                {
                  // Move left
                  EList.bomberman.setMoving(true);
                  EList.bomberman.setDir(3);
                  break;
                }

              case RIGHT:
                {
                  // Move right
                  EList.bomberman.setMoving(true);
                  EList.bomberman.setDir(4);
                  break;
                }
              default:
                break;
            }
          }
        });

    scene.setOnKeyReleased(
        new EventHandler<KeyEvent>() {
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

    timer =
        new AnimationTimer() {
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
      root = new Group(imageView);
      scene = new Scene(root);
      scene.setOnKeyPressed(
          new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
              if (keyEvent.getCode() == KeyCode.ENTER) {
                System.exit(0);
              }
            }
          });
      mainStage.setScene(scene);
      mainStage.show();

    } catch (FileNotFoundException e) {
      System.exit(0);
    }
  }
}
