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
package org.terasology.sample.blocks;

import gnu.trove.map.TByteObjectMap;
import gnu.trove.map.hash.TByteObjectHashMap;
import org.joml.Vector3ic;
import org.terasology.math.JomlUtil;
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
import org.terasology.world.block.family.BlockPlacementData;
import org.terasology.world.block.family.BlockSections;
import org.terasology.world.block.family.RegisterBlockFamily;
import org.terasology.world.block.family.UpdatesWithNeighboursFamily;
import org.terasology.world.block.loader.BlockFamilyDefinition;
import org.terasology.world.block.shapes.BlockShape;

@RegisterBlockFamily("CubePC")
@BlockSections({"lone", "top", "bottom", "middle"})
public class CubePCFamily extends AbstractBlockFamily implements UpdatesWithNeighboursFamily {

    @In
    WorldProvider worldProvider;

    BlockUri blockUri;

    private TByteObjectMap<Block> blocks;

    public CubePCFamily(BlockFamilyDefinition definition, BlockShape shape, BlockBuilderHelper blockBuilder) {
        super(definition, shape, blockBuilder);
    }

    public CubePCFamily(BlockFamilyDefinition definition, BlockBuilderHelper blockBuilder) {
        super(definition, blockBuilder);

        blocks = new TByteObjectHashMap<Block>();

        blockUri = new BlockUri(definition.getUrn());

        addConnection((byte) 0, "lone", definition, blockBuilder);
        addConnection(SideBitFlag.getSide(Side.TOP), "top", definition, blockBuilder);
        addConnection(SideBitFlag.getSide(Side.BOTTOM), "bottom", definition, blockBuilder);
        addConnection(SideBitFlag.getSides(Side.TOP, Side.BOTTOM), "middle", definition, blockBuilder);

        this.setBlockUri(blockUri);
        this.setCategory(definition.getCategories());
    }

    private void addConnection(Byte bitFlag, String section, BlockFamilyDefinition definition, BlockBuilderHelper blockBuilder) {
        blocks.put(bitFlag, addBlock(definition, blockBuilder, section, blockUri, bitFlag));
    }

    private Block getProperBlock(WorldProvider worldProviderArg, Vector3i location) {
        byte connections = 0;
        for (Side connectSide : new Side[] {Side.TOP, Side.BOTTOM}) {
            if (this.connectionCondition(location, connectSide)) {
                connections += SideBitFlag.getSide(connectSide);
            }
        }
        return blocks.get(connections);
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
        Block newBlock = blockBuilder.constructSimpleBlock(definition, section, new BlockUri(blockUriArg, new Name(String.valueOf(sides))), this);
        newBlock.setBlockFamily(this);

        return newBlock;
    }

    protected boolean connectionCondition(Vector3ic blockLocation, Side connectSide) {
        org.joml.Vector3i neighborLocation = new org.joml.Vector3i(blockLocation);
        neighborLocation.add(connectSide.direction());
        if (worldProvider.isBlockRelevant(neighborLocation)) {
            Block neighborBlock = worldProvider.getBlock(neighborLocation);
            final BlockFamily blockFamily = neighborBlock.getBlockFamily();
            return blockFamily instanceof CubePCFamily;
        }
        return false;
    }


    protected boolean connectionCondition(Vector3i blockLocation, Side connectSide) {
        Vector3i neighborLocation = new Vector3i(blockLocation);
        neighborLocation.add(connectSide.getVector3i());
        if (worldProvider.isBlockRelevant(neighborLocation)) {
            Block neighborBlock = worldProvider.getBlock(neighborLocation);
            final BlockFamily blockFamily = neighborBlock.getBlockFamily();
            if (blockFamily instanceof CubePCFamily) {
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
        return "Cube PC";
    }

    @Override
    public Block getBlockForPlacement(BlockPlacementData data) {
        return getProperBlock(worldProvider, JomlUtil.from(data.blockPosition));
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
