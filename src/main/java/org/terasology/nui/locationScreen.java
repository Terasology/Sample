/*
 * Copyright 2018 MovingBlocks
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
                        + "%nVelocity: " + localPlayer.getVelocity() + "%n View Direction: " + localPlayer.getViewDirection()
                        , localPlayer.getPosition().x, localPlayer.getPosition().y, localPlayer.getPosition().z);
                infoArea.setText(str);
            });
        }
    }
}
