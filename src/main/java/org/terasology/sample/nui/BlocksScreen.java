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

import org.terasology.rendering.nui.CoreScreenLayer;

/**
 * This class provides the Java side of the Block Selection Screen, which offers functionality similar to creative
 * mode in Minecraft in that it allows any blocks or items to be spawned via a GUI alternative to the /give command. It
 * also allows the searching of all available blocks and items.
 */
public class BlocksScreen extends CoreScreenLayer {

    @Override
    public void initialise() {
        //TODO: Get list of items and alphabatize names
        //TODO: Add list of items (alphabatized or otherwise) to Inventory view (after inventoryview is fixed)
        //TODO: Use binary search on alphabatized list of items for efficient searches (in another method called when search button is pressed)
    }

}
