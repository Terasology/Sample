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

import org.terasology.rendering.nui.CoreScreenLayer;
import org.terasology.rendering.nui.widgets.UIButton;
import org.terasology.rendering.nui.widgets.UIText;

public class AddAndSubtract extends CoreScreenLayer {
    private UIText infoArea;
    private UIButton addButton, subtractButton;

    private int count = 0;

    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);
        addButton = find("addButton", UIButton.class);
        subtractButton = find("subtractButton", UIButton.class);

        if (addButton != null) {
            addButton.subscribe(button -> {
                count++;
                infoArea.setText(Integer.toString(count));
            });
        }
        if (subtractButton != null) {
            subtractButton.subscribe(button -> {
                count--;
                infoArea.setText(Integer.toString(count));
            });
        }
    }
}
