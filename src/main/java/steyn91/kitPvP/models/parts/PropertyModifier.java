package steyn91.kitPvP.models.parts;

public record PropertyModifier(
        PropertyModifierType propertyModifierType,
        double modifierValue) {

    public enum PropertyModifierType {
        FLAT, // любой double
        MULTIPLY, // любой double
        MULTIPLY_LIMITED // от 0 до 1
    }

    public double modify(double value) {
        switch (propertyModifierType){
            case FLAT -> {
                return value + modifierValue;
            }
            case MULTIPLY -> {
                return value * modifierValue;
            }
            case MULTIPLY_LIMITED -> {
                return value + ((1 - value) * modifierValue);
            }
            default -> {
                return value;
            }
        }
    }

}
