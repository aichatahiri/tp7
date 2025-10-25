package ma.projet.service;

import ma.projet.classes.Produit;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class ProduitService implements IDao<Produit> {

    @Override
    public void create(Produit p) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.save(p);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }

    @Override
    public Produit getById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Produit.class, id);
        }
    }

    @Override
    public List<Produit> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("from Produit", Produit.class).list();
        }
    }

    @Override
    public void update(Produit p) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.update(p);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }

    @Override
    public void delete(Produit p) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.delete(p);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        }
    }

    // Produits par catégorie
    public List<Produit> getByCategorie(int idCategorie) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "from Produit where categorie.id = :idCategorie", Produit.class)
                    .setParameter("idCategorie", idCategorie)
                    .list();
        }
    }

    // Produits commandés entre deux dates
    public List<Produit> getProduitsEntreDates(Date d1, Date d2) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "select distinct l.produit from LigneCommandeProduit l where l.commande.date between :d1 and :d2",
                            Produit.class)
                    .setParameter("d1", d1)
                    .setParameter("d2", d2)
                    .list();
        }
    }

    // Produits dans une commande donnée
    public List<Produit> getProduitsParCommande(int idCommande) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery(
                            "select l.produit from LigneCommandeProduit l where l.commande.id = :id",
                            Produit.class)
                    .setParameter("id", idCommande)
                    .list();
        }
    }

    public Iterable<? extends Produit> getProduitsPrixSuperieur(int i) {
        return null;
    }
}
