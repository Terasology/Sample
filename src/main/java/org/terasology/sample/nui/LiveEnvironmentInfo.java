// Copyright 2020 The Terasology Foundation
// SPDX-License-Identifier: Apache-2.0
package org.terasology.sample.nui;

import org.terasology.engine.core.Time;
import org.terasology.engine.logic.players.LocalPlayer;
import org.terasology.engine.registry.In;
import org.terasology.engine.rendering.nui.CoreScreenLayer;
import org.terasology.nui.widgets.UIButton;
import org.terasology.nui.widgets.UIDropdown;
import org.terasology.nui.widgets.UIText;


public class LiveEnvironmentInfo extends CoreScreenLayer {
    final double bytesInMB = 1048576;
    double freeMemory;
    double memoryUsage;
    private UIText info;
    private UIButton updateButton;
    private UIDropdown<String> dropdown;
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
