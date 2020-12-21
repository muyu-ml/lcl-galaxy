package com.lcl.galaxy.design.pattern.factory.abstractPattern;

import com.lcl.galaxy.design.pattern.factory.abstractPattern.desk.Desk;
import com.lcl.galaxy.design.pattern.factory.abstractPattern.desk.WoodDesk;
import com.lcl.galaxy.design.pattern.factory.abstractPattern.shelf.Shelf;
import com.lcl.galaxy.design.pattern.factory.abstractPattern.shelf.WoodShelf;

public class WoodFactory extends ProductionFactory {

    @Override
    public Desk createDesk() {
        return new WoodDesk();
    }

    @Override
    public Shelf createShelf() {
        return new WoodShelf();
    }
}
