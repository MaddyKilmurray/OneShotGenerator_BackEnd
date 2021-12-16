<img src="https://github.com/MaddyKilmurray/OneShotGenerator_BackEnd/blob/main/OneShotGenerator.png" alt="An impressive, roaring dragon on a black background, with text in front. Text reads: 'One-Shot Generator: Created in Spring Boot and Angular. Written in Java, SQL, TypeScript, HTML,CSS and Javascript'">

####

#### All the fun of a Dungeons & Dragons game, without the heavy time commitment.

Created in 1972, Dungeons & Dragons is an adventure role-playing game which has captured the imaginations of people all of the world. The premise is simple: A group of friends tell a story together, guiding your heroes through quests for treasure, battles with deadly foes, daring rescues, courtly intrigue, and much more.

It been mentioned in movies and TV, been the start of many podcasts, and a countless amount of games amongst friends.

Many people struggle to play due to the heavy time commitment. A campaign can last anything from weeks to years! Why not play a one-shot instead?

Designed to be a short version of a standard D&D campaign, a one shot is designed to be played in a few hours, an evening, or at most a day. Stories are short and easy to resolve, and creating a character is fun and quirky.

#### Why Should You Use The D&D One-Shot Generator?

Part of the magic of a one-shot is the amount of time saved. The last thing a user would want to do is spend longer creating a character than they would playing it!

The D&D One-Shot Generator allows a player to randomly generate a character, assign them to a party and to print the details to a PDF file. A Games Master can keep a track of their players' characters with the party ID, generate a new party ID and generate their own characters as well.

The Generator is simple, reliable and fun.

#### Built With:

Technologies used in this project:

