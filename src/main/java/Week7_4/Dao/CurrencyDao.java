package Week7_4.Dao;

import Week7_4.Datasource.MariaDbJpaConnection;
import jakarta.persistence.EntityManager;
import Week7_4.Model.*;


public class CurrencyDao {

    public void persist(Currency currency) {
        EntityManager em = MariaDbJpaConnection.getInstance();
        em.getTransaction().begin();
        em.persist(currency);
        em.getTransaction().commit();
    }

    public Currency find(String abbreviation) {
        EntityManager em = MariaDbJpaConnection.getInstance();
        return em.find(Currency.class, abbreviation);
    }

    public void update(Currency currency) {
        EntityManager em = MariaDbJpaConnection.getInstance();
        em.getTransaction().begin();
        em.merge(currency);
        em.getTransaction().commit();
    }

    public void delete(Currency currency) {
        EntityManager em = MariaDbJpaConnection.getInstance();
        em.getTransaction().begin();
        em.remove(currency);
        em.getTransaction().commit();
    }

    public Currency[] findAllCurrencies() {
        EntityManager em = MariaDbJpaConnection.getInstance();
        return em.createQuery("FROM Currency ", Currency.class).getResultList().toArray(new Currency[0]);
    }

    public String[] findAllAbbreviations() {
        EntityManager em = MariaDbJpaConnection.getInstance();
        return em.createQuery("SELECT abbreviation FROM Currency ", String.class).getResultList().toArray(new String[0]);
    }
    public void persistCurrencies(){
        EntityManager em = MariaDbJpaConnection.getInstance();
        em.getTransaction().begin();
        em.persist(new Currency("USD", "USA Dollar", 1.00));
        em.persist(new Currency("EUR", "Euro", 1.0586));
        em.persist(new Currency("GBP", "Great Britain Pound", 1.2197));
        em.persist(new Currency("JPY", "Japan Yen", 0.0067));
        em.persist(new Currency("AUD", "Australi Dollar", 0.6428));
        em.persist(new Currency("BAM", "Bosnia Mark", 0.5413));
        em.persist(new Currency("CAD", "Canada Dollar", 0.7358));
        em.persist(new Currency("CUP", "Cuba Peso", 0.0416));
        em.getTransaction().commit();
    }
}
