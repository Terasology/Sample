/*
 * Copyright 2017 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.sample.nui;

import org.terasology.rendering.nui.CoreScreenLayer;
import org.terasology.rendering.nui.widgets.UIButton;
import org.terasology.rendering.nui.widgets.UIText;
import org.terasology.utilities.random.FastRandom;

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

    private UIText infoArea;
    private UIButton updateFactButton;
    private FastRandom random = new FastRandom();

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
