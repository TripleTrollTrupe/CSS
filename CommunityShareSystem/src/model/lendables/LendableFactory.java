package model.lendables;

import model.EMediumPropertiesData;
import model.EMediumType;

public interface LendableFactory {

	Object createLendable(EMediumType type,EMediumPropertiesData properties);
}
