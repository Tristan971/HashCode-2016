package moe.tristan;

import java.util.HashMap;

/**
 * Created by Tristan Deloche on 11/02/2016 for Hashcode2016.
 */

public class Warehouse {
    public HashMap<ProductType, Integer> availableProducts = new HashMap<>();
    public int[] location;
    public ProductType type;

    public Warehouse(int[] location) {
        this.location = location;
    }

    public void setProductType(ProductType productType, Integer n) {
        this.type = productType;

        if (this.availableProducts.containsKey(productType)) {

            this.availableProducts.put(
                    productType,
                    this.availableProducts.get(productType) + n
            );
        } else {
            this.availableProducts.put(productType, n);
        }
    }

    public void addProduct(ProductType p, Integer n) {
        setProductType(p,n);
    }

    public void removeProduct(ProductType p, Integer n) {
        setProductType(p,-1*n);
    }

    public void sellProducts(ProductType p, int n) {
        this.availableProducts.put(
                p,
                this.availableProducts.get(p) - n
        );
    }
}
