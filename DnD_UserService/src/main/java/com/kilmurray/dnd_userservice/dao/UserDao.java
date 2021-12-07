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
    private String email;
    private Boolean isDm;
    private Long partyId;
//    private List<LocalDate> availability;

    private String role;

    public UserDao(String username, String email, Boolean isDm, Long partyId, List<LocalDate> availability) {
        this.username = username;
        this.email = email;
        this.isDm = isDm;
        this.partyId = partyId;
//        this.availability = availability;
    }

    public UserDao(String username, String email, Long partyId) {
        this.username = username;
        this.email = email;
        this.partyId = partyId;
        this.isDm = false;
    }

    public UserDao(String username, String email) {
        this.username = username;
        this.email = email;
        this.isDm = false;
    }
}
