/*
 * Copyright 2017 MovingBlocks
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
import org.terasology.engine.Time;
import org.terasology.registry.In;
import java.util.Random;

public class EnvironmentInfoScreen extends CoreScreenLayer {
    private UIText infoArea;
    private UIButton updateInfoButton;

    @In
    private Time time;

    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);
        updateInfoButton = find("updateInfoButton", UIButton.class);

        if (updateInfoButton != null) {
            updateInfoButton.subscribe(button -> {
                Random rand = new Random();
                int  n = rand.nextInt(5) + 1;
                    if (1 == n) {
                        infoArea.setText(String.format("The Dinosaur was first discovered in 1824."));
                    } else if (2 == n) {
                        infoArea.setText(String.format("Dimetrodon is not a dinosaur."));
                    } else if (3 == n) {
                        infoArea.setText(String.format("The T-Rex and Stegosaurus did not live at the same time period."));
                    } else if (4 == n) {
                        infoArea.setText(String.format("In Jurassic Park, the 'Velociraptors' are far larger than their real life counterparts."));
                    } else if (5 == n) {
                        infoArea.setText(String.format("The first dinosaur discovered was an Iguanadon, but they build the skeleton incorrectly."));
                    }

            });
        }
    }
}