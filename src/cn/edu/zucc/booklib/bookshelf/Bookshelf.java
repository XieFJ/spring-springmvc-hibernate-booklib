/*
 * 
 * Copyright 2001 Sun Microsystems, Inc. All Rights Reserved.
 * 
 * This software is the proprietary information of Sun Microsystems, Inc. Use is
 * subject to license terms.
 *  
 */

package cn.edu.zucc.booklib.bookshelf;

import java.util.Collection;
import java.util.HashMap;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.zucc.booklib.databean.BeanBook;

public class Bookshelf {
    HashMap<Integer,BookshelfItem> items = null;

    int numberOfItems = 0;

    public Bookshelf() {
        items = new HashMap<>();
    }

    public synchronized void add(int bookId, BeanBook book) {
        if (items.containsKey(bookId)) {
            BookshelfItem bsitem = (BookshelfItem) items.get(bookId);
            bsitem.incrementQuantity();
        } else {
            BookshelfItem newItem = new BookshelfItem(book);
            items.put(bookId, newItem);
        }

        numberOfItems++;
    }

    public synchronized void remove(int bookId) {
        if (items.containsKey(bookId)) {
            BookshelfItem bsitem = (BookshelfItem) items.get(bookId);
            bsitem.decrementQuantity();

            if (bsitem.getQuantity() <= 0)
                items.remove(bookId);

            numberOfItems--;
        }
    }

    public synchronized Collection<BookshelfItem> getItems() {
        return items.values();
    }

    protected void finalize() throws Throwable {
        items.clear();
    }

    public synchronized int getNumberOfItems() {
        return numberOfItems;
    }

    public synchronized void clear() {
        items.clear();
        numberOfItems = 0;
    }
}
