package com.kilmurray.dnd_createservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterDetailDTO {

    private String alignment;
    private String startingWeapon;
    private String startingArmour;
    private String startingGear;
    private String startingTrinket;

    private int strength;
    private int strengthModifier;
    private int dexterity;
    private int dexterityModifier;
    private int constitution;
    private int constitutionModifier;
    private int intelligence;
    private int intelligenceModifier;
    private int wisdom;
    private int wisdomModifier;
    private int charisma;
    private int charismaModifier;
    private int armourClass;

}
