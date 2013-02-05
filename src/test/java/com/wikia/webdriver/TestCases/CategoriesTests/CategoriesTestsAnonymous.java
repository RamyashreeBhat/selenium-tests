package com.wikia.webdriver.TestCases.CategoriesTests;

import java.awt.PageAttributes;

import org.testng.annotations.Test;

import com.wikia.webdriver.Common.ContentPatterns.PageContent;
import com.wikia.webdriver.Common.ContentPatterns.XSSContent;
import com.wikia.webdriver.Common.Core.Global;
import com.wikia.webdriver.Common.Templates.TestTemplate;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiBasePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticleEditMode;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiArticlePageObject;
import com.wikia.webdriver.PageObjectsFactory.PageObject.WikiPage.WikiCategoryPageObject;

public class CategoriesTestsAnonymous extends TestTemplate {

	String categoryName;
	String pageName;
	
	/*
	 * TestCase001 Open random wiki page as anonymous user add category Verify
	 * category is added
	 */
	@Test(groups = { "CategoriesTestsAnonymous_001", "CategoriesTestsAnonymous" })
	public void CategoriesTestsAnonymous_001_addCategoryNotEditMode() {

		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		categoryName = PageContent.categoryNamePrefix + wiki.getTimeStamp();
		wiki.openWikiPage();
		WikiArticlePageObject article = new WikiArticlePageObject(driver,
				Global.DOMAIN, "random");
		article.openRandomArticle();
		article.categories_clickAddCategory();
		article.categories_typeCategoryName(categoryName);
		article.categories_clickOnSave();
		article.categories_verifyCategoryPresent(categoryName);

	}

	/*
	 * TestCase002 Open random wiki page as anonymous user edit it and add
	 * category Verify category is added
	 */
	@Test(groups = { "CategoriesTestsAnonymous_002", "CategoriesTestsAnonymous" })
	public void CategoriesTestsAnonymous_002_addCategoryEditMode() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		categoryName = PageContent.categoryNamePrefix + wiki.getTimeStamp();
		wiki.openWikiPage();
		WikiArticlePageObject article = new WikiArticlePageObject(driver,
				Global.DOMAIN, "random");
		article.openRandomArticle();
		WikiArticleEditMode articleEdit = article.clickEditButton("");
		articleEdit.categories_addCategoryEditMode(categoryName);
		articleEdit.categories_verifyCategoryAddedEditMode(categoryName);
		article = articleEdit.clickOnPublishButton();
		article.categories_verifyCategoryPresent(categoryName);
	}

	/*
	 * TestCase003 Open random wiki page as anonymous user edit it and add
	 * category, then remove it Verify category is removed
	 */
	@Test(groups = { "CategoriesTestsAnonymous_003", "CategoriesTestsAnonymous" })
	public void CategoriesTestsAnonymous_003_removeCategoryEditMode() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		categoryName = PageContent.categoryNamePrefix + wiki.getTimeStamp();
		pageName = PageContent.categoryPageNamePrefix + wiki.getTimeStamp();		
		wiki.openWikiPage();
		WikiArticleEditMode articleEdit = wiki.createNewArticle(pageName, 1);
		articleEdit.categories_addCategoryEditMode(categoryName);
		// adding the below category will save 1 minute of time execution time.
		// Reason: it assures that list of categories will not be empty, what
		// eliminates searching for empty lists during verification
		articleEdit.categories_addCategoryEditMode(categoryName + 2);
		articleEdit.categories_verifyCategoryAddedEditMode(categoryName);
		articleEdit.categories_verifyCategoryAddedEditMode(categoryName + 2);
		WikiArticlePageObject article = articleEdit.clickOnPublishButton();
		article.categories_verifyCategoryPresent(categoryName);
		articleEdit = article.clickEditButton("");
		articleEdit.categories_removeCategoryEditMode(categoryName);
		articleEdit.categories_verifyCategoryRemovedEditMode(categoryName);
		article = articleEdit.clickOnPublishButton();
		article.categories_verifyCategoryRemoved(categoryName);

	}

	/*
	 * TestCase004 Open random wiki page as anonymous user edit it and add
	 * category, then publish it Open category page and verify it is valid
	 */
	@Test(groups = { "CategoriesTestsAnonymous_004", "CategoriesTestsAnonymous" })
	public void CategoriesTestsAnonymous_004_verifyCategoryPage() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		categoryName = PageContent.categoryNamePrefix + wiki.getTimeStamp();
		wiki.openWikiPage();
		WikiArticlePageObject article = new WikiArticlePageObject(driver,
				Global.DOMAIN, "random");
		article.openRandomArticle();
		String articleName = article.getArticleNameFromURL();
		article.categories_clickAddCategory();
		article.categories_typeCategoryName(categoryName);
		article.categories_clickOnSave();
		article.categories_verifyCategoryPresent(categoryName);
		article.refreshPage();
		WikiCategoryPageObject categoryPage = article.openCategoryPage(categoryName);
		categoryPage.verifyCategoryContainsPage(articleName);
	}

	/*
	 * TestCase005 Open random wiki page as anonymous user add category from
	 * suggestions Verify category is added
	 */
