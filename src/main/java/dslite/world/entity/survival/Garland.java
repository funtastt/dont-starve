package dslite.world.entity.survival;

import dslite.ui.views.GameView;
import dslite.utils.enums.ItemType;
import dslite.utils.interfaces.Craftable;
import dslite.utils.interfaces.Updatable;
import dslite.world.World;
import dslite.world.entity.Item;

import java.util.Map;

public final class Garland extends Item implements Craftable, Updatable {

    //Durability
    private int durability = 15 * World.DAY_LENGTH;

    public Garland() {
        super(ItemType.GARLAND);
    }

    @Override
    public void update() {
        if (--durability <= 0) {
            GameView.getPlayer().getInventory().removeItem(this);
        }
    }

    public Map<ItemType, Integer> getRequirements() {
        return Map.of(ItemType.PETAL, 1);
    }
}