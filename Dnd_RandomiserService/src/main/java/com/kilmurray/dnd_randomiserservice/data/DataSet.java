package com.kilmurray.dnd_randomiserservice.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DataSet {

    private String[][] starterWeapons = {
            {"Club","Simple Melee"},{"Dagger","Simple Melee"},{"Greatclub","Simple Melee"},
            {"Handaxe","Simple Melee"}, {"Javelin","Simple Melee"}, {"Light Hammer","Simple Melee"},
            {"Mace","Simple Melee"}, {"Quarterstaff","Simple Melee"}, {"Sickle","Simple Melee"},
            {"Spear","Simple Melee"}, {"Light Crossbow","Simple Ranged"}, {"Dart","Simple Ranged"},
            {"Shortbow","Simple Ranged"}, {"Sling","Simple Ranged"},
            {"Battleaxe","Martial Melee"},{"Flail","Martial Melee"},{"Glaive","Martial Melee"},
            {"Greataxe","Martial Melee"}, {"Greatsword","Martial Melee"}, {"Halberd","Martial Melee"},
            {"Lance","Martial Melee"}, {"Longsword","Martial Melee"}, {"Maul","Martial Melee"},
            {"Morningstar","Martial Melee"},{"Pike","Martial Melee"},{"Rapier","Martial Melee"},
            {"Scimitar","Martial Melee"}, {"Shortsword","Martial Melee"}, {"Trident","Martial Melee"},
            {"War pick","Martial Melee"}, {"Warhammer","Martial Melee"}, {"Whip","Martial Melee"},
            {"Blowgun","Martial Ranged"}, {"Hand crossbow","Martial Ranged"}, {"Heavy crossbow","Martial Ranged"},
            {"Longbow","Martial Ranged"}, {"Net","Martial Ranged"}
    };
    private String[][] starterArmour = {
            {"Padded","Light Armour"}, {"Leather","Light Armour"}, {"Studded leather","Light Armour"},
            {"Hide","Medium Armour"}, {"Chain shirt","Medium Armour"}, {"Scale mail","Medium Armour"},
            {"Breastplate","Medium Armour"}, {"Half plate","Medium Armour"}, {"Ring mail","Heavy Armour"},
            {"Chain mail","Heavy Armour"}, {"Splint","Heavy Armour"}, {"Plate","Heavy Armour"},
            {"Shield","Shield"}
    };
    private String[] starterGear = {
            "Burglar's pack: A backpack, a bag of 1000 ball bearings, 10 feet of string, a bell, 5 candles, a crowbar, a hammer, 10 pitons, a hooded lantern, 2 flasks of oil, 5 days of rations, a tinderbox, a waterskin, and 50 feet of hempen rope",
            "Diplomat's pack: A chest, 2 cases for maps and scrolls, a set of fine clothes, a bottle of ink, an ink pen, a lamp, 2 flasks of oil, 5 sheets of paper, a vial of perfume, sealing wax, and soap",
            "Dungeoneer's pack: A backpack, a crowbar, a hammer, 10 pitons, 10 torches, a tinderbox, 10 days of rations, a waterskin, and 50 feet of hempen rope",
            "Explorer's pack: A backpack, a bedroll, a mess kit, a tinderbox, 10 torches, 10 days of rations, a waterskin, and 50 feet of hempen rope",
            "Entertainer's pack: A backpack, a bedroll, 2 costumes, 5 candles, 5 days of rations, a waterskin, and a disguise kit",
            "Priest's pack: A backpack, a blanket, 10 candles, a tinderbox, an alms box, 2 block of incense, a censer, vestments, 2 days of rations, and a waterskin",
            "Scholar's pack: A backpack, a book of lore, a bottle of ink, an ink pen, 10 sheets of parchment, a little bag of sand, and a small knife"
    };
    private String[] starterTrinkets = {
            "A mummified goblin hand", "A piece of crystal that faintly glows in the moonlight","A gold coin minted in an unknown land",
            "A diary written in a language you don't know","A brass ring that never tarnishes","An old chess piece made of glass",
            "A pair of knuclebone dice, each with a skull symbol on the side that would normally show six pips","A small idol depicting a nightmarish creature that gives you unsettling dreams when you sleep near it",
            "A rope necklace from which dangles four mummified elf fingers","The deed for a parcel of land in a realm unknown to you","A 1-ounce block made from an unknown material",
            "A small cloth doll skewered with needles","A tooth from an unknown beast","An enormous scale, perhaps from a dragon","A bright green feather",
            "An old divination card bearing your likeness","A glass orb filled with moving smoke","A 1-pound egg with a bright red shell","A pipe that blows bubbles",
            "A glass jar containing a weird bit of flesh floating in pickling fluid","A tiny gnome-crafted music box that plays a song you dimly remember from your childhood","A small wooden statuette of a smug halfling",
            "A brass orb etched with strange runes","A multicolored stone disk","A tiny silver icon of a raven","A bag containing forty-seven humanoid teeth, one of which is rotten","A shard of obsidian that always feels warm to the touch",
            "A dragon's bony talon hanging from a plain leather necklace","A pair of old socks","A blank book whose pages refuse to hold ink, chalk, graphite, or any other substance or marking","A silver badge in the shape of a five-pointed star",
            "A knife that belonged to a relative","A glass vial filled with nail clippings","A rectangular metal device with two tiny metal cups on one end that throws sparks when wet","A white, sequined glove sized for a human",
            "A vest with one hundred tiny pockets","A small, weightless stone block","A tiny sketch portrait of a goblin","An empty glass vial that smells of perfume when opened","A gemstone that looks like a lump of coal when examined by anyone but you",
            "A scrap of cloth from an old banner","A rank insignia from a lost legionnaire","A tiny silver bell without a clapper","A mechanical canary inside a gnome-crafted lamp","A tiny chest carved to look like it has numerous feet on the bottom",
            "A dead sprite inside a clear glass bottle","A metal can that has no opening but sounds as if it is filled with liquid, sand, spiders, or broken glass (your choice)","A glass orb filled with water, in which swims a clockwork goldfish","A silver spoon with an M engraved on the handle",
            "A whistle made from gold-colored wood","A dead scarab beetle the size of your hand","Two toy soldiers, one with a missing head","A small box filled with different-sized buttons","A candle that can???t be lit",
            "A tiny cage with no door","An old key","An indecipherable treasure map","A hilt from a broken sword","A rabbit???s foot","A glass eye","A cameo carved in the likeness of a hideous person","A silver skull the size of a coin","An alabaster mask","A pyramid of sticky black incense that smells very bad",
            "A nightcap that, when worn, gives you pleasant dreams","A single caltrop made from bone","A gold monocle frame without the lens","A 1-inch cube, each side painted a different color","A crystal knob from a door","A small packet filled with pink dust","A fragment of a beautiful song, written as musical notes on two pieces of parchment",
            "A silver teardrop earring made from a real teardrop","The shell of an egg painted with scenes of human misery in disturbing detail","A fan that, when unfolded, shows a sleeping cat","A set of bone pipes","A four-leaf clover pressed inside a book discussing manners and etiquette","A sheet of parchment upon which is drawn a complex mechanical contraption",
            "An ornate scabbard that fits no blade you have found so far","An invitation to a party where a murder happened","A bronze pentacle with an etching of a rat's head in its center","A purple handkerchief embroidered with the name of a powerful archmage","Half of a floorplan for a temple, castle, or some other structure",
            "A bit of folded cloth that, when unfolded, turns into a stylish cap","A receipt of deposit at a bank in a far-flung city","A diary with seven missing pages","An empty silver snuffbox bearing an inscription on the surface that says \"dreams\"",
            "An iron holy symbol devoted to an unknown god","A book that tells the story of a legendary hero's rise and fall, with the last chapter missing","A vial of dragon blood","An ancient arrow of elven design","A needle that never bends",
            "An ornate brooch of dwarven design","An empty wine bottle bearing a pretty label that says, \"The Wizard of Wines Winery, Red Dragon Crush, 331422-W\"","A mosaic tile with a multicolored, glazed surface","A petrified mouse","A black pirate flag adorned with a dragon's skull and crossbones",
            "A tiny mechanical crab or spider that moves about when it???s not being observed","A glass jar containing lard with a label that reads, \"Griffon Grease\"","A wooden box with a ceramic bottom that holds a living worm with a head on each end of its body","A metal urn containing the ashes of a hero"
    };
    private String[] alignment = {
            "Lawful good","Neutral good","Chaotic good","Lawful neutral","Neutral","Chaotic neutral","Lawful evil","Neutral evil","Chaotic evil"
    };

    private String[] dragonData = {
            "The average adult Dragon produces a gallon of saliva every hour.",
            "Ancient Fairy Dragons are rare, but they throw the best parties. Looking for lit parties in the Fey Wild is most effective means of tracking them.",
            "A dragon can leap up to five times it's own body length in a single bound to assist with takeoff.",
            "Certain breeds of bronze dragon can appear cross-eyed, because the nerves from the left side of the brain go to mostly the right eye and the nerves from the right side of the brain go mostly to the left eye.",
            "Most dragons do not have the ability to taste sweetness, but they have an overdeveloped sense of taste for sickness or infection.",
            "A dragon's memories are imbued into its very blood due to their magic",
            "They love to eat fish, they hate eels, the food they gather isn't for them and grass is like catnip to them",
            "The reason red dragons sleep on a pile of gold is because they have extremely warm bodies and that causes the gold to warm and become softer and actually make a bed for the dragon.",
            "All dragons were omnivorous and could eat almost anything, thanks to their innate elemental nature that allowed them to consume and digest all sorts of food, including substances that wouldn't qualify as food to other living creatures",
            "Most dragons usually consumed half their own weight in meat every day.",
            "Dragons had excellent depth perception and comparably good peripheral vision, able to see twice as well as a human in daylight",
            "Dragons were capable of blindsense, the sense in which eyes, ears, and other senses were used to detect invisible persons or objects"
    };


}
