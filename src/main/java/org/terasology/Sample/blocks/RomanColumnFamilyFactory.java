package org.terasology.Sample.blocks;

import com.google.common.collect.ImmutableSet;
import gnu.trove.map.TByteObjectMap;
import gnu.trove.map.hash.TByteObjectHashMap;
import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.entitySystem.event.ReceiveEvent;
import org.terasology.entitySystem.systems.BaseComponentSystem;
import org.terasology.entitySystem.systems.RegisterMode;
import org.terasology.entitySystem.systems.RegisterSystem;
import org.terasology.math.Side;
import org.terasology.math.SideBitFlag;
import org.terasology.math.geom.Vector3i;
import org.terasology.naming.Name;
import org.terasology.registry.In;
import org.terasology.world.BlockEntityRegistry;
import org.terasology.world.WorldProvider;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockBuilderHelper;
import org.terasology.world.block.BlockComponent;
import org.terasology.world.block.BlockUri;
import org.terasology.world.block.family.BlockFamily;
import org.terasology.world.block.family.BlockFamilyFactory;
import org.terasology.world.block.family.RegisterBlockFamilyFactory;
import org.terasology.world.block.items.OnBlockItemPlaced;
import org.terasology.world.block.loader.BlockFamilyDefinition;

import java.util.Set;

@RegisterBlockFamilyFactory("romancolumn")
@RegisterSystem(RegisterMode.AUTHORITY)
public class RomanColumnFamilyFactory extends BaseComponentSystem implements BlockFamilyFactory {

    @In
    private WorldProvider worldProvider;

    @In
    private BlockEntityRegistry blockEntityRegistry;

    private static final ImmutableSet<String> BLOCK_NAMES = ImmutableSet.of(
            "lone_block",
            "on_bottom",
            "on_top",
            "in_a_line");

    public RomanColumnFamilyFactory() {
    }

    @Override
    public BlockFamily createBlockFamily(BlockFamilyDefinition definition, BlockBuilderHelper blockBuilder) {
        TByteObjectMap<Block> blocksForConnections = new TByteObjectHashMap<>();

        BlockUri blockUri = new BlockUri(definition.getUrn());

        // I'm lazy so I'll just hard code all the possible combinations
        blocksForConnections.put((byte) 0, makeABlock(definition, blockBuilder, "no_connections", blockUri, (byte) 0));
        blocksForConnections.put(SideBitFlag.getSide(Side.TOP), makeABlock(definition, blockBuilder, "on_bottom", blockUri, SideBitFlag.getSide(Side.TOP)));
        blocksForConnections.put(SideBitFlag.getSide(Side.BOTTOM), makeABlock(definition, blockBuilder, "on_top", blockUri, SideBitFlag.getSide(Side.BOTTOM)));
        blocksForConnections.put(SideBitFlag.getSides(Side.BOTTOM, Side.TOP),
                makeABlock(definition, blockBuilder, "in_a_line", blockUri, SideBitFlag.getSides(Side.TOP, Side.BOTTOM)));

        final Block archetypeBlock = blocksForConnections.get(SideBitFlag.getSides(Side.BOTTOM, Side.TOP));
        return new RomanColumnFamily(blockUri, definition.getCategories(),
                archetypeBlock, blocksForConnections);
    }

    @ReceiveEvent()
    public void onPlaceBlock(OnBlockItemPlaced event, EntityRef entity) {
        BlockComponent blockComponent = event.getPlacedBlock().getComponent(BlockComponent.class);
        if (blockComponent == null) {
            return;
        }

        Vector3i targetBlock = blockComponent.getPosition();
        processUpdateForBlockLocation(targetBlock);
    }

    private void processUpdateForBlockLocation(Vector3i blockLocation) {
        for (Side side : Side.values()) {
            Vector3i neighborLocation = new Vector3i(blockLocation);
            neighborLocation.add(side.getVector3i());
            if (worldProvider.isBlockRelevant(neighborLocation)) {
                Block neighborBlock = worldProvider.getBlock(neighborLocation);
                final BlockFamily blockFamily = neighborBlock.getBlockFamily();
                if (blockFamily instanceof RomanColumnFamily) {
                    RomanColumnFamily neighboursFamily = (RomanColumnFamily) blockFamily;
                    Block neighborBlockAfterUpdate = neighboursFamily.getBlockForNeighborUpdate(worldProvider, blockEntityRegistry, neighborLocation, neighborBlock);
                    if (neighborBlock != neighborBlockAfterUpdate) {
                        worldProvider.setBlock(neighborLocation, neighborBlockAfterUpdate);
                    }
                }
            }
        }
    }

    private Block makeABlock(BlockFamilyDefinition definition, BlockBuilderHelper blockBuilder, String section, BlockUri blockUri, byte sides) {
        Block newBlock = blockBuilder.constructSimpleBlock(definition, section);
        newBlock.setUri(new BlockUri(blockUri, new Name(String.valueOf(sides))));

        return newBlock;
    }

    @Override
    public Set<String> getSectionNames() {
        return BLOCK_NAMES;
    }

}
