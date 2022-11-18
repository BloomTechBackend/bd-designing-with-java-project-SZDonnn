package com.amazon.ata.service;

import com.amazon.ata.cost.CostStrategy;
import com.amazon.ata.cost.MonetaryCostStrategy;
import com.amazon.ata.dao.PackagingDAO;
import com.amazon.ata.datastore.PackagingDatastore;
import com.amazon.ata.types.*;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ShipmentServiceTest {
    private FulfillmentCenter existentFC = new FulfillmentCenter("ABE2");
    private FulfillmentCenter nonExistentFC = new FulfillmentCenter("NonExistentFC");
    private Item smallItem = Item.builder()
            .withHeight(BigDecimal.valueOf(1))
            .withWidth(BigDecimal.valueOf(1))
            .withLength(BigDecimal.valueOf(1))
            .withAsin("abcde")
            .build();
    private Item largeItem = Item.builder()
            .withHeight(BigDecimal.valueOf(1000))
            .withWidth(BigDecimal.valueOf(1000))
            .withLength(BigDecimal.valueOf(1000))
            .withAsin("12345")
            .build();

    @Mock
    private PackagingDAO packagingDAO;
    private CostStrategy costStrategy = new MonetaryCostStrategy();;
    @InjectMocks
    private ShipmentService shipmentService = new ShipmentService(new PackagingDAO(new PackagingDatastore()), costStrategy);;


    @Test
    void findBestShipmentOption_existentFCAndItemCanFit_returnsShipmentOption() {
        // GIVEN & WHEN
        ShipmentOption shipmentOption = shipmentService.findShipmentOption(smallItem, existentFC);


        // THEN
        assertNotNull(shipmentOption);
    }

    @Test
    void findBestShipmentOption_existentFCAndItemCannotFit_returnsShipmentOption() {
        // GIVEN & WHEN
        ShipmentOption shipmentOption = shipmentService.findShipmentOption(largeItem, existentFC);

        // THEN
        assertNull(shipmentOption);
    }

    @Test
    void findBestShipmentOption_nonExistentFCAndItemCanFit_returnsShipmentOption() {
        // GIVEN & WHEN
        ShipmentOption shipmentOption = shipmentService.findShipmentOption(smallItem, nonExistentFC);

        // THEN
        assertNull(shipmentOption);
    }

    @Test
    void findBestShipmentOption_nonExistentFCAndItemCannotFit_returnsShipmentOption() {
        // GIVEN & WHEN
        ShipmentOption shipmentOption = shipmentService.findShipmentOption(largeItem, nonExistentFC);

        // THEN
        assertNull(shipmentOption);
    }

}