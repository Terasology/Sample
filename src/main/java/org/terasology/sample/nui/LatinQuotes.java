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
import java.util.Random;

public class LatinQuotes extends CoreScreenLayer {
    private UIText infoArea;
    private UIButton updateInfoButton;
    private Random random = new Random();

    private String[] latinQuotes = {
            "Barba tenus sapientes",
            "Carpe Noctem!",
            "Ex nihilo nihil fit",
            "Ignotum per ignotius",
            "Alea iacta est",
            "Timendi causa est nescire",
            "Si vis servari, serva"
    };

    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);
        updateInfoButton = find("updateInfoButton", UIButton.class);

        if (updateInfoButton != null) {
            updateInfoButton.subscribe(button -> {
                infoArea.setText(latinQuotes[random.nextInt(latinQuotes.length)]);
            });
        }
    }
}
