package com.wikia.webdriver.testcases.adstests;

import com.wikia.webdriver.common.core.annotations.Execute;
import com.wikia.webdriver.common.core.annotations.InBrowser;
import com.wikia.webdriver.common.core.annotations.NetworkTrafficDump;
import com.wikia.webdriver.common.core.drivers.Browser;
import com.wikia.webdriver.common.core.helpers.Emulator;
import com.wikia.webdriver.common.core.helpers.Geo;
import com.wikia.webdriver.common.core.url.Page;
import com.wikia.webdriver.common.dataprovider.TrackingOptInDataProvider;
import com.wikia.webdriver.common.dataprovider.ads.AdsDataProvider;
import com.wikia.webdriver.common.templates.NewTestTemplate;
import com.wikia.webdriver.elements.common.TrackingOptInModal;
import com.wikia.webdriver.pageobjectsfactory.componentobject.TrackingOptInPage;
import com.wikia.webdriver.pageobjectsfactory.componentobject.ad.AssertionAds;
import com.wikia.webdriver.pageobjectsfactory.pageobject.adsbase.AdsBaseObject;
import org.testng.annotations.Test;

import java.util.List;


@InBrowser(
    browser = Browser.CHROME,
    emulator = Emulator.NEXUS_5X_WITHOUT_TOUCH
)
public class TestAdsTrackingOptInRequestsMobileWiki extends NewTestTemplate {

