/*
 * Copyright 2019 MovingBlocks
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

import org.joml.Vector3ic;
import org.terasology.math.Rotation;
import org.terasology.math.Side;
import org.terasology.math.SideBitFlag;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockBuilderHelper;
import org.terasology.world.block.BlockUri;
import org.terasology.world.block.family.BlockFamily;
import org.terasology.world.block.family.BlockSections;
import org.terasology.world.block.family.MultiConnectFamily;
import org.terasology.world.block.family.RegisterBlockFamily;
import org.terasology.world.block.loader.BlockFamilyDefinition;

@RegisterBlockFamily("ironplate")
@BlockSections({"single", "one_connection", "line", "corner", "t", "cross"})
public class IronPlateFamily extends MultiConnectFamily {

    public IronPlateFamily(BlockFamilyDefinition definition, BlockBuilderHelper blockBuilder) {
        super(definition, blockBuilder);

        BlockUri blockUri = new BlockUri(definition.getUrn());

        this.registerBlock(blockUri, definition, blockBuilder, "single", (byte) 0, Rotation.horizontalRotations());
        this.registerBlock(blockUri, definition, blockBuilder, "one_connection", SideBitFlag.getSide(Side.RIGHT), Rotation.horizontalRotations());
        this.registerBlock(blockUri, definition, blockBuilder, "line", SideBitFlag.getSides(Side.FRONT, Side.BACK), Rotation.horizontalRotations());
        this.registerBlock(blockUri, definition, blockBuilder, "corner", SideBitFlag.getSides(Side.LEFT, Side.BACK), Rotation.horizontalRotations());
        this.registerBlock(blockUri, definition, blockBuilder, "t", SideBitFlag.getSides(Side.LEFT, Side.BACK, Side.RIGHT), Rotation.horizontalRotations());
        this.registerBlock(blockUri, definition, blockBuilder, "cross", SideBitFlag.getSides(Side.LEFT, Side.FRONT, Side.RIGHT, Side.BACK), Rotation.horizontalRotations());
    }

    @Override
    protected boolean connectionCondition(Vector3ic blockLocation, Side connectSide) {
        org.joml.Vector3i neighborLocation = new org.joml.Vector3i(blockLocation);
        neighborLocation.add(connectSide.direction());
        if (worldProvider.isBlockRelevant(neighborLocation)) {
            Block neighborBlock = worldProvider.getBlock(neighborLocation);
            final BlockFamily blockFamily = neighborBlock.getBlockFamily();
            return blockFamily instanceof IronPlateFamily;
        }
        return false;
    }

    @Override
    public byte getConnectionSides() {
        return SideBitFlag.getSides(Side.FRONT, Side.BACK, Side.LEFT, Side.RIGHT);
    }

    @Override
    public Block getArchetypeBlock() {
        return blocks.get((byte) 0);
    }

}
