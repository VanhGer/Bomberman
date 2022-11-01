package uet.oop.bomberman;

import javafx.util.Pair;
import uet.oop.bomberman.entities.DynamicEntity;
import uet.oop.bomberman.entities.Entity;

import java.security.cert.X509Certificate;
import java.util.ArrayDeque;
import java.util.Queue;

public class AutoFindPath {
    static int[][] visited = new int[40][40];
    static int[][] visited2 = new int[40][40];
    static int[][] dir = new int[40][40];
    static int[][] dir2 = new int[40][40];
    static int time = 0;

    private static boolean check_moveable1(int nxt_x, int nxt_y) {
        for (Entity ent: EList.walls) {
            if (nxt_x == ent.getxUnit() && nxt_y == ent.getyUnit()) {
                return false;
            }
        }

        for (Entity ent: EList.bricks) {
            if (nxt_x == ent.getxUnit() && nxt_y == ent.getyUnit()) {
                return false;
            }
        }

        for (Entity ent: EList.bombs) {
            if (nxt_x == ent.getxUnit() && nxt_y == ent.getyUnit()) {
                return false;
            }
        }

        return true;
    }

    private static boolean check_moveable2(int nxt_x, int nxt_y) {
        for (Entity ent: EList.walls) {
            if (nxt_x == ent.getxUnit() && nxt_y == ent.getyUnit()) {
                return false;
            }
        }

        for (Entity ent: EList.bombs) {
            if (nxt_x == ent.getxUnit() && nxt_y == ent.getyUnit()) {
                return false;
            }
        }

        return true;
    }

    public static void BFS(int x, int y) {
        time++;
        Queue<Pair<Integer, Integer>> q = new ArrayDeque<>();
        visited[x][y] = time;
        q.add(new Pair<>(x, y));
        while (! q.isEmpty()) {
            Pair<Integer, Integer> u = q.remove();
            for (int i = 1; i <= 4; i++) {
                int nxt_x = u.getKey();
                int nxt_y = u.getValue();
                if (i == 1) nxt_y += 1;
                if (i == 2) nxt_y -= 1;
                if (i == 3) nxt_x += 1;
                if (i == 4) nxt_x -= 1;
                if (nxt_x < 0 || nxt_x >= BombermanGame.WIDTH || nxt_y < 0 || nxt_y >= BombermanGame.HEIGHT)
                    continue;
                if (! check_moveable1(nxt_x, nxt_y)) {
                    continue;
                }
                if (visited[nxt_x][nxt_y] < time) {
                    visited[nxt_x][nxt_y] = time;
                    dir[nxt_x][nxt_y] = i;
                    q.add(new Pair<>(nxt_x, nxt_y));
                }
            }
        }
        visited2[x][y] = time;
        q.add(new Pair<>(x, y));
        while (! q.isEmpty()) {
            Pair<Integer, Integer> u = q.remove();
            for (int i = 1; i <= 4; i++) {
                int nxt_x = u.getKey();
                int nxt_y = u.getValue();
                if (i == 1) nxt_y += 1;
                if (i == 2) nxt_y -= 1;
                if (i == 3) nxt_x += 1;
                if (i == 4) nxt_x -= 1;
                if (nxt_x < 0 || nxt_x >= BombermanGame.WIDTH || nxt_y < 0 || nxt_y >= BombermanGame.HEIGHT)
                    continue;
                if (! check_moveable2(nxt_x, nxt_y)) {
                    continue;
                }
                if (visited2[nxt_x][nxt_y] < time) {
                    visited2[nxt_x][nxt_y] = time;
                    dir2[nxt_x][nxt_y] = i;
                    q.add(new Pair<>(nxt_x, nxt_y));
                }
            }
        }
    }

    public static int getAutoDir1(int x, int y) {
        if (visited[x][y] < time) return 0;
        return dir[x][y];
    }
    public static int getAutoDir2(int x, int y) {
        if (visited2[x][y] < time) return 0;
        return dir2[x][y];
    }
}
