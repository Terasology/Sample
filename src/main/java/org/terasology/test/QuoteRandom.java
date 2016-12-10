package org.terasology.test;

import org.terasology.engine.Time;
import org.terasology.registry.In;
import org.terasology.rendering.nui.CoreScreenLayer;
import org.terasology.rendering.nui.UIWidget;
import org.terasology.rendering.nui.widgets.UIButton;
import org.terasology.rendering.nui.widgets.UILabel;

public class QuoteRandom extends CoreScreenLayer {
    private UILabel infoArea;
    private UIButton updateInfoButton;

    @In
    private Time time;

    @Override
    public void initialise() {
        infoArea = find("quoteRandomBody", UILabel.class);
        updateInfoButton = find("updateInfoButton", UIButton.class);

        if (updateInfoButton != null) {
            updateInfoButton.subscribe(this::onClickingQuoteButton);
        }
    }

    private void onClickingQuoteButton(UIWidget uiWidget) {
        //getManager().popScreen();
        infoArea.setText(infoArea.getText() + " !! ");
    }
}