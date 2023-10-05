package Week7_4.Dao;

import Week7_4.Model.*;
import jakarta.persistence.EntityManager;
import Week7_4.Datasource.MariaDbJpaConnection;

public class TransactionDao {

        public void persist(Transaction transaction) {
            EntityManager em = MariaDbJpaConnection.getInstance();
            em.getTransaction().begin();
            em.persist(transaction);
            em.getTransaction().commit();
        }

        public Transaction find(int id) {
            EntityManager em = MariaDbJpaConnection.getInstance();
            return em.find(Transaction.class, id);
        }

        public void update(Transaction transaction) {
            EntityManager em = MariaDbJpaConnection.getInstance();
            em.getTransaction().begin();
            em.merge(transaction);
            em.getTransaction().commit();
        }

        public void delete(Transaction transaction) {
            EntityManager em = MariaDbJpaConnection.getInstance();
            em.getTransaction().begin();
            em.remove(transaction);
            em.getTransaction().commit();
        }

        public Transaction[] findAllTransactions() {
            EntityManager em = MariaDbJpaConnection.getInstance();
            return em.createQuery("FROM Transaction ", Transaction.class).getResultList().toArray(new Transaction[0]);
        }

        public int[] findAllIds() {
            EntityManager em = MariaDbJpaConnection.getInstance();
            return em.createQuery("SELECT id FROM Transaction ", int.class).getResultList().stream().mapToInt(i->i).toArray();
        }
}
