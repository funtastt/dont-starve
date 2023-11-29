package dslite.ui.tiles;

import dslite.interfaces.Drawable;
import javafx.scene.canvas.GraphicsContext;

public class Tile implements Drawable {

    private TileType type;

    public Tile(TileType type) {
        this.type = type;
    }

    public void draw(GraphicsContext gc, int i, int j) {
        gc.setFill(type.getColor());
        gc.fillRect(i, j, 1.0, 1.0);
    }

    public void setType(TileType t) {
        this.type = t;
    }

    public TileType getType() {
        return type;
    }

}