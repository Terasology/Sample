// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.sample.nui;

import org.terasology.engine.rendering.nui.CoreScreenLayer;
import org.terasology.nui.widgets.UIButton;
import org.terasology.nui.widgets.UIText;

public class AddAndSubtract extends CoreScreenLayer {
    private UIText infoArea;
    private UIButton addButton;
    private UIButton subtractButton;

    private int count;

    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);
        addButton = find("addButton", UIButton.class);
        subtractButton = find("subtractButton", UIButton.class);

        if (addButton != null) {
            addButton.subscribe(button -> {
                count++;
                infoArea.setText(Integer.toString(count));
            });
        }
        if (subtractButton != null) {
            subtractButton.subscribe(button -> {
                count--;
                infoArea.setText(Integer.toString(count));
            });
        }
    }
}
