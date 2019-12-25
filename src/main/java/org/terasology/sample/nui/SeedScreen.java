package org.terasology.nui;

import org.terasology.registry.In;
import org.terasology.rendering.nui.CoreScreenLayer;
import org.terasology.rendering.nui.widgets.UIButton;
import org.terasology.rendering.nui.widgets.UIText;
import org.terasology.world.WorldProvider;

public class SeedScreen extends CoreScreenLayer {
    private UIText seedText;
    @In
    private WorldProvider provider;
    @Override
    public void initialise() {
        seedText = find("seedText", UIText.class);
        UIButton seedUpdateButton = find("seedUpdateButton", UIButton.class);
        if (seedText != null && seedUpdateButton != null) {
            seedUpdateButton.subscribe(button -> {
                seedText.setText("The seed of this world is : " + provider.getSeed());
            });
        }
    }
}
