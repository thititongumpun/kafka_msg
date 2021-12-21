package com.github.thititongumpun.SpringLabs.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "users")
public class User extends AbstractEntity{
    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;
}
