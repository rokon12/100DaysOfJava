package com.bazlur;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Optional;

public class StockExchangeService {

  public static final String GOOGLE_FINANCE_QUOTE_BTC_CAD = "https://www.google.com/finance/quote/BTC-CAD";
  public static final String GOOGLE_FINANCE_QUOTE_BTC_USD = "https://www.google.com/finance/quote/BTC-USD";
  public static final String CAD_TEXT = "Bitcoin to Canadian dollar";
  public static final String USD_TEXT = "Bitcoin to United States Dollar";

  public Optional<String> getBitcoinValueInCAD() {
    return getBitConValue(GOOGLE_FINANCE_QUOTE_BTC_CAD, CAD_TEXT);
  }

  public Optional<String> getBitcoinValueInUSD() {
    return getBitConValue(GOOGLE_FINANCE_QUOTE_BTC_USD, USD_TEXT);
  }

  private Optional<String> getBitConValue(String url, String textToFind) {
    var connect = Jsoup.connect(url);
    try {
      var document = connect.get();
      var select = document.select("h2");
      return select.stream()
              .filter(element -> element.text().contains(textToFind))
              .map(Element::parent)
              .map(Element::text)
              .findFirst();
    } catch (IOException e) {
      return Optional.empty();
    }
  }


}
