// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.sample.blocks;

import gnu.trove.map.hash.TByteObjectHashMap;
import org.terasology.engine.math.Rotation;
import org.terasology.engine.math.Side;
import org.terasology.engine.math.SideBitFlag;
import org.terasology.engine.world.block.Block;
import org.terasology.engine.world.block.BlockBuilderHelper;
import org.terasology.engine.world.block.BlockUri;
import org.terasology.engine.world.block.family.BlockSections;
import org.terasology.engine.world.block.family.MultiConnectFamily;
import org.terasology.engine.world.block.family.RegisterBlockFamily;
import org.terasology.engine.world.block.loader.BlockFamilyDefinition;
import org.terasology.engine.world.block.shapes.BlockShape;
import org.terasology.math.geom.Vector3i;

import java.util.Collections;

import static org.terasology.engine.math.SideBitFlag.getSides;

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
        registerBlock(blockUri, definition, blockBuilder, "bottom", SideBitFlag.getSide(Side.TOP),
                Collections.singleton(Rotation.none()));
        registerBlock(blockUri, definition, blockBuilder, "top", SideBitFlag.getSide(Side.BOTTOM),
                Collections.singleton(Rotation.none()));
        registerBlock(blockUri, definition, blockBuilder, "middle", getSides(Side.TOP, Side.BOTTOM),
                Collections.singleton(Rotation.none()));
        this.setCategory(definition.getCategories());
    }

    @Override
    protected boolean connectionCondition(Vector3i blockLocation, Side connectSide) {
        Vector3i target = connectSide.getAdjacentPos(blockLocation);
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

