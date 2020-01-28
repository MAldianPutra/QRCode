package stardust.future.feature_qrcode.helper;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;
import java.io.*;
import org.springframework.http.MediaType;
import com.google.zxing.client.j2se.*;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

@Service
public class QRCodeGenerator {

    // Constant
    private static final Integer WIDTH = 400;
    private static final Integer HEIGHT = 400;

    // Directory
    private String temp = "Project/feature_qrcode/src/main/resources/";
    private String projectDir = "D:/" + temp;
    private String uploadDir = "uploads/productQR";

    public String generateQR(String text, String productId) {
        try {
        String qrLink = projectDir + uploadDir + productId + ".png";
            File file = new File(qrLink);
            if(!file.exists()) {
                file.mkdirs();
            }else {
                file.delete();
            }
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text + productId, BarcodeFormat.QR_CODE, WIDTH, HEIGHT);
        Path path = FileSystems.getDefault().getPath(qrLink);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        return qrLink;
        } catch (IOException | WriterException ex) {
            return ex.getMessage();
        }
    }
}
