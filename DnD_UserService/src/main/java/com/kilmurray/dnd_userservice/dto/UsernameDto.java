package com.kilmurray.dnd_userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.SecondaryTable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsernameDto {
    public String email;
}
