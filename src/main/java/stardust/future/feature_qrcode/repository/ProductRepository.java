package stardust.future.feature_qrcode.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import stardust.future.feature_qrcode.entity.Product;

@Repository
public interface ProductRepository extends ReactiveMongoRepository<Product, String> {



}
