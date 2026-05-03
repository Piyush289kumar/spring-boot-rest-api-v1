package com.restcrudv1.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.restcrudv1.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Optional<Role> findByName(String name);
}
