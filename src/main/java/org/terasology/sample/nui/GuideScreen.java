package org.terasology.sample.nui;

//import org.terasology.engine.Time;
//import org.terasology.registry.In;
import org.terasology.rendering.nui.CoreScreenLayer;
import org.terasology.rendering.nui.widgets.UIButton;
import org.terasology.rendering.nui.widgets.UIText;

public class GuideScreen extends CoreScreenLayer {
    private UIText infoArea;
    private UIButton receiveGuide;


    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);
        receiveGuide = find("click me", UIButton.class);

        if (receiveGuide != null) {
            receiveGuide.subscribe(button -> {
                /*final double bytesInMegabyte = 1048576.0;
                double memoryUsage = ((double) Runtime.getRuntime().totalMemory() - (double) Runtime.getRuntime().freeMemory()) / bytesInMegabyte;
                infoArea.setText(String.format("Welcome to the environment info screen!%n" +
                                "The current world has been active for %.0f (in-game) seconds.%n" +
                                "Currently running at %.2f FPS and using %.2f MB of memory out of %.2f available.%n" +
                                "Please enjoy the game!!!",
                        time.getGameTime(), time.getFps(),
                        memoryUsage, Runtime.getRuntime().maxMemory() / bytesInMegabyte));*/
                infoArea.setText("Welcome to Terasology! You are officially stuck in this dimension.\n" +
                            "Use your brains to break down code and make a module.\n" +
                        "Use the modules to craft code into useful and interactive gameplay!");
            });
        }
    }
}