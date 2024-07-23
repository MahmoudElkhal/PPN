package esj.profil.daos;

import esj.profil.models.DossierMedical;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DossierMedicalRepository extends JpaRepository<DossierMedical,Long> {
}
