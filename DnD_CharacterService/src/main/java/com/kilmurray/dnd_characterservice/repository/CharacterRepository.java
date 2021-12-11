package com.kilmurray.dnd_characterservice.repository;

import com.kilmurray.dnd_characterservice.dao.CharacterDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterDao, Long> {

    List<CharacterDao> findCharacterDaoByPlayerId(Long id);

    List<CharacterDao> findCharacterDaoByPartyId(Long partyID);

    List<CharacterDao> findCharacterDaoByEmail(String email);
}
