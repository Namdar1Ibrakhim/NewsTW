package com.example.newstw.repository;

import com.example.newstw.entity.Category;
import com.example.newstw.entity.News;
import com.example.newstw.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Long>{

    @Query("SELECT n FROM News n " +
            "WHERE (:title IS NULL OR n.title LIKE %:title%) " +
            "AND (:description IS NULL OR n.description LIKE %:description%) " +
            "AND (:imageUrl IS NULL OR n.imageUrl LIKE %:imageUrl%) " +
            "AND (:numberOfLikes IS NULL OR n.numberOfLikes = :numberOfLikes)" +
            "AND (n.status = 'ACTIVE')" )
    List<News> filterNews(
            @Param("title") String title,
            @Param("description") String description,
            @Param("imageUrl") String imageUrl,
            @Param("numberOfLikes") String numberOfLikes
    );

    @Query("SELECT n FROM News n WHERE n.id = :id AND n.status='ACTIVE'")
    Optional<News> findById(Long id);

    List<News> getByStatus(Status status);

    Optional<News> findByCategory(Category category);
}
