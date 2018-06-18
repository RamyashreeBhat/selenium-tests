package com.wikia.webdriver.common.core.helpers;

public enum Geo {
  NULL("", ""),

  DENMARK("EU", "DK"),
  GERMANY("EU", "DE"),
  POLAND("EU", "PL"),

  USA("NA", "US");

  private String continent;
  private String country;

  Geo(String continent, String country) {
    this.continent = continent;
    this.country = country;
  }

  public String getContinent() {
    return continent;
  }

  public String getCountry() {
    return country;
  }
}
