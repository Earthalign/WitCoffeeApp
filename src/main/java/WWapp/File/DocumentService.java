package WWapp.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {


    @Autowired
    private DocumentRepository fileRepository;

    //Zapisywanie pliku
    public Document saveFile(MultipartFile file){
        String filename = file.getOriginalFilename();
        try{
            Document doc = new Document(filename, file.getContentType(),file.getBytes());
            return fileRepository.save(doc);
        }
        catch (Exception e){
            e.printStackTrace();

        }
        return null;
    }
    //Wyswietlanie jednego pliku
    public Optional<Document> getFile(Long fileId){
        return fileRepository.findById(fileId);
    }

    //Wszystkie pliki
    public List<Document> getAllFiles(){
        return fileRepository.findAll();
    }

    //Usuwanie pliku
    public void deleteFile(Long fileId) {
        fileRepository.deleteById(fileId);
    }
}
