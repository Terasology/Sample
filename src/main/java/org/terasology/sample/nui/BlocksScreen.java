// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.sample.nui;

import org.terasology.engine.rendering.nui.CoreScreenLayer;

/**
 * This class provides the Java side of the Block Selection Screen, which offers functionality similar to creative mode
 * in Minecraft in that it allows any blocks or items to be spawned via a GUI alternative to the /give command. It also
 * allows the searching of all available blocks and items.
 */
public class BlocksScreen extends CoreScreenLayer {

    @Override
    public void initialise() {
        //TODO: Get list of items and alphabatize names
        //TODO: Add list of items (alphabatized or otherwise) to Inventory view (after inventoryview is fixed)
        //TODO: Use binary search on alphabatized list of items for efficient searches (in another method called when
        // search button is pressed)
    }

}
