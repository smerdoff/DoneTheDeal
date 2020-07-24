package models;

import lombok.*;

@Data
@AllArgsConstructor
@Builder
public class Product {
    String productName;
    String price;
}