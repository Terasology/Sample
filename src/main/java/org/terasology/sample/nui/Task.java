// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.sample.nui;

import org.terasology.engine.rendering.nui.CoreScreenLayer;
import org.terasology.nui.widgets.UIButton;
import org.terasology.nui.widgets.UIText;

public class Task extends CoreScreenLayer {
    private static int i;

    final String[] quotes = {
            "Where there is love there is life. - Mahatma Gandhi",
            "The best and most beautiful things in the world cannot be seen or even touched - they must be felt with " +
                    "the heart. - Helen Keller",
            "Keep love in your heart. A life without it is like a sunless garden when the flowers are dead. - Oscar " +
                    "Wilde",
            "It is during our darkest moments that we must focus to see the light. - Aristotle",
            "Try to be a rainbow in someone's cloud. - Maya Angelou",
            "Find a place inside where there's joy, and the joy will burn out the pain. - Joseph Campbell",
            "Nothing is impossible, the word itself says 'I'm possible'! - Audrey Hepburn",
            "Don't judge each day by the harvest you reap but by the seeds that you plant. - Robert Louis Stevenson",
            "Do not dwell in the past, do not dream of the future, concentrate the mind on the present moment. - " +
                    "Buddha",
            "The only thing necessary for the triumph of evil is for good men to do nothing. - Edmund Burke",
            "One of the most beautiful qualities of true friendship is to understand and to be understood. - Lucius " +
                    "Annaeus Seneca",
            "Do all things with love. - Og Mandino",
            "You can't blame gravity for falling in love. - Albert Einstein",
            "In three words I can sum up everything I've learned about life: it goes on. - Robert Frost",
            "Education is the most powerful weapon which you can use to change the world. - Nelson Mandela",
            "Life's most persistent and urgent question is, 'What are you doing for others?' - Martin Luther King, Jr.",
            "The only true wisdom is in knowing you know nothing. - Socrates",
            "I love you the more in that I believe you had liked me for my own sake and for nothing else. - John Keats",
            "But man is not made for defeat. A man can be destroyed but not defeated. - Ernest Hemingway",
            "When you reach the end of your rope, tie a knot in it and hang on. - Franklin D. Roosevelt",
            "You cannot shake hands with a clenched fist. - Indira Gandhi"
    };

    private UIText infoArea;
    private UIButton infoButton;

    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);
        infoButton = find("infoButton", UIButton.class);

        if (infoButton != null) {
            infoButton.subscribe(button -> {
                if (i == 20) {
                    i = 0;
                }
                infoArea.setText(quotes[i]);
                i++;
            });
        }
    }
}
