package com.amazon.ata.types;

import java.math.BigDecimal;
import java.util.Objects;

public class PolyBag extends Packaging {
    /**
     * This packaging's largest volume.
     */
    private BigDecimal volume;

    /**
     * Instantiates a new Packaging object.
     *
     * @param material - the Material of the package
     */
    public PolyBag(Material material, BigDecimal volume) {
        super(material);
        this.volume = volume;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    /**
     * Returns whether the given item will fit in this packaging.
     *
     * @param item the item to test fit for
     * @return whether the item will fit in this packaging
     */
    @Override
    public boolean canFitItem(Item item) {
        BigDecimal itemVolume = item.getLength().multiply(item.getWidth()).multiply(item.getHeight());
        return itemVolume.doubleValue() < this.volume.doubleValue();
    }

    /**
     * Returns the mass of the packaging in grams. The packaging weighs 1 gram per square centimeter.
     * @return the mass of the packaging
     */
    @Override
    public BigDecimal getMass() {
        double mass = Math.ceil(Math.sqrt(volume.doubleValue()) * 0.6);
        return BigDecimal.valueOf(mass);
    }

    @Override
    public boolean equals(Object o) {
        // Can't be equal to null
        if (o == null) {
            return false;
        }

        // Referentially equal
        if (this == o) {
            return true;
        }

        // Check if it's a different type
        if (getClass() != o.getClass()) {
            return false;
        }

        Packaging packaging = (Packaging) o;
        return getMaterial() == packaging.getMaterial();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMaterial());
    }

}
