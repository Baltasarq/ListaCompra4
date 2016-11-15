package com.baltasarq.listacompra4.Core;

/**
 * Representa un item a comprar.
 * Created by baltasarq on 27/10/16.
 */

public class Item {
    private String nombre;
    private int num;

    /**
     * Crea un nuevo item
     * @param n El nombre del nuevo item.
     */
    public Item(String n)
    {
        this.nombre = n;
    }

    /** @return La cantidad de unidades para este item. */
    public int getNum() {
        return num;
    }

    /**
     * Modifica las unidades para este item.
     * @param num la cantidad de unidades, como int.
     */
    public void setNum(int num) {
        this.num = num;
    }

    /** @return el nombre del item. */
    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString()
    {
        return this.getNombre() + ". Num.: " + this.getNum();
    }
}
