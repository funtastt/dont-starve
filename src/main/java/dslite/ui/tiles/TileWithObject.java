package dslite.ui.tiles;

import dslite.utils.enums.Texture;
import dslite.utils.enums.TileType;
import dslite.world.entity.ObjectGenerator;
import dslite.world.entity.MapObject;
import dslite.world.map.Point;
import javafx.scene.canvas.GraphicsContext;

public final class TileWithObject extends Tile {
    private MapObject object;
    private final Point objPosition;

    public TileWithObject(TileType type, Point pos, int objIndex) {
        super(type);
        this.objPosition = pos;
        object = ObjectGenerator.getObject(objIndex);

        assert object != null;
        object.setTile(this);
    }

    @Override
    public void draw(GraphicsContext gc, int i, int j) {
        super.draw(gc, i, j);
        gc.drawImage(Texture.getTextureBySprite(object.getSprite()), i, j, 1.0, 1.0);
    }

    public Point getObjPosition() {
        return objPosition;
    }

    public void setObject(MapObject object) {
        this.object = object;
    }

    public MapObject getObject() {
        return object;
    }

}
