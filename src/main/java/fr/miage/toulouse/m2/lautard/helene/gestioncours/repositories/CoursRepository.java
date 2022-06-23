package fr.miage.toulouse.m2.lautard.helene.gestioncours.repositories;


import fr.miage.toulouse.m2.lautard.helene.gestioncours.DTO.ParticipantDTO;
import fr.miage.toulouse.m2.lautard.helene.gestioncours.entities.Cours;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CoursRepository extends MongoRepository<Cours, Long> {

    List<Cours> findAllByIdEnseignant(Long idEnseignant);

    List<Cours> findAllByListeParticipantsContains(ParticipantDTO Participant);
}
