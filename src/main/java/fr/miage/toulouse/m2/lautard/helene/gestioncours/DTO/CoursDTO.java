package fr.miage.toulouse.m2.lautard.helene.gestioncours.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CoursDTO {

    private Long numCours;
    private String titre;
    private int niveau;
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    private Date date;
    private String lieu;
    private Long duree;
    private Long idEnseignant;
    List<ParticipantDTO> listeParticipants;

    public CoursDTO(String titre, int niveau,Date date, String lieu, Long duree, Long idEnseignant, List<ParticipantDTO> listeParticipants) {

        this.titre = titre;
        this.niveau = niveau;
        this.date = date;
        this.lieu = lieu;
        this.duree = duree;
        this.idEnseignant = idEnseignant;
        this.listeParticipants = listeParticipants;
    }
}
