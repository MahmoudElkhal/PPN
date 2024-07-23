package esj.profil.daos;

import esj.profil.models.Jeune;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JeuneRepository extends JpaRepository<Jeune, Long> {
    // You can add custom query methods if needed
}

