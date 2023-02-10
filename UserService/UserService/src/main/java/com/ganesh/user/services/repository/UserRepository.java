package com.ganesh.user.services.repository;

import com.ganesh.user.services.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
