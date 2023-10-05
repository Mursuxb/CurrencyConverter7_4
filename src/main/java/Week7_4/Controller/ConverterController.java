package Week7_4.Controller;

import Week7_4.Dao.*;
import Week7_4.Model.*;
import Week7_4.View.CurrencyView;

public class ConverterController {
    private CurrencyView view;
    private CurrencyDao curDao = new CurrencyDao();
    private TransactionDao transDao = new TransactionDao();
    Currency.CurrencyConverter converter = new Currency.CurrencyConverter();

    public void createCurrencies() {
        curDao.persistCurrencies();
    }
    public void createCurrencies2() {
        curDao.persist(new Currency("USD", "USA Dollar", 1.00));
        curDao.persist(new Currency("EUR", "Euro", 1.0586));
        curDao.persist(new Currency("GBP", "Great Britain Pound", 1.2197));
        curDao.persist(new Currency("JPY", "Japan Yen", 0.0067));
        curDao.persist(new Currency("AUD", "Australi Dollar", 0.6428));
        curDao.persist(new Currency("BAM", "Bosnia Mark", 0.5413));
        curDao.persist(new Currency("CAD", "Canada Dollar", 0.7358));
        curDao.persist(new Currency("CUP", "Cuba Peso", 0.0416));
    }

    public void getCurrencies() {
        Currency[] currencies = curDao.findAllCurrencies();
        for (Currency currency : currencies) {
            converter.add(currency);
        }
    }

    public void createCurrency(String abbreviation, String name, double rate) {
        Currency currency = new Currency(abbreviation, name, rate);
        converter.add(currency);
        curDao.persist(currency);
    }

    public String convert(double amount, String source, String target) {
        double amountInUSD = converter.convertToUSD(amount, source);
        return converter.convertFromUSD(amountInUSD, target);
    }

    public String[] getAbbreviations() {
        return curDao.findAllAbbreviations();
    }

    public void createTransaction(double amount, String source, String target) {
        Currency sourceCurrency = curDao.find(source);
        Currency targetCurrency = curDao.find(target);
        Transaction transaction = new Transaction(amount, sourceCurrency, targetCurrency);
        transDao.persist(transaction);

    }}
