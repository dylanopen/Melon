package dev.dylancode.melon.viewdistance;

import org.bukkit.World;

public class ViewDistance {
    public static int get(World world) {
        return world.getViewDistance();
    }

    public static void set(World world, int distance) {
        world.setViewDistance(distance);
    }
}
