package esj.profil.controllers;

import esj.profil.models.DossierMedical;
import esj.profil.services.DossierMedicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dossiersmedicaux")
@CrossOrigin(origins = "http://localhost:3000")
public class DossierMedicalController {

    @Autowired
    private DossierMedicalService dossierMedicalService;

    @GetMapping("/{id}")
    public ResponseEntity<DossierMedical> getDossierMedicalById(@PathVariable Long id) {
        DossierMedical dossierMedical = dossierMedicalService.getById(id);
        if (dossierMedical == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dossierMedical);
    }

    @GetMapping
    public ResponseEntity<List<DossierMedical>> getAllDossiersMedicaux() {
        List<DossierMedical> dossiersMedicaux = dossierMedicalService.getAllDossiersMedicaux();
        return ResponseEntity.ok(dossiersMedicaux);
    }

    @PostMapping
    public ResponseEntity<DossierMedical> createDossierMedical(@RequestBody DossierMedical dossierMedical) {
        DossierMedical savedDossierMedical = dossierMedicalService.saveOrUpdate(dossierMedical);
        return ResponseEntity.ok(savedDossierMedical);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DossierMedical> updateDossierMedical(@PathVariable Long id,
            @RequestBody DossierMedical dossierMedical) {
        DossierMedical existingDossierMedical = dossierMedicalService.getById(id);
        if (existingDossierMedical == null) {
            return ResponseEntity.notFound().build();
        }
        dossierMedical.setId(id);
        DossierMedical updatedDossierMedical = dossierMedicalService.saveOrUpdate(dossierMedical);
        return ResponseEntity.ok(updatedDossierMedical);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDossierMedical(@PathVariable Long id) {
        DossierMedical existingDossierMedical = dossierMedicalService.getById(id);
        if (existingDossierMedical == null) {
            return ResponseEntity.notFound().build();
        }
        dossierMedicalService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
