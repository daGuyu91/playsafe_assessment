package game.roulette;


import game.BetTarget;
import game.GameResult;

import static game.roulette.EvenOddTarget.Type.EVEN;
import static game.roulette.EvenOddTarget.Type.ODD;

/**
 * A result of one game round (a spin for Roulette)
 */
class RouletteResult implements GameResult {

    private final NumberTarget numberTarget;
    private final EvenOddTarget evenOddTarget;

    RouletteResult(final int number) {
        this.numberTarget = new NumberTarget(number);
        this.evenOddTarget = evenOddBy(number);
    }

    private EvenOddTarget evenOddBy(final int number) {
        if (number == 0) {
            return new None(null);
        }
        if (number % 2 == 0) {
            return new EvenOddTarget(EVEN);
        }
        return new EvenOddTarget(ODD);
    }

    @Override
    public BetTarget[] allWinningTargets() {
        return new BetTarget[] {numberTarget, evenOddTarget};
    }

    /**
     * A helper class used to cover even/odd response to zero.
     */
    class None extends EvenOddTarget {

        private None(Type type) {
            super(type);
        }

        @Override
        public double payoff() {
            return 0.0;
        }

        @Override
        public String valueToString() {
            return "";
        }

    }
}