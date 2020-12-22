package com.lcl.galaxy.design.pattern.prototype;


import java.io.*;

public class Prototype implements Cloneable, Serializable {
    private static final long serialVersion = 1L;

    private String name;

    private Order order;

    public void setName(String name) {
        this.name = name;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public Order getOrder() {
        return order;
    }

    public Prototype shallowClone() throws CloneNotSupportedException {
        Prototype prototype = (Prototype) super.clone();
        return prototype;
    }

    public Prototype deepClone() throws IOException, ClassNotFoundException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        oos.writeObject(this);

        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (Prototype) ois.readObject();
    }
}
