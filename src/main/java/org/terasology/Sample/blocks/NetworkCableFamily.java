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

import org.terasology.math.Rotation;
import org.terasology.math.Side;
import org.terasology.math.SideBitFlag;
import org.terasology.math.geom.Vector3i;
import org.terasology.naming.Name;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockBuilderHelper;
import org.terasology.world.block.BlockUri;
import org.terasology.world.block.family.BlockSections;
import org.terasology.world.block.family.MultiConnectFamily;
import org.terasology.world.block.family.RegisterBlockFamily;
import org.terasology.world.block.family.UpdatesWithNeighboursFamily;
import org.terasology.world.block.loader.BlockFamilyDefinition;
import org.terasology.world.block.shapes.BlockShape;

@RegisterBlockFamily("sample:NetworkCable")
@BlockSections({"one_connection", "line_connection", "2d_corner","3d_corner","2d_t","cross","3d_side","five_connections"})
public class NetworkCableFamily extends MultiConnectFamily implements UpdatesWithNeighboursFamily {

    public NetworkCableFamily(BlockFamilyDefinition definition, BlockShape shape, BlockBuilderHelper builderHelper) {
        super(definition, shape, builderHelper);
    }

    public NetworkCableFamily(BlockFamilyDefinition definition, BlockBuilderHelper builderHelper) {
        super(definition, builderHelper);

        BlockUri blockUri = new BlockUri(definition.getUrn());

        Block block = builderHelper.constructSimpleBlock(definition);
        block.setBlockFamily(this);
        block.setUri(new BlockUri(blockUri,new Name(String.valueOf(0))));
        this.blocks.put((byte) 0,block);

//        this.registerBlock(blockUri, definition, builderHelper, "one_connection", (byte) 0, Rotation.allValues());
        this.registerBlock(blockUri, definition, builderHelper, "one_connection", SideBitFlag.getSides(Side.BACK), Rotation.allValues());
        this.registerBlock(blockUri, definition, builderHelper, "line_connection",SideBitFlag.getSides(Side.BACK, Side.FRONT), Rotation.allValues());
        this.registerBlock(blockUri, definition, builderHelper, "2d_corner",  SideBitFlag.getSides(Side.LEFT, Side.BACK), Rotation.allValues());
        this.registerBlock(blockUri, definition, builderHelper, "3d_corner", SideBitFlag.getSides(Side.LEFT, Side.BACK, Side.TOP), Rotation.allValues());
        this.registerBlock(blockUri, definition, builderHelper, "2d_t", SideBitFlag.getSides(Side.LEFT, Side.BACK, Side.FRONT), Rotation.allValues());
        this.registerBlock(blockUri, definition, builderHelper, "cross", SideBitFlag.getSides(Side.RIGHT, Side.LEFT, Side.BACK, Side.FRONT), Rotation.allValues());
        this.registerBlock(blockUri, definition, builderHelper, "3d_side", SideBitFlag.getSides(Side.LEFT, Side.BACK, Side.FRONT, Side.TOP), Rotation.allValues());
        this.registerBlock(blockUri, definition, builderHelper, "five_connections", SideBitFlag.getSides(Side.LEFT, Side.BACK, Side.FRONT, Side.TOP, Side.BOTTOM), Rotation.allValues());

    }

    @Override
    public byte getConnectionSides() {
        return 63;
    }

    @Override
    public Block getArchetypeBlock() {
        return blocks.get((byte)0);
    }

    @Override
    protected boolean connectionCondition(Vector3i location, Side side) {
        Vector3i target = side.getAdjacentPos(location);
        return worldProvider.isBlockRelevant(target) && worldProvider.getBlock(target).getBlockFamily() instanceof NetworkCableFamily;
    }
}
