
package org.terasology.nui;

import org.terasology.registry.In;
import org.terasology.rendering.nui.CoreScreenLayer;
import org.terasology.rendering.nui.widgets.UIButton;
import org.terasology.rendering.nui.widgets.UIText;
import org.terasology.world.time.WorldTimeImpl;

public class WorldTimeScreen extends CoreScreenLayer {
    private UIText infoArea;
    private UIButton updateInfoButton;

    @In
    private WorldTimeImpl worldTime;

    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);
        updateInfoButton = find("updateInfoButton", UIButton.class);

        if (updateInfoButton != null) {
            updateInfoButton.subscribe(button -> {
                //get info for ui screen here
                infoArea.setText(String.format("The current world is on day %.0f, with the time" +
                                               "%.2f:%.2f.%.2f. Press \"Update Button\" to update this information.",
                        worldTime.getDays(), worldTime.getSeconds() / 60,
                        worldTime.getSeconds(), worldTime.getMilliseconds()));
            });
        }
    }
}