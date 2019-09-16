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
    @Size(max = 20)
    @NotNull
    private String name;
    @Size(max = 20)
    @NotNull
    private String brand;
    @Size(max = 20)
    @NotNull
    private String price;
    @Size(max = 20)
    @NotNull
    private Long quantity;

}
