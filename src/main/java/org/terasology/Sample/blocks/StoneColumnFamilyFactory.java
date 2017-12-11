/*
 * Copyright 2017 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.Sample.blocks;

import com.google.common.collect.ImmutableSet;
import gnu.trove.map.TByteObjectMap;
import gnu.trove.map.hash.TByteObjectHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.terasology.world.block.family.UpdatesWithNeighboursFamilyFactory;
import org.terasology.world.block.items.OnBlockItemPlaced;
import org.terasology.world.block.loader.BlockFamilyDefinition;

import java.util.Set;

@RegisterBlockFamilyFactory("Sample:stoneColumn")
@RegisterSystem(RegisterMode.AUTHORITY)
public class StoneColumnFamilyFactory extends BaseComponentSystem implements BlockFamilyFactory {
    public static final String MIDDLE = "middle";
    public static final String BOTTOM = "bottom";
    public static final String TOP = "top";

    private static final ImmutableSet<String> SECTIONS = ImmutableSet.of(MIDDLE, BOTTOM, TOP);

    private static final ImmutableSet<Byte> CONNECTIONS = ImmutableSet.of(
            (byte) 0,
            SideBitFlag.getSides(Side.TOP, Side.BOTTOM),
            SideBitFlag.getSide(Side.BOTTOM),
            SideBitFlag.getSide(Side.TOP)
    );

    private static final TByteObjectMap<String> SECTION_CONNECTIONS =
            new TByteObjectHashMap<String>() {
                {
                    put((byte) 0, MIDDLE);
                    put(SideBitFlag.getSides(Side.TOP, Side.BOTTOM), MIDDLE);
                    put(SideBitFlag.getSide(Side.BOTTOM), TOP);
                    put(SideBitFlag.getSide(Side.TOP), BOTTOM);
                }
            };

    @In
    WorldProvider worldProvider;

    @In
    BlockEntityRegistry blockEntityRegistry;

    @Override
    public BlockFamily createBlockFamily(BlockFamilyDefinition definition, BlockBuilderHelper blockBuilder) {
        TByteObjectMap<Block> blocks = new TByteObjectHashMap<>();
        BlockUri blockUri = new BlockUri(definition.getUrn());

        for (byte connection : CONNECTIONS) {
            blocks.put(connection, getBlock(definition, blockBuilder, SECTION_CONNECTIONS.get(connection),
                    blockUri, connection));
        }

        final Block archetypeBlock = blocks.get(SideBitFlag.getSides(Side.BOTTOM, Side.TOP));

        return new StoneColumnFamily(blockUri, definition.getCategories(),  archetypeBlock, blocks);
    }

    private Block getBlock(BlockFamilyDefinition definition, BlockBuilderHelper blockBuilder, String section, BlockUri blockUri, byte sides) {
        Block newBlock = blockBuilder.constructSimpleBlock(definition, section);

        newBlock.setUri(new BlockUri(blockUri, new Name(section + String.valueOf(sides))));

        return newBlock;
    }

    @Override
    public Set<String> getSectionNames() {
        return SECTIONS;
    }

    @ReceiveEvent()
    public void onPlaceBlock(OnBlockItemPlaced event, EntityRef entity) {
        BlockComponent blockComponent = event.getPlacedBlock().getComponent(BlockComponent.class);

        if (blockComponent != null) {
            return;
        }

        Vector3i targetBlock = blockComponent.getPosition();
        processUpdateForBlockLocation(targetBlock);
    }

    private void processUpdateForBlockLocation(Vector3i blockLocation) {
        for (Side side : Side.values()) {
            Vector3i neighborLocation = new Vector3i(blockLocation);
            neighborLocation.add(side.getVector3i());

            if (!worldProvider.isBlockRelevant(neighborLocation)) {
                continue;
            }

            Block neighborBlock = worldProvider.getBlock(neighborLocation);
            final BlockFamily blockFamily = neighborBlock.getBlockFamily();

            if (!(blockFamily instanceof StoneColumnFamily)) {
                continue;
            }

            StoneColumnFamily neighboursFamily = (StoneColumnFamily) blockFamily;
            Block neighborBlockAfterUpdate = neighboursFamily.getBlockForNeighborUpdate(worldProvider, blockEntityRegistry, neighborLocation, neighborBlock);
            if (neighborBlock != neighborBlockAfterUpdate) {
                worldProvider.setBlock(neighborLocation, neighborBlockAfterUpdate);
            }
        }
    }
}
