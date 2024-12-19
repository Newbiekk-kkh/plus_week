package com.example.demo.domain.item.entity;

import com.example.demo.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;


@Entity
@Getter
@DynamicInsert
// TODO: 6. Dynamic Insert
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToOne
    @JoinColumn(name = "manager_id")
    private User manager;

    @Column(nullable = false, columnDefinition = "varchar(20) default 'PENDING'")
    private String status;

    public Item(String name, String description, User manager, User owner) {
        this.name = name;
        this.description = description;
        this.manager = manager;
        this.owner = owner;
    }

    public void setStatus(String status) {
        if(status == null) {
            throw new IllegalArgumentException("status 는 Null 값이 허용되지 않습니다.");
        }
        this.status = status;
    }

    public Item() {}
}
