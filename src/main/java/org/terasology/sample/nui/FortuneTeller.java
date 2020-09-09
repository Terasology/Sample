// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.sample.nui;

import org.terasology.engine.rendering.nui.CoreScreenLayer;
import org.terasology.nui.widgets.UIRadialRing;
import org.terasology.nui.widgets.UIRadialSection;
import org.terasology.nui.widgets.UIText;

import java.util.LinkedList;
import java.util.List;

public class FortuneTeller extends CoreScreenLayer {
    private final String[] fortunes = new String[]{
            "You will discover a new block!",
            "A radial menu event will trigger!",
            "A bugfix will be released!",
            "You will be underwhelmed by this fortune!",
            "You will click another radial button!"
    };
    private UIText infoArea;
    private UIRadialRing radialRing;

    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);
        radialRing = find("radialMenu", UIRadialRing.class);

        List<UIRadialSection> newSections = new LinkedList<>();

        for (int i = 0; i < fortunes.length; i++) {
            UIRadialSection newSection = new UIRadialSection(Integer.toString(i));
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
