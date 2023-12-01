package dslite.ui.tiles;

import dslite.utils.enums.ObjectType;
import dslite.utils.enums.Texture;
import dslite.utils.enums.TileType;
import dslite.world.entity.generators.MapObjectGenerator;
import dslite.world.entity.MapObject;
import dslite.world.map.Point;
import javafx.scene.canvas.GraphicsContext;

public final class TileWithObject extends Tile {
    private MapObject object;
    private final Point objPosition;

    public TileWithObject(TileType type, Point pos, int objectSprite) {
        super(type);
        this.objPosition = pos;
        object = MapObjectGenerator.getObject(ObjectType.getBySprite(objectSprite));

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
