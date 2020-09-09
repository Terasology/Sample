// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.sample.crash;

import org.terasology.engine.entitySystem.entity.EntityRef;
import org.terasology.engine.entitySystem.event.ReceiveEvent;
import org.terasology.engine.entitySystem.systems.BaseComponentSystem;
import org.terasology.engine.entitySystem.systems.RegisterSystem;
import org.terasology.engine.logic.common.ActivateEvent;

/**
 * Crashes the game when a CrashGameBlock is activated. Good for testing.
 */
@RegisterSystem
public class CrashGameBlockSystem extends BaseComponentSystem {

    @ReceiveEvent(components = CrashGameBlockComponent.class)
    public void onActivate(ActivateEvent event, EntityRef entity) {
        CrashGameBlockComponent cgbc = entity.getComponent(CrashGameBlockComponent.class);
        if (cgbc.reallyCrashy) {
            System.out.println("REALLY crashing the game now - via violating module security!");
            System.out.println("That is, unless you are running with security disabled? Naughty");
        } else {
            throw new RuntimeException("Test crash triggered!");
        }
    }
}
