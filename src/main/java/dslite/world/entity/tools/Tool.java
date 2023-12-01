package dslite.world.entity.tools;

import dslite.utils.enums.ItemType;
import dslite.ui.views.GameView;
import dslite.world.entity.Item;

public abstract class Tool extends Item {

    protected int durability;

    public Tool(ItemType type, byte durability) {
        super(type);
        this.durability = durability;
    }

    public int getDurability() {
        return durability;
    };

    public void setDurability(int durability) {
        this.durability += durability;
        if (getDurability() <= 0) {
            GameView.getPlayer().getInventory().removeItem(this);
        }
    };
}
