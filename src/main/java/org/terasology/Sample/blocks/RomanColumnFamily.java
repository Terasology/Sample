package org.terasology.Sample.blocks;

import gnu.trove.map.TByteObjectMap;
import org.terasology.math.Side;
import org.terasology.math.SideBitFlag;
import org.terasology.math.geom.Vector3i;
import org.terasology.world.BlockEntityRegistry;
import org.terasology.world.WorldProvider;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockUri;
import org.terasology.world.block.family.BlockFamily;
import org.terasology.world.block.family.UpdatesWithNeighboursFamily;

import java.util.ArrayList;
import java.util.List;

public class RomanColumnFamily extends UpdatesWithNeighboursFamily {

    private TByteObjectMap<Block> blocks;

    // Archetype is the base block of the family
    // attachmentSide is the side of the block that the block being placed is going to be attached to
    public RomanColumnFamily(BlockUri blockUri, List<String> categories, Block archetypeBlock, TByteObjectMap<Block> blocks) {
        super(null, blockUri, categories, archetypeBlock, blocks, (byte) 63);
        this.blocks = blocks;
    }

    @Override
    public Block getBlockForPlacement(WorldProvider worldProvider, BlockEntityRegistry blockEntityRegistry, Vector3i location, Side attachmentSide, Side direction) {
        return getProperBlock(worldProvider, location);
    }

    @Override
    public Block getBlockForNeighborUpdate(WorldProvider worldProvider, BlockEntityRegistry blockEntityRegistry, Vector3i location, Block oldBlock) {
        return getProperBlock(worldProvider, location);
    }

    private Block getProperBlock(WorldProvider worldProvider, Vector3i location) {

        List<Side> sides = new ArrayList<>();
        for (Side side : new Side[] {Side.TOP, Side.BOTTOM}) {
            Vector3i neighborLocation = new Vector3i(location);
            neighborLocation.add(side.getVector3i());
            if (worldProvider.isBlockRelevant(neighborLocation)) {
                Block neighborBlock = worldProvider.getBlock(neighborLocation);
                final BlockFamily blockFamily = neighborBlock.getBlockFamily();
                if (blockFamily instanceof RomanColumnFamily) {
                    sides.add(side);
                }
            }
        }
        if (sides.contains(Side.BOTTOM) && !sides.contains(Side.TOP)) {
            return blocks.get(SideBitFlag.getSide(Side.TOP)); // Returns a block that should be on top of the stack
        } else if (!sides.contains(Side.BOTTOM) && sides.contains(Side.TOP)) {
            return blocks.get(SideBitFlag.getSide(Side.BOTTOM)); // Returns a block that should be on the bottom of the stack
        } else if (sides.contains(Side.BOTTOM) && sides.contains(Side.TOP)) {
            return blocks.get(SideBitFlag.getSides(Side.BOTTOM, Side.TOP)); // Returns a normal column block
        } else if (!sides.contains(Side.BOTTOM) && !sides.contains(Side.TOP)) {
            return blocks.get(SideBitFlag.getSides(Side.BOTTOM, Side.TOP)); // Returns a normal column block
        }
        return null; // If we return null, something has gone terribly wrong
    }

}
