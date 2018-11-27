package org.terasology.tutorialnui.widgets;

import java.util.List;

//*****************************************
// Nate Schnitzer
// TemperatureConversionScreen.java
// 11/25/18
// GCI Task
//*****************************************

import org.terasology.registry.In;
import org.terasology.rendering.nui.CoreScreenLayer;
import org.terasology.rendering.nui.widgets.UIButton;
import org.terasology.rendering.nui.widgets.UIText;
import org.terasology.rendering.nui.widgets.UISlider;
import org.terasology.rendering.nui.widgets.UIDropdown;
import org.terasology.engine.Time;
import java.util.ArrayList;

public class TemperatureConversionScreen extends CoreScreenLayer {
    private UIText answerLabel;
    private UISlider temperatureChooser;
    private UIDropdown<String> conversionChooser;
    private UIButton updateTempButton;
    
    @In
    private Time time;
    
    @Override
    public void initialise() {
        answerLabel = find("answerLabel", UIText.class);
        conversionChooser = find("conversionChooser", UIDropdown.class);
        updateTempButton = find("updateTempButton", UIButton.class);
        temperatureChooser = find("temperatureChooser", UISlider.class);
        ArrayList<String> conversionList = new ArrayList<String>();
        conversionList.add("Fahrenheit to Celsius");
        conversionList.add("Celsius to Fahrenheit");
        conversionChooser.setOptions(conversionList);
        
        if (updateTempButton != null) {
            updateTempButton.subscribe(button -> {
                if (conversionChooser.getSelection() == conversionList.get(0)) //Fahrenehit to Celsius
                {
                    double temp = temperatureChooser.getValue();
                    temp = (temp-32.0) * (5.0/9.0);
                    answerLabel.setText("The temperature in celsius is " + temp);
                }
                else //Celsius to Fahrenheit
                {
                    double temp = temperatureChooser.getValue();
                    temp = (temp * (9.0/5.0)) + 32.0;
                    answerLabel.setText("The temperature in fahrenheit is " + temp + " degrees.");
                }
            });
        }
    }

}