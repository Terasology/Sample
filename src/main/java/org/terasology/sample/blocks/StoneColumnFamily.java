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

import gnu.trove.map.hash.TByteObjectHashMap;
import org.joml.Vector3ic;
import org.terasology.math.Rotation;
import org.terasology.math.Side;
import org.terasology.math.SideBitFlag;
import org.terasology.math.geom.Vector3i;
import org.terasology.world.block.Block;
import org.terasology.world.block.BlockBuilderHelper;
import org.terasology.world.block.BlockUri;
import org.terasology.world.block.family.BlockSections;
import org.terasology.world.block.family.MultiConnectFamily;
import org.terasology.world.block.family.RegisterBlockFamily;
import org.terasology.world.block.loader.BlockFamilyDefinition;
import org.terasology.world.block.shapes.BlockShape;

import java.util.Collections;

import static org.terasology.math.SideBitFlag.getSides;

@RegisterBlockFamily("sample:StoneColumn")
@BlockSections({"bottom", "top", "middle"})

public class StoneColumnFamily extends MultiConnectFamily {
    public StoneColumnFamily(BlockFamilyDefinition definition, BlockShape shape, BlockBuilderHelper blockBuilder) {
        super(definition, shape, blockBuilder);
    }

    public StoneColumnFamily(BlockFamilyDefinition definition, BlockBuilderHelper blockBuilder) {
        super(definition, blockBuilder);
        BlockUri blockUri;
        blocks = new TByteObjectHashMap<Block>();
        blockUri = new BlockUri(definition.getUrn());
        registerBlock(blockUri, definition, blockBuilder, "standby", (byte) 0, Collections.singleton(Rotation.none()));
        registerBlock(blockUri, definition, blockBuilder, "bottom", SideBitFlag.getSide(Side.TOP), Collections.singleton(Rotation.none()));
        registerBlock(blockUri, definition, blockBuilder, "top", SideBitFlag.getSide(Side.BOTTOM), Collections.singleton(Rotation.none()));
        registerBlock(blockUri, definition, blockBuilder, "middle", getSides(Side.TOP, Side.BOTTOM), Collections.singleton(Rotation.none()));
        this.setCategory(definition.getCategories());
    }

    @Override
    protected boolean connectionCondition(Vector3i blockLocation, Side connectSide) {
        Vector3i target = connectSide.getAdjacentPos(blockLocation);
        return worldProvider.isBlockRelevant(target) && worldProvider.getBlock(target).getBlockFamily() instanceof StoneColumnFamily;
    }

    @Override
    protected boolean connectionCondition(Vector3ic blockLocation, Side connectSide) {
        org.joml.Vector3i target = connectSide.getAdjacentPos(blockLocation, new org.joml.Vector3i());
        return worldProvider.isBlockRelevant(target) && worldProvider.getBlock(target).getBlockFamily() instanceof StoneColumnFamily;
    }

    @Override
    public byte getConnectionSides() {
        return getSides(Side.TOP, Side.BOTTOM);
    }

    @Override
    public Block getArchetypeBlock() {
        return blocks.get(getSides(Side.TOP, Side.BOTTOM));
    }
}

