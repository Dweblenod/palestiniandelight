package com.dweb.paldelight.data.tag;

import com.dweb.paldelight.PalDelight;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class PDTags {
    
    public static class Blocks {
        public static final TagKey<Block> OLIVE_LOGS = tag("logs/olive");
        
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(PalDelight.id(name));
        }
    }
    
    public static class Items {
        public static final TagKey<Item> OLIVE_LOGS = tag("logs/olive");
        
        private static TagKey<Item> tag(String name) {
            return ItemTags.create(PalDelight.id(name));
        }
    }
}
