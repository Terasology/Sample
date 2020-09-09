// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.sample.blocks;

import com.google.common.collect.ImmutableList;
import org.terasology.engine.math.Pitch;
import org.terasology.engine.math.Roll;
import org.terasology.engine.math.Rotation;
import org.terasology.engine.math.Side;
import org.terasology.engine.math.SideBitFlag;
import org.terasology.engine.math.Yaw;
import org.terasology.engine.world.block.Block;
import org.terasology.engine.world.block.BlockBuilderHelper;
import org.terasology.engine.world.block.BlockUri;
import org.terasology.engine.world.block.family.BlockFamily;
import org.terasology.engine.world.block.family.BlockSections;
import org.terasology.engine.world.block.family.MultiConnectFamily;
import org.terasology.engine.world.block.family.RegisterBlockFamily;
import org.terasology.engine.world.block.family.UpdatesWithNeighboursFamily;
import org.terasology.engine.world.block.loader.BlockFamilyDefinition;
import org.terasology.engine.world.block.shapes.BlockShape;
import org.terasology.math.geom.Vector3i;

@RegisterBlockFamily("Sample:Speaker")
@BlockSections({"left", "middle", "right"})
public class SpeakerFamily extends MultiConnectFamily implements UpdatesWithNeighboursFamily {
    public static final String LEFT = "left";
    public static final String MIDDLE = "middle";
    public static final String RIGHT = "right";

    ImmutableList<Rotation> rotation1 = ImmutableList.of(
            Rotation.rotate(Yaw.NONE, Pitch.NONE, Roll.NONE),
            Rotation.rotate(Yaw.CLOCKWISE_90, Pitch.NONE, Roll.NONE));

    public SpeakerFamily(BlockFamilyDefinition definition, BlockShape shape, BlockBuilderHelper blockBuilder) {
        super(definition, shape, blockBuilder);
    }

    public SpeakerFamily(BlockFamilyDefinition definition, BlockBuilderHelper blockBuilder) {
        super(definition, blockBuilder);

        BlockUri blockUri = new BlockUri(definition.getUrn());

        this.registerBlock(blockUri, definition, blockBuilder, MIDDLE, (byte) 0, Rotation.allValues());
        this.registerBlock(blockUri, definition, blockBuilder, MIDDLE, SideBitFlag.getSides(Side.LEFT, Side.RIGHT),
                Rotation.allValues());
        this.registerBlock(blockUri, definition, blockBuilder, LEFT, SideBitFlag.getSides(Side.RIGHT), rotation1);
        this.registerBlock(blockUri, definition, blockBuilder, RIGHT, SideBitFlag.getSides(Side.LEFT), rotation1);

    }

    @Override
    protected boolean connectionCondition(Vector3i blockLocation, Side connectSide) {
        Vector3i neighborLocation = new Vector3i(blockLocation);
        neighborLocation.add(connectSide.getVector3i());
        if (worldProvider.isBlockRelevant(neighborLocation)) {
            Block neighborBlock = worldProvider.getBlock(neighborLocation);
            final BlockFamily blockFamily = neighborBlock.getBlockFamily();
            return blockFamily instanceof SpeakerFamily;
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
