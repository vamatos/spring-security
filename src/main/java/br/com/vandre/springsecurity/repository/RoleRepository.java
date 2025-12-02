package br.com.vandre.springsecurity.repository;

import br.com.vandre.springsecurity.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {

    Role findByName(String name);
}
