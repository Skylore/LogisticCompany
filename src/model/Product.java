package model;

/**
 * Created by Влад on 11.12.2016.
 */
public class Product {

    private String name;
    private int weight;
    private int size;

    public Product(String name, int weight, int size) {
        this.name = name;
        this.weight = weight;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (weight != product.weight) return false;
        if (size != product.size) return false;
        return name != null ? name.equals(product.name) : product.name == null;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", weight=" + weight +
                ", size=" + size +
                '}';
    }
}
