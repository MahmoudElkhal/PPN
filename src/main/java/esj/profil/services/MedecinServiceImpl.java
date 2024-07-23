package esj.profil.services.impl;

import esj.profil.models.Medecin;
import esj.profil.daos.MedecinRepository;
import esj.profil.services.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MedecinServiceImpl implements MedecinService {

    @Autowired
    private MedecinRepository medecinRepository;

    @Override
    public List<Medecin> getAllMedecins() {
        System.out.println("repo trying to get medecin");
        return medecinRepository.findAll();
    }

    @Override
    public Medecin getMedecinById(Long id) {
        Optional<Medecin> medecin = medecinRepository.findById(id);
        return medecin.orElse(null);
    }

    @Override
    public Medecin saveMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    @Override
    public Medecin updateMedecin(Long id, Medecin medecin) {
        Optional<Medecin> existingMedecin = medecinRepository.findById(id);
        if (existingMedecin.isPresent()) {
            Medecin updatedMedecin = existingMedecin.get();
            updatedMedecin.setNom(medecin.getNom());
            updatedMedecin.setPrenom(medecin.getPrenom());
            updatedMedecin.setCin(medecin.getCin());
            updatedMedecin.setAdresseMail(medecin.getAdresseMail());
            updatedMedecin.setNumTelephone(medecin.getNumTelephone());
            updatedMedecin.setSpecialite(medecin.getSpecialite());
            return medecinRepository.save(updatedMedecin);
        } else {
            return null;
        }
    }

    @Override
    public void deleteMedecin(Long id) {
        medecinRepository.deleteById(id);
    }
}
