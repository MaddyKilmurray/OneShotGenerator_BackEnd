package com.example.dnd_createservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterIncomingDto {

    private Long id;
    private Long playerId;
    private String characterName;
    private String alignment;

    // Race Bonuses - Cannot be updated
    private String race;
    private int speed;
    private String abilityScore;
    private int abilityBonus;
    private String size;
    private String weaponProficiencies;
    private String proficiency;
    private String languages;
    private String traits;

    // Class Bonuses - Cannot be updated
    private String charClass;
    private int hitDice;
    private int hitPoints;
    private String classSkills;
    private String classProficiencies;
    private String savingThrows;

//    Optional:
//    private String ideals;
//    private String bonds;
//    private String flaws;
//    private String background;
}
