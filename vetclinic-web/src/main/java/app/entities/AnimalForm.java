package app.entities;

import org.springframework.web.multipart.MultipartFile;

public class AnimalForm {
    public String name;
    public Integer age;
    public String description;
    public MultipartFile file;
}