- [Java](https://www.java.com/)
- [Spring](https://spring.io/)
- [MySQL](https://www.mysql.com/)
- [GitHub](https://github.com/)
- [Node.js](https://nodejs.org/en/)
- [Angular](https://angular.io/)

### Setup

#### Backend Application

- Clone the backend repository: [https://github.com/MaddyKilmurray/OneShotGenerator_BackEnd](https://github.com/MaddyKilmurray/OneShotGenerator_BackEnd)
- Setup the following database name and user, or setup your own database by changing the values in the `application.properties` file

```sql
CREATE DATABASE OneShotGeneratorDatabase;

CREATE USER 'SysAdmin'@'localhost' IDENTIFIED BY '5ys@m1n';
GRANT ALL PRIVILEGES ON *.* TO 'SysAdmin'@'localhost';
FLUSH PRIVILEGES;
```

- Open each service, right-click on the pom.xml file and select "Add as Maven project"
- Select "Trust Project"
- Run each service

#### Frontend Application

- Clone the frontend repository: [https://github.com/MaddyKilmurray/OneShotGenerator-FrontEnd](https://github.com/MaddyKilmurray/OneShotGenerator-FrontEnd)
- Install the dependencies: `npm install`
- Run the following command to start the frontend application: `npm start`
- The application will be available at: [http://localhost:4200/](http://localhost:4200/)

## Services

### Edge Services

| API Route | Route Type | Route Description | Input | Output |
| --- | --- | --- | --- | --- |
| localhost:8600/api/create/view | POST | Generate a New Complete Character | Character Incoming DTO | Character Outgoing DTO |
| localhost:8600/api/create/save | POST | Save a Complete Character | Character Outgoing DTO | NONE |

### DTOs

#### Character Incoming DTO

```javascript
{
    id: long,
    playerId: long,
    characterName: String,
    partyId: long,

    race: int,
    speed:int,
    abilityScore: String,
    abilityBonus: int,
    size: String,
    proficiency: String,
    weaponProficiencies: String,
    languages: String,
    traits: String,

    charClass: String,
    hitDice: int,
    hitPoints: int,
    classSkills: String,
    classProficiencies: String,
    savingThrows: String,
    spellCasting: String,
}
```

#### Character Outgoing DTO

```javascript
{
    id: long,
    playerId: long,
    characterName: String,
    partyId: long,
    level: int,
    experience: int,
    alignment: String,
    startingWeapon: String,
    startingArmour: String,
    startingGear: String,
    startingTrinket: String,

    numberOfHitDice: int,
    race: int,
    speed:int,
    abilityScore: String,
    abilityBonus: int,
    size: String,
    proficiency: String,
    weaponProficiencies: String,
    languages: String,
    traits: String,

    charClass: String,
    hitDice: int,
    hitPoints: int,
    classSkills: String,
    classProficiencies: String,
    savingThrows: String,
    spellCasting: String,

    strength: int,
    dexterity: int,
    constitution: int,
    intelligence: int,
    wisdom: int,
    charisma: int,
    armourClass: int,
    strengthModifier: int,
    dexterityModifier: int,
    constitutionModifier: int,
    intelligenceModifier: int,
    wisdomModifier: int,
    charismaModifier: int
}
```

### Randomiser Service

| API Routes | Route Type | Route Description | Input | Output |
| --- | --- | --- | --- | --- |
| localhost:8390/api/random/d4 | GET | Returns a random number between 1 and 4 | NONE | Int |
| localhost:8390/api/random/d6 | GET | Returns a random number between 1 and 6 | NONE | Int |
| localhost:8390/api/random/d8 | GET | Returns a random number between 1 and 8 | NONE | Int |
| localhost:8390/api/random/d12 | GET | Returns a random number between 1 and 12 | NONE | Int |
| localhost:8390/api/random/d20 | GET | Returns a random number between 1 and 20 | NONE | Int |
| localhost:8390/api/random/d100 | GET | Returns a random number between 1 and 100 | NONE | Int |
| localhost:8390/api/random/weapon | GET | Returns a random weapon from an existing list | NONE | String |
| localhost:8390/api/random/armour | GET | Returns a random armour from an existing list | NONE | String |
| localhost:8390/api/random/dragon | GET | Returns a random dragon fact from an existing list | NONE | Fact DTO |
| localhost:8390/api/random/gear | GET | Returns a random gear set from an existing list | NONE | String |
| localhost:8390/api/random/gear/{gear} | GET | Returns a requested gear from the existing gear list | Gear Name | String |
| localhost:8390/api/random/trinket | GET | Returns a random trinket from an existing list | NONE | String |
| localhost:8390/api/random/newStat | GET | Returns a number between 3 and 18 | NONE | Int |
| localhost:8390/api/random/alignment | GET | Returns a random alignment from an existing list | NONE | String |
| localhost:8390/api/random/statModifer/{stat} | GET | Returns the stat modifier value, depending on the value supplied | Int | Int |
| localhost:8390/api/random/generateCharDetails | GET | Returns all randomised character details in a DTO | NONE | Character DTO |

### <u>DTOs</u>

#### Character DTO

```javascript
{
    id: long,
    playerId: long,
    characterName: String,
    partyId: long,
    level: int,
    experience: int,
    alignment: String,
    startingWeapon: String,
    startingArmour: String,
    startingGear: String,
    startingTrinket: String,

    numberOfHitDice: int,
    race: int,
    speed:int,
    abilityScore: String,
    abilityBonus: int,
    size: String,
    proficiency: String,
    weaponProficiencies: String,
    languages: String,
    traits: String,

    charClass: String,
    hitDice: int,
    hitPoints: int,
    classSkills: String,
    classProficiencies: String,
    savingThrows: String,
    spellCasting: String,

    strength: int,
    dexterity: int,
    constitution: int,
    intelligence: int,
    wisdom: int,
    charisma: int,

    armourClass: int,
    strengthModifier: int,
    dexterityModifier: int,
    constitutionModifier: int,
    intelligenceModifier: int,
    wisdomModifier: int,
    charismaModifier: int
}
```

### Fact DTO

```javascript
{
    fact: String
}
```

### Repository Services

#### Character Service

| API Route | Route Type | Route Description | Input | Output |
| --- | --- | --- | --- | --- |
| localhost:8330/api/character | GET | Return All Characters | NONE | List<CharacterDao> |
| localhost:8330/api/character/{id} | GET | Return Character By ID | Character ID | CharacterDTO |
| localhost:8330/api/character/byPlayer/{id} | GET | Return a List of Characters by Player ID | Player ID | List<CharacterDTO> |
| localhost:8330/api/character/byPartyId/{id} | GET | Return a list of Characters by Party ID | Party ID | List<CharacterDTO> |
| localhost:8330/api/character/user/byPartyId/{id} | GET | Return a list of Characters by Party ID with User Information | Party ID | List<CharacterWithUserDTo> |
| localhost:8330/api/character/create | POST | Save a new character | CharacterDTO | NONE |
| localhost:8330/api/character/update/{charId} | PATCH | Update an Existing Character | Character ID, OPTIONAL: Character Name, Level, Experience, Alignment, Starting Weapon, Starting Armour, Starting Gear, Starting Trinker | CharacterDTO |
| localhost:8330/api/character/delete/{id} | DELETE | Delete an Existing Character | Character ID | NONE |

#### <u>DTOs</u>

#### Character DTO

```javascript
{
    id: long,
    playerId: long,
    characterName: String,
    partyId: long,
    level: int,
    experience: int,
    alignment: String,
    startingWeapon: String,
    startingArmour: String,
    startingGear: String,
    startingTrinket: String,

    numberOfHitDice: int,
    race: int,
    speed:int,
    abilityScore: String,
    abilityBonus: int,
    size: String,
    proficiency: String,
    weaponProficiencies: String,
    languages: String,
    traits: String,

    charClass: String,
    hitDice: int,
    hitPoints: int,
    classSkills: String,
    classProficiencies: String,
    savingThrows: String,
    spellCasting: String,

    strength: int,
    dexterity: int,
    constitution: int,
    intelligence: int,
    wisdom: int,
    charisma: int,

    armourClass: int,
    strengthModifier: int,
    dexterityModifier: int,
    constitutionModifier: int,
    intelligenceModifier: int,
    wisdomModifier: int,
    charismaModifier: int
}
```

#### Character With User DTO

```javascript
{
    id: long,
    playerId: long,
    playerUsername: String,
    characterName: String,
    partyId: long,
    level: int,
    experience: int,
    alignment: String,
    startingWeapon: String,
    startingArmour: String,
    startingGear: String,
    startingTrinket: String,

    numberOfHitDice: int,
    race: int,
    speed:int,
    abilityScore: String,
    abilityBonus: int,
    size: String,
    proficiency: String,
    weaponProficiencies: String,
    languages: String,
    traits: String,

    charClass: String,
    hitDice: int,
    hitPoints: int,
    classSkills: String,
    classProficiencies: String,
    savingThrows: String,
    spellCasting: String,

    strength: int,
    dexterity: int,
    constitution: int,
    intelligence: int,
    wisdom: int,
    charisma: int,
    armourClass: int,
    strengthModifier: int,
    dexterityModifier: int,
    constitutionModifier: int,
    intelligenceModifier: int,
    wisdomModifier: int,
    charismaModifier: int
}
```

### User Service

| API Routes | Route Type | Route Description | Input | Output |
| --- | --- | --- | --- | --- |
| localhost:8300/api/users | GET | Return a list of all users | NONE | List<UserDTO> |
| localhost:8300/api/users/{id} | GET | Return a user by ID | User ID | User DTO |
| localhost:8300/api/users/by_email/{email} | GET | Return a user by email address | Email | User DTO |
| localhost:8300/api/users/register | POST | Create and save a user | User DTO | User DTO |
| localhost:8300/api/users/update/{email} | PATCH | Update a user by email | User Email, OPTIONAL: Email, is DM, Party ID | UserDTO |
| localhost:8300/api/users/update/partyId/{email} | PUT | Update a user's party ID | User Email, Party ID | User DTO |
| localhost:8300/api/users/validate/username | POST | Validate if a username already exists in server | Username DTO | Boolean |
| localhost:8300/api/users/validate/email | POST | Validate if an email already exists in server | Email DTO | Boolean |
| localhost:8300/api/users/characters/{email} | GET | Return a list of Characters by their email address | User Email | List<CharacterDTO> |

#### DTOs

#### User DTO

```javascript
{
    id: long,
    username: String,
    email: String,
    isDm: boolean,
    partyId: Long
}
```

#### Username DTO

```javascript
{
    username: String
}
```

#### Email DTO

```javascript
{
    email: String
}
```

###
