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
package org.terasology.sample.nui;

import org.terasology.nui.widgets.UIButton;
import org.terasology.nui.widgets.UIText;
import org.terasology.engine.rendering.nui.CoreScreenLayer;

public class Test extends CoreScreenLayer {
    private UIText infoArea;
    private UIButton button;

    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);
        button = find("button", UIButton.class);

        if (button != null) {
            button.subscribe(btn -> {
                infoArea.setText(String.format("[TODO] Hello World! \n This is the first module made by: \n Nickname: Koke_Cacao \n Google Code-in HankeChen"));
            });
        }
    }
}
