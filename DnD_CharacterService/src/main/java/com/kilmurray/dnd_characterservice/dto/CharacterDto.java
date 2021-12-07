package com.kilmurray.dnd_characterservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterDto {

    private Long id;
    private Long playerId;
    private String characterName;
    private Long partyId;
    private int level;
    private int experience;
    private String alignment;
    private String startingWeapon;
    private String startingArmour;
    private String startingGear;
    private String startingTrinket;

    // Cannot be updated
    private int numberOfHitDice;

    // Race Bonuses - Cannot be updated
    private String race;
    private int speed;
    private String abilityScore;
    private int abilityBonus;
    private String size;
    private String proficiency;
    private String weaponProficiencies;
    private String languages;
    private String traits;

    // Class Bonuses - Cannot be updated
    private String charClass;
    private int hitDice;
    private int hitPoints;
    private String classSkills;
    private String classProficiencies;
    private String savingThrows;
    private String spellCasting;

    // Player stats - Cannot be updated
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;
    private int armourClass;
    private int strengthModifier;
    private int dexterityModifier;
    private int constitutionModifier;
    private int intelligenceModifier;
    private int wisdomModifier;
    private int charismaModifier;

//    Optional:
//    private String ideals;
//    private String bonds;
//    private String flaws;
//    private String background;
}
