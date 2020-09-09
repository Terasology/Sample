// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.sample.nui;

import org.terasology.engine.rendering.nui.CoreScreenLayer;
import org.terasology.nui.widgets.UIButton;
import org.terasology.nui.widgets.UIText;

public class InspirationScreen extends CoreScreenLayer {
    private final String[] inspirationQuotes = {
            "Keep your face always toward the sunshine - and shadows will fall behind you -Walt Whitman",
            "Believe you can and you're halfway there -Theodore Roosevelt",
            "Try to be a rainbow in someone's cloud -Maya Angelou",
            "We know what we are, but know not what we may be  -William Shakespeare",
            "No act of kindness, no matter how small, is ever wasted -Aesop",
            "If opportunity doesn't knock, build a door -Milton Berle"
    };
    private UIText infoArea;
    private UIButton updateInfoButton;
    private int selectedIndex = -1;

    public String getNextQuote() {
        selectedIndex++;

        if (selectedIndex >= inspirationQuotes.length) {
            selectedIndex = 0;
        }

        return inspirationQuotes[selectedIndex];
    }

    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);
        infoArea.setText(getNextQuote());
        updateInfoButton = find("updateInfoButton", UIButton.class);

        if (updateInfoButton != null) {
            updateInfoButton.subscribe(button -> {
                infoArea.setText(getNextQuote());
            });
        }
    }
}

