package esj.profil.controllers;

import esj.profil.dtos.ConsultationDTO;
import esj.profil.models.AntecedentFamilial;
import esj.profil.models.AntecedentPersonnel;
import esj.profil.models.Jeune;
import esj.profil.services.JeuneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jeunes")
@CrossOrigin(origins = "http://localhost:3000")
public class JeuneController {
    @Autowired
    private JeuneService jeuneService;

    @GetMapping("/{id}")
    public ResponseEntity<Jeune> getJeuneById(@PathVariable Long id) {
        Jeune jeune = jeuneService.getById(id);
        if (jeune != null) {
            return ResponseEntity.ok(jeune);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public List<Jeune> getAllJeunes() {
        return jeuneService.getAllJeunes();
    }

    @PostMapping
    public ResponseEntity<Jeune> createJeune(@RequestBody Jeune jeune) {
        Jeune savedJeune = jeuneService.saveOrUpdate(jeune);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedJeune);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Jeune> updateJeune(@PathVariable Long id, @RequestBody Jeune jeune) {
        Jeune existingJeune = jeuneService.getById(id);
        if (existingJeune != null) {
            jeune.setId(id);
            Jeune updatedJeune = jeuneService.saveOrUpdate(jeune);
            return ResponseEntity.ok(updatedJeune);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJeune(@PathVariable Long id) {
        Jeune existingJeune = jeuneService.getById(id);
        if (existingJeune != null) {
            jeuneService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/consultations")
    public ResponseEntity<Jeune> addConsultationToJeune(@PathVariable Long id,
            @RequestBody ConsultationDTO consultationDTO) {
        Jeune jeune = jeuneService.addConsultationDTOToJeune(id, consultationDTO);
        if (jeune == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(jeune);
    }

    @PostMapping("/{id}/antecedentsFamiliaux")
    public ResponseEntity<Jeune> addAntecedentFamilialToJeune(@PathVariable Long id,
                                                              @RequestBody AntecedentFamilial antecedentFamilial) {
        Jeune jeune = jeuneService.addAntecedentFamilialToJeune(id, antecedentFamilial);
        if (jeune == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(jeune);
    }

    @PostMapping("/{id}/antecedentsPersonnels")
    public ResponseEntity<Jeune> addAntecedentPersonnelToJeune(@PathVariable Long id,
                                                               @RequestBody AntecedentPersonnel antecedentPersonnel) {
        Jeune jeune = jeuneService.addAntecedentPersonnelToJeune(id, antecedentPersonnel);
        if (jeune == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(jeune);
    }

}
