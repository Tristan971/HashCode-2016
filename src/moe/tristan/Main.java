package moe.tristan;

import java.util.LinkedList;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {

        Parser parser = new Parser("data/mother_of_all_warehouses.in");

        String output = "";

        int turn = 0;

        Stack<Action> orderStack = new Stack<>();

        for (Order order: parser.orders) {
            for (String prodType : order.productTypeQuantityMap.keySet()) {
                Action action = new Action(
                        "D",
                        order,
                        prodType,
                        null,
                        order.productTypeQuantityMap.get(prodType)
                );

                orderStack.push(action);
            }
        }

        while (turn < parser.turns) {
            updateAvailability(parser.drones, turn);

            Stack<Drone> available = new Stack<>();
            for (Drone drone : parser.drones) {
                if (!drone.busy) {
                    available.push(drone);
                }
            }

            for (Action action : orderStack) {
                action.assignDrone(""+parser.drones.indexOf(available.firstElement()));
                if (available.firstElement().hasEnough(action.productType, action.ordernb)) {
                    output += action.start();
                    orderStack.pop();
                } else {
                    available.firstElement().load(action.productType, action.ordernb);
                }
            }

            turn++;

            System.out.println(output);
        }

    }

    private static void updateAvailability(LinkedList<Drone> drones, int turnToSet) {
        for (Drone drone : drones) {
            drone.currentTurn = turnToSet;
            drone.busy = (drone.busyUntil != turnToSet);
        }
    }

    private static void removeOrders(Order order, LinkedList<Order> orders) {
        orders.remove(order);
    }
}
