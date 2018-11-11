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

import org.terasology.rendering.nui.CoreScreenLayer;
import org.terasology.math.TeraMath;
import org.terasology.rendering.nui.widgets.UIButton;
import org.terasology.rendering.nui.widgets.UIText;

import java.util.Random;

public class LuckTestScreen extends CoreScreenLayer {

    private final static int START_TOKENS = 500;
    private final static int LOAN_TOKENS = 250;
    private final static int STARTING_WAGER = 10;
    private final static float BASE_CHANCE = 0.8f;
    private final static int HALF_CHANCE_TOKENS = 10000; // tokens required to decrease win chance by half.

    private final static int WAGER_STATE = 0;
    private final static int GAME_STATE = 1;
    private final static int WIN_STATE = 2;
    private final static int LOSE_STATE = 3;
    private final static int PAYOUT_STATE = 4;
    private final static int BANKRUPT_STATE = 5;
    private final static int TUTORIAL_STATE = 6;

    private int tokens;
    private int wager;
    private float chance;
    private int gameState;

    private UIText infoArea;
    private UIButton topButton;
    private UIButton bottomButton;

    private Random random;

    @Override
    public void initialise() {
        infoArea = find("infoArea", UIText.class);
        topButton = find("topButton", UIButton.class);
        bottomButton = find("bottomButton", UIButton.class);

        gameState = TUTORIAL_STATE;
        tokens = START_TOKENS;
        wager = STARTING_WAGER;
        chance = BASE_CHANCE;

        random = new Random();

        displayTutorialText();

        if (topButton != null) {
            topButton.subscribe(button -> {
                switch (gameState) {
                    case WAGER_STATE:
                        if(wager >= tokens) {
                            wager = STARTING_WAGER;
                        } else {
                            wager = TeraMath.clamp(wager * 2, wager, tokens);
                        }

                        displayWagerText();
                        break;
                    case GAME_STATE:
                        calculateChance();
                        if(random.nextFloat() < chance) {
                            wager *= 2;
                            gameState = WIN_STATE;

                            displayWinText();
                        } else {
                            gameState = LOSE_STATE;

                            displayLoseText();
                        }
                        break;
                    case WIN_STATE:
                        gameState = GAME_STATE;
                        displayGameText();

                        break;
                    case LOSE_STATE:
                        if(tokens <= 0) {
                            tokens = LOAN_TOKENS;
                            gameState = BANKRUPT_STATE;

                            displayBankruptText();
                        } else {
                            wager = STARTING_WAGER;
                            gameState = WAGER_STATE;

                            displayWagerText();
                        }

                        break;
                    case PAYOUT_STATE:
                    case BANKRUPT_STATE:
                    case TUTORIAL_STATE:
                        wager = STARTING_WAGER;
                        gameState = WAGER_STATE;

                        displayWagerText();

                        break;
                }
            });
        }

        if (bottomButton != null) {
            bottomButton.subscribe(button -> {
                switch (gameState) {
                    case WAGER_STATE:
                        tokens -= wager;
                        gameState = GAME_STATE;

                        displayGameText();
                        break;
                    case GAME_STATE:
                        tokens += wager;
                        gameState = PAYOUT_STATE;

                        displayPayoutText();
                        break;
                    case WIN_STATE:
                    case LOSE_STATE:
                    case PAYOUT_STATE:
                    case BANKRUPT_STATE:
                        break;
                }
            });
        }
    }

    private void calculateChance() {
        chance = BASE_CHANCE * (((((float) wager / HALF_CHANCE_TOKENS) + 2) / (((float) wager / HALF_CHANCE_TOKENS) + 1)) - 1);
    }

    private void displayWagerText() {
        infoArea.setText(String.format("What do you want to bet? %n" +
                        "You will bet %d GooeyTokens." +
                        "You currently have %d GooeyTokens. %n",
                wager, tokens));
        setButtonText("Change Wager", "Use Wager");
    }

    private void displayGameText() {
        infoArea.setText(String.format("Are you feeling lucky? %n" +
                        "Your wager is currently %d GooeyTokens.",

                        wager));
        setButtonText("I'm Feeling Lucky", "Take Back Wager");
    }

    private void displayWinText() {
        infoArea.setText(String.format("You have won the draw! %n" +
                        "Your wager has increased to %d GooeyTokens!",
                        wager));
        setButtonText();
    }

    private void displayLoseText() {
        infoArea.setText(String.format("You have lost the draw... %n" +
                        "Your wager of %d GooeyTokens has been lost.",
                wager, chance));
        setButtonText();
    }

    private void displayPayoutText() {
        infoArea.setText(String.format("You have won %d GooeyTokens! %n" +
                        "You now have a total of %d GooeyTokens.",
                wager, tokens));
        setButtonText();
    }

    private void displayBankruptText() {
        infoArea.setText(String.format("You are out of GooeyTokens! %n" +
                        "You have been given a loan of %d GooeyTokens, for being such a good sport.",
                tokens));
        setButtonText();
    }

    private void displayTutorialText() {
        infoArea.setText(String.format("Welcome to the Gooey Arcade! %n" +
                        "You have been given %d GooeyTokens to start off. %n" +
                        "Want more? Then you will need to try your luck a little! %n" +
                        "Select your wager, and see if the odds are in your favour... %n" +
                        "Win and double your wager, otherwise you lose it all!",
                tokens));
        setButtonText();
    }

    private void setButtonText() {
        setButtonText("Continue", "");
    }

    private void setButtonText(String topText, String bottomText) {
        topButton.setText(topText);
        bottomButton.setText(bottomText);
    }
}
