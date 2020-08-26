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

import com.google.common.collect.Lists;
import org.terasology.nui.UIWidget;
import org.terasology.nui.widgets.UIButton;
import org.terasology.nui.widgets.UILabel;
import org.terasology.rendering.nui.CoreScreenLayer;
import java.util.Collections;
import java.util.List;

public class QuoteRandom extends CoreScreenLayer {
    private UILabel infoArea;
    private UIButton updateInfoButton;

    private List<String> quoteList = Lists.newArrayList("Strive not to be a success, but rather to be of value. - Albert Einstein",
            "When a goal matters enough to a person, that person will find a way to accomplish what at first seemed impossible. - Nido Qubein",
            "You can't let praise or criticism get to you. It's a weakness to get caught up in either one. - John Wooden",
            "Good, better, best. Never let it rest. Until your good is better and your better is best. - Tim Duncan",
            "If you can see the positive sides of everything, you'll be able to live a much richer life than others. - Celestine Chua",
            "Million dollar ideas are a dime a dozen. The determination to see the idea through is what's priceless. - Robert Dieffenbach",
            "Many of life's failures are people who did not realize how close they were to success when they gave up. - Thomas Edison",
            "The greatest glory in living lies not in never falling, but in rising every time we fall. - Nelson Mandela");

    @Override
    public void initialise() {
        infoArea = find("quoteRandomBody", UILabel.class);
        updateInfoButton = find("updateInfoButton", UIButton.class);

        if (updateInfoButton != null) {
            updateInfoButton.subscribe(this::onClickingQuoteButton);
        }
    }

    private void onClickingQuoteButton(UIWidget uiWidget) {
        Collections.shuffle(quoteList);
        infoArea.setText(quoteList.get(0));
    }
}
