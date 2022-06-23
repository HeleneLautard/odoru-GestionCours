package fr.miage.toulouse.m2.lautard.helene.gestioncours.services;

import fr.miage.toulouse.m2.lautard.helene.gestioncours.DTO.ParticipantDTO;
import fr.miage.toulouse.m2.lautard.helene.gestioncours.DTO.StatistiquesCoursDTO;
import fr.miage.toulouse.m2.lautard.helene.gestioncours.entities.Cours;
import fr.miage.toulouse.m2.lautard.helene.gestioncours.exceptions.BadDateException;
import fr.miage.toulouse.m2.lautard.helene.gestioncours.exceptions.CoursNotFoundException;
import fr.miage.toulouse.m2.lautard.helene.gestioncours.exceptions.InscriptionException;

import java.util.List;

public interface GestionCours {

    /**
     * Création d'un nouveau cours
     * @param cours Informations sur le cours
     * @return Cours
     * @throws BadDateException
     */
    Cours creerCours(Cours cours) throws BadDateException;

    /**
     * Obtenir les informations d'un cours
     * @param num_cours numéro du cours recherché
     * @return Cours
     * @throws CoursNotFoundException
     */
    Cours getCours(Long num_cours) throws CoursNotFoundException;


    /**
     * Obtenir la liste de tous les cours
     * @return Liste des cours
     */
    List<Cours> getListeCours();

    /**
     * Obtenir la liste des cours d'un enseignant
     * @param num_enseignant numéro d'identification d'un enseignant
     * @return liste des cours
     */
    Iterable<Cours> getCoursEnseignant(Long num_enseignant);

    /**
     * Obtenir la liste des cours auquel un adherent a participé
     * @param participant numéro d'identification d'un adherent
     * @return liste des cours
     */
    Iterable<Cours> getCoursParticipant(ParticipantDTO participant);

    /**
     * Suppression d'un cours de la base de données
     * @param id identifiant du cours
     * @throws CoursNotFoundException
     */
    void deleteCours(Long id)throws CoursNotFoundException;

    /**
     * Récupérer le nombre de cours
     * @return nombre de cours
     */
    Long getNbCours();

    /**
     * Nombre et liste des élèves présents à un cours
     * @param idCours identifiant du cours
     * @return statistiques
     */
    StatistiquesCoursDTO getStatistiquesCours(Long idCours) throws CoursNotFoundException;

    /**
     * Enregistrer les modifications d'un cours
     * @param cours
     * @return
     */
    Cours saveCours(Cours cours);
}
