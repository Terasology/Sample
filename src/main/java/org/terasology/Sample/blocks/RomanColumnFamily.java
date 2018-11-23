package org.terasology.Sample.blocks;

import gnu.trove.map.TByteObjectMap;
import gnu.trove.map.hash.TByteObjectHashMap;
import org.terasology.math.Side;
import org.terasology.math.SideBitFlag;
import org.terasology.math.geom.Vector3i;
import org.terasology.naming.Name;
import org.terasology.registry.In;
import org.terasology.world.WorldProvider;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockBuilderHelper;
import org.terasology.world.block.BlockUri;
import org.terasology.world.block.family.AbstractBlockFamily;
import org.terasology.world.block.family.BlockFamily;
import org.terasology.world.block.family.BlockSections;
import org.terasology.world.block.family.RegisterBlockFamily;
import org.terasology.world.block.family.UpdatesWithNeighboursFamily;
import org.terasology.world.block.loader.BlockFamilyDefinition;
import org.terasology.world.block.shapes.BlockShape;

import java.util.ArrayList;

@RegisterBlockFamily("romancolumn")
@BlockSections({"lone_block", "on_top", "on_bottom", "in_a_line"})
public class RomanColumnFamily extends AbstractBlockFamily implements UpdatesWithNeighboursFamily {

    @In
    WorldProvider worldProvider;
    
    private TByteObjectMap<Block> blocks;

    BlockUri blockUri;

    // Archetype is the base block of the family
    // attachmentSide is the side of the block that the block being placed is going to be attached to
    public RomanColumnFamily(BlockFamilyDefinition definition, BlockShape shape, BlockBuilderHelper blockBuilder) {
        super(definition, shape, blockBuilder);
    }

    public RomanColumnFamily(BlockFamilyDefinition definition, BlockBuilderHelper blockBuilder) {
        super(definition, blockBuilder);

        blocks = new TByteObjectHashMap<Block>();

        blockUri = new BlockUri(definition.getUrn());

        addConnection((byte) 0, "lone_block", definition, blockBuilder);
        addConnection(SideBitFlag.getSide(Side.TOP), "on_bottom", definition, blockBuilder);
        addConnection(SideBitFlag.getSide(Side.BOTTOM), "on_top", definition, blockBuilder);
        addConnection(SideBitFlag.getSides(Side.TOP, Side.BOTTOM), "in_a_line", definition, blockBuilder);

        this.setBlockUri(blockUri);
        this.setCategory(definition.getCategories());
    }

    private void addConnection(Byte bitFlag, String section, BlockFamilyDefinition definition, BlockBuilderHelper blockBuilder) {
        blocks.put(bitFlag, addBlock(definition, blockBuilder, section, blockUri, bitFlag));
    }

    private Block getProperBlock(WorldProvider worldProvider, Vector3i location) {
        byte connections = 0;
        for (Side connectSide : new Side[] {Side.TOP, Side.BOTTOM}) {
            if (this.connectionCondition(location, connectSide)) {
                connections += SideBitFlag.getSide(connectSide);
            }
        }
        return blocks.get(connections);
    }

    private Block addBlock(BlockFamilyDefinition definition, BlockBuilderHelper blockBuilder, String section, BlockUri blockUri, byte sides) {
        Block newBlock = blockBuilder.constructSimpleBlock(definition, section,new BlockUri(blockUri, new Name(String.valueOf(sides))),this);
        newBlock.setBlockFamily(this);

        return newBlock;
    }

    protected boolean connectionCondition(Vector3i blockLocation, Side connectSide) {
        Vector3i neighborLocation = new Vector3i(blockLocation);
        neighborLocation.add(connectSide.getVector3i());
        if (worldProvider.isBlockRelevant(neighborLocation)) {
            Block neighborBlock = worldProvider.getBlock(neighborLocation);
            final BlockFamily blockFamily = neighborBlock.getBlockFamily();
            if (blockFamily instanceof RomanColumnFamily) {
                return true;
            }
        }
        return false;
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
        return blocks.get((byte) 0);
    }

    @Override
    public Block getBlockFor(BlockUri blockUri) {

        for (Block block : blocks.valueCollection()) {
            if (block.getURI().equals(blockUri)) {
                return block;
            }
        }
        return null;

    }

    @Override
    public Iterable<Block> getBlocks() {
        return blocks.valueCollection();
    }

    @Override
    public Block getBlockForNeighborUpdate(Vector3i location, Block oldBlock) {
        return getProperBlock(worldProvider, location);
    }

}
