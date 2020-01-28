package stardust.future.feature_qrcode.helper;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileUploadHelper {

    private String temp = "Project/feature_qrcode/src/main/resources";
    private String projectDir = "D:/" + temp;
    private String uploadDir = "uploads/productPhoto";

    public String uploadPhoto(MultipartFile photo, String productId) throws IOException {
        String photoLink = projectDir + uploadDir + productId;
        File file = new File(photoLink);
        if(!file.exists()) {
            file.mkdirs();
        }else {
            file.delete();
        }
        photo.transferTo(file);
        return photoLink;
    }

}
