package moe.tristan;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by Tristan Deloche on 11/02/2016 for Hashcode2016.
 */

public class Drone {
    int[] location;
    public int maxload = 0;

    public int currentTurn;

    public boolean busy = false;
    public int busyUntil = 0;

    public HashMap<ProductType, Integer> products;

    public Drone(int[] location, int maxload) {
        this.location = location;
        this.maxload = maxload;
    }

    private void makeBusy(int turns) {
        busy = true;
        busyUntil = currentTurn+turns;
    }

    public boolean hasEnough(ProductType p, Integer n) {
        return products.containsKey(p) && products.get(p) >= n;
    }

    public int goTo(int[] pos) {
        int dist = (int) Math.ceil(
                Utils.getDistance(
                        location,
                        pos
                )
        );
        location[0] = pos[0];
        location[1] = pos[1];

        return dist;
    }

    public int load(ProductType productType, int n) {
        if (productType.weight * n <= this.maxload) {
            this.products.put(
                    productType,
                    products.get(productType) + n
            );
            return 1;
        } else {
            return 1;
        }
    }

    public int deliver(Order order) {

        if (Arrays.equals(location, order.deliveryLocation)) {
            for (ProductType p : products.keySet()) {
                if (order.productTypeQuantityMap.keySet().contains(p)) {
                    order.productTypeQuantityMap.put(
                            p.type,
                            order.productTypeQuantityMap.get(p) - products.get(p)
                    );
                    products.remove(p);
                }
            }
            return 0;
        } else {
            int add = goTo(order.deliveryLocation)+1;
            currentTurn+=add;
            return add;
        }
    }
}

