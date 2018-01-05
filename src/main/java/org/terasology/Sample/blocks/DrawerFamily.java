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

import com.google.common.collect.Sets;
import gnu.trove.iterator.TByteObjectIterator;
import gnu.trove.map.TByteObjectMap;
import gnu.trove.map.hash.TByteObjectHashMap;
import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.math.Rotation;
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
import org.terasology.world.block.family.AbstractBlockFamily;
import org.terasology.world.block.family.BlockSections;
import org.terasology.world.block.family.RegisterBlockFamily;
import org.terasology.world.block.family.UpdatesWithNeighboursFamily;
import org.terasology.world.block.loader.BlockFamilyDefinition;

import java.util.*;
import java.util.stream.Collectors;

@RegisterBlockFamily("Sample:Drawer")
@BlockSections({"no_connections", "one_connection", "line_connection", "2d_corner", "2d_t", "cross"})
public class DrawerFamily extends AbstractBlockFamily implements UpdatesWithNeighboursFamily {

    private static final String NO_CONNECTIONS = "no_connections";
    private static final String ONE_CONNECTION = "one_connection";
    private static final String TWO_CONNECTIONS_LINE = "line_connection";
    private static final String TWO_CONNECTIONS_CORNER = "2d_corner";
    private static final String THREE_CONNECTIONS_T = "2d_t";
    private static final String FOUR_CONNECTIONS_CROSS = "cross";

    private static final Map<String, Byte> DRAWER_MAPPING = new HashMap<String, Byte>() {
        private static final long serialVersionUID = 1L;
        {
            put(NO_CONNECTIONS, (byte) 0);
            put(ONE_CONNECTION, SideBitFlag.getSides(Side.RIGHT));
            put(TWO_CONNECTIONS_LINE, SideBitFlag.getSides(Side.LEFT, Side.RIGHT));
            put(TWO_CONNECTIONS_CORNER, SideBitFlag.getSides(Side.LEFT, Side.FRONT));
            put(THREE_CONNECTIONS_T, SideBitFlag.getSides(Side.LEFT, Side.RIGHT, Side.FRONT));
            put(FOUR_CONNECTIONS_CROSS, SideBitFlag.getSides(Side.RIGHT, Side.LEFT, Side.BACK, Side.FRONT));
        }
    };

    @In
    WorldProvider worldProvider;

    @In
    BlockEntityRegistry blockEntityRegistry;

    private TByteObjectMap<Block> blocks;
    private BlockUri blockUri;
    private byte connectionSides;

    public DrawerFamily(BlockFamilyDefinition definition, BlockBuilderHelper blockBuilder) {
        super(definition, blockBuilder);

        @SuppressWarnings("unchecked")TByteObjectMap<String>[] basicBlocks = new TByteObjectMap[7];

        blocks = new TByteObjectHashMap<>();
        blockUri = new BlockUri(definition.getUrn());

        addConnections(basicBlocks, 0, NO_CONNECTIONS);
        addConnections(basicBlocks, 1, ONE_CONNECTION);
        addConnections(basicBlocks, 2, TWO_CONNECTIONS_LINE);
        addConnections(basicBlocks, 2, TWO_CONNECTIONS_CORNER);
        addConnections(basicBlocks, 3, THREE_CONNECTIONS_T);
        addConnections(basicBlocks, 4, FOUR_CONNECTIONS_CROSS);

        connectionSides = 0b110110;
        // Now make sure we have all combinations based on the basic set (above) and rotations
        for (byte connections = 0; connections < 64; connections++) {
            // Only the allowed connections should be created
            if ((connections & connectionSides) == connections) {
                Block block = constructBlockForConnections(connections, blockBuilder, definition, basicBlocks);
                if (block == null) {
                    throw new IllegalStateException("Unable to find correct block definition for connections: " + connections);
                }
                block.setUri(new BlockUri(blockUri, new Name(String.valueOf(connections))));
                block.setBlockFamily(this);
                blocks.put(connections, block);
            }
        }

        this.setBlockUri(blockUri);
        this.setCategory(definition.getCategories());
    }

