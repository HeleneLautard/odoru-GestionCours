package fr.miage.toulouse.m2.lautard.helene.gestioncours.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StatistiquesCoursDTO {
    int nbElevesPresent;
    List<ParticipantDTO> listePresents;
}
