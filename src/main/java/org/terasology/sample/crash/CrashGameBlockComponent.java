// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.sample.crash;

import org.terasology.engine.entitySystem.Component;

/**
 * Used by a block meant to crash the game, yay!
 */
public class CrashGameBlockComponent implements Component {
    boolean reallyCrashy;
}
