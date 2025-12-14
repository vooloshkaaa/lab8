package org.example.service;

import org.example.model.Stats;

import java.util.concurrent.ThreadLocalRandom;

public class CharacterGenerator {

    public Stats generateStats() {
        return new Stats(
                roll(8, 18),
                roll(8, 18),
                roll(8, 18)
        );
    }

    private int roll(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
