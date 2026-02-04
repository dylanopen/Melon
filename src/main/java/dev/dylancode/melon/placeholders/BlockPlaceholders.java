package dev.dylancode.melon.placeholders;

import org.bukkit.block.Block;

import java.util.HashMap;

public class BlockPlaceholders {
    public static HashMap<String, String> get(Block block, String prefix) {
        if (prefix == null) {
            prefix = "";
        }
        HashMap<String, String> placeholders = new HashMap<>();
        placeholders.put(prefix + "material", block.getType().toString().toLowerCase());
        placeholders.put(prefix + "x", String.valueOf(block.getX()));
        placeholders.put(prefix + "y", String.valueOf(block.getY()));
        placeholders.put(prefix + "z", String.valueOf(block.getZ()));
        placeholders.put(prefix + "world", block.getWorld().getName());
        placeholders.put(prefix + "chunkx", String.valueOf(block.getChunk().getX()));
        placeholders.put(prefix + "chunkz", String.valueOf(block.getChunk().getZ()));
        placeholders.put(prefix + "biome", block.getBiome().toString().toLowerCase());
        placeholders.put(prefix + "lightlevel", String.valueOf(block.getLightLevel()));
        placeholders.put(prefix + "power", String.valueOf(block.getBlockPower()));
        placeholders.put(prefix + "isloaded", String.valueOf(block.getChunk().isLoaded()));
        placeholders.put(prefix + "isliquid", String.valueOf(block.isLiquid()));
        placeholders.put(prefix + "issolid", String.valueOf(block.getType().isSolid()));
        placeholders.put(prefix + "isoccluding", String.valueOf(block.getType().isOccluding()));

        return placeholders;
    }

    public static HashMap<String, String> get(Block block) {
        return get(block, "");
    }
}
