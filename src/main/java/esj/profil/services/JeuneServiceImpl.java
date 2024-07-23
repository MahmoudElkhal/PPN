package esj.profil.services;

import esj.profil.daos.JeuneRepository;
import esj.profil.models.Consultation;
import esj.profil.models.DossierMedical;
import esj.profil.models.Jeune;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JeuneServiceImpl implements JeuneService {
    @Autowired
    private JeuneRepository jeuneRepository;

    public Jeune getById(Long id) {
        return jeuneRepository.findById(id).orElse(null);
    }

    public List<Jeune> getAllJeunes() {
        return jeuneRepository.findAll();
    }

    public Jeune saveOrUpdate(Jeune jeune) {
        if (jeune.getDossierMedial() == null) {
            DossierMedical dossierMedical = new DossierMedical();
            dossierMedical.setJeune(jeune);
            jeune.setDossierMedial(dossierMedical);
        }
        return jeuneRepository.save(jeune);
    }

    public void deleteById(Long id) {
        jeuneRepository.deleteById(id);
    }

    @Override
    public Jeune addConsultationToJeune(Long jeuneId, Consultation consultation) {
        Jeune jeune = jeuneRepository.findById(jeuneId).orElse(null);
        if (jeune != null) {
            DossierMedical dossierMedical = jeune.getDossierMedial();
            if (dossierMedical != null) {
                consultation.setDossierMedical(dossierMedical);
                dossierMedical.getHistoriqueConsultations().add(consultation);
                jeuneRepository.save(jeune);
            }
        }
        return jeune;
    }
}

