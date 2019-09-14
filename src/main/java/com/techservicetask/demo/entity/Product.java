package com.techservicetask.demo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product extends AbstractEntity {
    @Size(min = 1,max = 20)
    @NotNull
    private String name;
    @NotNull
    @Size(min = 1,max = 20)
    private String brand;
    @Size(min = 1,max = 20)
    @NotNull
    private String price;
    @NotNull
    private Long quantity;

}
