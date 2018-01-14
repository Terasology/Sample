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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.terasology.rendering.nui.CoreScreenLayer;
import org.terasology.rendering.nui.widgets.UIButton;
import org.terasology.rendering.nui.widgets.UIText;

import java.util.ArrayList;

public class ListenerScreen extends CoreScreenLayer {
    private UIText text;
    private UIButton button;
    private static ArrayList<ListenerScreenTextProvider> providers = new ArrayList<>();

    @Override
    public void initialise() {
        text = find("listenerText", UIText.class);
        button = find("listenerButton", UIButton.class);

        if (button != null) {
            button.subscribe(w -> {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < providers.size(); i++) {
                    sb.append(providers.get(i).getText());
                    if (i < providers.size() - 1) {
                        sb.append("\n");
                    }
                }
                text.setText(sb.toString());
            });
        }
    }

    public static void registerProvider(ListenerScreenTextProvider provider) {
        providers.add(provider);
    }
}
