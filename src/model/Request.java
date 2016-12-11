package model;

/**
 * Created by Влад on 11.12.2016.
 */
public class Request {

    private int id;
    private Product product;
    private String from;
    private String to;

    public Request(Product product, String from, String to, int id) {
        this.product = product;
        this.from = from;
        this.to = to;
        this.id = id;
    }
}
