//package esj.profil.controllers;
//
//import esj.profil.models.Prescription;
//import esj.profil.services.PrescriptionService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/prescriptions")
//public class PrescriptionController {
//
//    private final PrescriptionService prescriptionService;
//
//    @Autowired
//    public PrescriptionController(PrescriptionService prescriptionService) {
//        this.prescriptionService = prescriptionService;
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<Prescription> getPrescriptionById(@PathVariable("id") Long id) {
//        Prescription prescription = prescriptionService.getPrescriptionById(id);
//        if (prescription != null) {
//            return ResponseEntity.ok(prescription);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @PostMapping("/create")
//    public ResponseEntity<Prescription> createPrescription(@RequestBody Prescription prescription) {
//        Prescription createdPrescription = prescriptionService.createPrescription(prescription);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdPrescription);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<Prescription> updatePrescription(
//            @PathVariable("id") Long id,
//            @RequestBody Prescription prescription) {
//        Prescription updatedPrescription = prescriptionService.updatePrescription(id, prescription);
//        if (updatedPrescription != null) {
//            return ResponseEntity.ok(updatedPrescription);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deletePrescription(@PathVariable("id") Long id) {
//        prescriptionService.deletePrescription(id);
//        return ResponseEntity.noContent().build();
//    }
//
//    @GetMapping("/consultation/{consultationId}")
//    public ResponseEntity<List<Prescription>> getPrescriptionsByConsultation(
//            @PathVariable("consultationId") Long consultationId) {
//        List<Prescription> prescriptions = prescriptionService.getPrescriptionsByConsultation(consultationId);
//        if (!prescriptions.isEmpty()) {
//            return ResponseEntity.ok(prescriptions);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}
//
//
