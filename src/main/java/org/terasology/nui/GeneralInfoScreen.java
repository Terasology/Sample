/*
 * Copyright 2016 MovingBlocks
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

import org.terasology.engine.Time;
import org.terasology.logic.health.HealthComponent;
import org.terasology.logic.players.LocalPlayer;
import org.terasology.registry.In;
import org.terasology.rendering.nui.CoreScreenLayer;
import org.terasology.rendering.nui.widgets.UIButton;
import org.terasology.rendering.nui.widgets.UIText;

public class GeneralInfoScreen extends CoreScreenLayer {
    private UIText infoArea;
    private UIButton updateButton;

    @In
    private LocalPlayer localPlayer;

    @In
    private Time time;

    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);
        updateButton = find("updateInfoButton", UIButton.class);

        if (updateButton != null) {
            updateButton.subscribe(button -> {
                HealthComponent healthComponent = localPlayer.getCharacterEntity().getComponent(HealthComponent.class);
                if (healthComponent != null) {
                    final double bytesInMegabyte = 1048576.0;
                    double memoryUsage = ((double) Runtime.getRuntime().totalMemory() - (double) Runtime.getRuntime().freeMemory()) / bytesInMegabyte;

                    infoArea.setText(String.format("Welcome to the general information screen!%n" +
                            "The current world has been active for %.0f seconds.%n" +
                            "Currently running at %.2f FPS, using %.2f MB of memory out of %.2f available.%n" +
                            "Health: %d out of %d",
                            time.getGameTime(), time.getFps(),
                            memoryUsage, Runtime.getRuntime().maxMemory() / bytesInMegabyte,
                            healthComponent.currentHealth, healthComponent.maxHealth));
                }
            });
        }
    }
}
