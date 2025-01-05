package com.integrative.socialMediaApplication.Repository;



import com.integrative.socialMediaApplication.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}