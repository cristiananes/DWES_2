package com.marcosd.seguridad.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcosd.seguridad.beans.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
