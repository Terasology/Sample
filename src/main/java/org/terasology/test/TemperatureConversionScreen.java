package org.terasology.tutorialnui.widgets;


import org.terasology.registry.In;
import org.terasology.rendering.nui.CoreScreenLayer;
import org.terasology.rendering.nui.widgets.UIText;
import org.terasology.rendering.nui.widgets.UISlider;
import org.terasology.rendering.nui.widgets.UIDropdown;
import org.terasology.engine.Time;
import java.util.ArrayList;

public class TemperatureConversionScreen extends CoreScreenLayer {
	private UIText answerLabel;
	private UISlider temperatureChooser;
	private UIDropdown<String> conversionChooser;

	ArrayList<String> conversionList = new ArrayList<String>();

	@In
	private Time time;
	private final long TIME_COMPARE = time.getRealTimeInMs();


	@Override
	public void initialise() {
		answerLabel = find("answerLabel", UIText.class);
		conversionChooser = find("conversionChooser", UIDropdown.class);
		temperatureChooser = find("temperatureChooser", UISlider.class);
		conversionList.add("Fahrenheit to Celsius");
		conversionList.add("Celsius to Fahrenheit");
		conversionChooser.setOptions(conversionList);
		//Makeshift change listener
		while (TIME_COMPARE != time.getRealTimeInMs())
		{
			updateValue();
		}
	}

	public void updateValue()
	{
		if (conversionChooser.getSelection() == conversionList.get(0)) //Fahrenehit to Celsius
		{
			double temp = temperatureChooser.getValue();
			temp = (temp-32.0) * (5.0/9.0);
			answerLabel.setText("The temperature in celsius is " + ((Double)temp).toString().substring(0, 5));
		}
		else //Celsius to Fahrenheit
		{
			double temp = temperatureChooser.getValue();
			temp = (temp * (9.0/5.0)) + 32.0;
			answerLabel.setText("The temperature in fahrenheit is " + ((Double)temp).toString().substring(0, 5));
		}
	}

}
