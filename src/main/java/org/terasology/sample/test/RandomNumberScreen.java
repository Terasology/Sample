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
import org.terasology.nui.widgets.UIButton;
import org.terasology.nui.widgets.UIText;
import org.terasology.registry.In;
import org.terasology.rendering.nui.CoreScreenLayer;

import java.util.Random;

public class RandomNumberScreen extends CoreScreenLayer {
    private UIText text2;
    private UIButton button2;

    private Random rand;

    @In
    private Time time;

    @Override
    public void initialise() {
        text2 = find("text2", UIText.class);
        button2 = find("button2", UIButton.class);

        rand = new Random();

        if (button2 != null) {
            button2.subscribe(button -> {
                int random = rand.nextInt(10 - 1 + 1) + 1;
                String text = "Random Number: " + random;
                text2.setText(text);
            });
        }

    }
}
