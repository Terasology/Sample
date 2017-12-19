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

import org.terasology.math.Side;
import org.terasology.math.geom.Vector3i;
import org.terasology.world.block.BlockBuilderHelper;
import org.terasology.world.block.family.MultiConnectFamily;
import org.terasology.world.block.family.RegisterBlockFamily;
import org.terasology.world.block.loader.BlockFamilyDefinition;
import org.terasology.world.block.shapes.BlockShape;

@RegisterBlockFamily("sample:NetworkCable")
public class NetworkCableFamily extends MultiConnectFamily {
    public NetworkCableFamily(BlockFamilyDefinition definition, BlockShape shape, BlockBuilderHelper builderHelper) {
        super(definition, shape, builderHelper);
    }

    public NetworkCableFamily(BlockFamilyDefinition definition, BlockBuilderHelper builderHelper) {
        super(definition, builderHelper);
    }

    @Override
    public byte getConnectionSides() {
        return 63;
    }

    @Override
    protected boolean connectionCondition(Vector3i location, Side side) {
        Vector3i target = side.getAdjacentPos(location);
        return worldProvider.isBlockRelevant(target) && worldProvider.getBlock(target).getBlockFamily() instanceof NetworkCableFamily;
    }
}
