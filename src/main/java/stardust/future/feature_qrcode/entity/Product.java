package stardust.future.feature_qrcode.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "product")
public class Product {

    @Id
    @Field(value = "productId")
    private String productId;

    @Field(value = "productName")
    private String productName;

    @Field(value = "productPhotoLink")
    private String productPhotoLink;

    @Field(value = "productQRLink")
    private String productQrLink;

}
