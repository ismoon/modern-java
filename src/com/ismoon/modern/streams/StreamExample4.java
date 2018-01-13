package com.ismoon.modern.streams;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

/**
 * @author ismoon on 2017-09-05.
 */
public class StreamExample4 {
    public static void main(String[] args) {

        final List<Product> products = Arrays.asList(
                new Product(1L, "A", new BigDecimal("100.50"))
                , new Product(2L, "B", new BigDecimal("23.00"))
                , new Product(3L, "C", new BigDecimal("31.45"))
                , new Product(4L, "D", new BigDecimal("80.20"))
                , new Product(5L, "E", new BigDecimal("7.50"))
        );

        System.out.println("Products.prince >= 30 : " +
                products.stream()
                        .filter(product -> product.getPrice().compareTo(new BigDecimal("30")) >= 0)
                        .collect(toList())
        );
        System.out.println("\n====================================");
        System.out.println("Products.prince >= 30 (with joining(\"\\n\")): \n" +
                products.stream()
                        .filter(product -> product.getPrice().compareTo(new BigDecimal("30")) >= 0)
                        .map(Product::toString)
                        .collect(joining("\n"))
        );

        System.out.println("\n====================================");
        System.out.println("Total Price: " +
                products.stream()
                        .map(Product::getPrice)
                        .reduce(BigDecimal.ZERO, (price1, price2) -> price1.add(price2))//(이전값, 다음값)
        );

        System.out.println("\n====================================");
        System.out.println("Total Price of Products.price >= 30: " +
                products.stream()
                        .filter(product -> product.getPrice().compareTo(new BigDecimal("30")) >= 0)
                        .map(Product::getPrice)
                        .reduce(BigDecimal.ZERO, BigDecimal::add)
        );

        System.out.println("\n====================================");
        System.out.println("Number of Products.price >= 30: " +
                products.stream()
                        .filter(product -> product.getPrice().compareTo(new BigDecimal("30")) >= 0)
                        .count()
        );

        final OrderedItem item1 = new OrderedItem(1L, products.get(0), 1);
        final OrderedItem item2 = new OrderedItem(2L, products.get(2), 3);
        final OrderedItem item3 = new OrderedItem(3L, products.get(4), 10);

        final Order order = new Order(1L, Arrays.asList(item1, item2, item3));
        System.out.println("\n====================================");
        System.out.println("order.totalPrice() :  " + order.totalPrice());

    }
}

class Product {
    private Long id;
    private String name;
    private BigDecimal price;

    @Override
    public String toString() {
        return "id=" + id + " name=" + name + " price=" + price.toString();
    }

    public Product(Long id, String name, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}

class OrderedItem {
    private Long id;
    private Product product;
    private int quantity;

    public OrderedItem(Long id, Product product, int quantity) {
        this.id = id;
        this.product = product;
        this.quantity = quantity;
    }

    public BigDecimal getTotalPrice() {
        return product.getPrice().multiply(new BigDecimal(quantity));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

class Order {
    private Long id;
    List<OrderedItem> items;

    public Order(Long id, List<OrderedItem> items) {
        this.id = id;
        this.items = items;
    }

    public BigDecimal totalPrice() {
        return items.stream()
                .map(OrderedItem::getTotalPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<OrderedItem> getItems() {
        return items;
    }

    public void setItems(List<OrderedItem> items) {
        this.items = items;
    }
}
