package com.lcl.galaxy.pattern.behavior.strategy.concrete;

import com.lcl.galaxy.pattern.behavior.strategy.abs.TravelStrategy;

/***
 * 具体策略类(ConcreteStrategy)
 * 
 * @author think
 *
 */
public class TrainStrategy implements TravelStrategy {

	@Override
	public void travelWay() {
		System.out.println("旅游方式选择：乘坐火车");
	}

	@Override
	public boolean isOK(int type) {
		if (type >= 50 && type < 1000) {
			return true;
		}
		return false;
	}

}
