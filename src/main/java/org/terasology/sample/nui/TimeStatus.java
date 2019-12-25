package org.terasology.nui;

import org.terasology.engine.Time;
import org.terasology.registry.In;
import org.terasology.rendering.nui.CoreScreenLayer;
import org.terasology.rendering.nui.widgets.UIButton;
import org.terasology.rendering.nui.widgets.UIText;

public class TimeStatus extends CoreScreenLayer {
    private UIText infoArea;
    private UIButton updateInfoButton;

    @In
    private Time time;

    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);
        updateInfoButton = find("updateInfoButton", UIButton.class);

        if (updateInfoButton != null) {
            updateInfoButton.subscribe(button -> {
                infoArea.setText(String.format("Welcome to the time status screen!\n" +
                                "FPS - %1f\n" +
                                "Game Time - %2d" + "ms\n" +
                                "Real Time Delta - %3d" + "ms", time.getFps(), time.getGameTimeInMs(), time.getGameDeltaInMs()));
            });
        }
    }
}
