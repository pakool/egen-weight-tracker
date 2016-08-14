package org.pako.egen.weight.rules;

import java.security.InvalidParameterException;

import org.easyrules.annotation.Rule;
import org.easyrules.spring.SpringRule;
import org.springframework.stereotype.Component;

/**
 * This rule will execute when the weight is below 10% of the base value
 * @author Pako Castillo
 *
 */
@Component
@SpringRule
@Rule(name="Under Weight Rule",description="This rule will be triggered when the base weight is 10% below the base")
public class UnderWeightRule extends WrightRule {

	/**
	 * @see org.pako.egen.weight.rules.WrightRule#evaluate()
	 */
	@Override
	public boolean evaluate() {
		Integer mixAcceptableWeight = propertyManager.getMinAcceptableWeight();

		System.out.println("Current value: " + getCurrentValue() + " Base weight: " + getBaseweight() + " Min Acceptable " + mixAcceptableWeight);

		/** If the parameter is null, throw an invalid parameter exception **/
		if(mixAcceptableWeight == null || getCurrentValue() == null || getBaseweight() == null){
			throw new InvalidParameterException("The Maximum acceptable percentage is null");
		}

		LOG.debug("Current value: " + getCurrentValue() + " Base weight: " + getBaseweight() + " Min Acceptable " + mixAcceptableWeight);

		/**  **/
		if(1- getCurrentValue()/getBaseweight() > mixAcceptableWeight/100){
			return true;
		}

		return false;
	}

	/**
	 * @see org.pako.egen.weight.rules.WrightRule#execute()
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

}
