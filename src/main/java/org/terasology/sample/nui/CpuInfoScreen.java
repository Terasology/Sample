// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.sample.nui;

import org.terasology.engine.core.Time;
import org.terasology.engine.registry.In;
import org.terasology.engine.rendering.nui.CoreScreenLayer;
import org.terasology.nui.widgets.UIButton;
import org.terasology.nui.widgets.UIText;

public class CpuInfoScreen extends CoreScreenLayer {
    private UIText infoArea;
    private UIButton updateInfoButton;

    @In
    private Time time;

    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);
        updateInfoButton = find("updateInfoButton", UIButton.class);

        if (updateInfoButton != null) {
            updateInfoButton.subscribe(button -> {
                int nCores = Runtime.getRuntime().availableProcessors();

                // Get current CPU usage

                infoArea.setText(String.format("Welcome to the CPU info screen!%n" +
                                "There are currently %d CPU cores available to Terasology.%n" +
                                "As for temperature... well, your computer is on. We know that much.%n" +
                                "(If you can figure out a way to measure core temp in Java, submit a pull request on " +
                                "GitHub!)",
                        nCores
                ));
            });
        }
    }
}
