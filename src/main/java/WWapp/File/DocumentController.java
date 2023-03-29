package WWapp.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class DocumentController {

    @Autowired
    private DocumentService fileService;

    //Wyświetlanie pliku html "file", w tym wszystkich dokumentów.

    @GetMapping("/file")
    public String get(Model model){
        List<Document> files = fileService.getAllFiles();
        model.addAttribute("files", files);
        return "file";
    }

    //Wgrywanie pliku do bazy
    @PostMapping("/upload")     //MultipartFile -wiele plików
    public String uploadManyFiles(@RequestParam("files") MultipartFile[] files){
        for(MultipartFile file: files) {
            fileService.saveFile(file);
        }
        return "redirect:/file";


        }
        //Pobieranie pliku z bazy danych
        @GetMapping("/download/{fileId}")
        public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable Long fileId){
        Document doc = fileService.getFile(fileId).get();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(doc.getType()))
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment:filename=\"" + doc.getName() + "\"")
                .body(new ByteArrayResource(doc.getData()));
        }

        //Proste usuwanie pliku z bazy danych
        @GetMapping("/delete/{fileId}")
    public String deleteFile(@PathVariable long fileId){
        //fileService.
            fileService.deleteFile(fileId);
            return "redirect:/file";
        }


    }




