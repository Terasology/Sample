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
package org.terasology.sample.nui;

import org.terasology.nui.widgets.UIButton;
import org.terasology.nui.widgets.UIText;
import org.terasology.rendering.nui.CoreScreenLayer;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Queue;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;

public class CalculatorScreen extends CoreScreenLayer {
    private UIText resultText;
    private boolean flagError;

    @Override
    public void initialise() {
        resultText = find("calculationOutput", UIText.class);
        if (resultText != null) {
            resultText.setText("");
        }

        for (int buttonNo = 0; buttonNo <= 9; buttonNo++) {
            String buttonId = "button" + Integer.toString(buttonNo);
            UIButton numberButton = find(buttonId, UIButton.class);
            final int outputNumber = buttonNo;
            if (numberButton != null) {
                numberButton.subscribe(button -> {
                    resultText.setText(resultText.getText() + Integer.toString(outputNumber));
                });
            }
        }

        for (Operator operator : Operator.values()) {
            if (operator != Operator.None) {
                UIButton operatorButton = find("button" + operator.name(), UIButton.class);
                if (operatorButton != null) {
                    operatorButton.subscribe(button -> {
                        resultText.setText(resultText.getText() + operator.toString());
                    });
                }
            }
        }

        UIButton dotButton = find("buttonDot", UIButton.class);
        if (dotButton != null) {
            dotButton.subscribe(button -> {
                resultText.setText(resultText.getText() + ".");
            });
        }

        UIButton equalsButton = find("buttonEquals", UIButton.class);
        if (equalsButton != null) {
            equalsButton.subscribe(button -> {
                try {
                    resultText.setText(Double.toString(calculate(resultText.getText())));
                } catch (Exception e) {
                    e.printStackTrace();
                    flagError = true;
                }

                if (flagError) {
                    resultText.setText("ERROR");
                    flagError = false;
                }
            });
        }

        UIButton clearButton = find("buttonClear", UIButton.class);
        if (clearButton != null) {
            clearButton.subscribe(button -> {
                resultText.setText("");
            });
        }

        UIButton backButton = find("buttonBack", UIButton.class);
        if (backButton != null) {
            backButton.subscribe(button -> {
                String currentText = resultText.getText();
                if (currentText.length() > 0) {
                    resultText.setText(currentText.substring(0, currentText.length() - 1));
                }
            });
        }
    }

    //Implements a shunting-yard algorithm(see https://en.wikipedia.org/wiki/Shunting-yard_algorithm)
    private Double calculate(String calculation) {
        Stack<Operator> operators = new Stack<Operator>();
        Queue<String> output = new ArrayBlockingQueue<String>(2048);

        Map<Operator, Integer> operatorPriority = new HashMap<Operator, Integer>();
        operatorPriority.put(Operator.LeftBracket, 0);
        operatorPriority.put(Operator.RightBracket, 0);
        operatorPriority.put(Operator.Add, 1);
        operatorPriority.put(Operator.Subtract, 1);
        operatorPriority.put(Operator.Multiply, 2);
        operatorPriority.put(Operator.Divide, 2);

        String[] tokens = calculation.replace(" ", "").split("(?<=[+|\\-|*|/|(|)])|(?=[+|\\-|*|/|(|)])");

        if (tokens.length > 1 && (tokens[0].equals("+") || tokens[0].equals("-"))) {
            ArrayList<String> temp = new ArrayList<String>(Arrays.asList(tokens));
            temp.add(0, "0");
            tokens = temp.toArray(tokens);
        }

        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];

            try {
                double tokenOperand = Double.parseDouble(token);
                output.add(token);
            } catch (NumberFormatException ignore) {
            }

            Operator tokenOperator = getOperator(token);
            if (tokenOperator != Operator.None) {
                if (tokenOperator == Operator.LeftBracket) {
                    operators.push(tokenOperator);
                    continue;
                }

                if (tokenOperator == Operator.RightBracket) {
                    while (true) {
                        if (operators.size() == 0) {
                            //ERROR: There is not opening bracket.
                            return 0d;
                        }

                        Operator operator = operators.pop();
                        if (operator == Operator.LeftBracket) {
                            break;
                        } else {
                            output.add(operator.toString());
                        }
                    }
                    continue;
                }

                while (operators.size() != 0 && operatorPriority.get(operators.peek()) >= operatorPriority.get(tokenOperator) && operators.peek() != Operator.LeftBracket) {
                    output.add(operators.pop().toString());
                }
                operators.push(tokenOperator);
            }
        }

        while (operators.size() != 0) {
            output.add(operators.pop().toString());
        }

        Stack<Double> evalStack = new Stack<Double>();

        for (String token : output) {
            Operator tokenOperator = getOperator(token);
            if (tokenOperator != Operator.None) {
                double operand2 = evalStack.pop();
                double operand1 = evalStack.pop();
                switch (tokenOperator) {
                    case Add:
                        operand1 += operand2;
                        break;
                    case Subtract:
                        operand1 -= operand2;
                        break;
                    case Multiply:
                        operand1 *= operand2;
                        break;
                    case Divide:
                        operand1 /= operand2;
                        break;
                }
                evalStack.push(operand1);
                continue;
            }

            try {
                double tokenOperand = Double.parseDouble(token);
                evalStack.push(tokenOperand);
            } catch (NumberFormatException ignore) {
            }
        }
        return evalStack.pop();
    }

    private Operator getOperator(String value) {
        switch (value) {
            case "*":
                return Operator.Multiply;
            case "/":
                return Operator.Divide;
            case "+":
                return Operator.Add;
            case "-":
                return Operator.Subtract;
            case "(":
                return Operator.LeftBracket;
            case ")":
                return Operator.RightBracket;
            default:
                return Operator.None;
        }
    }

    private enum Operator {
        Add,
        Subtract,
        Multiply,
        Divide,
        LeftBracket,
        RightBracket,
        None;

        @Override
        public String toString() {
            switch (this) {
                case Multiply:
                    return "*";
                case Divide:
                    return "/";
                case Add:
                    return "+";
                case Subtract:
                    return "-";
                case LeftBracket:
                    return "(";
                case RightBracket:
                    return ")";
                default:
                    return "";
            }
        }
    }
}
