
package org.terasology.nui;

import org.terasology.rendering.nui.CoreScreenLayer;
import org.terasology.rendering.nui.widgets.UIButton;
import org.terasology.rendering.nui.widgets.UIText;

public class UsefulCommandsScreen extends CoreScreenLayer {
    private UIText infoArea;
    private UIButton nextCommandButton;
    
    private String[] commands = {"hspeed", "setworldtime <time>", "ghost", "setJumpSpeed <amount>", "setSpawnLocation", "setSpeedMultiplier <amount>", "setMaxHealth <health>", "teleport <x> <y> <z>", "give <blockName> <amount>"};
    private String[] descriptions = {"Go really fast", "Sets the current time for the local player in days (0.5 = midday)",
                "Grants flight and movement through walls", "Set jump speed", "Sets the spawn location of the user to the current location",
                "Set speed multiplier", "Set maximum health of the user.", "Teleports the player to a location (x,y,z)", "Adds an item or block to the player's inventory."};

    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);
        nextCommandButton = find("nextCommandButton", UIButton.class);
        
        if (nextCommandButton != null) {
            nextCommandButton.subscribe(button -> {
                int random = (int) ((Math.random() * commands.length));
                infoArea.setText(String.format("%1$s%n%2$s",
                        commands[random], descriptions[random]));
            });
        }
    }
}
