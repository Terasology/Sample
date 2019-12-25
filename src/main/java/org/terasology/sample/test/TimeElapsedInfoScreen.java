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
package org.terasology.sample.test;

import org.terasology.engine.Time;
import org.terasology.registry.In;
import org.terasology.rendering.nui.CoreScreenLayer;
import org.terasology.rendering.nui.widgets.UIButton;
import org.terasology.rendering.nui.widgets.UIText;

public class TimeElapsedInfoScreen extends CoreScreenLayer {

    @In
    private Time time;

    private UIText infoArea;
    private UIButton updateInfoButton;

    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);
        updateInfoButton = find("updateInfoButton", UIButton.class);
        long startTime = time.getGameTimeInMs();

        if (updateInfoButton != null) {
            updateInfoButton.subscribe(button -> {
                infoArea.setText(String.format("Hello there! %d milliseconds have passed since you first opened this screen. ", time.getGameTimeInMs() - startTime));
            });
        }
    }
}
