/*
 * Copyright 2015 MovingBlocks
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

import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.math.Rotation;
import org.terasology.math.Side;
import org.terasology.math.SideBitFlag;
import org.terasology.math.geom.Vector3i;
import org.terasology.registry.In;
import org.terasology.world.WorldProvider;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockBuilderHelper;
import org.terasology.world.block.BlockUri;
import org.terasology.world.block.family.BlockSections;
import org.terasology.world.block.family.MultiConnectFamily;
import org.terasology.world.block.family.RegisterBlockFamily;
import org.terasology.world.block.loader.BlockFamilyDefinition;
import org.terasology.world.block.shapes.BlockShape;

/**
 * @author Marcin Sciesinski <marcins78@gmail.com>
 */
@RegisterBlockFamily("NetworkCable")
@BlockSections({"no_connections", "one_connection", "line_connection", "2d_corner", "3d_corner", "2d_t", "cross", "3d_side", "five_connections", "all"})
public class NetworkCableBlockFamily extends MultiConnectFamily {
    @In
    WorldProvider worldProvider;

    public static final String NO_CONNECTIONS = "no_connections";
    public static final String ONE_CONNECTION = "one_connection";
    public static final String TWO_CONNECTIONS_LINE = "line_connection";
    public static final String TWO_CONNECTIONS_CORNER = "2d_corner";
    public static final String THREE_CONNECTIONS_CORNER = "3d_corner";
    public static final String THREE_CONNECTIONS_T = "2d_t";
    public static final String FOUR_CONNECTIONS_CROSS = "cross";
    public static final String FOUR_CONNECTIONS_SIDE = "3d_side";
    public static final String FIVE_CONNECTIONS = "five_connections";
    public static final String SIX_CONNECTIONS = "all";

    public NetworkCableBlockFamily(BlockFamilyDefinition definition, BlockShape shape, BlockBuilderHelper blockBuilder) {
        super(definition, shape, blockBuilder);
    }

    public NetworkCableBlockFamily(BlockFamilyDefinition definition, BlockBuilderHelper blockBuilder) {
        super(definition, blockBuilder);

        BlockUri blockUri = new BlockUri(definition.getUrn());

        this.registerBlock(blockUri,definition,blockBuilder,NO_CONNECTIONS, (byte)0, Rotation.allValues());
        this.registerBlock(blockUri,definition,blockBuilder,ONE_CONNECTION, SideBitFlag.getSides(Side.BACK), Rotation.allValues());
        this.registerBlock(blockUri,definition,blockBuilder,TWO_CONNECTIONS_LINE, SideBitFlag.getSides(Side.BACK, Side.FRONT), Rotation.allValues());
        this.registerBlock(blockUri,definition,blockBuilder,TWO_CONNECTIONS_CORNER, SideBitFlag.getSides(Side.LEFT, Side.BACK), Rotation.allValues());
        this.registerBlock(blockUri,definition,blockBuilder,THREE_CONNECTIONS_CORNER, SideBitFlag.getSides(Side.LEFT, Side.BACK, Side.TOP), Rotation.allValues());
        this.registerBlock(blockUri,definition,blockBuilder,THREE_CONNECTIONS_T,  SideBitFlag.getSides(Side.LEFT, Side.BACK, Side.FRONT), Rotation.allValues());
        this.registerBlock(blockUri,definition,blockBuilder,FOUR_CONNECTIONS_CROSS,SideBitFlag.getSides(Side.RIGHT, Side.LEFT, Side.BACK, Side.FRONT), Rotation.allValues());
        this.registerBlock(blockUri,definition,blockBuilder,FOUR_CONNECTIONS_SIDE,  SideBitFlag.getSides(Side.LEFT, Side.BACK, Side.FRONT, Side.TOP), Rotation.allValues());
        this.registerBlock(blockUri,definition,blockBuilder,FIVE_CONNECTIONS, SideBitFlag.getSides(Side.LEFT, Side.BACK, Side.FRONT, Side.TOP, Side.BOTTOM), Rotation.allValues());
        this.registerBlock(blockUri,definition,blockBuilder,SIX_CONNECTIONS, SideBitFlag.getSides(Side.LEFT, Side.BACK, Side.FRONT, Side.TOP, Side.BOTTOM,Side.RIGHT), Rotation.allValues());
    }


    @Override
    public byte getConnectionSides() {
        return SideBitFlag.getSides(Side.LEFT, Side.BACK, Side.FRONT, Side.TOP, Side.BOTTOM,Side.RIGHT);
    }

    @Override
    public Block getArchetypeBlock() {
        return blocks.get(SideBitFlag.getSides(Side.RIGHT,Side.LEFT));
    }

    @Override
    public boolean connectionCondition(Vector3i blockLocation, Side connectSide) {
        Vector3i neighborLocation = new Vector3i(blockLocation);
        neighborLocation.add(connectSide.getVector3i());

        EntityRef neighborEntity = blockEntityRegistry.getBlockEntityAt(neighborLocation);
        return neighborEntity != null && worldProvider.getBlock(neighborLocation).getBlockFamily() instanceof  NetworkCableBlockFamily;
    }


}