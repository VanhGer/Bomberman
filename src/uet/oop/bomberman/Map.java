package uet.oop.bomberman;

import uet.oop.bomberman.enemies.Balloon;
import uet.oop.bomberman.enemies.Oneal;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Map {
    public static int level = 1;
    public static final int maxLevel = 5;
    public static void Read() {
        if (level == maxLevel) {
            // win -> in ra mh chien thang.
            return;
        }
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
                switch(c) {
                    case '#': {
                        ent = new Wall(i, j, Sprite.wall.getFxImage());
                        EList.stillObjects.add(ent);
                        break;
                    }
                    case '*':
                    case 'x':
                    case 'b':
                    case 'f':
                    case 's': {
                        ent = new Wall(i, j, Sprite.brick.getFxImage());
                        EList.bricks.add(ent);
                        ent = new Grass(i, j, Sprite.grass.getFxImage());
                        EList.stillObjects.add(ent);
                        // xu ly th ben trong co item???
                        if (c == 'x') {
                            ent = new Item(i, j, Sprite.portal.getFxImage());
                        } else if (c == 'b') {
                            ent = new Item(i, j, Sprite.powerup_bombs.getFxImage());
                        } else if (c == 'f') {
                            ent = new Item(i, j, Sprite.powerup_flames.getFxImage());
                        } else if (c == 's') {
                            ent = new Item(i, j, Sprite.powerup_speed.getFxImage());
                        }
                        if (c != '*') {
                            EList.items.add(ent);
                        }
                        break;
                    }
                    case 'p': {
                        ent = new Bomber(i, j, Sprite.player_down.getFxImage());
                        EList.entities.add(ent);
                        ent = new Grass(i, j, Sprite.grass.getFxImage());
                        EList.stillObjects.add(ent);
                        break;
                    }
                    case '1': {
                        ent = new Balloon(i, j, Sprite.balloom_right1.getFxImage());
                        EList.enemies.add(ent);
                        ent = new Grass(i, j, Sprite.grass.getFxImage());
                        EList.stillObjects.add(ent);
                        break;
                    }
                    case '2': {
                        ent = new Oneal(i, j, Sprite.oneal_right1.getFxImage());
                        EList.enemies.add(ent);
                        ent = new Grass(i, j, Sprite.grass.getFxImage());
                        EList.stillObjects.add(ent);
                        break;
                    }
                    default: {
                        ent = new Grass(i, j, Sprite.grass.getFxImage());
                        EList.stillObjects.add(ent);
                    }
                }
            }
        }
    }
}
