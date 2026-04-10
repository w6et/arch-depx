package base_jdk_new_jep._record.record01;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public record Ordered(List<String> products) implements Order {

    public Ordered {
        Objects.requireNonNull(products, "products is required");
    }
    @Override
    public Order next() {
        return new Delivered(products);
    }

    @Override
    public List<String> products() {
        return Collections.unmodifiableList(products);
    }
}

//public record Delivered(List<String> products) implements Order {
//
//    public Delivered {
//        Objects.requireNonNull(products, "products is required");
//    }
//    @Override
//    public Order next() {
//        return new Received(products);
//    }
//
//    @Override
//    public List<String> products() {
//        return Collections.unmodifiableList(products);
//    }
//}