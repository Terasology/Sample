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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.engine.Time;
import org.terasology.registry.In;
import org.terasology.rendering.nui.CoreScreenLayer;
import org.terasology.rendering.nui.layouts.relative.RelativeLayout;
import org.terasology.rendering.nui.widgets.UIButton;
import org.terasology.rendering.nui.widgets.UIText;

public class TutorialScreen extends CoreScreenLayer {
    private static final Logger logger = LoggerFactory.getLogger("logger");
    private UIText info;
    private UIButton proceed;
    private RelativeLayout layout;
    private String base = "Welcome to Terasology! This tutorial will guide you through using the default controls. Press 'Okay' to continue.";
    private String pt0 = "Welcome to Terasology! This tutorial will guide you through using the default controls. Press 'Okay' to continue.";
    private String pt1 = "To destroy blocks, you can use the left mouse button.";
    private String pt2 = "To place blocks, use your right mouse button.";
    private String pt3 = "Press space to jump, and use W, A, S, and D to move.";
    private String pt4 = "Press E to activate or use an item, and I to access your inventory.";
    private String pt5 = "You can use the number keys (1-0) to move through your toolbar slots.";
    private String pt6 = "Enjoy playing Terasology!";

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
