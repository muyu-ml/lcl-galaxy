package com.lcl.galaxy.pattern.creative.factory.factory_method.factory;

import com.lcl.galaxy.pattern.creative.factory.factory_method.product.Animal;
import com.lcl.galaxy.pattern.creative.factory.factory_method.product.Cat;

// 具体的工厂实现类
public class CatFactory extends AnimalFactory {

	@Override
	public Animal createAnimal() {
		return new Cat();
	}
}
