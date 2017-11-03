package com.wikia.webdriver.testcases.mcfootertests;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.helpers.User;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.mercury.pages.discussions.DiscussionsPage;
import com.wikia.webdriver.pageobjectsfactory.componentobject.mcfooter.MixedContentFooter;
import com.wikia.webdriver.pageobjectsfactory.pageobject.FandomPageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.UserProfilePage;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;

import org.testng.annotations.Test;

@Test (groups = {"EnAnonMixedContentFooter"})
@Execute(onWikia = "gameofthrones", asUser = User.ANONYMOUS)
public class EnAnonMixedContentFooterTests extends NewTestTemplate{

  @Test
   public void mcFooterIsPresentOnENwiki() {
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    Assertion.assertTrue(mcFooter.isMCFooterPresent());
  }

  @Test
   public void exploreWikisCardIsPresentOnENwiki(){
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    Assertion.assertTrue(mcFooter.isExploreWikisCardPresent());
  }

  @Test
  public void discussionsCardIsPresentOnENwikiWithDiscussions(){
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    Assertion.assertTrue(mcFooter.isDiscussionsCardPresent());
  }

  @Test
  @Execute(onWikia = "mcfwithoutmoreofwikiarticles")
  public void discussionsCardIsPresentOnENwikiWithEmptyDiscussions(){
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    Assertion.assertTrue(mcFooter.isDiscussionsCardPresent());
  }

  @Test
  @Execute(onWikia = "serowiec")
  public void discussionsCardIsNotPresentOnENwikiWithoutDiscussions(){
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    Assertion.assertTrue(mcFooter.isDiscussionsCardNotPresent());
  }

  @Test
  public void moreOfWikiArticlesCardIsPresentOnENwiki() {
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    Assertion.assertTrue(mcFooter.isMoreOfWikiArticlesCardPresent());
  }

  @Test
  public void countNoOfArticlesInMCFooterWithDiscussionsAndWithMoreOfWikiArticles(){
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    Assertion.assertEquals(mcFooter.countArticleCards(), 17);
  }

  @Test
  @Execute(onWikia = "glee")
  //SHOULD BE RUN AT enwikiwithoutdiscussions.wikia.com ONCE 'More of..' will appear on this wiki
  public void countNoOfArticlesInMCFooterWithoutDiscussionsAndWithMoreOfWikiArticles(){
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    Assertion.assertEquals(mcFooter.countArticleCards(), 19);
  }

  @Test
  @Execute(onWikia = "mcfwithoutmoreofwikiarticles")
  public void countNoOfArticlesInMCFooterWithDiscussionsAndWithoutMoreOfWikiArticles(){
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    Assertion.assertEquals(mcFooter.countArticleCards(), 18);
  }

  @Test
  public void countNoOfArticlesInExploreCard() {
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    Assertion.assertEquals(mcFooter.countArticlesInExploreCard(), 3);
  }

  @Test
  public void userIsTakenToDiscussionsAfterClickOnViewAll() {
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    mcFooter.clickOnViewAllLinkInDiscussions();
    Assertion.assertTrue(mcFooter.isDiscussions());
  }

  @Test
  public void userIsTakenToUserProfileAfterClickOnAvatar() {
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage()
        .scrollToMCFooter();
    String username = mcFooter.getUsername()
        .replaceAll(" •.*$", "");
    mcFooter.clickUserAvatar();
    Assertion.assertEquals(new UserProfilePage().getUserName(), username);
  }

  @Test
  public void userIsTakenToDiscussionsPostViewAfterClickOnPost() {
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage()
        .scrollToMCFooter();
    mcFooter.clickDiscussionsPost();
    Assertion.assertEquals(new DiscussionsPage().getUrl(), mcFooter.getCurrentUrl() );
  }

  @Test
  @Execute(onWikia = "enwikiwithemptydiscussions")
  public void zeroStateAppearsInDiscussionsWithoutPosts() {
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage()
        .scrollToMCFooter();
    Assertion.assertTrue(mcFooter.isZeroState());
  }

  @Test
  public void userIsTakenToFandomPageAfterClickOnFandomArticleCard() {
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    mcFooter.clickFanomArticleCard();
    mcFooter.waitForPageLoad();
    String url = driver.getCurrentUrl();
    Assertion.assertTrue(url.contains("fandom.wikia.com/articles/"));
  }

  @Test
  public void userIsTakenToWikiArticleAfterClickOnWikiArticleCard() {
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    mcFooter.clickWikiArticlecard();
    mcFooter.waitForPageLoad();
    String url = driver.getCurrentUrl();
    Assertion.assertTrue(url.contains(".wikia.com/wiki/"));
  }

  @Test
  public void userIsTakenToFandomArticleWithVideoAfterClickOnFandomVideoCard() {
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    mcFooter.clickFanomVideoCard();
    mcFooter.waitForPageLoad();
    String url = driver.getCurrentUrl();
    Assertion.assertTrue(url.contains("fandom.wikia.com/articles/"));
    FandomPageObject video = new FandomPageObject();
    Assertion.assertTrue(video.isFeaturedVideo());
  }

  @Test
  public void userIsTakenToWikiArticleWithVideoAfterClickOnWikiVideoCard() {
    MixedContentFooter mcFooter = new MixedContentFooter();
    mcFooter.openWikiMainPage().scrollToMCFooter();
    mcFooter.clickWikiVideoCard();
    mcFooter.waitForPageLoad();
    String url = driver.getCurrentUrl();
    Assertion.assertTrue(url.contains(".wikia.com/wiki/"));
    ArticlePageObject video = new ArticlePageObject();
    Assertion.assertTrue(video.isFeaturedVideo());
  }


}
