package com.kilmurray.dnd_userservice.repository;

import com.kilmurray.dnd_userservice.dao.UserDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserDao, Long> {

    Optional<UserDao> findByUsername(String username);

    UserDao getByUsername(String username);

    Optional<UserDao> findByEmail(String email);

    List<UserDao> findByPartyId(Long partyId);
}
