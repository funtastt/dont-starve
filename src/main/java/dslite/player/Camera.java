package dslite.player;

import dslite.utils.interfaces.Updatable;

public final class Camera implements Updatable {
    private final int width;
    private final int height;
    private int xOffset;
    private int yOffset;
    private Player player;

    private static final int COLUMNS = GameScreen.COLUMNS_ON_SCREEN_COUNT;
    private static final int ROWS = GameScreen.ROWS_ON_SCREEN_COUNT;

    public Camera(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Camera(int width, int height, Player player) {
        this(width, height);
        setPlayer(player);
    }

    @Override
    public void update() {
        // Thanks to this logic, the player is always in the middle of screen

        int x = player.getPositionX();
        int y = player.getPositionY();

        xOffset = x - COLUMNS / 2;
        if (xOffset > 0) {
            xOffset = Math.min(x - COLUMNS / 2, width - 1);
        }

        yOffset = y - ROWS / 2;
        if (yOffset > 0) {
            yOffset = Math.min(y - ROWS / 2, height - 1);
        }
    }

    public void setPlayer(Player player) {
        this.player = player;
        update();
    }

    public int getxOffset() {
        return xOffset;
    }

    public int getyOffset() {
        return yOffset;
    }
}
