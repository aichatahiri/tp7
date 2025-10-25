package org.example;

import ma.projet.classes.*;
import ma.projet.service.*;

import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // ==== Initialisation des services ====
        CategorieService categorieService = new CategorieService();
        ProduitService produitService = new ProduitService();
        CommandeService commandeService = new CommandeService();
        LigneCommandeService ligneCommandeService = new LigneCommandeService();

        // ==== 1️⃣ Création des catégories ====
        Categorie cat1 = new Categorie("Ordinateurs");
        Categorie cat2 = new Categorie("Périphériques");
        categorieService.create(cat1);
        categorieService.create(cat2);

        // ==== 2️⃣ Création des produits ====
        Produit p1 = new Produit("ES12", 120, cat1);
        Produit p2 = new Produit("ZR85", 100, cat1);
        Produit p3 = new Produit("EE85", 200, cat2);
        produitService.create(p1);
        produitService.create(p2);
        produitService.create(p3);

        // ==== 3️⃣ Création des commandes ====
        Commande cmd1 = new Commande();
        Commande cmd2 = new Commande();
        commandeService.create(cmd1);
        commandeService.create(cmd2);

        // ==== 4️⃣ Création des lignes de commande ====
        LigneCommande lc1 = new LigneCommande();
        LigneCommande lc2 = new LigneCommande();
        LigneCommande lc3 = new LigneCommande();
        LigneCommande lc4 = new LigneCommande();

        ligneCommandeService.create(lc1);
        ligneCommandeService.create(lc2);
        ligneCommandeService.create(lc3);
        ligneCommandeService.create(lc4);

        // ==== 5️⃣ Afficher tous les produits ====
        System.out.println("\n📦 Liste des produits :");
        for (Produit p : produitService.getAll()) {
            System.out.println(p.getReference() + " | " + p.getPrix() + " DH | Catégorie : " + p.getCategorie().getLibelle());
        }

        // ==== 6️⃣ Produits d'une catégorie ====
        System.out.println("\n🗂️ Produits de la catégorie 'Ordinateurs' :");
        for (Produit p : produitService.getByCategorie(cat1.getId())) {
            System.out.println(p.getReference() + " - " + p.getPrix() + " DH");
        }

        // ==== 7️⃣ Produits d'une commande ====
        System.out.println("\n🧾 Produits de la commande ID " + cmd1.getId() + " :");
        for (Produit p : produitService.getProduitsParCommande(cmd1.getId())) {
            System.out.println(p.getReference() + " - " + p.getPrix() + " DH");
        }

        // ==== 8️⃣ Produits dont le prix > 100 DH ====
        System.out.println("\n💰 Produits dont le prix > 100 DH :");
        for (Produit p : produitService.getProduitsPrixSuperieur(100)) {
            System.out.println(p.getReference() + " - " + p.getPrix() + " DH");
        }

        // ==== 9️⃣ Produits commandés entre deux dates ====
        Date debut = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000); // hier
        Date fin = new Date(); // aujourd’hui
        System.out.println("\n📅 Produits commandés entre " + debut + " et " + fin + " :");
        for (Produit p : produitService.getProduitsEntreDates(debut, fin)) {
            System.out.println(p.getReference() + " - " + p.getPrix() + " DH");
        }

        System.out.println("\n✅ Test terminé avec succès !");
    }
}
