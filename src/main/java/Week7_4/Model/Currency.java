package Week7_4.Model;

import jakarta.persistence.*;

import java.util.HashMap;

@Entity
@Table(name = "currency")
public class Currency {
    @Id
    @Column(name = "abbreviation")
    private String abbreviation;
    @Column(name = "currency_name")
    private String name;
    @Column(name = "currency_rate")
    private double rate;

    public Currency(String abbreviation, String name, double rate) {
        super();
        this.abbreviation = abbreviation;
        this.name = name;
        this.rate = rate;
    }

    public Currency() {

    }

    public String getAbbreviation() {
        return this.abbreviation;
    }

    public String getName() {
        return this.name;
    }

    public double getRate() {
        return this.rate;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public static class CurrencyConverter {
        private HashMap<String, Currency> rates = new HashMap<>();


        public void add(Currency currency) {
            rates.put(currency.getAbbreviation(), currency);
        }

        public String[] getAbbreviations() {
            return rates.keySet().toArray(new String[0]);
        }

        public double convertToUSD(double amount, String sourceCurrency) {
            return amount * rates.get(sourceCurrency).getRate();
        }

        public String convertFromUSD(double amount, String targetCurrency) {
            return String.format("%.2f", amount / rates.get(targetCurrency).getRate()) + " " + targetCurrency;
        }
    }
}

