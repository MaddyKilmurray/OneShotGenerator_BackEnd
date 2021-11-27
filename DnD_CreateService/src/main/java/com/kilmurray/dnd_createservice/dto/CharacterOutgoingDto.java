package com.kilmurray.dnd_createservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CharacterOutgoingDto {

    private Long id;
    private Long playerId;
    private String characterName;
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

    public CharacterOutgoingDto(Long id, Long playerId, String characterName, String race, int speed,
                                String abilityScore, int abilityBonus,
                                String size, String weaponProficiencies, String proficiency,
                                String languages, String traits, String charClass, int hitDice,
                                String classSkills, String classProficiencies, String savingThrows) {
        this.id = id;
        this.playerId = playerId;
        this.characterName = characterName;
        this.race = race;
        this.speed = speed;
        this.abilityScore = abilityScore;
        this.abilityBonus = abilityBonus;
        this.size = size;
        this.weaponProficiencies = weaponProficiencies;
        this.proficiency = proficiency;
        this.languages = languages;
        this.traits = traits;
        this.charClass = charClass;
        this.hitDice = hitDice;
        this.classSkills = classSkills;
        this.classProficiencies = classProficiencies;
        this.savingThrows = savingThrows;
        this.level = 1;
        this.experience = 0;
        this.numberOfHitDice = 1;
    }

}
