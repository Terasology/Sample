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

import org.terasology.rendering.nui.CoreScreenLayer;
import org.terasology.rendering.nui.widgets.UIText;

public class HamsterScreen extends CoreScreenLayer {
    private UIText infoArea;

    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);

        infoArea.setText(String.format("\t\t\t                  \n" +
                "               (>\\---/<)\n" +
                "            ,'           `.\n" +
                "           /    0   0     \\\n" +
                "          (    >(_Y_)<  )\n" +
                "           >-'    `-' `-<-.\n" +
                "          /  _.==  ,=.,- \\\n" +
                "         /,    )`   '(    )\n" +
                "        ; `._.'       `--<\n" +
                "       :     )         (  )\n" +
                "       \\      )        ;_/ \n" +
                "        `._ _/_  ___.'-(((\n" +
                "           `--)))\n" +
                "\n" +
                "Meet my hamster! Her name is Cinnamon.\n" +
                "\n"));
    }
}
