// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.sample.nui;

import org.terasology.engine.registry.In;
import org.terasology.engine.rendering.nui.CoreScreenLayer;
import org.terasology.engine.world.WorldProvider;
import org.terasology.nui.widgets.UIButton;
import org.terasology.nui.widgets.UIText;

public class SeedScreen extends CoreScreenLayer {
    private UIText seedText;
    @In
    private WorldProvider provider;

    @Override
    public void initialise() {
        seedText = find("seedText", UIText.class);
        UIButton seedUpdateButton = find("seedUpdateButton", UIButton.class);
        if (seedText != null && seedUpdateButton != null) {
            seedUpdateButton.subscribe(button -> {
                seedText.setText("The seed of this world is : " + provider.getSeed());
            });
        }
    }
}
