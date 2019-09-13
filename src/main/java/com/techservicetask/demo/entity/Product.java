package com.techservicetask.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product extends AbstractEntity {
    @Size(min = 1,max = 10)
    private String name;
    @Size(min = 1,max = 10)
    private String brand;
    @Size(min = 1,max = 10)
    private String price;
    @Size(min = 1,max = 10)
    private String quantity;

}
