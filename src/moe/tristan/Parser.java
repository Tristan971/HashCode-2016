package moe.tristan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Parser {
    public LinkedList<String> lines = new LinkedList<>();

    public int rows, cols, turns, max_payload;
    public LinkedList<Warehouse> warehouses = new LinkedList<>();
    public LinkedList<Drone> drones = new LinkedList<>();
    public LinkedList<Order> orders = new LinkedList<>();


    public Parser(String file) {
        try {
            read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //private int parseInt(String s) {
    //    int a = Integer.parseInt(s);
    //    System.out.println(a);
    //    return a;
    //}

    public void parseToDataMap() {

        //BASE DATA
        String[] gameData = lines.get(0).split(" ");
        rows = Integer.parseInt(gameData[0]);
        cols = Integer.parseInt(gameData[1]);
        max_payload = Integer.parseInt(gameData[4]);

        int dronenb = Integer.parseInt(gameData[2]);
        for (int i = 0; i < dronenb; i++) {
            drones.add(new Drone(new int[] {0,0}, max_payload));
        }

        turns = Integer.parseInt(gameData[3]);

        //PRODUCT TYPES
        gameData = lines.get(1).split(" ");
        int productTypes = Integer.parseInt(gameData[0]);

        //PRODUCT WEIGHTS
        gameData = lines.get(2).split(" ");
        int[] productWeights = new int[productTypes];
        for (int i = 0; i<productTypes; i++) {
            productWeights[i] = Integer.parseInt(gameData[i]);
        }

        //WEARHOUSES
        int wearhousesNumber = Integer.parseInt(lines.get(3).split(" ")[0]);

        for (int i = 4; i < 4+(wearhousesNumber*2); i+=2) {
            String[] lineData = lines.get(i).split(" ");
            int[] location = new int[] {
                    Integer.parseInt(lineData[0]),
                    Integer.parseInt(lineData[0])
            };

            Warehouse warehouse = new Warehouse(location);

            lineData = lines.get(i+1).split(" ");
            for (int j = 0; j < productTypes; j++) {
                warehouse.addProduct(
                        new ProductType(Integer.toString(j), productWeights[j]),
                        new Integer(lineData[j])
                );
            }

            warehouses.add(warehouse);
        }

        //COMMANDS
        int commandsStartLine = 4+(wearhousesNumber*2);
        for (int i = commandsStartLine+1; i < lines.size(); i+=3) {
            String[] a = lines.get(i).split(" ");
            Order order = new Order(
                    new int[] {
                            Integer.parseInt(a[0]),
                            Integer.parseInt(a[1])
                    }
            );

            //int nbitems = Integer.parseInt(lines.get(i+1).split(" ")[0]);
            a = lines.get(i+1).split(" ");
            for (String anItem : a) {
                order.add(anItem, 1);
            }
            orders.add(order);
        }
    }

    private void read(String file) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(
                new FileReader(file)
        );
        String currentLine;

        while ((currentLine = bufferedReader.readLine()) != null) {
            lines.add(currentLine);
        }
        bufferedReader.close();

        parseToDataMap();
    }
}
