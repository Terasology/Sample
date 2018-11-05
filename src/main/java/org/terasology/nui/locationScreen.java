package org.terasology.nui;

import org.terasology.registry.In;
import org.terasology.rendering.nui.CoreScreenLayer;
import org.terasology.rendering.nui.widgets.UIButton;
import org.terasology.rendering.nui.widgets.UIText;
import org.terasology.logic.players.LocalPlayer;

public class locationScreen extends CoreScreenLayer {
    private UIText infoArea;
    private UIButton updateInfoButton;

    @In
    private LocalPlayer localPlayer;

    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);
        updateInfoButton = find("updateInfoButton", UIButton.class);

        if (updateInfoButton != null) {
            updateInfoButton.subscribe(button -> {
                String str = String.format("X: %.3f %n" + "Y: %.3f %n" + "Z: %.3f %n" + "Viewing Position: " + localPlayer.getViewPosition()
                        + "%nVelocity: " + localPlayer.getVelocity() + "%nView Direction: " + localPlayer.getViewDirection() + "%nRotation: " + localPlayer.getRotation()
                        , localPlayer.getPosition().x, localPlayer.getPosition().y, localPlayer.getPosition().z);
                infoArea.setText(str);
            });
        }
    }
}
