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

    private TByteObjectMap<Block> blocks;

    BlockUri blockUri;

    public BlackAndWhiteFamily(BlockFamilyDefinition definition, BlockShape shape, BlockBuilderHelper blockBuilder) {
        super(definition, shape, blockBuilder);
    }

    public BlackAndWhiteFamily(BlockFamilyDefinition definition, BlockBuilderHelper blockBuilder) {
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
        Block newBlock = blockBuilder.constructSimpleBlock(definition, section);
        newBlock.setUri(new BlockUri(blockUri, new Name(String.valueOf(sides))));
        newBlock.setBlockFamily(this);

        return newBlock;
    }

    protected boolean connectionCondition(Vector3i blockLocation, Side connectSide) {
        Vector3i neighborLocation = new Vector3i(blockLocation);
        neighborLocation.add(connectSide.getVector3i());
        if (worldProvider.isBlockRelevant(neighborLocation)) {
            Block neighborBlock = worldProvider.getBlock(neighborLocation);
            final BlockFamily blockFamily = neighborBlock.getBlockFamily();
            if (blockFamily instanceof BlackAndWhiteFamily) {
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
        return "Black and White";
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