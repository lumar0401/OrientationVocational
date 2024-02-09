package com.co.orientationVocational.app.services.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.co.orientationVocational.app.services.models.usuarioIcfes;

@Repository
public interface usuarioIcfesRepository extends JpaRepository<usuarioIcfes, Integer>{

}
