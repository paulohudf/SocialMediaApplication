package com.integrative.socialMediaApplication.Repository;


import com.integrative.socialMediaApplication.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}