public class Main {
    String characterName;
    String weaponName;

    int weaponDamage;
    int hp;
    static int hammerMaximumDamage = 60;
    static int hammerMinimumDamage = 40;
    /*
    Max damage minus min damage times a random value between 0 and 1 plus minimum damage, and then converted to integer.
    Example with 0 as the random value which would give hammerMinimumDamage as a result:
        60 - 40 = 20,  20 * 0 = 0, 0 + 40 = 40 == hammerMinimumDamage
    Example with 1 as the random value, expected outcome == hammerMaximumDamage..
        60 - 40 = 20, 20 * 1 = 20, 20 + 40 = 60 == hammerMaximumDamage
    Example with 0.442 as the random value, expected result < (hammerMaximumDamage + hammerMinimumDamage) / 2:
        60 - 40 = 20, 20 * 0.442 = 8.84, 8.84 + 40 = 48.84, 48.84 converted to int = 48
    */
    static int hammer = (int) (Math.random() * (hammerMaximumDamage - hammerMinimumDamage) + hammerMinimumDamage);

    static int magicWandMaximumDamage = 100;
    static int magicWandMinimumDamage = 0;
    static int magicWand = (int) (Math.random() * (magicWandMaximumDamage - magicWandMinimumDamage) + magicWandMinimumDamage);

    static int excaliburMaximumDamage = 100;
    static int excaliburMinimumDamage = 50;
    static int excalibur = (int) (Math.random() * (excaliburMaximumDamage - excaliburMinimumDamage) + excaliburMinimumDamage);

    //Constructor method
    public Main(String characterName, int weaponDamage, int hp, String weaponName) {
        this.characterName = characterName;
        this.weaponName = weaponName;
        this.weaponDamage = weaponDamage;
        this.hp = hp;
    }
    //Method can be called on any Main object to change the weaponName to "excalibur and the weaponDamage to excalibur(int value instantiated in the Main class
    public void equipExcalibur() {
        weaponName = "Excalibur";
        weaponDamage = excalibur;
        System.out.println(weaponName + " is equipped, new weapon damage is " + excaliburMinimumDamage + " - " + excaliburMaximumDamage);
    }

    public void beingAttacked(String defenderName, String attackerName, int attackerWeaponDamage, String attackerWeaponName) {
        if (hp <= 0 || attackerWeaponName.equals("Excalibur")) {
            System.err.println(attackerName + " CANNOT ATTACK");
        } else {
            System.out.println(attackerName + " STRIKES " + defenderName + " WITH " + attackerWeaponName + " AND DOES " + attackerWeaponDamage + " DAMAGE");
            hp = (hp - attackerWeaponDamage);
            System.out.println(defenderName + " HAS " + hp + " REMAINING HEALTH! ");
        }
    }

    public static void searchWeapon(String weaponName) {
        System.out.println("Search for: " + weaponName);
        switch (weaponName) {
            case "Hammer of Vigilance":
                System.out.println("The Beast carries " + weaponName);
                break;
            case "Wand of Who":
                System.out.println("Ja'far carries " + weaponName);
                break;
            case "Excalibur":
                System.out.println("The Beast could be carrying " + weaponName + " soon, or maybe The Beast is carrying " + weaponName + "..?");
                break;
            default:
                System.out.println("No character carries " + weaponName);
                break;
        }
    }

    public static void main(String[] args) {
        Main goodGuy = new Main("The Beast", hammer, 1000, "Hammer of Vigilance");
        Main evilGuy = new Main("Ja'far", magicWand, 500, "Wand of Who");

        searchWeapon(goodGuy.weaponName);
        searchWeapon(evilGuy.weaponName);
        searchWeapon("Excalibur");
        searchWeapon("The Grandfather");
        evilGuy.beingAttacked(evilGuy.characterName, goodGuy.characterName, goodGuy.weaponDamage, goodGuy.weaponName);
        goodGuy.equipExcalibur();
        searchWeapon(goodGuy.weaponName);
        searchWeapon("Hammer of Vigilance");
        System.out.println(evilGuy.characterName + " carries " + evilGuy.weaponName);
        System.out.println(goodGuy.weaponName);
        goodGuy.beingAttacked(goodGuy.characterName, evilGuy.characterName, evilGuy.weaponDamage, evilGuy.weaponName);
        evilGuy.beingAttacked(evilGuy.characterName, goodGuy.characterName, goodGuy.weaponDamage, goodGuy.weaponName);
    }
}

