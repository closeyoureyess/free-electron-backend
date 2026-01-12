package com.electron.handle.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Data
@Builder
@Entity
@Table(name = "user_table")
public class UserEntity {

    @Id
    @UuidGenerator
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "bio")
    private String bio;

    @Column(name = "nick_name")
    private String nickName;

    @Column(nullable = false, name = "email")
    private String email;

    @Column(name = "number_phone")
    private String numberPhone;

}
