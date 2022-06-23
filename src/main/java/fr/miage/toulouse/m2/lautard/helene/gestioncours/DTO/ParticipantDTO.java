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
    private Boolean presence;

    public ParticipantDTO(Long id, String nomPrenom) {
        this.presence=false;
        this.numMembre=id;
        this.NomPrenom = nomPrenom;
    }
}
