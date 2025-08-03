package steyn91.kitPvP.models.parts;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

public class Property {

    private final List<PropertyModifier> modifiers = new LinkedList<>();
    @Getter private double value;
    @Getter private final double baseValue;

    public Property(double baseValue){
        this.value = baseValue;
        this.baseValue = baseValue;
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
                .filter(m -> m.propertyModifierType() == PropertyModifier.PropertyModifierType.FLAT)
                .toList())
        {
            value = modifier.modify(value);
        }

        for (PropertyModifier modifier : modifiers
                .stream()
                .filter(m -> m.propertyModifierType() != PropertyModifier.PropertyModifierType.FLAT)
                .toList())
        {
            value = modifier.modify(value);
        }
    }
}
