package com.jetbrains.aplicatiequiz.repositories;

import com.jetbrains.aplicatiequiz.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question,Long> {

}