    private void addConnections(TByteObjectMap<String>[] basicBlocks, int index, String connections) {
        if (basicBlocks[index] == null) {
            basicBlocks[index] = new TByteObjectHashMap<>();
        }
        Byte val = DRAWER_MAPPING.get(connections);
        if (val != null) {
            basicBlocks[index].put(DRAWER_MAPPING.get(connections), connections);
        }
    }

    private Block constructBlockForConnections(final byte connections, final BlockBuilderHelper blockBuilder,
                                               BlockFamilyDefinition definition, TByteObjectMap<String>[] basicBlocks) {
        int connectionCount = SideBitFlag.getSides(connections).size();
        TByteObjectMap<String> possibleBlockDefinitions = basicBlocks[connectionCount];
        final TByteObjectIterator<String> blockDefinitionIterator = possibleBlockDefinitions.iterator();
        while (blockDefinitionIterator.hasNext()) {
            blockDefinitionIterator.advance();
            final byte originalConnections = blockDefinitionIterator.key();
            final String section = blockDefinitionIterator.value();
            Rotation rot = getRotationToAchieve(originalConnections, connections);
            if (rot != null) {
                return blockBuilder.constructTransformedBlock(definition, section, rot);
            }
        }
        return null;
    }

    private Rotation getRotationToAchieve(byte source, byte target) {
        Collection<Side> originalSides = SideBitFlag.getSides(source);

        Iterable<Rotation> rotations = getHorizontalOnly() ? Rotation.horizontalRotations() : Rotation.values();
        for (Rotation rot : rotations) {
            Set<Side> transformedSides = Sets.newHashSet();
            transformedSides.addAll(originalSides.stream().map(rot::rotate).collect(Collectors.toList()));

            byte transformedSide = SideBitFlag.getSides(transformedSides);
            if (transformedSide == target) {
                return rot;
            }
        }
        return null;
    }

    private boolean isConnectingTo(Vector3i blockLocation, Side connectSide, BlockEntityRegistry blockEntityRegistry) {
        Vector3i neighborLocation = new Vector3i(blockLocation);
        neighborLocation.add(connectSide.getVector3i());

        EntityRef neighborEntity = blockEntityRegistry.getEntityAt(neighborLocation);

        return neighborEntity != null && connectsToNeighbor(neighborEntity, connectSide.reverse());
    }

    private boolean connectsToNeighbor(EntityRef neighborEntity, Side side) {
        BlockComponent blockComponent = neighborEntity.getComponent(BlockComponent.class);
        return neighborEntity.hasComponent(ConnectsToDrawerComponent.class)
                || (blockComponent != null && blockComponent.getBlock().isFullSide(side));
    }

    private boolean getHorizontalOnly() {
        return true;
    }

    @Override
    public BlockUri getURI() {
        return blockUri;
    }

    @Override
    public String getDisplayName() {
        return "Drawer";
    }

    @Override
    public Block getBlockForPlacement(Vector3i location, Side attachmentSide, Side direction) {
        byte connections = 0;
        for (Side connectSide : SideBitFlag.getSides(connectionSides)) {
            if (isConnectingTo(location, connectSide, blockEntityRegistry)) {
                connections += SideBitFlag.getSide(connectSide);
            }
        }
        return blocks.get(connections);
    }

    @Override
    public Block getArchetypeBlock() {
        return blocks.get((byte) 0);
    }

    @Override
    public Block getBlockFor(BlockUri blockUri) {

        if (getURI().equals(blockUri.getFamilyUri())) {
            try {
                byte connections = Byte.parseByte(blockUri.getIdentifier().toString().toLowerCase(Locale.ENGLISH));
                return blocks.get(connections);
            } catch (IllegalArgumentException e) {
                return null;
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
        byte connections = 0;
        for (Side connectSide : SideBitFlag.getSides(connectionSides)) {
            if (isConnectingTo(location, connectSide, blockEntityRegistry)) {
                connections += SideBitFlag.getSide(connectSide);
            }
        }
        return blocks.get(connections);
    }
}
