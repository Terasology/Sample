package org.terasology.Sample.blocks;

import gnu.trove.map.TByteObjectMap;
import gnu.trove.map.hash.TByteObjectHashMap;
import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.entitySystem.event.ReceiveEvent;
import org.terasology.math.Side;
import org.terasology.math.SideBitFlag;
import org.terasology.math.geom.Vector3i;
import org.terasology.naming.Name;
import org.terasology.registry.In;
import org.terasology.world.WorldProvider;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockBuilderHelper;
import org.terasology.world.block.BlockComponent;
import org.terasology.world.block.BlockUri;
import org.terasology.world.block.family.AbstractBlockFamily;
import org.terasology.world.block.family.BlockFamily;
import org.terasology.world.block.family.BlockSections;
import org.terasology.world.block.family.RegisterBlockFamily;
import org.terasology.world.block.family.UpdatesWithNeighboursFamily;
import org.terasology.world.block.items.OnBlockItemPlaced;
import org.terasology.world.block.loader.BlockFamilyDefinition;
import org.terasology.world.block.shapes.BlockShape;

import java.util.ArrayList;
import java.util.List;

@RegisterBlockFamily("romancolumn")
@BlockSections({"lone_block", "on_top", "on_bottom", "in_a_line"})
public class RomanColumnFamily extends AbstractBlockFamily implements UpdatesWithNeighboursFamily {

    @In
    WorldProvider worldProvider;

    Iterable<String> CATEGORIES = new ArrayList<String>() {

        private static final long serialVersionUID = 1L;

        {

            add("roman");
            add("column");
            add("romancolumn");

        }
    };

    private TByteObjectMap<Block> BLOCKS;

    BlockUri blockUri;

    // Archetype is the base block of the family
    // attachmentSide is the side of the block that the block being placed is going to be attached to
    public RomanColumnFamily(BlockFamilyDefinition definition, BlockShape shape, BlockBuilderHelper blockBuilder) {
        super(definition, shape, blockBuilder);
    }

    public RomanColumnFamily(BlockFamilyDefinition definition, BlockBuilderHelper blockBuilder) {
        super(definition, blockBuilder);

        BLOCKS = new TByteObjectHashMap<Block>();

        blockUri = new BlockUri(definition.getUrn());

        BLOCKS.put((byte) 0, makeABlock(definition, blockBuilder, "lone_block", blockUri, (byte) 0));
        BLOCKS.put(SideBitFlag.getSide(Side.TOP), makeABlock(definition, blockBuilder, "on_bottom", blockUri, SideBitFlag.getSide(Side.TOP)));
        BLOCKS.put(SideBitFlag.getSide(Side.BOTTOM), makeABlock(definition, blockBuilder, "on_top", blockUri, SideBitFlag.getSide(Side.BOTTOM)));
        BLOCKS.put(SideBitFlag.getSides(Side.BOTTOM, Side.TOP),
                makeABlock(definition, blockBuilder, "in_a_line", blockUri, SideBitFlag.getSides(Side.TOP, Side.BOTTOM)));

        this.setBlockUri(blockUri);
        this.setCategory(definition.getCategories());
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
            return BLOCKS.get(SideBitFlag.getSide(Side.TOP)); // Returns a block that should be on top of the stack
        } else if (!sides.contains(Side.BOTTOM) && sides.contains(Side.TOP)) {
            return BLOCKS.get(SideBitFlag.getSide(Side.BOTTOM)); // Returns a block that should be on the bottom of the stack
        } else if (sides.contains(Side.BOTTOM) && sides.contains(Side.TOP)) {
            return BLOCKS.get(SideBitFlag.getSides(Side.BOTTOM, Side.TOP)); // Returns a normal column block
        } else if (!sides.contains(Side.BOTTOM) && !sides.contains(Side.TOP)) {
            return BLOCKS.get(SideBitFlag.getSides(Side.BOTTOM, Side.TOP)); // Returns a normal column block
        }
        return null; // If we return null, something has gone terribly wrong
    }

    private Block makeABlock(BlockFamilyDefinition definition, BlockBuilderHelper blockBuilder, String section, BlockUri blockUri, byte sides) {
        Block newBlock = blockBuilder.constructSimpleBlock(definition, section);
        newBlock.setUri(new BlockUri(blockUri, new Name(String.valueOf(sides))));
        newBlock.setBlockFamily(this);

        return newBlock;
    }

    @ReceiveEvent
    public void onPlaceBlock(OnBlockItemPlaced event, EntityRef entity) {
        BlockComponent blockComponent = event.getPlacedBlock().getComponent(BlockComponent.class);
        if (blockComponent == null) {
            return;
        }

        Vector3i targetBlock = blockComponent.getPosition();
        processUpdateForBlockLocation(targetBlock);
    }

    private void processUpdateForBlockLocation(Vector3i blockLocation) {
        for (Side side : new Side[] {Side.TOP, Side.BOTTOM}) {
            Vector3i neighborLocation = new Vector3i(blockLocation);
            neighborLocation.add(side.getVector3i());
            if (worldProvider.isBlockRelevant(neighborLocation)) {
                Block neighborBlock = worldProvider.getBlock(neighborLocation);
                final BlockFamily blockFamily = neighborBlock.getBlockFamily();
                if (blockFamily instanceof RomanColumnFamily) {
                    RomanColumnFamily neighboursFamily = (RomanColumnFamily) blockFamily;
                    Block neighborBlockAfterUpdate = neighboursFamily.getBlockForNeighborUpdate(neighborLocation, neighborBlock);
                    if (neighborBlock != neighborBlockAfterUpdate) {
                        worldProvider.setBlock(neighborLocation, neighborBlockAfterUpdate);
                    }
                }
            }
        }
    }

    @Override
    public BlockUri getURI() {
        return blockUri;
    }

    @Override
    public String getDisplayName() {
        return "Roman Column";
    }

    @Override
    public Block getBlockForPlacement(Vector3i location, Side attachmentSide, Side direction) {
        return getProperBlock(worldProvider, location);
    }

    @Override
    public Block getArchetypeBlock() {
        return BLOCKS.get((byte) 0);
    }

    @Override
    public Block getBlockFor(BlockUri blockUri) {

        for (Block block : BLOCKS.valueCollection()) {
            if (block.getURI().equals(blockUri)) {
                return block;
            }
        }
        return null;

    }

    @Override
    public Iterable<Block> getBlocks() {
        return BLOCKS.valueCollection();
    }

    @Override
    public Iterable<String> getCategories() {
        return CATEGORIES;
    }

    @Override
    public boolean hasCategory(String category) {

        // Using category_1 because the parameter is already category
        for (String category_1 : CATEGORIES) {
            if (category_1.equals(category)) {
                return true;
            }
        }
        return false;

    }

    @Override
    public Block getBlockForNeighborUpdate(Vector3i location, Block oldBlock) {
        return getProperBlock(worldProvider, location);
    }

}
