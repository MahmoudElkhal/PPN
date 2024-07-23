package esj.profil.services;

import esj.profil.models.Medecin;
import java.util.List;

public interface MedecinService {
    List<Medecin> getAllMedecins();
    Medecin getMedecinById(Long id);
    Medecin saveMedecin(Medecin medecin);
    Medecin updateMedecin(Long id, Medecin medecin);
    void deleteMedecin(Long id);
}

