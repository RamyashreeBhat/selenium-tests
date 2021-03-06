package com.wikia.webdriver.pageobjectsfactory.componentobject.visualeditordialogs;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.logging.Log;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class VisualEditorInsertTemplateDialog extends VisualEditorDialog {

  @FindBy(css = ".oo-ui-searchWidget-query input")
  private WebElement searchInput;
  @FindBy(css = ".oo-ui-searchWidget-query .oo-ui-clearableTextInputWidget-clearButton")
  private WebElement searchClearButton;
  @FindBy(css = ".ve-ui-wikiaTemplateSearchWidget-suggestions")
  private WebElement suggestedWidget;
  @FindBy(css = ".ve-ui-wikiaTemplateSearchWidget-suggestions .ve-ui-wikiaTemplateOptionWidget")
  private List<WebElement> suggestedTemplates;
  @FindBy(css = ".oo-ui-searchWidget-results:not(.ve-ui-wikiaTemplateSearchWidget-suggestions)")
  private WebElement resultWidget;
  @FindBy(
      css = ".oo-ui-searchWidget-results:not(.ve-ui-wikiaTemplateSearchWidget-suggestions) " +
            ".ve-ui-wikiaTemplateOptionWidget")
  private List<WebElement> resultTemplates;
  @FindBy(css = ".oo-ui-searchWidget-query .oo-ui-pendingElement-pending")
  private WebElement queryPending;

  private By labelBy = By.cssSelector(".oo-ui-labelElement-label");
  private By
      suggestedTemplatesBy =
      By.cssSelector(".ve-ui-wikiaTemplateSearchWidget-suggestions div");
  private By resulteTemplateBy =
      By.cssSelector(".oo-ui-searchWidget-results:not(.ve-ui-wikiaTemplateSearchWidget-suggestions)"
                   + " .ve-ui-wikiaTemplateOptionWidget");

  public VisualEditorInsertTemplateDialog(WebDriver driver) {
    super(driver);
  }

  public void typeInSearchInput(String searchString) {
    waitForDialogVisible();
    wait.forElementVisible(searchInput);
    searchInput.sendKeys(searchString);
    waitForValueToBePresentInElementsAttributeByElement(searchInput, "value", searchString);
    Log.log(
        "typeInSearchInput",
        "Typed '" + searchString + "' into the template search textfield",
        true
    );
  }

  public void clearSearchInput() {
    searchInput.clear();
    Log.log("clearSearchInput", "Cleared the template search input field", true);
  }

  public VisualEditorEditTemplateDialog selectSuggestedTemplate(int index) {
    waitForDialogVisible();
    wait.forElementVisible(suggestedWidget);
    WebElement selected = suggestedTemplates.get(index).findElement(labelBy);
    selected.click();
    Log.log(
        "selectSuggestedTemplate",
        "Suggested template selected: " + selected.getText(),
        true
    );
    return new VisualEditorEditTemplateDialog(driver);
  }

  public VisualEditorEditTemplateDialog selectResultTemplate(String searchString, int index) {
    typeInSearchInput(searchString);
    waitForDialogVisible();
    wait.forElementVisible(resultWidget);
    WebElement selected = resultTemplates.get(index).findElement(labelBy);
    selected.click();
    Log.log(
        "selectResultTemplate",
        "Search result template selected: " + selected.getText(),
        true
    );
    return new VisualEditorEditTemplateDialog(driver);
  }

  public int getNumberOfResultTemplates() {
    return getNumOfElementOnPage(resulteTemplateBy);
  }

  public void verifyIsResultTemplate() {
    wait.forElementVisible(resultWidget);
    waitForElementNotVisibleByElement(queryPending);
    Assertion.assertTrue(getNumberOfResultTemplates() > 0, "No result template shown.");
    Log.log("verifyIsResultTemplate", "Result templates found", true);
  }

  public void verifyNoResultTemplate() {
    waitForElementNotVisibleByElement(queryPending);
    Assertion.assertTrue(getNumberOfResultTemplates() == 0, "There is result template shown.");
    Log.log("verifyNoResultTemplate", "No result templates found", true);
  }

  public void verifyIsSuggestedTemplate() {
    Assertion
        .assertTrue(isElementOnPage(suggestedTemplatesBy), "No suggested template shown.");
    Log.log("verifyIsSuggestedTemplate", "Suggested templates found", true);
  }
}
