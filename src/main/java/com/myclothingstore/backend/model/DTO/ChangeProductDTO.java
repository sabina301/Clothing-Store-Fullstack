package com.myclothingstore.backend.model.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeProductDTO {

    private String productName;

    private String productDescription;

    private String productIcon;

    private Integer productPrice;

    private String productStatus;

}
