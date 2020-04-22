package com.example.healthstore.Iterator;

import static com.example.healthstore.Iterator.Interface.shoppingList;

public class CartRepository implements Container, Interface{

    @Override
    public Iterator getIterator() {
        return new PriceIterator();
    }

    private class PriceIterator implements Iterator {
        int index;
        @Override
        public boolean hasNext() {

            if(index < shoppingList.size()){
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            if(this.hasNext()){
                return shoppingList.get(index++);
            }
            return null;
        }
    }
}