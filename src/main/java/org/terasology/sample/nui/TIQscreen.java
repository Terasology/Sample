/*
 * Copyright 2018 MovingBlocks
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

import org.terasology.engine.Time;
import org.terasology.registry.In;
import org.terasology.rendering.nui.CoreScreenLayer;
import org.terasology.rendering.nui.widgets.UIButton;
import org.terasology.rendering.nui.widgets.UILabel;
import org.terasology.rendering.nui.widgets.UIText;

import java.util.Calendar;
import java.util.Random;

public class TIQscreen extends CoreScreenLayer {
    private UILabel timeDisplay;
    private UIText infoArea;
    private UIText quoteDisplay;
    private UIButton updateButton;

    private final double bytesInMegabyte = 1048576.0;

    private String[] quotes = {
        "\"A champion is defined not by their wins but by how they can recover when they fall.\"  - Serena Williams",
        "\"It's the possibility of having a dream come true that makes life interesting.\" - Paulo Coelho, The Alchemist",
        "\"Even the darkest night will end and the sun will rise.\" - Victor Hugo, Les Miserables",
        "\"So many things are possible just as long as you don’t know they’re impossible.\" - Norton Juster, The Phantom Tollbooth",
        "\"The worst enemy to creativity is self-doubt.\" - Sylvia Plath, The Unabridged Journals of Sylvia Plath"
    };

    @In
    private Time time;

    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);
        timeDisplay = find("timeDisplay", UILabel.class);
        quoteDisplay = find("quoteDisplay", UIText.class);
        updateButton = find("updateButton", UIButton.class);

        quoteDisplay.setText(quotes[new Random().nextInt(quotes.length)]);

        if (updateButton != null) {
            updateButton.subscribe(button -> quoteDisplay.setText(quotes[new Random().nextInt(quotes.length)]));
        }
    }

    @Override
    public void update(float delta) {
        setInfoArea();
        setTimeDisplay();
    }

    private void setInfoArea() {
        double memoryUsage = ((double) Runtime.getRuntime().totalMemory()
                - (double) Runtime.getRuntime().freeMemory()) / bytesInMegabyte;
        infoArea.setText(String.format("INFORMATION%n" + " %n" +
                        "The current world has been active for %d min., %.1f sec. (in-game).%n" +
                        "Currently running at %.2f FPS%n" +
                        "Using %.2f MB of memory out of %.2f available.%n",
                minutesInGame(), secondsInGame(), time.getFps(), memoryUsage,
                (Runtime.getRuntime().maxMemory() / bytesInMegabyte)));
    }

    private void setTimeDisplay() {
        timeDisplay.setText(String.format("Local Time: " + getRealWorldTime()));
    }

    private int minutesInGame() {
        return (int) (time.getGameTime() / 60);
    }

    private float secondsInGame() {
        return  time.getGameTime() - (minutesInGame() * 60);
    }

    private String getRealWorldTime() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
    }
}
