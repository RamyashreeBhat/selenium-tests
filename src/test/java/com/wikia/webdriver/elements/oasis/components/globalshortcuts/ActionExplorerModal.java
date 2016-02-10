package com.wikia.webdriver.elements.oasis.components.globalshortcuts;

import com.wikia.webdriver.common.core.elemnt.JavascriptActions;
import com.wikia.webdriver.common.core.elemnt.Wait;
import com.wikia.webdriver.common.logging.PageObjectLogging;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ActionExplorerModal {

  @FindBy(css = ".label-in-suggestions")
  private WebElement searchSuggestions;

  @FindBy(css = "#global_shortcuts_search_field")
  private WebElement searchField;

  @FindBy(css = ".global-shortcuts-search div[data-index='16']")
  private WebElement specialAllPagesLink;

  @FindBy(css = ".autocomplete-suggestions")
  private WebElement autocompleteSuggestions;

  private By actionExplorerModalSelector = By.cssSelector(".global-shortcuts-search");
  private By keyboardShortcutsModalSelector = By.cssSelector(".global-shortcuts-help");

  private Wait wait;
  private Actions actions;
  private JavascriptActions jsActions;
  private WebDriver driver;

  public ActionExplorerModal(WebDriver driver) {
    this.wait = new Wait(driver);
    this.actions = new Actions(driver);
    this.jsActions = new JavascriptActions(driver);
    this.driver = driver;

    PageFactory.initElements(driver, this);
  }

  public ActionExplorerModal useShortcut(String shortcut) {
    switch (shortcut) {
      case ".":
        triggerDotShortcut();
        break;
      case "ESC":
        triggerEscapeShortcut();
        break;
      default:
        break;
    }

    return this;
  }

  private ActionExplorerModal triggerDotShortcut() {
    actions.sendKeys(".").perform();

    wait.forElementVisible(actionExplorerModalSelector);
    PageObjectLogging.logInfo("Action Explorer modal was opened by . button");

    return this;
  }

  private ActionExplorerModal triggerEscapeShortcut() {
    actions.sendKeys(Keys.ESCAPE).perform();

    wait.forElementNotVisible(actionExplorerModalSelector);
    PageObjectLogging.logInfo("Action explorer was closed by esc button");

    return this;
  }

  public ActionExplorerModal searchFor(String searchQuery) {
    wait.forElementClickable(searchField);
    actions.sendKeys(searchQuery).perform();
    PageObjectLogging.logInfo("Typed in search box: " + searchQuery);

    return this;
  }

  public ActionExplorerModal selectKeyboardShortcutsFromSearchSuggestions() {
    wait.forElementClickable(searchSuggestions);
    PageObjectLogging.logInfo("Select: " + searchSuggestions.getText());

    actions.sendKeys(Keys.ENTER).perform();

    wait.forElementVisible(keyboardShortcutsModalSelector);
    PageObjectLogging.logInfo("Keyboard shortcuts modal was opened");

    return this;
  }

  public ActionExplorerModal scrollToAllPagesLink() {
    WebElement searchModal = driver.findElement(actionExplorerModalSelector);
    wait.forElementClickable(searchModal);

    jsActions.scrollToElementInModal(autocompleteSuggestions, specialAllPagesLink);
    PageObjectLogging.logInfo("Scrolled to all pages link");

    return this;
  }

  public ActionExplorerModal openAllPagesLink() {
    wait.forElementClickable(specialAllPagesLink);
    specialAllPagesLink.click();

    PageObjectLogging.logInfo("Special all pages was opened");

    return this;
  }
}
