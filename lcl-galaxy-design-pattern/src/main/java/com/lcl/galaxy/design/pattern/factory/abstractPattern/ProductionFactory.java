package com.lcl.galaxy.design.pattern.factory.abstractPattern;

import com.lcl.galaxy.design.pattern.factory.abstractPattern.desk.Desk;
import com.lcl.galaxy.design.pattern.factory.abstractPattern.shelf.Shelf;

public abstract class ProductionFactory {
    public abstract Desk createDesk();
    public abstract Shelf createShelf();
}
