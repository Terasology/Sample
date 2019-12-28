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
package org.terasology.sample.nui;

import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.logic.health.HealthComponent;
import org.terasology.logic.inventory.InventoryManager;
import org.terasology.logic.players.LocalPlayer;
import org.terasology.registry.In;
import org.terasology.rendering.nui.CoreScreenLayer;
import org.terasology.rendering.nui.widgets.UIButton;
import org.terasology.rendering.nui.widgets.UIText;

public class PlayerInfoScreen extends CoreScreenLayer {
    private UIText infoArea;
    private UIButton updateButton;

    @In
    private LocalPlayer localPlayer;

    @In
    private InventoryManager inventoryManager;

    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);
        updateButton = find("updateInfoButton", UIButton.class);

        if (updateButton != null) {
            updateButton.subscribe(button -> {
                String response = "Welcome to the player information screen.\n\nPosition: " + localPlayer.getPosition().toString();
                HealthComponent healthComponent = localPlayer.getCharacterEntity().getComponent(HealthComponent.class);
                if (healthComponent != null) {
                    response += "\n\nHealth: " + healthComponent.currentHealth + " out of " + healthComponent.maxHealth;
                }

                int numSlots = inventoryManager.getNumSlots(localPlayer.getCharacterEntity());
                int numFilled = 0;
                for (int i = 0; i < numSlots; i++) {
                    if (!(inventoryManager.getItemInSlot(localPlayer.getCharacterEntity(), i).equals(EntityRef.NULL))) {
                        numFilled++;
                    }
                }

                response += "\n\nFilled inventory slots: " + numFilled + "\nTotal inventory slots: " + numSlots;

                infoArea.setText(response);
            });
        }
    }
}
