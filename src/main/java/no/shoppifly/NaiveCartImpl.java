package no.shoppifly;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Metrics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
class NaiveCartImpl implements CartService, ApplicationListener<ApplicationEvent> {

    private final Map<String, Cart> shoppingCarts = new HashMap<>();
    private final MeterRegistry meterRegistry;

    @Autowired
    public NaiveCartImpl(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }


    @Override
    public Cart getCart(String id) {
        return shoppingCarts.get(id);
    }

    @Override
    public Cart update(Cart cart) {
        if (cart.getId() == null) {
            cart.setId(UUID.randomUUID().toString());
        }
        shoppingCarts.put(cart.getId(), cart);
        return shoppingCarts.put(cart.getId(), cart);
    }

    @Override
    public String checkout(Cart cart) {
        shoppingCarts.remove(cart.getId());
        meterRegistry.counter("checkouts").increment();

        return UUID.randomUUID().toString();
    }

    @Override
    public List<String> getAllsCarts() {
        return new ArrayList<>(shoppingCarts.keySet());
    }

    // @author Jim; I'm so proud of this one, took me one week to figure out !!!
    public float total() {
        return shoppingCarts.values().stream()
                .flatMap(c -> c.getItems().stream()
                        .map(i -> i.getUnitPrice() * i.getQty()))
                .reduce(0f, Float::sum);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        Gauge.builder("carts", shoppingCarts, s -> s.values().size()).register(meterRegistry);

        Gauge.builder("cartsvalue", shoppingCarts,
                        s -> s.values()
                                .stream()
                                .map(Cart::getCartSum)
                                .mapToDouble(Float::doubleValue)
                                .sum())
                .register(meterRegistry);

    }
}
