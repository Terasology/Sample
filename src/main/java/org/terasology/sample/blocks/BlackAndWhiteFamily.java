// Copyright 2021 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.sample.blocks;

import gnu.trove.map.TByteObjectMap;
import gnu.trove.map.hash.TByteObjectHashMap;
import org.joml.Vector3ic;
import org.terasology.engine.math.Side;
import org.terasology.engine.math.SideBitFlag;
import org.terasology.naming.Name;
import org.terasology.engine.registry.In;
import org.terasology.engine.world.WorldProvider;
import org.terasology.engine.world.block.Block;
import org.terasology.engine.world.block.BlockBuilderHelper;
import org.terasology.engine.world.block.BlockUri;
import org.terasology.engine.world.block.family.AbstractBlockFamily;
import org.terasology.engine.world.block.family.BlockFamily;
import org.terasology.engine.world.block.family.BlockPlacementData;
import org.terasology.engine.world.block.family.BlockSections;
import org.terasology.engine.world.block.family.RegisterBlockFamily;
import org.terasology.engine.world.block.family.UpdatesWithNeighboursFamily;
import org.terasology.engine.world.block.loader.BlockFamilyDefinition;
import org.terasology.engine.world.block.shapes.BlockShape;

import java.util.ArrayList;

@RegisterBlockFamily("Sample:BlackAndWhite")
@BlockSections({"lone_block", "on_top", "on_bottom", "in_a_line"})

public class BlackAndWhiteFamily extends AbstractBlockFamily implements UpdatesWithNeighboursFamily {

    @In
    WorldProvider worldProvider;

    Iterable<String> categories = new ArrayList<String>() {

        private static final long serialVersionUID = 1L;

        {
            add("Black");
            add("White");
            add("BlackAndWhite");
        }
    };

    BlockUri blockUri;

    private TByteObjectMap<Block> blocks;

    public BlackAndWhiteFamily(BlockFamilyDefinition definition, BlockShape shape, BlockBuilderHelper blockBuilder) {
        super(definition, shape, blockBuilder);
    }

    public BlackAndWhiteFamily(BlockFamilyDefinition definition, BlockBuilderHelper blockBuilder) {
        super(definition, blockBuilder);

        blocks = new TByteObjectHashMap<>();

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

    private Block getProperBlock(WorldProvider worldProviderArg, Vector3ic location) {
        byte connections = 0;
        for (Side connectSide : new Side[] {Side.TOP, Side.BOTTOM}) {
            if (this.connectionCondition(location, connectSide)) {
                connections += SideBitFlag.getSide(connectSide);
            }
        }
        return blocks.get(connections);
    }

    private Block addBlock(BlockFamilyDefinition definition, BlockBuilderHelper blockBuilder, String section, BlockUri blockUriArg, byte sides) {
        return blockBuilder.constructSimpleBlock(definition, section, new BlockUri(blockUriArg, new Name(String.valueOf(sides))), this);
    }

    protected boolean connectionCondition(Vector3ic blockLocation, Side connectSide) {
        org.joml.Vector3i neighborLocation = new org.joml.Vector3i(blockLocation);
        neighborLocation.add(connectSide.direction());
        if (worldProvider.isBlockRelevant(neighborLocation)) {
            Block neighborBlock = worldProvider.getBlock(neighborLocation);
            final BlockFamily blockFamily = neighborBlock.getBlockFamily();
            return blockFamily instanceof BlackAndWhiteFamily;
        }
        return false;
    }

    @Override
    public BlockUri getURI() {
        return blockUri;
    }

    @Override
    public String getDisplayName() {
        return "Black and White";
    }

    @Override
    public Block getBlockForPlacement(BlockPlacementData data) {
        return getProperBlock(worldProvider, data.blockPosition);
    }

    @Override
    public Block getArchetypeBlock() {
        return blocks.get((byte) 0);
    }

    @Override
    public Block getBlockFor(BlockUri blockUriArg) {
        for (Block block : blocks.valueCollection()) {
            if (block.getURI().equals(blockUriArg)) {
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
    public Block getBlockForNeighborUpdate(Vector3ic location, Block oldBlock) {
        return getProperBlock(worldProvider, location);
    }

}

