// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.sample.nui;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.engine.core.Time;
import org.terasology.engine.registry.In;
import org.terasology.engine.rendering.nui.CoreScreenLayer;
import org.terasology.nui.layouts.relative.RelativeLayout;
import org.terasology.nui.widgets.UIButton;
import org.terasology.nui.widgets.UIText;

public class TutorialScreen extends CoreScreenLayer {
    private static final Logger logger = LoggerFactory.getLogger("logger");
    private final String pt0 = "Welcome to Terasology! This tutorial will guide you through using the default " +
            "controls. Press 'Okay' to continue.";
    private final String pt1 = "To destroy blocks, you can use the left mouse button.";
    private final String pt2 = "To place blocks, use your right mouse button.";
    private final String pt3 = "Press space to jump, and use W, A, S, and D to move.";
    private final String pt4 = "Press E to activate or use an item, and I to access your inventory.";
    private final String pt5 = "You can use the number keys (1-0) to move through your toolbar slots.";
    private final String pt6 = "Enjoy playing Terasology!";
    private UIText info;
    private UIButton proceed;
    private RelativeLayout layout;
    private String base = "Welcome to Terasology! This tutorial will guide you through using the default controls. " +
            "Press 'Okay' to continue.";
    @In
    private Time time;

    @Override
    public void initialise() {
        info = find("info", UIText.class);
        proceed = find("proceed", UIButton.class);
        layout = find("main", RelativeLayout.class);

        layout.setEnabled(true);
        layout.setVisible(true);

        info.setText(base);

        if (proceed != null) {
            proceed.subscribe(button -> {
                advance();
                info.setText(base);
                if (base.equals("")) {
                    layout.setVisible(false);
                    layout.setEnabled(false);
                }
            });
        }
    }

    private void advance() {
        if (base.equals(pt0)) {
            base = pt1;
        } else if (base.equals(pt1)) {
            base = pt2;
        } else if (base.equals(pt2)) {
            base = pt3;
        } else if (base.equals(pt3)) {
            base = pt4;
        } else if (base.equals(pt4)) {
            base = pt5;
        } else if (base.equals(pt5)) {
            base = pt6;
        } else {
            base = "";
        }
    }
}
