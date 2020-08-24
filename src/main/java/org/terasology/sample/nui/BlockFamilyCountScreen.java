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
package org.terasology.sample.nui;

import org.terasology.nui.widgets.UILabel;
import org.terasology.registry.In;
import org.terasology.rendering.nui.CoreScreenLayer;
import org.terasology.world.block.BlockManager;

public class BlockFamilyCountScreen extends CoreScreenLayer {

    @In
    BlockManager blockManager;

    private UILabel blockFamilyCountLabel;

    @Override
    public void initialise() {
        blockFamilyCountLabel = find("blockFamilyCountLabel", UILabel.class);

        blockFamilyCountLabel.setText("Number of Registered Block Families: " + blockManager.getBlockFamilyCount());
    }
}
