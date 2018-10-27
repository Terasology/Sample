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

import com.google.common.collect.ImmutableList;
import org.terasology.engine.Time;
import org.terasology.entitySystem.entity.EntityRef;
import org.terasology.logic.console.Console;
import org.terasology.logic.console.commandSystem.CommandParameter;
import org.terasology.logic.console.commandSystem.ConsoleCommand;
import org.terasology.logic.console.commandSystem.annotations.Sender;
import org.terasology.logic.console.commandSystem.exceptions.CommandExecutionException;
import org.terasology.logic.console.commandSystem.exceptions.CommandSuggestionException;
import org.terasology.logic.players.LocalPlayer;
import org.terasology.naming.Name;
import org.terasology.registry.In;
import org.terasology.rendering.nui.CoreScreenLayer;
import org.terasology.rendering.nui.widgets.UIButton;
import org.terasology.rendering.nui.widgets.UIDropdown;
import org.terasology.rendering.nui.widgets.UIText;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Timer;


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
        freeMemory = Runtime.getRuntime().freeMemory()/bytesInMB;
        memoryUsage = Runtime.getRuntime().totalMemory()/bytesInMB - freeMemory;

        if(updateButton != null)
        {
            updateButton.subscribe(button -> {
                freeMemory = Runtime.getRuntime().freeMemory()/bytesInMB;
                memoryUsage = Runtime.getRuntime().totalMemory()/bytesInMB - freeMemory;
                info.setText("Time Passed: " + time.getGameTime() + "s\n" +
                                "FPS: " + Math.round(time.getFps() * 100)/100.0 + "\n\n\n\n\n\n" +
                                "Memory Usage: " + Math.round(memoryUsage * 100)/100.0 + " MB\n" +
                                "Available Memory: " + Math.round(freeMemory * 100)/100.0 + " MB\n" +
                                "Made by Torpedo");
            });
        }
    }

    @Override
    public void update(float delta) {
        info.setText("Time Spent in Game: " + time.getGameTime() + "s\n" +
                "Memory Usage: " + Math.round(memoryUsage * 100)/100.0 + " MB\n" +
                "Available Memory: " + Math.round(freeMemory * 100)/100.0 + " MB\n" +
                "FPS: " + Math.round(time.getFps() * 100)/100.0 + "\n\n\n\n\n\n" +
                "Made by Torpedo");
        super.update(delta);
    }
}