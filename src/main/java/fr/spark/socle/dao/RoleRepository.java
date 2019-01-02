package fr.spark.socle.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.spark.socle.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	public Role findByRoleName(String roleName);
}
