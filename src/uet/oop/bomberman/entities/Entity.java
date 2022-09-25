package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Entity {
    //Tọa độ X tính từ góc trái trên trong Canvas
    private int x;

    //Tọa độ Y tính từ góc trái trên trong Canvas
    private int y;

    // Real coordinates
    private int xUnit;
    private int yUnit;

    protected Image img;

    //Khởi tạo đối tượng, chuyển từ tọa độ đơn vị sang tọa độ trong canvas
    public Entity( int xUnit, int yUnit, Image img) {
        this.x = xUnit * Sprite.SCALED_SIZE;
        this.y = yUnit * Sprite.SCALED_SIZE;
        this.img = img;
        this.xUnit = xUnit;
        this.yUnit = yUnit;
    }

    public int getxUnit() {
        return xUnit;
    }

    public void setxUnit(int xUnit) {
        this.xUnit = xUnit;
    }

    public int getyUnit() {
        return yUnit;
    }

    public void setyUnit(int yUnit) {
        this.yUnit = yUnit;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public Image getImg() {
        return img;
    }

    public void change_coordinates(int nxt_x, int nxt_y) {
        setX(nxt_x);
        setY(nxt_y);
        setxUnit((nxt_x + Sprite.SCALED_SIZE / 2) / Sprite.SCALED_SIZE);
        setyUnit((nxt_y + Sprite.SCALED_SIZE / 2)/ Sprite.SCALED_SIZE);
    }
    public void render(GraphicsContext gc) {
        gc.drawImage(img, x, y);
    }
    public abstract void update();
}
