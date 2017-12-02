/*
 * Copyright 2017 MovingBlocks
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terasology.nui;

import org.terasology.engine.Time;
import org.terasology.engine.subsystem.headless.device.TimeSystem;
import org.terasology.registry.In;
import org.terasology.rendering.nui.CoreScreenLayer;
import org.terasology.rendering.nui.widgets.UIButton;
import org.terasology.rendering.nui.widgets.UIText;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class TimeScreen extends CoreScreenLayer {
    private UIText infoArea;
    private UIButton updateInfoButton;

    @In
    private Time time;


    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);
        updateInfoButton = find("updateInfoButton", UIButton.class);
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(System.currentTimeMillis());

        String date = c.get(Calendar.YEAR)+"-"+c.get(Calendar.MONTH)+"-"+c.get(Calendar.DAY_OF_MONTH);
        String time = c.get(Calendar.HOUR_OF_DAY)+":"+c.get(Calendar.MINUTE)+":"+c.get(Calendar.SECOND);




        infoArea.setText("Real Time: "+date + " " + time+"\nPress ESC to exit");

        if (updateInfoButton != null) {
            updateInfoButton.subscribe(button -> {
                Calendar d = Calendar.getInstance();
                d.setTimeInMillis(System.currentTimeMillis());

                String date2 = d.get(Calendar.YEAR)+"-"+d.get(Calendar.MONTH)+"-"+d.get(Calendar.DAY_OF_MONTH);
                String time2 = d.get(Calendar.HOUR_OF_DAY)+":"+d.get(Calendar.MINUTE)+":"+d.get(Calendar.SECOND);




                infoArea.setText("Real Time: "+date2 + " " + time2+"\nPress ESC to exit");
            });
        }
    }
}
