package fr.miage.toulouse.m2.lautard.helene.gestioncours.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

public class CoursDTO {

    private Long numCours;
    private String titre;
    private int niveau;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    private Date date;
    private String lieu;
    private Long duree;
    private Long idEnseignant;
    List<Long> listeParticipants;

    public CoursDTO(String titre, int niveau,Date date, String lieu, Long duree, Long idEnseignant, List<Long> listeParticipants) {

        this.titre = titre;
        this.niveau = niveau;
        this.date = date;
        this.lieu = lieu;
        this.duree = duree;
        this.idEnseignant = idEnseignant;
        this.listeParticipants = listeParticipants;
    }
}
