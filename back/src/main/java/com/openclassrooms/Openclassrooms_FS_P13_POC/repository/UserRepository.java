package com.openclassrooms.Openclassrooms_FS_P13_POC.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.Openclassrooms_FS_P13_POC.models.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	List<User> findAllByStatus(String string);

	User findByEmail(String email);

	User findByFirstName(String firstName);

}
