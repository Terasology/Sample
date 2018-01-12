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
package org.terasology.nui;


import org.terasology.rendering.nui.CoreScreenLayer;
import org.terasology.rendering.nui.widgets.UIRadialRing;
import org.terasology.rendering.nui.widgets.UIRadialSection;
import org.terasology.rendering.nui.widgets.UIText;

import java.util.LinkedList;
import java.util.List;


public class FortuneTeller extends CoreScreenLayer {
    private UIText infoArea;
    private UIRadialRing radialRing;

    private final String[] fortunes = new String[] {
            "You will discover a new block!",
            "A radial menu event will trigger!",
            "A bugfix will be released!",
            "You will be underwhelmed by this fortune!",
            "You will click another radial button!"
    };

    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);
        radialRing = find("radialMenu", UIRadialRing.class);

        List<UIRadialSection> newSections = new LinkedList<>();

        for (int i = 0; i < fortunes.length; i++) {
            UIRadialSection newSection = new UIRadialSection();
            newSection.setText(Integer.toString(i));

            newSections.add(newSection);
        }

        radialRing.setSections(newSections);

        if (radialRing != null) {
            for (int i = 0; i < 5; i++) {
                final int index = i;
                radialRing.subscribe(i, widget -> infoArea.setText(fortunes[index]));
            }
        }
    }

}
