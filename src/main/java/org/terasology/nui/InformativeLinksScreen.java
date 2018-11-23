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

public class InformativeLinksScreen extends CoreScreenLayer {
    private UIText infoArea;
    private UIButton updateInfoButton;
    private int index;

    private String[] linksList = {
            "Care to visit Terasology's main website?\nhttp://terasology.org/",
            "Check out the Terasology forum here:\nhttp://forum.terasology.org/",
            "Wish to contribute to our Open-Source Code? Find our Github repository here:\nhttps://github.com/MovingBlocks/Terasology",
            "Follow us on Twitter:\nhttps://twitter.com/terasology?lang=en",
            "Follow us on Facebook:\nhttps://www.facebook.com/Terasology/"
    };

    public String getNextLink() {

        index++;

        if (index >= linksList.length) {
            index = 0;
        }

        return linksList[index];
    }

    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);
        updateInfoButton = find("updateInfoButton", UIButton.class);

        if (updateInfoButton != null) {
            updateInfoButton.subscribe(button -> {
                infoArea.setText(getNextLink());
            });
        }
    }
}
