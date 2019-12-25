package org.terasology.sample.nui;


import com.google.common.collect.Iterables;
import org.terasology.entitySystem.entity.EntityManager;
import org.terasology.registry.In;
import org.terasology.rendering.nui.CoreScreenLayer;



import org.terasology.rendering.nui.widgets.UIButton;

import org.terasology.rendering.nui.widgets.UIText;



public class AmountofEntities extends CoreScreenLayer {
    private UIText infoArea;
    private UIButton updateInfoButton;
    private Iterable iter;
    private int n;

    @In
    private EntityManager entityManager;

    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);
        updateInfoButton = find("updateInfoButton", UIButton.class);
        iter = entityManager.getAllEntities();
        n = Iterables.size(iter);




        if (updateInfoButton != null) {
            updateInfoButton.subscribe(button -> {
                infoArea.setText("Total Entities= "+Integer.toString(n));
            });
        }
    }

}