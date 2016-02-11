package moe.tristan;

/**
 * Created by Tristan Deloche on 11/02/2016 for Hashcode2016.
 */

public class Action {

    String drone;
    String action;
    Warehouse warehouse;
    int ordernb;
    Order order;
    ProductType productType;

    public Action(
            String action,
            Order order,
            String productType,
            Warehouse warehouse,
            int ordernb
    ) {
        this.action = action;
        this.warehouse = warehouse;
        this.ordernb = ordernb;
        this.order = order;
    }

    public void assignDrone(String drone) {
        this.drone = drone;
    }

    //DRONE ACTION PLACE PRODTYPE NB
    public String start() {
        if (action.equals("L")) {
            return  drone+
                    action+
                    warehouse+
                    productType.type+
                    order.productTypeQuantityMap.get(productType.type)+'\n'
                    ;
        } else {
            return  drone+
                    action+
                    ordernb+
                    productType.type+
                    order.productTypeQuantityMap.get(productType.type)+'\n'
                    ;
        }
    }
}
