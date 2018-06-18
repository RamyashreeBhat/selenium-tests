package com.wikia.webdriver.pageobjectsfactory.componentobject.ad;

import com.wikia.webdriver.common.core.Assertion;
import com.wikia.webdriver.common.core.networktrafficinterceptor.NetworkTrafficInterceptor;
import org.openqa.selenium.Dimension;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class AssertionAds {
  public static void assertAspectRatio(Dimension size, double expected) {
    final double actual = (double) size.getWidth() / (double) size.getHeight();
    // Some divergent is possible because of browser size rounding
    Assertion.assertEquals(roundAspectRatio(actual), roundAspectRatio(expected), 0.03, "Aspect ratios are divergent");
  }

  private static double roundAspectRatio(double aspectRatio) {
    return new BigDecimal(aspectRatio).setScale(2, RoundingMode.HALF_UP).doubleValue();
  }

  public static void assertUrlPattern(NetworkTrafficInterceptor networkTrafficInterceptor, String pattern, boolean isExpected) {
    if (isExpected) {
      assertUrlPatternFound(networkTrafficInterceptor, pattern);
    } else {
      assertUrlPatternNotFound(networkTrafficInterceptor, pattern);
    }
  }

  public static void assertUrlPatternFound(NetworkTrafficInterceptor networkTrafficInterceptor, String pattern) {
      Assertion.assertNotNull(networkTrafficInterceptor.waitForEntryByUrlPattern(pattern), "Can't find url with pattern:" + pattern);
  }

  private static void assertUrlPatternNotFound(NetworkTrafficInterceptor networkTrafficInterceptor, String pattern) {
    Assertion.assertNull(networkTrafficInterceptor.waitForEntryByUrlPattern(pattern), "Found url with pattern: " + pattern);
  }

}
