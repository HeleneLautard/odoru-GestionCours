package fr.miage.toulouse.m2.lautard.helene.gestioncours.rest;

import fr.miage.toulouse.m2.lautard.helene.gestioncours.entities.Cours;
import fr.miage.toulouse.m2.lautard.helene.gestioncours.exceptions.BadDateException;
import fr.miage.toulouse.m2.lautard.helene.gestioncours.exceptions.CoursNotFoundException;
import fr.miage.toulouse.m2.lautard.helene.gestioncours.exceptions.InscriptionException;
import fr.miage.toulouse.m2.lautard.helene.gestioncours.services.GestionCours;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/cours")
public class CoursRestController {


    @Autowired
    GestionCours gestionCours;

    // TO DO : Gestion role
    @PostMapping("")
    public Cours postCours(@RequestBody Cours cours) {
        try {
            return this.gestionCours.creerCours(cours);
        } catch (BadDateException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "test");
        }
    }

    /**
     * Obtenir la liste de tous les cours
     * @returnliste des cours
     */
    @GetMapping("")
    public List<Cours> getListeCours(){
        return this.gestionCours.getListeCours();
    }

    /**
     * Obtenir les informations relatives à un cours
     * @param num_cours numéro d'identification d'un cours
     * @return informations sur le cours
     */
    @GetMapping("/{id}")
    public Cours getCours(@PathVariable("id") Long num_cours) {
        try {
            return this.gestionCours.getCours(num_cours);
        } catch (CoursNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Cours Not Found", e);
        }
    }


    /**
     * Obtenir la liste des cours donés par un enseignant
     * @param id identifiant de l'enseignant
     * @return Liste des cours donnés
     */
    @GetMapping("/enseignant")
    public Iterable<Cours> getListeCoursEnseignant(@RequestParam("enseignant") String id) {
        Long idl = Long.parseLong(id);
        return this.gestionCours.getCoursEnseignant(idl);
    }

    /**
     * Obtenir les informations sur les cours auxquels un adherent a participé
     * @param id identifiant de l'adherent
     * @return liste des cours
     */
    @GetMapping("/participant")
    public Iterable<Cours> getListeCoursParticipant(@RequestParam("participant") String id) {
        long idl = Long.parseLong(id);
        return this.gestionCours.getCoursParticipant(idl);
    }

    // TO DO : Gestion rôle
    @PutMapping("/{id}/inscriptions")
    public Cours postInscriptionParticipant(@PathVariable("id") Long idCours, @RequestParam("participant") String idPar) {
        long idParticipant = Long.parseLong(idPar);
        try {
            return this.gestionCours.inscriptionCours(idCours, idParticipant);
        } catch (CoursNotFoundException | InscriptionException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }
}
