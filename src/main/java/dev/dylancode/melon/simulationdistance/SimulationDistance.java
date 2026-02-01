package dev.dylancode.melon.simulationdistance;

import org.bukkit.World;

public class SimulationDistance {
    public static int get(World world) {
        return world.getSimulationDistance();
    }

    public static void set(World world, int distance) {
        world.setSimulationDistance(distance);
    }
}
