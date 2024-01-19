package GDSCKNU.CitySavior.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Category {

    @Id
    @Column(name = "category_id")
    private Long category_id;
    private String name;
    private String description;
}
