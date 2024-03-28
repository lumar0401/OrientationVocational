package com.co.orientationVocational.app.services.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

@Repository
public interface fileRepository {
	public void init();
	public void save(MultipartFile file);
	public Resource load(String filename);
	public void deleteAll();
	public Stream<Path> loadAll();
	public String deleteFile(String filename);
}
