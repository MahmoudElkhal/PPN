package esj.profil.services;

import esj.profil.models.Consultation;
import esj.profil.models.Jeune;

import java.util.List;

public interface JeuneService {
    Jeune getById(Long id);
    List<Jeune> getAllJeunes();
    Jeune saveOrUpdate(Jeune jeune);
    void deleteById(Long id);
    Jeune addConsultationToJeune(Long jeuneId, Consultation consultation);
}
