package model.lendables;

import model.EMediumPropertiesData;
import model.EMediumType;

public class LendableFactoryImpl implements LendableFactory {

	@Override
	public Object createLendable(EMediumType type,EMediumPropertiesData properties) {
		return new Lendable(type,properties);
	}

}
