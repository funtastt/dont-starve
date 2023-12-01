package dslite.world.entity.food;

import dslite.player.Player;
import dslite.player.inventory.Inventory;
import dslite.ui.views.GameView;
import dslite.utils.enums.ItemType;
import dslite.world.entity.Item;

public abstract class Food extends Item {

    protected double foodValue;
    protected double healthValue;
    protected double sanityValue;
    protected Item cookedForm;

    public Food(ItemType type, Food cookedForm, double foodValue, double healthValue, double sanityValue) {
        super(type);
        this.cookedForm = cookedForm;
        this.foodValue = foodValue;
        this.healthValue = healthValue;
        this.sanityValue = sanityValue;
    }

    public void eat() {
        Player player = GameView.getPlayer();
        player.addSatiety(foodValue);
        player.addHealth(healthValue);
        player.addSanity(sanityValue);
    }

    public void cook() {
        Inventory inv = GameView.getPlayer().getInventory();
        if (cookedForm == null) return;

        if (inv.removeItemByType(getType(),1) == 1) {
            if (!inv.addItem(cookedForm.getType(),1)) {
                inv.addItem(getType(),1);
            }
            GameView.getPlayer().decreaseActions(1);
        }
    }
}