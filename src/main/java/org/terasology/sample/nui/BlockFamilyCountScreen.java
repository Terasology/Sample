// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.sample.nui;

import org.terasology.engine.registry.In;
import org.terasology.engine.rendering.nui.CoreScreenLayer;
import org.terasology.engine.world.block.BlockManager;
import org.terasology.nui.widgets.UILabel;

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
