package fr.miage.toulouse.m2.lautard.helene.gestioncours.services;

import fr.miage.toulouse.m2.lautard.helene.gestioncours.entities.Cours;
import fr.miage.toulouse.m2.lautard.helene.gestioncours.exceptions.BadDateException;
import fr.miage.toulouse.m2.lautard.helene.gestioncours.exceptions.CoursNotFoundException;
import fr.miage.toulouse.m2.lautard.helene.gestioncours.exceptions.InscriptionException;
import fr.miage.toulouse.m2.lautard.helene.gestioncours.repositories.CoursRepository;
import fr.miage.toulouse.m2.lautard.helene.gestioncours.rest.CoursRestController;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

@Service
public class GestionCoursImpl implements GestionCours {


    @Autowired
    CoursRepository coursRepository;

    @Override
    public Cours creerCours(Cours cours) throws BadDateException {
        // Vérifier la date du cours
       /* Date jour = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(jour);
        c.add(Calendar.DATE, 7);
        Date newDate = c.getTime();

        if (newDate.compareTo(cours.getDate()) > 0) {
            throw new BadDateException("La date d’un cours doit toujours être supérieure de 7 jours calendaires par rapport à la date de saisie.");
        } else {*/
            return this.coursRepository.save(cours);
       // }
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
    public Cours inscriptionCours(Long num_cours, Long num_participant) throws CoursNotFoundException, InscriptionException {
        if(this.coursRepository.existsById(num_cours)){
            Cours cours = this.coursRepository.findById(num_cours).get();
            cours.getListeParticipants().add(num_participant);
            return this.coursRepository.save(cours);
        } else {
            throw new CoursNotFoundException("Le cours n'existe pas");
        }
    }

    @Override
    public Iterable<Cours> getCoursEnseignant(Long num_enseignant) {
        return this.coursRepository.findAllByIdEnseignant(num_enseignant);
    }

    @Override
    public Iterable<Cours> getCoursParticipant(Long num_participant) {
        return this.coursRepository.findAllByListeParticipantsContains(num_participant);
    }
}
