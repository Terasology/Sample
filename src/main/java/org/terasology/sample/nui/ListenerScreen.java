// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.sample.nui;

import org.terasology.engine.rendering.nui.CoreScreenLayer;
import org.terasology.nui.widgets.UIButton;
import org.terasology.nui.widgets.UIText;

import java.util.ArrayList;

public class ListenerScreen extends CoreScreenLayer {
    private static ListenerScreen instance;
    private final ArrayList<ListenerScreenTextProvider> providers = new ArrayList<>();
    private UIText text;

    public ListenerScreen() {
        this(true);
    }

    private ListenerScreen(boolean singleton) {
        if (singleton) {
            instance = this;
        }
    }

    public static void registerProvider(ListenerScreenTextProvider provider) {
        instance.providers.add(provider);
    }

    @Override
    public void initialise() {
        text = find("listenerText", UIText.class);
        UIButton button = find("listenerButton", UIButton.class);

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
}
