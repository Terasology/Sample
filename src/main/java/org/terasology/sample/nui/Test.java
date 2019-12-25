package org.terasology.helloworld;

import org.terasology.rendering.nui.CoreScreenLayer;
import org.terasology.rendering.nui.widgets.UIButton;
import org.terasology.rendering.nui.widgets.UIText;

public class Test extends CoreScreenLayer{
    private UIText infoArea;
    private UIButton button;

    @Override
    public void initialise(){
    	infoArea = find("infoArea", UIText.class);
        button = find("button", UIButton.class);
        

        if (button != null) {
        	button.subscribe(button -> {
            	infoArea.setText(String.format("[TODO] Hello World! \n This is the first module made by: \n Nickname: Koke_Cacao \n Google Code-in HankeChen"));
            });
        }
    }
}
