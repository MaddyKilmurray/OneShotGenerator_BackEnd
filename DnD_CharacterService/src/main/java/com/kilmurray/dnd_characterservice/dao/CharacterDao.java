package com.kilmurray.dnd_characterservice.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CharacterDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long playerId;
    private String characterName;
    private long partyId;
    private int level;
    private int experience;
    private String alignment;
    private String startingWeapon;
    private String startingArmour;
    @Column(name = "startingGear", columnDefinition = "longtext")
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
    @Column(name = "proficiency", columnDefinition = "longtext")
    private String proficiency;
    @Column(name = "weaponProficiencies", columnDefinition = "longtext")
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
}
