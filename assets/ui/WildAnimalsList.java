/*
 * Copyright 2019 MovingBlocks
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
package org.terasology.ui;
import org.terasology.rendering.nui.CoreScreenLayer;
import org.terasology.rendering.nui.widgets.UIButton;
import org.terasology.rendering.nui.widgets.UIText;

public class WildAnimalsList extends CoreScreenLayer {
    private UIText resultText;
    private boolean flagError;
    private UIButton b1;
    @Override
    public void initialise() {

        resultText = find("detail", UIText.class);
     resultText.setText("Facts about WildAnimals go here!");
     String bu="deer",bu2="bear",bu3="lion",bu4="tiger",bu5="leopard",bu6="crocodile";
        UIButton b1 = find(bu, UIButton.class);
        UIButton b2 = find(bu2, UIButton.class);
        UIButton b3 = find(bu3, UIButton.class);
        UIButton b4 = find(bu4, UIButton.class);
        UIButton b5 = find(bu5, UIButton.class);
        UIButton b6 = find(bu6, UIButton.class);

if(b1!=null)
        b1.subscribe(button -> {
            final String tex="Deer are very social and travel in groups called herds. " +
                    "The herd is often led by a dominant male, though with some species the herds are segregated by sex." +
                    " Sometimes the females will have their own herd and the males will have a separate herd." +
                    "In other cases, a female herd is watched over by a herd of males. Some caribou herds can have as many as 100,000 members, according to ADW. ";
                resultText.setText(tex);
            });
        if(b2!=null)
        b2.subscribe(button -> {
            final String tex="Bears are mammals that belong to the family Ursidae. They can be as small as four feet long and about 60 pounds (the sun bear) to as big as eight feet long and more than a thousand pounds (the polar bear). They’re found throughout North America, South America, Europe, and Asia.";

                                resultText.setText(tex);
        });
        if(b3!=null)
            b3.subscribe(button -> {
                final String tex="1. Lions usually live in groups of 10 or 15 animals called prides."+

                "  2. An adult male’s roar can be heard up to 8km away."+

                "3. A female lion needs 5kg of meat a day. A male needs 7kg or more a day."+

               " 4. The name for a baby lion is a cub, whelp or lionet.";

                               resultText.setText(tex);
            });
        if(b4!=null)
            b4.subscribe(button -> {
                final String tex="Tigers are the largest cat species in the world and the third-largest carnivore on land--only polar and brown bears are larger."+
               " An adult Amur or Siberian tiger (the largest subspecies) can weigh up to 660 pounds."+
                       "The Sumatran tiger is the smallest, with males only weighing up to 310 pounds. Females generally weigh less than males in all subspecies."+
                        "Tigers are the only cat species that are completely striped. They even have stripes on their skin";
                resultText.setText(tex);
            });
        if(b5!=null)
            b5.subscribe(button -> {
                final String tex="1. Most leopards are light coloured and have dark spots on their fur. These spots are called “rosettes” because their shape is similar to that of a rose. There are also black leopards, too, whose spots are hard to see because their fur is so dark."+

                "2. Leopards can be found in various places around the world – they live in Sub-Saharan Africa, northeast Africa, Central Asia, India and China."+

                "3. Leopards are fast felines and can run at up to 58km/h! They’re super springy, too, and can leap 6m forward through the air – that’s the length of three adults lying head to toe!";
                resultText.setText(tex);
            });
        if(b6!=null)
            b6.subscribe(button -> {
                final String tex="Closest relatives of crocodiles are birds and dinosaurs."+
                "Crocodiles live on the Earth 240 million years. They appeared at the same time when dinosaurs appeared."+
                 "       Crocodiles vary in size. Largest crocodile species is Saltwater Crocodile which can reach 13-18 feet in length and weigh up to 2200 pounds. Smallest crocodile species is Dwarf Crocodile which can reach 5 feet in length and weigh up to 40-70 pounds."+
                  "      Crocodiles are meat-eaters (carnivores). They have 24 sharp teeth which are used for killing of fish, birds, mammals and small crocodile (their prey).";
                        resultText.setText(tex);
            });

    }
}
