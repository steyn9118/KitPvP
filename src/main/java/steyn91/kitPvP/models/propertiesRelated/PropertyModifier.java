package steyn91.kitPvP.models.propertiesRelated;

public record PropertyModifier(
        ModifierType propertyModifierType,
        double modifierValue) {

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
