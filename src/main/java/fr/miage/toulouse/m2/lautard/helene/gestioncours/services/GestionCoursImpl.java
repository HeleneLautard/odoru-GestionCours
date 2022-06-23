package fr.miage.toulouse.m2.lautard.helene.gestioncours.services;

import fr.miage.toulouse.m2.lautard.helene.gestioncours.DTO.ParticipantDTO;
import fr.miage.toulouse.m2.lautard.helene.gestioncours.DTO.StatistiquesCoursDTO;
import fr.miage.toulouse.m2.lautard.helene.gestioncours.entities.Cours;
import fr.miage.toulouse.m2.lautard.helene.gestioncours.exceptions.BadDateException;
import fr.miage.toulouse.m2.lautard.helene.gestioncours.exceptions.CoursNotFoundException;
import fr.miage.toulouse.m2.lautard.helene.gestioncours.exceptions.InscriptionException;
import fr.miage.toulouse.m2.lautard.helene.gestioncours.repositories.CoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class GestionCoursImpl implements GestionCours {


    @Autowired
    CoursRepository coursRepository;

    @Override
    public Cours creerCours(Cours cours) throws BadDateException {
        // Vérifier la date du cours
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date dateSaisie = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dateSaisie);
        c.add(Calendar.DATE, 7); // Ajout de 7 jours à la date du jour

        if (dateSaisie.compareTo(cours.getDate()) > 0) {
            throw new BadDateException("La date d’un cours doit toujours être supérieure de 7 jours calendaires par rapport à la date de saisie.");
        } else {
            return this.coursRepository.save(cours);
        }
    }

    @Override
    public Cours getCours(Long num_cours) throws CoursNotFoundException {
        if (this.coursRepository.existsById(num_cours)) {
            return this.coursRepository.findById(num_cours).get();
        } else {
            throw new CoursNotFoundException("Le cours n'existe pas");
        }
    }

    @Override
    public List<Cours> getListeCours() {
        return this.coursRepository.findAll();
    }

    @Override
    public Iterable<Cours> getCoursEnseignant(Long num_enseignant) {
        return this.coursRepository.findAllByIdEnseignant(num_enseignant);
    }

    @Override
    public Iterable<Cours> getCoursParticipant(ParticipantDTO participant) {
        participant.setPresence(true);
        List<Cours> list = this.coursRepository.findAllByListeParticipantsContains(participant);
        participant.setPresence(false);
        list.addAll(this.coursRepository.findAllByListeParticipantsContains(participant));
        return list;
    }

    @Override
    public void deleteCours(Long id) throws CoursNotFoundException {
        if(this.coursRepository.existsById(id)){
            Cours cours = this.coursRepository.findById(id).get();
            this.coursRepository.delete(cours);
        } else {
            throw new CoursNotFoundException("Le cours n'existe pas, impossible de le supprimer");
        }
    }

    @Override
    public Long getNbCours() {
        return this.coursRepository.count();
    }

    @Override
    public StatistiquesCoursDTO getStatistiquesCours(Long idCours) throws CoursNotFoundException {
        StatistiquesCoursDTO stats = new StatistiquesCoursDTO();
        int nbPresents = 0;
        ArrayList<ParticipantDTO> listePresents = new ArrayList<>();
        if(this.coursRepository.existsById(idCours)){
            Cours cours = this.coursRepository.findById(idCours).get();
            for(ParticipantDTO p:cours.getListeParticipants()){
                if(p.getPresence()){
                    nbPresents++;
                    listePresents.add(p);
                }
            }
            stats.setNbElevesPresent(nbPresents);
            stats.setListePresents(listePresents);
            return stats;
        } else {
            throw new CoursNotFoundException("Cours inconnu");
        }
    }

    @Override
    public Cours saveCours(Cours cours) {
        return this.coursRepository.save(cours);
    }
}
