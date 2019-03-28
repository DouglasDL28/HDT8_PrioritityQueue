package com.company;

import java.util.Vector;

/**
 * Código extraído de la 7ma edición del libro Java Structures por Duane A. Bailey.
 * @param <E>
 */

public class VectorHeap<E extends Comparable<E>> implements PriorityQueue<E> {

    protected Vector<E> data; // the data, kept in heap order

    public VectorHeap()
    // post: constructs a new priority queue
    {
        data = new Vector<E>();
    }

    public VectorHeap(Vector<E> v)
    // post: constructs a new priority queue from an unordered vector
    {
        int i;
        data = new Vector<E>(v.size()); // we know ultimate size
        for (i = 0; i < v.size(); i++) { // add elements to heap
            add(v.get(i));
        }
    }

    /**
     * Esta función se utiliza para conocer la posición en el vector en la que se encuentra el parent de dado i.
     * @param i La posición del elemento del cual se quiere saber la referencia del parent.
     * @return La posición en el vector del pariente.
     */
    protected static int parent(int i)
    // pre: 0 <= i < size
    // post: returns parent of node at location i
    {
        return (i - 1) / 2;
    }

    /**
     * Esta función se utiliza para conocer la posición del hijo en la posición izquierda de algún elemento en la posición i.
     * @param i La posición del elemento del cual se quiere saber la referencia del hijo izquierdo.
     * @return La posición en el vector del hijo izquierdo.
     */
    protected static int left(int i)
    // pre: 0 <= i < size
    // post: returns index of left child of node at location i
    {
        return 2 * i + 1;
    }

    /**
     * Esta función se utiliza para conocer la posición del hijo en la posición derecha de algún elemento en la posición i.
     * @param i La posición del elemento del cual se quiere saber la referencia del hijo derecho.
     * @return La posición en el vector del hijo derecho.
     */
    protected static int right(int i)
    // pre: 0 <= i < size
    // post: returns index of right child of node at location i
    {
        return (2 * i + 1) + 1;
    }

    /**
     * Esta función ayuda a mover el elemento en la hoja a su debida posición dependiendo de su valor y el orden en que se guardan
     * los valores en el Heap.
     * @param leaf El valor que se encuentra en la hoja.
     */
    protected void percolateUp(int leaf)
    // pre: 0 <= leaf < size
    // post: moves node at index leaf up to appropriate position
    {
        int parent = parent(leaf);
        E value = data.get(leaf);
        while (leaf > 0 &&
                (value.compareTo(data.get(parent)) < 0)) {
            data.set(leaf, data.get(parent));
            leaf = parent;
            parent = parent(leaf);
        }
        data.set(leaf, value);
    }

    /**
     * Agrega un elemento en la hoja y luego utiliza la función percolateUp para ordenar el VectorHeap dependiendo del valor de
     * este elemento.
     * @param value
     */
    public void add(E value)
    // pre: value is non-null comparable
    // post: value is added to priority queue
    {
        data.add(value);
        percolateUp(data.size() - 1);
    }

    /**
     * Esta función está diseñada para mover el elemento en la raíz a su posición debida dependiendo del orden en que se almacenen
     * los elementos en el VectorHeap.
     * @param root La raíz del VectorHeap.
     */
    protected void pushDownRoot(int root)
    // pre: 0 <= root < size
    // post: moves node at index root down
    // to appropriate position in subtree
    {
        int heapSize = data.size();
        E value = data.get(root);
        while (root < heapSize) {
            int childpos = left(root);
            if (childpos < heapSize) {
                if ((right(root) < heapSize) &&
                        ((data.get(childpos + 1)).compareTo
                                (data.get(childpos)) < 0)) {
                    childpos++;
                }
                // Assert: childpos indexes smaller of two children
                if ((data.get(childpos)).compareTo
                        (value) < 0) {
                    data.set(root, data.get(childpos));
                    root = childpos; // keep moving down
                } else { // found right location
                    data.set(root, value);
                    return;
                }
            } else { // at a leaf! insert and halt
                data.set(root, value);
                return;
            }
        }
    }

    /**
     * Remueve el valor mínimo del vector heap y utiliza la función protected pushDownRoot, para volver a ordernar el Heap
     * luego de la adición.
     * @return
     */
    @Override
    public E remove()
    // pre: !isEmpty()
    // post: returns and removes minimum value from queue
    {
        E minVal = getFirst();
        data.set(0, data.get(data.size() - 1));
        data.setSize(data.size() - 1);
        if (data.size() > 1) pushDownRoot(0);
        return minVal;
    }

    /**
     * Obtiene el primer elemento en el VectorHeap.
     * @return Devuelve el primer elemento.
     */
    @Override
    public E getFirst() {
        return data.firstElement();
    }

    /**
     * Esta función revisa si el VectorHeap está vació o no y devuelve un valor booleano.
     * @return True en caso el VectorHeap esté vació y False en caso el VectorHeap tenga más de algún elemento.
     */
    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    /**
     * Esta función está diseñada para poder saber el tamano del VectorHeap (la cantidad de elementos que contiene).
     * @return Devuelve la cantidad de elementos en el VectorHeap.
     */
    @Override
    public int size() {
        return data.size();
    }

    /**
     * Esta función está diseñada para vaciar el VectorHeap.
     */
    @Override
    public void clear() {
        data.clear();
    }
}
