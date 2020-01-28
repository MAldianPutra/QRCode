package stardust.future.feature_qrcode.controller;

import com.google.zxing.WriterException;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import stardust.future.feature_qrcode.entity.Product;
import stardust.future.feature_qrcode.helper.QRCodeGenerator;
import stardust.future.feature_qrcode.service.ProductService;

import java.io.IOException;

@Api
@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    QRCodeGenerator qrCodeGenerator;

    @PostMapping("/products")
    public Mono<Product> insertProduct(@RequestBody Product product, @RequestParam MultipartFile photo) {
        return productService.saveProduct(product, photo).subscribeOn(Schedulers.elastic());
    }

    @PostMapping("/qr")
    public String generateQR(@RequestParam String id) throws IOException, WriterException {
        return qrCodeGenerator.generateQR("localhost:8080/products/id= ", id );
    }

}
