package com.example.newstw.repository;

import com.example.newstw.entity.Category;
import com.example.newstw.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsRepository extends JpaRepository<Comments, Long>{

}
