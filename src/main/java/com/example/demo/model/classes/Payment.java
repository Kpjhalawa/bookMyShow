package com.example.demo.model.classes;

import com.example.demo.model.enums.PaymentGetWay;
import com.example.demo.model.enums.PaymentMode;
import com.example.demo.model.enums.PaymentStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Payment extends BaseModel {
    private String ref_no;
    @Enumerated(EnumType.ORDINAL)
    private PaymentStatus paymentStatus;
    @Enumerated(EnumType.ORDINAL)
    private PaymentMode paymentMode;
    @Enumerated(EnumType.ORDINAL)
    private PaymentGetWay paymentGetWay;
    private int amount;

}
