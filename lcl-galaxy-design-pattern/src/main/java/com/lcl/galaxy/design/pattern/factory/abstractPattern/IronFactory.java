package com.lcl.galaxy.design.pattern.factory.abstractPattern;

import com.lcl.galaxy.design.pattern.factory.abstractPattern.desk.Desk;
import com.lcl.galaxy.design.pattern.factory.abstractPattern.desk.IronDesk;
import com.lcl.galaxy.design.pattern.factory.abstractPattern.shelf.IronShelf;
import com.lcl.galaxy.design.pattern.factory.abstractPattern.shelf.Shelf;

public class IronFactory extends ProductionFactory {

    @Override
    public Desk createDesk() {
        return new IronDesk();
    }

    @Override
    public Shelf createShelf() {
        return new IronShelf();
    }
}
