// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.sample.nui;

import org.terasology.engine.rendering.nui.CoreScreenLayer;
import org.terasology.nui.widgets.UIButton;
import org.terasology.nui.widgets.UIText;

public class InformativeLinksScreen extends CoreScreenLayer {
    private final String[] linksList = {
            "Care to visit Terasology's main website?\nhttp://terasology.org/",
            "Check out the Terasology forum here:\nhttp://forum.terasology.org/",
            "Wish to contribute to our Open-Source Code? Find our Github repository here:\nhttps://github" +
                    ".com/MovingBlocks/Terasology",
            "Follow us on Twitter:\nhttps://twitter.com/terasology?lang=en",
            "Follow us on Facebook:\nhttps://www.facebook.com/Terasology/"
    };
    private UIText infoArea;
    private UIButton updateInfoButton;
    private int index;

    public String getNextLink() {
        index++;

        if (index >= linksList.length) {
            index = 0;
        }

        return linksList[index];
    }

    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);
        updateInfoButton = find("updateInfoButton", UIButton.class);

        if (updateInfoButton != null) {
            updateInfoButton.subscribe(button -> {
                infoArea.setText(getNextLink());
            });
        }
    }
}
