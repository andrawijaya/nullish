package com.enigma.Service;

import com.enigma.repository.IFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class CourseUploadService implements ICourseUploadService {

    private IFileRepository fileRepository;

    public CourseUploadService(@Autowired IFileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    @Override
    public void uploadMaterial(MultipartFile multipartFile) {
        fileRepository.store(multipartFile);
    }

    @Override
    public Resource downloadMaterial(String fileName) {
        return fileRepository.load(fileName);
    }
}
