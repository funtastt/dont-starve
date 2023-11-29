package dslite.world;

import dslite.GameLauncher;
import dslite.controllers.MenuController;
import dslite.world.map.Point;

import java.util.*;

public final class WorldMap {
    private final int width;
    private final int height;

    private Point spawnPoint;

    public WorldMap(int width, int height) {
        this.width = width;
        this.height = height;

        generateMap();
    }

    // TODO:
    public void generateMap() {
    }


    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Point getSpawnPoint() {
        return spawnPoint;
    }

    public void setSpawnPoint(Point spawnPoint) {
        this.spawnPoint = spawnPoint;
    }
}