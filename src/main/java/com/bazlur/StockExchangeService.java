package com.bazlur;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.Optional;

public class StockExchangeService {

  public static final String GOOGLE_FINANCE_QUOTE_BTC_CAD = "https://www.google.com/finance/quote/BTC-CAD";
  public static final String TEXT_TO_FIND = "Bitcoin to Canadian dollar";

  public Optional<String> getBitcoinValueInCAD() {
    var connect = Jsoup.connect(GOOGLE_FINANCE_QUOTE_BTC_CAD);
    try {
      var document = connect.get();
      var select = document.select("h2");
      return select.stream()
              .filter(element -> element.text().contains(TEXT_TO_FIND))
              .map(Element::parent)
              .map(Element::text)
              .findFirst();
    } catch (IOException e) {
      return Optional.empty();
    }
  }
}
