package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.BasePageFunctions;

import java.util.HashMap;
import java.util.Map;

/**
 * this class represents the main calculator page
 *
 * @author Shlomi
 */

public class CalculatorPage extends BasePageFunctions {

    private final By calculateBTN = By.id("BtnCalc");
    private final By clearBTN = By.id("BtnClear");
    private final By calculatorElement = By.cssSelector("#display+div");
    private final By resultField = By.id("input");
    private final By historyBTN = By.cssSelector(".history");
    private final By historyResultElements = By.xpath("//p[@title and @class='r']");
    private final By historySequenceElements = By.xpath("//p[@title and @class='l']");
    private final Map<String, By> btns = new HashMap<>();

    // constructor
    public CalculatorPage(WebDriver driver) {
        super(driver);
    }

    // navigate to the site
    public Boolean getWebSite(String site) {
        return navigateToURL(site);
    }

    // get the current URL
    public Boolean validateSiteLoaded() {
        return waitForElementToBeVisible(calculatorElement);
    }

    // clear field
    public Boolean clearCalculator() {
        return clickOnElement(clearBTN);
    }

    // build the calculator buttons
    public void buildMap() {
        btns.put("0", By.id("Btn0"));
        btns.put("1", By.id("Btn1"));
        btns.put("2", By.id("Btn2"));
        btns.put("3", By.id("Btn3"));
        btns.put("4", By.id("Btn4"));
        btns.put("5", By.id("Btn5"));
        btns.put("6", By.id("Btn6"));
        btns.put("7", By.id("Btn7"));
        btns.put("8", By.id("Btn8"));
        btns.put("9", By.id("Btn9"));

        btns.put(".", By.id("BtnDot"));
        btns.put("+", By.id("BtnPlus"));
        btns.put("-", By.id("BtnMinus"));
        btns.put("*", By.id("BtnMult"));
        btns.put("/", By.id("BtnDiv"));
        btns.put("c", By.id("BtnClear"));
        btns.put("(", By.id("BtnParanL"));
        btns.put(")", By.id("BtnParanR"));
        btns.put(",", By.id("BtnColon"));
        btns.put("^", By.id("BtnPowXY"));
        btns.put("%", By.id("BtnPer"));

        btns.put("sin", By.id("BtnSin"));
        btns.put("sinh", By.id("BtnSinH"));
        btns.put("cot", By.id("BtnSin"));
        btns.put("cos", By.id("BtnCos"));
        btns.put("cosh", By.id("BtnCosH"));
        btns.put("sec", By.id("BtnSec"));
        btns.put("tan", By.id("BtnTan"));
        btns.put("tanh", By.id("BtnTanH"));
        btns.put("csc", By.id("BtnCsc"));
        btns.put("ncr", By.id("BtnNcR"));
        btns.put("npr", By.id("BtnNpR"));
    }

    // parse characters in exercise to click on char
    public void parseAndClick(String exercise) {
        Character[] chars = exercise.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
        for (int i = 0; i < chars.length; i++) {
            Character c = chars[i];
            if (Character.isLetter(c)) {
                StringBuilder action = new StringBuilder();
                while (Character.isLetter(chars[i])) {
                    action.append(chars[i]);
                    i++;
                }
                // Here we actually incremented the index to the next position, so we had to decrease it, but since calculator automatically adds '(' sign,
                // we don't decrease our index in order to skip the '(' sign in our input string
                waitForElementToBeClickableAndClickIt(btns.get(action.toString()));
            } else {
                waitForElementToBeClickableAndClickIt(btns.get(String.valueOf(c)));
            }

        }
    }

    // get result from result field
    public String performCalculationAndGetResult() {
        waitForElementToBeVisible(resultField);
        String initialResult = getElementAttribute(resultField, "value");
        clickOnElement(calculateBTN);
        waitForElementAttributeToChange(resultField, "value", initialResult);
        return getElementAttribute(resultField, "value").trim();
    }

    // open history list
    public Boolean openHistoryList() {
        return clickOnElement(historyBTN);
    }

    // get history result
    public String getHistorySequence() {
        return getElementAttribute(historySequenceElements, "title");
    }

    // get history calculation
    public String getHistoryResult() {
        return getElementAttribute(historyResultElements, "title");
    }


}
