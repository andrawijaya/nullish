package com.enigma.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;
@ToString
public class FormDataWithFile {
    @Getter @Setter
    private MultipartFile file;

}
