package com.kilmurray.dnd_userservice.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String email;
    private Boolean isDm;
    private Long partyId;
//    private List<LocalDate> availability;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Roles> roles = new HashSet<>();

    public UserDao(String username, String password, String email, Boolean isDm, Long partyId, List<LocalDate> availability) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isDm = isDm;
        this.partyId = partyId;
//        this.availability = availability;
    }

    public UserDao(String username, String password, String email, Long partyId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.partyId = partyId;
        this.isDm = false;
    }

    public UserDao(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.isDm = false;
    }
}
