package com.crudvideojuegos.model.language;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguageFacade extends JpaRepository<Language, Long> {
    List<Language> findLanguageByLocale(String locale);
}
