// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.sample.nui;

import org.terasology.engine.rendering.nui.CoreScreenLayer;
import org.terasology.nui.widgets.UIButton;
import org.terasology.nui.widgets.UIText;

public class CounterScreen extends CoreScreenLayer {
    private UIText infoArea;
    private UIButton updateIncreaseButton;
    private UIButton updateDecreaseButton;
    private int i;

    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);
        updateIncreaseButton = find("updateIncreaseButton", UIButton.class);

        if (updateIncreaseButton != null) {
            updateIncreaseButton.subscribe(button -> {
                i++;
                infoArea.setText("Count: " + i);
            });
        }

        updateDecreaseButton = find("updateDecreaseButton", UIButton.class);
        if (updateDecreaseButton != null) {
            updateDecreaseButton.subscribe(button -> {
                i--;
                infoArea.setText("Count: " + i);
            });
        }
    }
}

