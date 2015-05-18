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
    public static Stock lookupStock(String symbol) throws StockLookupException {

        CSVRecord stockInfo;
        CSVParser parser;
        URL url;

        // Fetch the CSV data
        try {
            url = new URL(urlBase + symbol);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            throw new StockLookupException("Problem resolving URL", symbol);
        }

        try {
            parser = CSVParser.parse(url, Charset.forName("UTF-8"), CSVFormat.DEFAULT);

            // We expect a single record
            stockInfo = parser.getRecords().get(0);
            parser.close();
        } catch (IOException e) {
            e.printStackTrace();
            throw new StockLookupException("Problem parsing fetched data", symbol);
        }

        return new Stock(stockInfo.get(0), stockInfo.get(1), Float.parseFloat(stockInfo.get(2)));
    }

}