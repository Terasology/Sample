// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.sample.nui;

import org.terasology.engine.rendering.nui.CoreScreenLayer;
import org.terasology.nui.UIWidget;
import org.terasology.nui.widgets.UIButton;
import org.terasology.nui.widgets.UIText;

import java.util.Calendar;

public class TimeScreen extends CoreScreenLayer {
    private UIText infoArea;
    private UIButton updateInfoButton;

    private void setInfoAreaText() {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());
        String date = c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH) + "-" + c.get(Calendar.DAY_OF_MONTH);
        String time = c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
        infoArea.setText("Real Time: " + date + " " + time + "\nPress ESC to exit");
    }

    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);
        updateInfoButton = find("updateInfoButton", UIButton.class);
        setInfoAreaText();
        if (updateInfoButton != null) {
            updateInfoButton.subscribe((UIWidget button) -> setInfoAreaText());
        }
    }
}
