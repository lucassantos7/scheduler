package com.maverick.scheduler.repository;

import com.maverick.scheduler.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.security.core.userdetails.UserDetails;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    UserDetails findByEmail(String email); // O retorno como UserDetails é importante aqui
}