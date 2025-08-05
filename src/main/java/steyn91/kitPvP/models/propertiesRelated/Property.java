package steyn91.kitPvP.models.propertiesRelated;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;

public class Property {

    private final List<PropertyModifier> modifiers = new LinkedList<>();
    @Getter private double value;
    @Getter private double baseValue;
    @Getter @Setter private Property maxValue;
    @Getter @Setter private Property minValue;

    public Property(double baseValue){
        this.value = baseValue;
        this.baseValue = baseValue;
    }

    public double increaseBaseValue(Double increment){
        baseValue += increment;
        if (maxValue != null && baseValue >= maxValue.getValue()) baseValue = maxValue.getValue();
        calculate();
        return value;
    }

    public double decreaseBaseValue(Double decrement){
        baseValue -= decrement;
        if (minValue != null && baseValue <= minValue.getValue()) baseValue = minValue.getValue();
        calculate();
        return value;
    }

    public double getRelativeValue(){
        return value / maxValue.getValue();
    }

    public void forceSetBaseValue(Double newBaseValue){
        baseValue = newBaseValue;
        calculate();
    }

    public void addModifier(PropertyModifier modifier){
        modifiers.add(modifier);
        calculate();
    }

    public void removeModifier(PropertyModifier modifier){
        modifiers.remove(modifier);
        calculate();
    }

    private void calculate(){
        value = baseValue;
        for (PropertyModifier modifier : modifiers
                .stream()
                .filter(m -> m.propertyModifierType() == ModifierType.FLAT)
                .toList())
        {
            value = modifier.modify(value);
        }

        for (PropertyModifier modifier : modifiers
                .stream()
                .filter(m -> m.propertyModifierType() != ModifierType.FLAT)
                .toList())
        {
            value = modifier.modify(value);
        }
    }
}
