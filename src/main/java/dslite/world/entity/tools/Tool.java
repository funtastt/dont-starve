package dslite.world.entity.tools;

import dslite.utils.enums.ItemType;
import dslite.ui.views.GameView;
import dslite.world.entity.Item;

public abstract class Tool extends Item {

    protected byte durability;

    public Tool(ItemType type, byte durability) {
        super(type);
        this.durability = durability;
    }

    public byte getDurability() {
        return durability;
    };

    public void setDurability(byte durability) {
        this.durability += durability;
        if (getDurability() <= 0) {
            GameView.getPlayer().getInventory().removeItem(this);
        }
    };
}
