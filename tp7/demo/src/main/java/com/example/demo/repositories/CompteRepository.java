package com.example.demo.repositories;

import com.example.demo.entities.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteRepository extends JpaRepository<Compte, Long> {
    // Ici, tu peux ajouter des méthodes personnalisées si nécessaire
    // Exemple : List<Compte> findByType(TypeCompte type);
}
