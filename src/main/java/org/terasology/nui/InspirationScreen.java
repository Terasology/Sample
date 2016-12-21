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

public class InspirationScreen extends CoreScreenLayer {
    private UIText infoArea;
    private UIButton updateInfoButton;

    private int selectedIndex = -1;
    
    private String[] inspirationQuotes = {
    		"Keep your face always toward the sunshine - and shadows will fall behind you -Walt Whitman",
    		"Believe you can and you're halfway there -Theodore Roosevelt",
    		"Try to be a rainbow in someone's cloud -Maya Angelou",
    		"We know what we are, but know not what we may be  -William Shakespeare",
    		"No act of kindness, no matter how small, is ever wasted -Aesop",
    		"If opportunity doesn't knock, build a door -Milton Berle"
    };
    
    public String getNextQuote(){
    	
    	selectedIndex++;
    	
    	if(selectedIndex >= inspirationQuotes.length){
    		selectedIndex = 0;
    	}
    		
    	return inspirationQuotes[selectedIndex];
    }
    

    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);
        infoArea.setText(getNextQuote());
        updateInfoButton = find("updateInfoButton", UIButton.class);

        if (updateInfoButton != null) {
            updateInfoButton.subscribe(button -> {
                infoArea.setText(getNextQuote());
            });
        }
    }
}