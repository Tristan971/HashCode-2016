package moe.tristan;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Order {

    public int[] deliveryLocation;
    public Map<String , Integer> productTypeQuantityMap = new HashMap<>();

    public Order(int[] deliveryLocation) {
        this.deliveryLocation = deliveryLocation;
    }

    public void add(String type, Integer quantity) {

        if (productTypeQuantityMap.containsKey(type)) {

            productTypeQuantityMap.put(
                    type,
                    productTypeQuantityMap.get(type) + quantity
            );
        } else {
            productTypeQuantityMap.put(
                    type,
                    quantity
            );
        }
    }

    public void remove(ProductType productType) {
        this.productTypeQuantityMap.remove(productType.type);
    }



    @Override
    public String toString() {
        return "location "+ Arrays.toString(deliveryLocation);
    }
}
