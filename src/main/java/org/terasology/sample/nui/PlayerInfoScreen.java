// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.sample.nui;

import org.terasology.engine.entitySystem.entity.EntityRef;
import org.terasology.engine.logic.players.LocalPlayer;
import org.terasology.engine.registry.In;
import org.terasology.engine.rendering.nui.CoreScreenLayer;
import org.terasology.health.logic.HealthComponent;
import org.terasology.inventory.logic.InventoryManager;
import org.terasology.nui.widgets.UIButton;
import org.terasology.nui.widgets.UIText;

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
                String response =
                        "Welcome to the player information screen.\n\nPosition: " + localPlayer.getPosition().toString();
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
