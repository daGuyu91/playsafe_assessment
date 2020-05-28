package game.roulette;


import game.BetTarget;
import game.BetTargetFactory;

class RouletteBetTargetFactory implements BetTargetFactory {

    @Override
    public BetTarget createFrom(final String string) {
        if (isNumberOfValidRange(string)) {
            return new NumberTarget(Integer.valueOf(string));
        }

        if (isEvenOddValidLabel(string)) {
            return new EvenOddTarget(EvenOddTarget.Type.byName(string));
        }

        return null;
    }

    private boolean isEvenOddValidLabel(final String string) {
        final EvenOddTarget.Type tryTarget = EvenOddTarget.Type.byName(string);
        return tryTarget != null;
    }

    private boolean isNumberOfValidRange(final String string) {
        try {

            final Integer tryNumber = Integer.valueOf(string);
            return (tryNumber > Roulette.ROULETTE_MIN) && (tryNumber <= Roulette.ROULETTE_MAX);

        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
