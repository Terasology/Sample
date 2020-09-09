// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.sample.test;

import org.terasology.engine.core.Time;
import org.terasology.engine.registry.In;
import org.terasology.engine.rendering.nui.CoreScreenLayer;
import org.terasology.nui.widgets.UIButton;
import org.terasology.nui.widgets.UIText;

import java.util.Random;

public class RandomNumberScreen extends CoreScreenLayer {
    private UIText text2;
    private UIButton button2;

    private Random rand;

    @In
    private Time time;

    @Override
    public void initialise() {
        text2 = find("text2", UIText.class);
        button2 = find("button2", UIButton.class);

        rand = new Random();

        if (button2 != null) {
            button2.subscribe(button -> {
                int random = rand.nextInt(10 - 1 + 1) + 1;
                String text = "Random Number: " + random;
                text2.setText(text);
            });
        }

    }
}
