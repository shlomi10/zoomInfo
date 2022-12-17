package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.BasePageFunctions;

/**
 * this class represents the main calculator page
 *
 * @author Shlomi
 */

public class CalculatorPage extends BasePageFunctions {

    private final By dotBTN = By.id("BtnDot");
    private final By additionBTN = By.id("BtnPlus");
    private final By calculateBTN = By.id("BtnCalc");
    private final By subtractionBTN = By.id("BtnMinus");
    private By btn;
    private final By multiplicationBTN = By.id("BtnMult");
    private final By divisionBTN = By.id("BtnDiv");
    private final By clearBTN = By.id("BtnClear");
    private final By leftParenthesis = By.id("BtnParanL");
    private final By colonBTN = By.id("BtnColon");
    private final By rightParenthesis = By.id("BtnParanR");
    private final By sinusBTN = By.id("BtnSin");
    private final By sinhBTN = By.id("BtnSinH");
    private final By percentBTN = By.id("BtnPer");
    private final By powerBTN = By.id("BtnPowXY");
    private final By calculatorElement = By.cssSelector("#display+div");

    private final By resultField = By.id("input");
    private final By historyBTN = By.cssSelector(".history");
    private final By historyResultElements = By.xpath("//p[@title and @class='r']");
    private final By historySequenceElements = By.xpath("//p[@title and @class='l']");

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

    // parse characters in exercise to click on char
    public void parseAndClick(String exercise) {
        Character[] chars = exercise.chars().mapToObj(c -> (char) c).toArray(Character[]::new);
        for (int i = 0; i < chars.length; i++) {
            Character c = chars[i];
            if (Character.isDigit(c)) {
                btn = By.id("Btn" + c);
                waitForElementToBeClickableAndClickIt(btn);
            } else if (Character.isLetter(c)) {
                switch (c) {
                    // Here I implemented only Sin and SinH. other functions should be added similarly
                    case 's':
                        StringBuilder action = new StringBuilder();
                        while (Character.isLetter(chars[i])) {
                            action.append(chars[i]);
                            i++;
                        }
                        // Here we actually incremented the index to the next position, so we had to decrease it, but since calculator automatically adds '(' sign,
                        // we don't decrease our index in order to skip the '(' sign in our input string
                        if (action.toString().equalsIgnoreCase("sinh")) {
                            waitForElementToBeClickableAndClickIt(sinhBTN);
                            break;
                        } else if (action.toString().equalsIgnoreCase("sin")) {
                            waitForElementToBeClickableAndClickIt(sinusBTN);
                            break;
                        }
                    default:
                        System.out.println("Unknown character found");
                }
            } else {
                switch (c) {
                    case '.':
                        waitForElementToBeClickableAndClickIt(dotBTN);
                        break;
                    case '+':
                        waitForElementToBeClickableAndClickIt(additionBTN);
                        break;
                    case '=':
                        waitForElementToBeClickableAndClickIt(calculateBTN);
                        break;
                    case '-':
                        waitForElementToBeClickableAndClickIt(subtractionBTN);
                        break;
                    case '*':
                        waitForElementToBeClickableAndClickIt(multiplicationBTN);
                        break;
                    case '/':
                        waitForElementToBeClickableAndClickIt(divisionBTN);
                        break;
                    case '(':
                        waitForElementToBeClickableAndClickIt(leftParenthesis);
                        break;
                    case ',':
                        waitForElementToBeClickableAndClickIt(colonBTN);
                        break;
                    case ')':
                        waitForElementToBeClickableAndClickIt(rightParenthesis);
                        break;
                    case '%':
                        waitForElementToBeClickableAndClickIt(percentBTN);
                        break;
                    case '^':
                        waitForElementToBeClickableAndClickIt(powerBTN);
                        break;
                    default:
                        System.out.println("Unknown character found");
                }
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
