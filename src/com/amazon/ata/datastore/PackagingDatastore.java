package com.amazon.ata.datastore;

import com.amazon.ata.types.*;

import javax.xml.transform.sax.SAXResult;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Stores all configured packaging pairs for all fulfillment centers.
 */
public class PackagingDatastore {

    /**
     * The stored pairs of fulfillment centers to the packaging options they support.
     */
    private final List<FcPackagingOption> fcPackagingOptions = Arrays.asList(
            createFcPackagingOptionBox("IND1", Material.CORRUGATE, "10", "10", "10"),
            createFcPackagingOptionBox("ABE2", Material.CORRUGATE, "20", "20", "20"),
            createFcPackagingOptionBox("ABE2", Material.CORRUGATE, "40", "40", "40"),
            createFcPackagingOptionBox("YOW4", Material.CORRUGATE, "10", "10", "10"),
            createFcPackagingOptionBox("YOW4", Material.CORRUGATE, "20", "20", "20"),
            createFcPackagingOptionBox("YOW4", Material.CORRUGATE, "60", "60", "60"),
            createFcPackagingOptionBox("IAD2", Material.CORRUGATE, "20", "20", "20"),
            createFcPackagingOptionBox("IAD2", Material.CORRUGATE, "20", "20", "20"),
            createFcPackagingOptionBox("PDX1", Material.CORRUGATE, "40", "40", "40"),
            createFcPackagingOptionBox("PDX1", Material.CORRUGATE, "60", "60", "60"),
            createFcPackagingOptionBox("PDX1", Material.CORRUGATE, "60", "60", "60"),
            createFcPackagingOptionPolyBag("IAD2", Material.LAMINATED_PLASTIC, "2000"),
            createFcPackagingOptionPolyBag("IAD2", Material.LAMINATED_PLASTIC, "10000")
    );

    /**
     * Create fulfillment center packaging option from provided parameters.
     */
    private FcPackagingOption createFcPackagingOptionBox(String fcCode, Material material,
                                                      String length, String width, String height) {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter(fcCode);
        Packaging packaging = new Box(material, new BigDecimal(length), new BigDecimal(width), new BigDecimal(height));
        return new FcPackagingOption(fulfillmentCenter, packaging);
    }
    private FcPackagingOption createFcPackagingOptionPolyBag(String fcCode, Material material,
                                                      String volume) {
        FulfillmentCenter fulfillmentCenter = new FulfillmentCenter(fcCode);
        Packaging packaging = new PolyBag(material, new BigDecimal(volume));
        return new FcPackagingOption(fulfillmentCenter, packaging);
    }

    public List<FcPackagingOption> getFcPackagingOptions() {
        return fcPackagingOptions;
    }
}
