package com.amazon.ata.strategy;

import com.amazon.ata.cost.CostStrategy;
import com.amazon.ata.cost.MonetaryCostStrategy;
import com.amazon.ata.types.ShipmentCost;
import com.amazon.ata.types.ShipmentOption;

import java.math.BigDecimal;

public class WeightedCostStrategy implements CostStrategy {
    private static final BigDecimal CARBON_COST_STRATEGY_WEIGHT = new BigDecimal("0.2");
    private static final BigDecimal MONETARY_COST_STRATEGY_WEIGHT = new BigDecimal("0.8");
    private MonetaryCostStrategy monetaryCostStrategy;
    private CarbonCostStrategy carbonCostStrategy;

    /**
     * Setter constructor for WeightedCostStrategy.
     * @param monetaryCostStrategy - to initialize MonetaryCostStrategy
     * @param carbonCostStrategy - to initialize CarbonCostStrategy
     */
    public WeightedCostStrategy(MonetaryCostStrategy monetaryCostStrategy, CarbonCostStrategy carbonCostStrategy) {
        this.monetaryCostStrategy = monetaryCostStrategy;
        this.carbonCostStrategy = carbonCostStrategy;
    }

    @Override
    public ShipmentCost getCost(ShipmentOption shipmentOption) {
        BigDecimal monetaryCost = monetaryCostStrategy.getCost(shipmentOption).getCost()
                .multiply(MONETARY_COST_STRATEGY_WEIGHT);
        BigDecimal carbonCost = carbonCostStrategy.getCost(shipmentOption).getCost()
                .multiply(CARBON_COST_STRATEGY_WEIGHT);
        BigDecimal cost = monetaryCost.add(carbonCost);
        return new ShipmentCost(shipmentOption, cost);
    }

    /**
     * Builder pattern for WeightedCostStrategy.
     * @return - builder
     */
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private MonetaryCostStrategy monetaryCostStrategy;
        private CarbonCostStrategy carbonCostStrategy;

        /**
         * Setter for MonetaryCostStrategy.
         * @param monetaryCost - initialize MonetaryCostStrategy
         * @return - builder
         */
        public Builder withMonetaryCostStrategy(final MonetaryCostStrategy monetaryCost) {
            this.monetaryCostStrategy = monetaryCost;
            return this;
        }

        /**
         * Setter for CarbonCostStrategy.
         * @param carbonCost - initialize CarbonCostStrategy
         * @return - builder
         */
        public Builder withCarbonCostStrategy(final CarbonCostStrategy carbonCost) {
            this.carbonCostStrategy = carbonCost;
            return this;
        }

        /**
         * Initialize the class WeightedCostStrategy.
         * @return - values from Builder
         */
        public WeightedCostStrategy build() {
            return new WeightedCostStrategy(monetaryCostStrategy, carbonCostStrategy);
        }
    }
}
