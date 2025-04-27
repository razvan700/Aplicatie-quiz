package com.jetbrains.aplicatiequiz.repositories;

import com.jetbrains.aplicatiequiz.models.Choice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice, Long> {

}
