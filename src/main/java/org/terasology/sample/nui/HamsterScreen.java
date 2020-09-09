// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.sample.nui;

import org.terasology.engine.rendering.nui.CoreScreenLayer;
import org.terasology.nui.widgets.UIText;

public class HamsterScreen extends CoreScreenLayer {
    private UIText infoArea;

    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);

        infoArea.setText(String.format("\t\t\t                  \n" +
                "               (>\\---/<)\n" +
                "            ,'           `.\n" +
                "           /    0   0     \\\n" +
                "          (    >(_Y_)<  )\n" +
                "           >-'    `-' `-<-.\n" +
                "          /  _.==  ,=.,- \\\n" +
                "         /,    )`   '(    )\n" +
                "        ; `._.'       `--<\n" +
                "       :     )         (  )\n" +
                "       \\      )        ;_/ \n" +
                "        `._ _/_  ___.'-(((\n" +
                "           `--)))\n" +
                "\n" +
                "Meet my hamster! Her name is Cinnamon.\n" +
                "\n"));
    }
}
