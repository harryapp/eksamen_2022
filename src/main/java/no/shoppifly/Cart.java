package no.shoppifly;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class Cart {
    private String id;
    List<Item> items = new ArrayList<>();

    public float getCartSum(){
        float sum = 0;
        for(Item i : items)
            sum += i.getSum();
        return sum;
    }
}

@Data
class Item {

    private String description;
    private int qty;
    private Float unitPrice;

    public float getSum(){
        return unitPrice * qty;
    }
}