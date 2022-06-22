package fr.miage.toulouse.m2.lautard.helene.gestioncours.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import fr.miage.toulouse.m2.lautard.helene.gestioncours.DTO.ParticipantDTO;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.http.codec.multipart.Part;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Document(collection = "cours")
public class Cours {

    @Id
    private Long numCours;

    @Field
    private String titre;

    @Field
    private int niveau;

    @Field
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd/MM/yyyy")
    private Date date;

    @Field
    private String lieu;

    @Field
    private Long duree;

    @Field
    private Long idEnseignant;

    @Field
    List<ParticipantDTO> listeParticipants;

    public Cours(String titre, int niveau,Date date, String lieu, Long duree, Long idEnseignant, List<ParticipantDTO> listeParticipants) {

        this.titre = titre;
        this.niveau = niveau;
        this.date = date;
        this.lieu = lieu;
        this.duree = duree;
        this.idEnseignant = idEnseignant;
        this.listeParticipants = listeParticipants;
    }
}
