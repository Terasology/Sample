package org.terasology.nui;


import org.terasology.registry.In;
import org.terasology.rendering.nui.CoreScreenLayer;



import org.terasology.rendering.nui.widgets.UIButton;

import org.terasology.rendering.nui.widgets.UIText;
import org.terasology.world.generator.internal.WorldGeneratorInfo;


public class GameplayTemplateInfo extends CoreScreenLayer {
    private UIText infoArea;
    private UIButton updateInfoButton;
    public String title = "";

    @In
    private WorldGeneratorInfo worldgeneratorinfo;

    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);
        updateInfoButton = find("updateInfoButton", UIButton.class);
        title = worldgeneratorinfo.getDisplayName();





        if (updateInfoButton != null) {
            updateInfoButton.subscribe(button -> {
               infoArea.setText("The current template running is: "+title);
            });
        }
    }

}