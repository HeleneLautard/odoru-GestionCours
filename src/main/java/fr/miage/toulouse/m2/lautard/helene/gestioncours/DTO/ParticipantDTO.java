package fr.miage.toulouse.m2.lautard.helene.gestioncours.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ParticipantDTO {

    private Long numMembre;
    private String NomPrenom;
    private boolean presence = false;

    public ParticipantDTO(Long id, String nomPrenom) {
        this.numMembre=id;
        this.NomPrenom = nomPrenom;
    }
}
