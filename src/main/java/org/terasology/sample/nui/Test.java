// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.sample.nui;

import org.terasology.engine.rendering.nui.CoreScreenLayer;
import org.terasology.nui.widgets.UIButton;
import org.terasology.nui.widgets.UIText;

public class Test extends CoreScreenLayer {
    private UIText infoArea;
    private UIButton button;

    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);
        button = find("button", UIButton.class);

        if (button != null) {
            button.subscribe(btn -> {
                infoArea.setText(String.format("[TODO] Hello World! \n This is the first module made by: \n Nickname:" +
                        " Koke_Cacao \n Google Code-in HankeChen"));
            });
        }
    }
}
