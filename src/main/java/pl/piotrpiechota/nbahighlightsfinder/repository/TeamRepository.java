package pl.piotrpiechota.nbahighlightsfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.piotrpiechota.nbahighlightsfinder.entity.Team;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Integer> {

    Team findFirstByName(String name);
    List<Team> findAllByConference(String conference);
}