  private static final Page ADS_MERCURY_PAGE = new Page("mercuryautomationtesting", "/join");
  private static final String POLAND = "PL";
  private static final String DENMARK = "DK";
  private static final String GERMANY = "DE";

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsKikimoraRejectedDataProvider",
      groups = "AdsOptInRejectedMobileWiki"
  )
  public void adsTrackingRejectedForKikimora(String[] instantGlobals, List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInPage modal = new TrackingOptInPage();
    modal.rejectOptInModal(driver, POLAND, AdsDataProvider.HOME_PAGE, instantGlobals);

    modal.verifyTrackingRequestsSend(urlPatterns, networkTrafficInterceptor);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsNpaSlotsDataProviderMobile",
      groups = "AdsOptInRejectedMobileWiki"
  )
  public void adsTrackingRejectedForSlotsAdSlots(List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInPage modal = new TrackingOptInPage();
    modal.rejectOptInModal(driver, POLAND, AdsDataProvider.HOME_PAGE);

    modal.verifyTrackingRequestsSend(urlPatterns, networkTrafficInterceptor);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsKruxDataProvider",
      groups = "AdsOptInRejectedMobileWiki"
  )
  public void adsTrackingRejectedForKrux(String[] instantGlobals, List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInPage modal = new TrackingOptInPage();
    modal.rejectOptInModal(driver, POLAND, AdsDataProvider.HOME_PAGE, instantGlobals);

    modal.verifyTrackingRequestsNotSend(urlPatterns, networkTrafficInterceptor);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsNetzAthletenDataProvider",
      groups = "AdsOptInRejectedMobileWiki"
  )
  public void adsTrackingRejectedForNetzAthleten(String[] instantGlobals,
                                                 List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInPage modal = new TrackingOptInPage();
    modal.rejectOptInModal(driver, GERMANY, AdsDataProvider.HOME_PAGE, instantGlobals);

    modal.verifyTrackingRequestsNotSend(urlPatterns, networkTrafficInterceptor);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsMoatDataProvider",
      groups = "AdsOptInRejectedMobileWiki"
  )
  public void adsTrackingRejectedForMoat(String[] instantGlobals, List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInPage modal = new TrackingOptInPage();
    modal.rejectOptInModal(driver, POLAND, AdsDataProvider.UAP_PAGE, instantGlobals);

    modal.verifyTrackingRequestsSend(urlPatterns, networkTrafficInterceptor);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsNordicsDataProvider",
      groups = "AdsOptInRejectedMobileWiki"
  )
  public void adsTrackingRejectedForNordics(List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInPage modal = new TrackingOptInPage();
    modal.rejectOptInModal(driver, DENMARK, AdsDataProvider.HOME_PAGE);

    modal.verifyTrackingRequestsNotSend(urlPatterns, networkTrafficInterceptor);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsPrebidDataProvider",
      groups = "AdsOptInRejectedMobileWiki"
  )
  public void adsTrackingRejectedForPrebid(String[] instantGlobals, List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInPage modal = new TrackingOptInPage();
    modal.rejectOptInModal(driver, POLAND, AdsDataProvider.HOME_PAGE, instantGlobals);

    modal.verifyTrackingRequestsNotSend(urlPatterns, networkTrafficInterceptor);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsA9DataProvider",
      groups = "AdsOptInRejectedMobileWiki"
  )
  public void adsTrackingRejectedForA9(String[] instantGlobals, List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInPage modal = new TrackingOptInPage();
    modal.rejectOptInModal(driver, POLAND, AdsDataProvider.HOME_PAGE, instantGlobals);

    modal.verifyTrackingRequestsNotSend(urlPatterns, networkTrafficInterceptor);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsComscoreDataProvider",
      groups = "AdsOptInRejectedMobileWiki"
  )
  public void adsTrackingRejectedForComscore(List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInPage modal = new TrackingOptInPage();
    modal.rejectOptInModal(driver, POLAND, AdsDataProvider.HOME_PAGE);

    modal.verifyTrackingRequestsNotSend(urlPatterns, networkTrafficInterceptor);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsQuantcastDataProvider",
      groups = "AdsOptInRejectedMobileWiki"
  )
  public void adsTrackingRejectedForQuantcast(List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInPage modal = new TrackingOptInPage();
    modal.rejectOptInModal(driver, POLAND, AdsDataProvider.HOME_PAGE);

    modal.verifyTrackingRequestsNotSend(urlPatterns, networkTrafficInterceptor);

  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsGoogleAnalyticsDataProvider",
      groups = "AdsOptInRejectedMobileWiki"
  )
  public void adsTrackingRejectedForGoogleAnalytics(List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInPage modal = new TrackingOptInPage();
    modal.rejectOptInModal(driver, POLAND, AdsDataProvider.HOME_PAGE);

    modal.verifyTrackingRequestsSend(urlPatterns, networkTrafficInterceptor);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "adsPetametricsDataProvider",
      groups = "AdsOptInRejectedMobileWiki"
  )
  public void adsTrackingRejectedForPetametrics(List<String> urlPatterns) {
    networkTrafficInterceptor.startIntercepting();
    TrackingOptInPage modal = new TrackingOptInPage();
    modal.rejectOptInModal(driver, POLAND, AdsDataProvider.HOME_PAGE);

    modal.verifyTrackingRequestsNotSend(urlPatterns, networkTrafficInterceptor);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false, geoCookie = Geo.POLAND)
  @Test(
      dataProviderClass = TrackingOptInDataProvider.class,
      dataProvider = "newTestData",
      groups = "AdsOptInAcceptedMobileWiki"
  )
  public void testUrlPatternsForAcceptedOptIn(String pattern, boolean isExpected, String[] instantGlobals) {
    AdsBaseObject page = openPageWithGeo(AdsDataProvider.HOME_PAGE, instantGlobals);

    final TrackingOptInModal modal = new TrackingOptInModal(page);
    modal.clickAcceptButton();

    AssertionAds.assertUrlPattern(networkTrafficInterceptor, pattern, isExpected);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false, geoCookie = Geo.GERMANY)
  @Test(groups = "AdsOptInAcceptedMobileWiki")
  public void adsTrackingAcceptedForNetzAthleten() {
    AdsBaseObject page = openPageWithGeo(AdsDataProvider.HOME_PAGE, TrackingOptInDataProvider.ADS_NETZ_ATHLETEN_INSTANT_GLOBALS);

    final TrackingOptInModal modal = new TrackingOptInModal(page);
    modal.clickAcceptButton();

    AssertionAds.assertUrlPatternFound(networkTrafficInterceptor, TrackingOptInDataProvider.ADS_NETZ_ATHLETEN_PATTERN);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptIn = false, geoCookie = Geo.POLAND)
  @Test(groups = "AdsOptInAcceptedMobileWiki")
  public void adsTrackingAcceptedForMoat() {
    AdsBaseObject page = openPageWithGeo(AdsDataProvider.UAP_PAGE, TrackingOptInDataProvider.ADS_MOAT_INSTANT_GLOBALS);

    final TrackingOptInModal modal = new TrackingOptInModal(page);
    modal.clickAcceptButton();

    AssertionAds.assertUrlPatternFound(networkTrafficInterceptor, TrackingOptInDataProvider.ADS_MOAT_PATTERN);
  }

  @NetworkTrafficDump(useMITM = true)
  @Execute(trackingOptOut = true, geoCookie = Geo.USA)
  @Test(groups = "AdsOptInAcceptedMobileWiki")
  public void adsTrackingPixelSentAuthPageOutsideUE() {
    networkTrafficInterceptor.startIntercepting();
    AdsBaseObject page = new AdsBaseObject(driver, ADS_MERCURY_PAGE.getUrl());
    page.waitForPageLoaded();

    AssertionAds.assertUrlPatternFound(networkTrafficInterceptor, TrackingOptInDataProvider.ADS_QUANTCAST_SECURE_PATTERN);
  }

  private AdsBaseObject openPageWithGeo(Page page, String[] instantGlobals) {
    networkTrafficInterceptor.startIntercepting();
    final AdsBaseObject pageObject = new AdsBaseObject(driver, TrackingOptInPage.appendInstantGlobals(page.getUrl(), instantGlobals));
    pageObject.waitForPageLoaded();
    return pageObject;
  }
}
