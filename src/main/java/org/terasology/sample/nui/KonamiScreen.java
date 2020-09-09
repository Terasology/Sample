// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.sample.nui;

import org.terasology.engine.rendering.nui.CoreScreenLayer;
import org.terasology.nui.widgets.UIButton;
import org.terasology.nui.widgets.UIText;

public class KonamiScreen extends CoreScreenLayer {
    private UIText infoArea;
    private UIButton toggleKonamiButton;
    private boolean konamiActivated;

    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);
        toggleKonamiButton = find("toggleKonamiButton", UIButton.class);

        if (konamiActivated) {
            infoArea.setText("Konami Mode is currently activated");
        } else {
            infoArea.setText("Konami Mode is currently deactivated");
        }

        if (toggleKonamiButton != null) {
            toggleKonamiButton.subscribe(button -> {
                if (konamiActivated) {
                    infoArea.setText("Konami Mode has been deactivated");
                } else {
                    infoArea.setText("Konami Mode has been activated");
                }
                konamiActivated = !konamiActivated;
            });
        }
    }
}

