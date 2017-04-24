package com.mike.exp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by mike on 4/23/2017.
 */
public class Anneal {
    private static final String TAG = Anneal.class.getSimpleName();
    private static Random random = new Random(12375L);

    static class Scenario {
        List<Offer> offers;

        public Scenario(List<Offer> offers) {
            this.offers = new ArrayList<>(offers.size());
            offers.forEach(offer -> this.offers.add(new Offer(offer)));
        }
        public Scenario(Scenario scenario) {
            this.offers = new ArrayList<>(scenario.offers.size());
            scenario.offers.forEach(offer -> this.offers.add(new Offer(offer)));
        }

        private void permute() {
            // move somebody
            int i = random.nextInt(offers.size());
            Offer offer = offers.get(i);
            offer.x += (random.nextDouble() - 0.5) * 4.0;
            offer.y += (random.nextDouble() - 0.5) * 4.0;
        }

        @Override
        public String toString() {
            return String.format("%.1f", energyOf());
        }

        public double energyOf() {
            final double[] d = {0};
            offers.forEach(offer -> {
                offer.tags.forEach(tag -> {
                    d[0] += distance(offer.x, offer.y, tag.x, tag.y);
                });
            });

            return d[0];
        }

        private double distance(double x1, double y1, double x2, double y2) {
            double dx = x1 - x2;
            double dy = y1 - y2;
            return Math.sqrt((dx * dx) + (dy * dy));
        }

    }

    private Scenario node;
    private boolean debug = false;

    public Random getRandom() {
        return random;
    }


    public Anneal(List<Offer> offers) {

        this.node = new Scenario(offers);
    }

    // Calculate the acceptance probability, like golf, lower score is better
    private double acceptanceProbability(double bestScore, double newScore, double temperature) {

        if (newScore < bestScore) {
            return 1.0; // accept for certain
        }

        // accept with some probability
        double p = Math.exp(-(newScore - bestScore) / temperature);
        return p;
    }

    public List<Offer> anneal() {

        Scenario scenario = new Scenario(node);

        Collections.shuffle(node.offers, random); // initial random locations

//        if (data.debug())
            Log.d(TAG, String.format("Initial solution energy: %.1f", node.energyOf()));

        double temperature = 10000;
        double coolingRate = 0.0029;
        int iterations = 0;


        // Set as current best
        Scenario best = new Scenario(scenario);
//        if (data.debug())
            Log.d(TAG, "Initial route: " + best.energyOf());

        // iterate until system has cooled
        while (temperature > 1) {

            Scenario next = new Scenario(scenario);
            next.permute();

            double p = acceptanceProbability(
                    scenario.energyOf(),
                    next.energyOf(),
                    temperature);

            if (p > random.nextDouble()) {
                scenario = new Scenario(next);
            }

            if (scenario.energyOf() < best.energyOf()) {

//                if (debug)
                    Log.d(TAG, "new best " + scenario.energyOf());

                best = new Scenario(scenario);
            }

            // Cool system
            temperature *= (1.0 - coolingRate);

            if ((iterations % 500) == 0)
                Log.d(TAG, String.format("%4d, %.1f %s",
                        iterations,
                        best.energyOf(),
                        ""));

            iterations++;
        }

//        if (data.debug()) {
            Log.d(TAG, "Iterations: " + iterations);
            Log.d(TAG, String.format("Best solution distance: %.1f", best.energyOf()));
            Log.d(TAG, "Route: " + best.toString());
//        }

        return best.offers;
    }


}
