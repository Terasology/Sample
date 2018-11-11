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

/**
 * An NUI screen featuring a simple casino-like game.
 */
public class LuckTestScreen extends CoreScreenLayer {

    /**
     * Amount of tokens that the player starts the game with.
     */
    private final static int START_TOKENS = 500;

    /**
     * Amount of tokens that the player will receive in the case of bankruptcy.
     */
    private final static int LOAN_TOKENS = 250;

    /**
     * The starting wager on the wager selection screen.
     */
    private final static int STARTING_WAGER = 10;

    /**
     * The starting chance that the player will win, where 1f is 100% and 0f is 0%
     */
    private final static float BASE_CHANCE = 0.8f;

    /**
     * The tokens in a wager required to decrease the base chance by half.
     */
    private final static int HALF_CHANCE_TOKENS = 10000;

    // State machine values
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
                    case WAGER_STATE: // wager selection change.
                        if (wager >= tokens) { // cycle back from maximum wager to minimum.
                            wager = STARTING_WAGER;
                        } else {
                            wager = TeraMath.clamp(wager * 2, wager, tokens); // wager cannot be more than player's tokens.
                        }

                        displayWagerText();
                        break;
                    case GAME_STATE:
                        calculateChance();
                        if (random.nextFloat() < chance) {
                            wager *= 2; // double wager on win
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
                        if (tokens <= 0) { // if all tokens are gone
                            tokens = LOAN_TOKENS; // give the 'loan'.
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
                    case TUTORIAL_STATE: // all three cases result in a return to the wager state.
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
                        tokens -= wager; // take away wager from player tokens.
                        gameState = GAME_STATE;

                        displayGameText();
                        break;
                    case GAME_STATE:
                        tokens += wager; // add the wager back to the player tokens.
                        gameState = PAYOUT_STATE;

                        displayPayoutText();
                        break;
                    case WIN_STATE:
                    case LOSE_STATE:
                    case PAYOUT_STATE:
                    case BANKRUPT_STATE:
                        break; // in all four states, the bottom button is blank.
                }
            });
        }
    }

    /**
     * Calculates the win chance based on the player's current wager.
     */
    private void calculateChance() {
        // (x+2)/(x+1) - 1, chance will never be zero but will get close.
        chance = BASE_CHANCE * (((((float) wager / HALF_CHANCE_TOKENS) + 2) / (((float) wager / HALF_CHANCE_TOKENS) + 1)) - 1);
    }

    /**
     * Sets the button/label display text for the wager selection state.
     */
    private void displayWagerText() {
        infoArea.setText(String.format("What do you want to bet? %n" +
                        "You will bet %d GooeyTokens." +
                        "You currently have %d GooeyTokens. %n",
                wager, tokens));
        setButtonText("Change Wager", "Use Wager");
    }

    /**
     * Sets the button/label display text for the main game state.
     */
    private void displayGameText() {
        infoArea.setText(String.format("Are you feeling lucky? %n" +
                        "Your wager is currently %d GooeyTokens.",

                        wager));
        setButtonText("I'm Feeling Lucky", "Take Back Wager");
    }

    /**
     * Sets the button/label display text for the wager win state.
     */
    private void displayWinText() {
        infoArea.setText(String.format("You have won the draw! %n" +
                        "Your wager has increased to %d GooeyTokens!",
                        wager));
        setButtonText();
    }

    /**
     * Sets the button/label display text for the wager lose state.
     */
    private void displayLoseText() {
        infoArea.setText(String.format("You have lost the draw... %n" +
                        "Your wager of %d GooeyTokens has been lost.",
                wager, chance));
        setButtonText();
    }

    /**
     * Sets the button/label display text for the payout state.
     */
    private void displayPayoutText() {
        infoArea.setText(String.format("You have won %d GooeyTokens! %n" +
                        "You now have a total of %d GooeyTokens.",
                wager, tokens));
        setButtonText();
    }

    /**
     * Sets the button/label display text for the bankruptcy state.
     */
    private void displayBankruptText() {
        infoArea.setText(String.format("You are out of GooeyTokens! %n" +
                        "You have been given a loan of %d GooeyTokens, for being such a good sport.",
                tokens));
        setButtonText();
    }

    /**
     * Sets the button/label display text for the tutorial state.
     */
    private void displayTutorialText() {
        infoArea.setText(String.format("Welcome to the Gooey Arcade! %n" +
                        "You have been given %d GooeyTokens to start off. %n" +
                        "Want more? Then you will need to try your luck a little! %n" +
                        "Select your wager, and see if the odds are in your favour... %n" +
                        "Win and double your wager, otherwise you lose it all!",
                tokens));
        setButtonText();
    }

    /**
     * Sets the text on both buttons to the single continue state.
     */
    private void setButtonText() {
        setButtonText("Continue", "");
    }

    /**
     * Sets the text on both buttons to provided strings.
     *
     * @param topText The string to be displayed on the top button.
     * @param bottomText The string to be displayed on the bottom button.
     */
    private void setButtonText(String topText, String bottomText) {
        topButton.setText(topText);
        bottomButton.setText(bottomText);
    }
}
