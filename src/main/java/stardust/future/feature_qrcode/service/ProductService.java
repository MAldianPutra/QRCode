package stardust.future.feature_qrcode.service;

import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Mono;
import stardust.future.feature_qrcode.entity.Product;
import stardust.future.feature_qrcode.helper.FileUploadHelper;
import stardust.future.feature_qrcode.helper.QRCodeGenerator;
import stardust.future.feature_qrcode.repository.ProductRepository;

import java.io.IOException;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    FileUploadHelper fileUploadHelper;

    @Autowired
    QRCodeGenerator qrCodeGenerator;

    public Mono<Product> saveProduct(Product product, MultipartFile photo) {
        return Mono.just(product)
                .doOnNext((product1) -> {
                    try {
                        product.setProductPhotoLink(fileUploadHelper.uploadPhoto(photo, product.getProductId()));
                        product.setProductQrLink(qrCodeGenerator.generateQR("localhost:8080/qrcode/products/", product.getProductId()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                })
                .flatMap(product1 -> productRepository.save(product));
    }
}
