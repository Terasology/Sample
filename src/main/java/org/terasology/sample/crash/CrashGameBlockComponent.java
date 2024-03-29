// Copyright 2021 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.sample.crash;

import org.terasology.gestalt.entitysystem.component.Component;

/**
 * Used by a block meant to crash the game, yay!
 */
public class CrashGameBlockComponent implements Component<CrashGameBlockComponent> {
    public boolean reallyCrashy;

    @Override
    public void copyFrom(CrashGameBlockComponent other) {
        this.reallyCrashy = other.reallyCrashy;
    }
}
