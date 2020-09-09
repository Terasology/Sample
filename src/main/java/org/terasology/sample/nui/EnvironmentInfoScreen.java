// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.sample.nui;

import org.terasology.engine.core.Time;
import org.terasology.engine.registry.In;
import org.terasology.engine.rendering.nui.CoreScreenLayer;
import org.terasology.nui.widgets.UIButton;
import org.terasology.nui.widgets.UIText;

public class EnvironmentInfoScreen extends CoreScreenLayer {

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
                final double bytesInMegabyte = 1048576.0;
                double memoryUsage =
                        ((double) Runtime.getRuntime().totalMemory() - (double) Runtime.getRuntime().freeMemory()) / bytesInMegabyte;
                infoArea.setText(String.format("Welcome to the environment info screen!%n" +
                                "The current world has been active for %.0f (in-game) seconds.%n" +
                                "Currently running at %.2f FPS and using %.2f MB of memory out of %.2f available.",
                        time.getGameTime(), time.getFps(), memoryUsage,
                        Runtime.getRuntime().maxMemory() / bytesInMegabyte));
            });
        }
    }
}
