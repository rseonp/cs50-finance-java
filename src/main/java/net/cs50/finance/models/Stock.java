package net.cs50.finance.models;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by Chris Bay on 5/15/15.
 */
public class Stock {

    private final String symbol;
    private final float price;
    private final String name;

    private static final String urlBase = "http://download.finance.yahoo.com/d/quotes.csv?f=snl1&s=";

    private Stock(String symbol, String name, float price) {
        this.symbol = symbol;
        this.price = price;
        this.name = name;
    }

    public String getSymbol() {
        return symbol;
    }

    public float getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return getName() + " (" + getSymbol() + "): $" + Float.toString(getPrice());
    }

    /**
     * Factory to create new Stock instances with current price information.
     *
     * @param symbol    stock symbol
     * @return          Stock instance with current price information, if available, null otherwise
     */
    public static Stock lookupStock(String symbol) {

        CSVRecord stockInfo;
        CSVParser parser;

        try {

            // Fetch the CSV data
            URL url = new URL(urlBase + symbol);
            parser = CSVParser.parse(url, Charset.forName("UTF-8"), CSVFormat.DEFAULT);

            // We expect a single record
            stockInfo = parser.getRecords().get(0);
            parser.close();

        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return new Stock(stockInfo.get(0), stockInfo.get(1), Float.parseFloat(stockInfo.get(2)));
    }

}
