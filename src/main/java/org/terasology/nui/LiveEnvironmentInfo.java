/*
 * Copyright 2018 MovingBlocks
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
import org.terasology.logic.players.LocalPlayer;
import org.terasology.nui.widgets.UIButton;
import org.terasology.nui.widgets.UIDropdown;
import org.terasology.nui.widgets.UIText;
import org.terasology.registry.In;
import org.terasology.rendering.nui.CoreScreenLayer;


public class LiveEnvironmentInfo extends CoreScreenLayer {
    private UIText info;
    private UIButton updateButton;
    private UIDropdown<String> dropdown;
    final double bytesInMB = 1048576;
    double freeMemory;
    double memoryUsage;

    @In
    private Time time;

    @In
    private LocalPlayer localPlayer;

    @Override
    public void initialise() {
        info = find("infoArea", UIText.class);
        updateButton = find("updateInfoButton", UIButton.class);
        freeMemory = Runtime.getRuntime().freeMemory() / bytesInMB;
        memoryUsage = Runtime.getRuntime().totalMemory() / bytesInMB - freeMemory;

        if (updateButton != null) {
            updateButton.subscribe(button -> {
                freeMemory = Runtime.getRuntime().freeMemory() / bytesInMB;
                memoryUsage = Runtime.getRuntime().totalMemory() / bytesInMB - freeMemory;
                info.setText("Time Passed: " + convertTime(time.getGameTime()) + "\n" +
                    "FPS: " + Math.round(time.getFps() * 100) / 100.0 + "\n\n\n\n\n\n" +
                    "Memory Usage: " + Math.round(memoryUsage * 100) / 100.0 + " MB\n" +
                    "Available Memory: " + Math.round(freeMemory * 100) / 100.0 + " MB\n" +
                    "Made by Torpedo");
            });
        }
    }

    @Override
    public void update(float delta) {
        info.setText("Time Spent in Game: " + convertTime(time.getGameTime()) + "\n" +
            "Memory Usage: " + Math.round(memoryUsage * 100) / 100.0 + " MB\n" +
            "Available Memory: " + Math.round(freeMemory * 100) / 100.0 + " MB\n" +
            "FPS: " + Math.round(time.getFps() * 100) / 100.0 + "\n\n\n\n\n\n" +
            "Made by Torpedo");
        super.update(delta);
    }

    public String convertTime(double d) {
        int seconds = (int) d;
        String time = seconds / 3600 + " hrs " + ((seconds % 3600) / 60) + " mins " + (seconds % 60) + " s";
        return time;

    }

    public String convertTime(float d) {
        int seconds = (int) d;
        String time = seconds / 3600 + " hrs " + ((seconds % 3600) / 60) + " mins " + (seconds % 60) + " s";
        return time;

    }
}
