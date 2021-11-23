package com.kilmurray.dnd_characterservice.repository;

import com.kilmurray.dnd_characterservice.dao.CharacterDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterDao, Long> {
}
