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

@RegisterBlockFamilyFactory("ironplate")
@RegisterSystem(RegisterMode.AUTHORITY)
public class IronPlateFamilyFactory extends BaseComponentSystem implements BlockFamilyFactory {

    @In
    private WorldProvider worldProvider;

    @In
    private BlockEntityRegistry blockEntityRegistry;

    private static final ImmutableSet<String> BLOCK_NAMES = ImmutableSet.of(
            "single",
            
            "left",
            "right",
            "front",
            "back",
            
            "left_right",
            "front_back",
            
            "front_left",
            "front_right",
            "back_left",
            "back_right",
            
            "left_t",
            "right_t",
            "front_t",
            "back_t",
            
            "cross");

    public IronPlateFamilyFactory() {
    }

    @Override
    public BlockFamily createBlockFamily(BlockFamilyDefinition definition, BlockBuilderHelper blockBuilder) {
        TByteObjectMap<Block> blocksForConnections = new TByteObjectHashMap<>();

        BlockUri blockUri = new BlockUri(definition.getUrn());
        
        Side left = Side.LEFT;
        Side right = Side.RIGHT;
        Side front = Side.FRONT;
        Side back = Side.BACK;

        blocksForConnections.put((byte) 0, makeABlock(definition, blockBuilder, "single", blockUri, (byte) 0));
        blocksForConnections.put(SideBitFlag.getSides(left), makeABlock(definition, blockBuilder, "left", blockUri, SideBitFlag.getSides(left)));
        blocksForConnections.put(SideBitFlag.getSides(right), makeABlock(definition, blockBuilder, "right", blockUri, SideBitFlag.getSides(right)));
        blocksForConnections.put(SideBitFlag.getSides(front), makeABlock(definition, blockBuilder, "front", blockUri, SideBitFlag.getSides(front)));
        blocksForConnections.put(SideBitFlag.getSides(back), makeABlock(definition, blockBuilder, "back", blockUri, SideBitFlag.getSides(back)));
        blocksForConnections.put(SideBitFlag.getSides(left, right), makeABlock(definition, blockBuilder, "left_right", blockUri, SideBitFlag.getSides(left, right)));
        blocksForConnections.put(SideBitFlag.getSides(front, back), makeABlock(definition, blockBuilder, "front_back", blockUri, SideBitFlag.getSides(front, back)));
        blocksForConnections.put(SideBitFlag.getSides(left, front), makeABlock(definition, blockBuilder, "front_left", blockUri, SideBitFlag.getSides(left, front)));
        blocksForConnections.put(SideBitFlag.getSides(right, front), makeABlock(definition, blockBuilder, "front_right", blockUri, SideBitFlag.getSides(right, front)));
        blocksForConnections.put(SideBitFlag.getSides(left, back), makeABlock(definition, blockBuilder, "back_left", blockUri, SideBitFlag.getSides(left, back)));
        blocksForConnections.put(SideBitFlag.getSides(right, back), makeABlock(definition, blockBuilder, "back_right", blockUri, SideBitFlag.getSides(right, back)));
        blocksForConnections.put(SideBitFlag.getSides(right, front, back), makeABlock(definition, blockBuilder, "left_t", blockUri, SideBitFlag.getSides(right, front, back)));
        blocksForConnections.put(SideBitFlag.getSides(left, front, back), makeABlock(definition, blockBuilder, "right_t", blockUri, SideBitFlag.getSides(left, front, back)));
        blocksForConnections.put(SideBitFlag.getSides(left, right, back), makeABlock(definition, blockBuilder, "front_t", blockUri, SideBitFlag.getSides(left, right, back)));
        blocksForConnections.put(SideBitFlag.getSides(left, right, front), makeABlock(definition, blockBuilder, "back_t", blockUri, SideBitFlag.getSides(left, right, front)));
        blocksForConnections.put(SideBitFlag.getSides(left, right, front, back), makeABlock(definition, blockBuilder, "cross", blockUri, SideBitFlag.getSides(left, right, front, back)));

        final Block archetypeBlock = blocksForConnections.get((byte) 0);
        return new IronPlateFamily(blockUri, definition.getCategories(),
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
                if (blockFamily instanceof IronPlateFamily) {
                    IronPlateFamily neighboursFamily = (IronPlateFamily) blockFamily;
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
