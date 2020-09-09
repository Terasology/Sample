// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.sample.nui;

import org.terasology.engine.core.Time;
import org.terasology.engine.registry.In;
import org.terasology.engine.rendering.nui.CoreScreenLayer;
import org.terasology.nui.widgets.UIButton;
import org.terasology.nui.widgets.UILabel;
import org.terasology.nui.widgets.UIText;

import java.util.Calendar;
import java.util.Random;

public class TIQscreen extends CoreScreenLayer {
    private final double bytesInMegabyte = 1048576.0;
    private final String[] quotes = {
            "\"A champion is defined not by their wins but by how they can recover when they fall.\"  - Serena " +
                    "Williams",
            "\"It's the possibility of having a dream come true that makes life interesting.\" - Paulo Coelho, The " +
                    "Alchemist",
            "\"Even the darkest night will end and the sun will rise.\" - Victor Hugo, Les Miserables",
            "\"So many things are possible just as long as you don’t know they’re impossible.\" - Norton Juster, The " +
                    "Phantom Tollbooth",
            "\"The worst enemy to creativity is self-doubt.\" - Sylvia Plath, The Unabridged Journals of Sylvia Plath"
    };
    private UILabel timeDisplay;
    private UIText infoArea;
    private UIText quoteDisplay;
    private UIButton updateButton;
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
        return time.getGameTime() - (minutesInGame() * 60);
    }

    private String getRealWorldTime() {
        Calendar c = Calendar.getInstance();
        return c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
    }
}
