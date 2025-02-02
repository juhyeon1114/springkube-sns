package study.springkubesnsimage.image;

import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("image") MultipartFile file) throws IOException {
        String imageId = imageService.store(file);
        return ResponseEntity.ok(imageId);
    }

    @GetMapping("/view/{imageId}")
    public ResponseEntity<Resource> getImage(
            @PathVariable("imageId") String imageId,
            @RequestParam(value = "thumbnail", defaultValue = "false") boolean isThumbnail
    ) {
        Resource image = imageService.get(imageId, isThumbnail);
        return image != null
                ? ResponseEntity.ok().contentType(MediaType.valueOf("image/jpeg")).body(image)
                : ResponseEntity.notFound().build();
    }

}
