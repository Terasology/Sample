package org.terasology.test;

import com.google.common.collect.Lists;
import org.terasology.rendering.nui.CoreScreenLayer;
import org.terasology.rendering.nui.UIWidget;
import org.terasology.rendering.nui.widgets.UIButton;
import org.terasology.rendering.nui.widgets.UILabel;

import java.util.Collections;
import java.util.List;

public class QuoteRandom extends CoreScreenLayer {
    private UILabel infoArea;
    private UIButton updateInfoButton;

    private List<String> quoteList = Lists.newArrayList("a", "b", "c");

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
        Collections.shuffle(quoteList);
        infoArea.setText(infoArea.getText() + " !! ");
        infoArea.setText(quoteList.get(0));
    }
}