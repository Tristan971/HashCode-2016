package moe.tristan;

import java.util.LinkedList;

public class Utils {
    private Utils() {}

    public static double getDistance(int[] a, int[] b) {
        return Math.sqrt(Math.pow(a[0]-b[0], 2)+Math.pow(a[1]-b[1],2));
    }

    public static Warehouse getBestWarehouse(
            LinkedList<Warehouse> warehouses,
            int[] droneLocation,
            int[] clientLocation,
            ProductType p,
            int n
    ) {
        LinkedList<Warehouse> goodWarehouses = new LinkedList<>();
        for (Warehouse warehouse : warehouses) {
            if (warehouse.availableProducts.get(p) >= n) {
                goodWarehouses.add(warehouse);
            }
        }
        return fastestPathTree(droneLocation, clientLocation, goodWarehouses);
    }

    public static Warehouse fastestPathTree(
            int[] departure,
            int[] arrival,
            LinkedList<Warehouse> availableWarehouses
    ) {
        //todo dijkstra goes here
        return null;
    }

}
