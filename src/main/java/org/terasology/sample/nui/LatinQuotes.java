// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.sample.nui;

import org.terasology.engine.rendering.nui.CoreScreenLayer;
import org.terasology.nui.widgets.UIButton;
import org.terasology.nui.widgets.UIText;

import java.util.Random;

public class LatinQuotes extends CoreScreenLayer {
    private final Random random = new Random();
    private final String[] latinQuotes = {
            "Barba tenus sapientes",
            "Carpe Noctem!",
            "Ex nihilo nihil fit",
            "Ignotum per ignotius",
            "Alea iacta est",
            "Timendi causa est nescire",
            "Si vis servari, serva"
    };
    private UIText infoArea;
    private UIButton updateInfoButton;

    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);
        updateInfoButton = find("updateInfoButton", UIButton.class);

        if (updateInfoButton != null) {
            updateInfoButton.subscribe(button -> {
                infoArea.setText(latinQuotes[random.nextInt(latinQuotes.length)]);
            });
        }
    }
}
