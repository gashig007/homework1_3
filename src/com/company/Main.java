package com.company;

import java.util.Random;

public class Main {
    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefence;
    public static String[] heroesAttackType = {"Physical", "Magical", "Mental", "Medic", "Golem", "Lucky", "Berserk", "Thor"};
    public static int[] heroesHealth = {270, 260, 250, 300, 600, 250, 260, 240};
    public static int[] heroesDamage = {10, 15, 20, 0, 5, 15, 20, 10};
    public static int roundNumber = 0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameOver()) {
            playRound();
        }
    }

    public static void playRound() {
        roundNumber++;
        chooseBossDefence();
        bossHits();
        heroesHit();
        medic();
        golem();
        lucky();
        berserk();
        thor();
        printStatistics();
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefence = heroesAttackType[randomIndex];
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                int damage = heroesDamage[i];
                if (heroesAttackType[i] == bossDefence) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2;
                    damage = heroesDamage[i] * coeff;
                    System.out.println("Critical damage: " + damage);
                }

                else if (bossHealth - damage < 0) {
                    bossHealth = 0;
                } else {
                    bossHealth = bossHealth - damage;
                }
            }
        }
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static boolean isGameOver() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }

        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void printStatistics() {
        System.out.println("ROUND " + roundNumber + " --------------------");
        System.out.println("Boss health: " + bossHealth + " damage: " + bossDamage
                + " defence: " + (bossDefence == null ? "No Defence" : bossDefence));
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + " damage: " + heroesDamage[i]);
        }
    }

    private static void medic() {
        for (int i = 0; i < heroesHealth.length; i++) {
            Random ramdom1 = new Random();
            if (heroesHealth[i] > 0 && heroesHealth[i] < 100 && heroesHealth[3] > 0) {
                heroesHealth[i] = ramdom1.nextInt(20) + heroesHealth[i];
                break;
            }  else if (heroesHealth[i] <= 0 && heroesHealth[3] <= 0  && i == 3) {
                boolean random1 = false;
            }
        }
    }

    public static void golem() {
        for (int i = 0; i < heroesHealth.length; i++) {
            int coeff = 0;

            heroesHealth[i] = heroesHealth[i] - 4 / 5 * bossDamage;
            coeff++;

            if (i == 4) {
                heroesHealth[4] = heroesHealth[4] - coeff * (1 / 5);
            }
        }
    }

    public static void lucky() {
        Random random = new Random();
        boolean ranrandom = random.nextBoolean();
        if (ranrandom) {
            bossDamage = 0;
        } else {
            heroesHealth[4] = heroesHealth[4] - bossDamage;
        }
    }

    public static void berserk() {
        Random random = new Random();
        int randomIndex = random.nextInt(50);
        heroesHealth[5] = heroesHealth[5] - (bossDamage - randomIndex);
        bossHealth = bossHealth - (heroesDamage[5] + randomIndex);
    }

    public static void thor() {
        Random random = new Random();
        boolean ran = random.nextBoolean();
        if (ran) {
            bossDamage = 0;
        } else {
            bossDamage = 50;
        }
    }
}
