package com.amazon.ata.strategy;

import com.amazon.ata.cost.CostStrategy;
import com.amazon.ata.cost.MonetaryCostStrategy;
import com.amazon.ata.types.Material;
import com.amazon.ata.types.Packaging;
import com.amazon.ata.types.ShipmentCost;
import com.amazon.ata.types.ShipmentOption;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeightedCostStrategy implements CostStrategy {
    private MonetaryCostStrategy monetaryCostStrategy;
    private CarbonCostStrategy carbonCostStrategy;
    private static final BigDecimal MONETARY_COST_STRATEGY_WEIGHT = new BigDecimal("0.8");
    private static final BigDecimal CARBON_COST_STRATEGY_WEIGHT = new BigDecimal("0.2");

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

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private MonetaryCostStrategy monetaryCostStrategy;
        private CarbonCostStrategy carbonCostStrategy;

        public Builder withMonetaryCostStrategy(final MonetaryCostStrategy monetaryCostStrategy) {
            this.monetaryCostStrategy = monetaryCostStrategy;
            return this;
        }

        public Builder withCarbonCostStrategy(final CarbonCostStrategy carbonCostStrategy) {
            this.carbonCostStrategy = carbonCostStrategy;
            return this;
        }

        public WeightedCostStrategy build() {
            return new WeightedCostStrategy(monetaryCostStrategy, carbonCostStrategy);
        }
    }
}
