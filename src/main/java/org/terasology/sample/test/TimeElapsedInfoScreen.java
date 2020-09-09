// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.sample.test;

import org.terasology.engine.core.Time;
import org.terasology.engine.registry.In;
import org.terasology.engine.rendering.nui.CoreScreenLayer;
import org.terasology.nui.widgets.UIButton;
import org.terasology.nui.widgets.UIText;

public class TimeElapsedInfoScreen extends CoreScreenLayer {

    @In
    private Time time;

    private UIText infoArea;
    private UIButton updateInfoButton;

    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);
        updateInfoButton = find("updateInfoButton", UIButton.class);
        long startTime = time.getGameTimeInMs();

        if (updateInfoButton != null) {
            updateInfoButton.subscribe(button -> {
                infoArea.setText(String.format("Hello there! %d milliseconds have passed since you first opened this " +
                        "screen. ", time.getGameTimeInMs() - startTime));
            });
        }
    }
}
