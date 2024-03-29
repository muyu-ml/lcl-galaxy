package com.lcl.galaxy.pattern.creative.factory.factory_method.factory;

import com.lcl.galaxy.pattern.creative.factory.factory_method.product.Animal;

// 抽象出来的动物工厂----它只负责生产一种产品
public abstract class AnimalFactory {
	// 工厂方法
	public abstract Animal createAnimal();
}
