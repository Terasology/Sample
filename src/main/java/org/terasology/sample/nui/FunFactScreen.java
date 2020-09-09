// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.sample.nui;

import org.terasology.engine.rendering.nui.CoreScreenLayer;
import org.terasology.engine.utilities.random.FastRandom;
import org.terasology.nui.widgets.UIButton;
import org.terasology.nui.widgets.UIText;

public class FunFactScreen extends CoreScreenLayer {
    private static final String[] FACTS = {
            "Rocks are made from compressed sand.",
            "McDonald's is part of Canada.",
            "Running was invented by Thomas Running when he tried to walk twice at the same time.",
            "Science and Facts have proven that the Windows Phone is superior to iOS and Android.",
            "If you hold your breath for 3 hours, you unlock cheat codes.",
            "Alaska is actually larger than several countries, including the US.",
            "Goldfish are the last animal to be classified as endangered.",
            "Crocodiles can change color, but only when you're not looking.",
            "People who are left handed finish their tests earlier on average than people with no hands."
    };
    private final FastRandom random = new FastRandom();
    private UIText infoArea;
    private UIButton updateFactButton;

    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);
        updateFactButton = find("updateFactButton", UIButton.class);

        if (updateFactButton != null) {
            updateFactButton.subscribe(button -> {
                infoArea.setText(FACTS[random.nextInt(FACTS.length)]);
            });
        }
    }

}