//	@Test(groups = { "CategoriesTestsAnonymous_005", "CategoriesTestsAnonymous" })
	public void CategoriesTestsAnonymous_005_addSuggestedCategoryEditMode() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		wiki.openWikiPage();
		WikiArticlePageObject article = new WikiArticlePageObject(driver,
				Global.DOMAIN, "random");
		article.openRandomArticle();
		WikiArticleEditMode articleEdit = article.navigateToEditPage();
		// remmemeber name of added category
		String categoryName = articleEdit
				.categories_addSuggestedCategoryEditMode("t");
		articleEdit.categories_verifyCategoryAddedEditMode(categoryName);
		article = articleEdit.clickOnPublishButton();
		article.categories_verifyCategoryPresent(categoryName);
	}

	/*
	 * TestCase006 Open random wiki page as anonymous user edit it and add
	 * category from source mode Verify category is properly added in visual
	 */
	@Test(groups = { "CategoriesTestsAnonymous_006", "CategoriesTestsAnonymous" })
	public void CategoriesTestsAnonymous_006_addCategorySourceModeTransitionToVisual() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		categoryName = PageContent.categoryNamePrefix + wiki.getTimeStamp();
		wiki.openWikiPage();
		WikiArticlePageObject article = new WikiArticlePageObject(driver,
				Global.DOMAIN, "random");
		article.openRandomArticle();
		WikiArticleEditMode articleEdit = article.navigateToEditPage();
		articleEdit.clickOnSourceButton();
		articleEdit.categories_addCategoryEditMode(categoryName);
		articleEdit.categories_verifyCategoryAddedEditMode(categoryName);
		articleEdit.clickOnVisualButton();
		articleEdit.categories_verifyCategoryAddedEditMode(categoryName);
		article = articleEdit.clickOnPublishButton();
		article.categories_verifyCategoryPresent(categoryName);
	}

	/*
	 * TestCase007 Open random wiki page as anonymous user edit it and add
	 * category from visaul mode Verify category is properly added in source
	 */
	@Test(groups = { "CategoriesTestsAnonymous_007", "CategoriesTestsAnonymous" })
	public void CategoriesTestsAnonymous_007_addCategoryVisualModeTransitionToSource() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
		categoryName = PageContent.categoryNamePrefix + wiki.getTimeStamp();
		wiki.openWikiPage();
		WikiArticlePageObject article = new WikiArticlePageObject(driver,
				Global.DOMAIN, "random");
		article.openRandomArticle();
		WikiArticleEditMode articleEdit = article.navigateToEditPage();
		articleEdit.categories_addCategoryEditMode(categoryName);
		articleEdit.categories_verifyCategoryAddedEditMode(categoryName);
		articleEdit.clickOnSourceButton();
		articleEdit.categories_verifyCategoryAddedEditMode(categoryName);
		article = articleEdit.clickOnPublishButton();
		article.categories_verifyCategoryPresent(categoryName);
	}
	
	/*
	 * TestCase008 Open random wiki page as anonymous user edit it and add
	 * to category area js popup code: <script> alert("CategoriesTest"); </script>
	 * obsolete test case after refactoring of categories
	 */
//	@Test(groups = { "CategoriesTestsAnonymous_008", "CategoriesTestsAnonymous" })
	public void CategoriesTestsAnonymous_008_addJSpopupSourceModeJSprevention() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
//		String JSalert = "<script> alert(\"CategoriesTest\"); </script>";
//		String JSalertMessage = "CategoriesTest";
		wiki.openWikiPage();
		WikiArticlePageObject article = new WikiArticlePageObject(driver,
				Global.DOMAIN, "random");
		article.openRandomArticle();
		WikiArticleEditMode articleEdit = article.navigateToEditPage();
		articleEdit.clickOnSourceButton();
		articleEdit.categories_addToCategorySourceEditMode("[[Category:"+XSSContent.jsAlertCategories+"]]");
		articleEdit.categories_verifyCategoryAddedSourceEditMode("[[Category:"+XSSContent.jsAlertCategories+"]]");
		articleEdit.clickOnVisualButtonAndCheckJSalertThere(XSSContent.jsAlertMessageCategories, false);	
		article = articleEdit.clickOnPublishButtonAndCheckJSalertNotThere(XSSContent.jsAlertMessageCategories);	
	}
	
	/*
	 * TestCase009 verify if typing wrong category in edit source mode is prevented
	 * obsolete test case after refactoring of categories
	 */
//	@Test(groups = { "CategoriesTestsAnonymous_009", "CategoriesTestsAnonymous" })
	public void CategoriesTestsAnonymous_009_invalidCategoryInSourceModePrevention() {
		WikiBasePageObject wiki = new WikiBasePageObject(driver, Global.DOMAIN);
//		String JSalertMessage = "Unhandled syntax detected - switching back to visual mode impossible.";
		wiki.openWikiPage();
		WikiArticlePageObject article = new WikiArticlePageObject(driver,
				Global.DOMAIN, "random");
		article.openRandomArticle();
		WikiArticleEditMode articleEdit = article.navigateToEditPage();
		articleEdit.clickOnSourceButton();
		articleEdit.categories_addToCategorySourceEditMode("this is invalid source mode category syntax");
		articleEdit.categories_verifyCategoryAddedSourceEditMode("this is invalid source mode category syntax");
		articleEdit.clickOnVisualButtonAndCheckJSalertThere(PageContent.jsAlertMessage, true);	
		article = articleEdit.clickOnPublishButton();
		//TODO: check if alert is send
//		articleEdit.categories_verifyCategoryAddedEditMode(categoryName);
//		article = articleEdit.clickOnPublishButton();
//		article.categories_verifyCategoryAdded(categoryName);
	}
	// articleEdit.categories_verifyCategoryAddedSourceEditMode(categoryName);

}
