package fr.miage.toulouse.m2.lautard.helene.gestioncours.rest;

import fr.miage.toulouse.m2.lautard.helene.gestioncours.DTO.CoursDTO;
import fr.miage.toulouse.m2.lautard.helene.gestioncours.DTO.ParticipantDTO;
import fr.miage.toulouse.m2.lautard.helene.gestioncours.DTO.StatistiquesCoursDTO;
import fr.miage.toulouse.m2.lautard.helene.gestioncours.entities.Cours;
import fr.miage.toulouse.m2.lautard.helene.gestioncours.exceptions.BadDateException;
import fr.miage.toulouse.m2.lautard.helene.gestioncours.exceptions.CoursNotFoundException;
import fr.miage.toulouse.m2.lautard.helene.gestioncours.exceptions.InscriptionException;
import fr.miage.toulouse.m2.lautard.helene.gestioncours.services.GestionCours;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/cours")
public class CoursRestController {


    @Autowired
    GestionCours gestionCours;

    ModelMapper modelMapper = new ModelMapper();

    // TO DO : Gestion role
    @PostMapping("")
    public CoursDTO postCours(@RequestBody CoursDTO cours) {
        try {
            Cours rqBody = this.modelMapper.map(cours, Cours.class);
            return this.modelMapper.map(this.gestionCours.creerCours(rqBody), CoursDTO.class);
        } catch (BadDateException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "test");
        }
    }

    @PutMapping("")
    public CoursDTO updateCours(@RequestBody CoursDTO cours){
        Cours rqBody = this.modelMapper.map(cours, Cours.class);
        return this.modelMapper.map(this.gestionCours.saveCours(rqBody), CoursDTO.class);
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
     * Suppression d'un cours
     * @param id identifiant du cours
     */
    @DeleteMapping("/{id}")
    public void deleteCours(@PathVariable("id") Long id){
        try{
            this.gestionCours.deleteCours(id);
        } catch(CoursNotFoundException ex){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage(), ex);
        }
    }

    @GetMapping(path="/statistiques")
    public Long getNbCours(){
        return this.gestionCours.getNbCours();
    }

    @GetMapping(path="/statistiques/{id}")
    public StatistiquesCoursDTO getStatistiquesCours(@PathVariable("id") Long idCours){
        try{
            return this.gestionCours.getStatistiquesCours(idCours);
        } catch (CoursNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, e.getMessage(), e);
        }
    }

    /**
     * Obtenir les informations sur les cours auxquels un adherent a participé
     * @param participant adherent
     * @return liste des cours avec indications des présences/absences
     */
    @GetMapping("/statistiques/participants/participant")
    public Iterable<Cours> getListeCoursParticipant(@RequestBody ParticipantDTO participant) {
        return this.gestionCours.getCoursParticipant(participant);
    }

    @GetMapping(path="/niveau/{niveau}")
    public Iterable<Cours> getCoursNiveau(@PathVariable("niveau") int niveau){
        return this.gestionCours.findByNiveau(niveau);
    }


    @GetMapping(path="/enseignant/{idEnseignant}")
    public Iterable<Cours> getCoursNiveau(@PathVariable("idEnseignant") Long idEnseignant){
        return this.gestionCours.findByEnseignant(idEnseignant);
    }
}
