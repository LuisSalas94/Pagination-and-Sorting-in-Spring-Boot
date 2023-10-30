package net.fernandosalas.posts.repository;

import net.fernandosalas.posts.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
