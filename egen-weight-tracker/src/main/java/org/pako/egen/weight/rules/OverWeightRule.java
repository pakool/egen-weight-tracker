package org.pako.egen.weight.rules;

import java.security.InvalidParameterException;

import org.easyrules.annotation.Rule;
import org.easyrules.spring.SpringRule;
import org.springframework.stereotype.Component;

/**
 * This rule will execute when the weight exceeds 10% of the base value
 *
 * @author Pako Castillo
 *
 */
@Component
@SpringRule
@Rule(name="Over Weight Rule",description="This rule will be triggered when the base weight is 10% bigger than the base value")
public class OverWeightRule extends WrightRule{

	/**
	 * Return true if the value exceeds the threshold
	 *
	 * @see org.pako.egen.weight.rules.WrightRule#evaluate()
	 */
	@Override
	public boolean evaluate() throws InvalidParameterException{
		Integer maxAcceptableWeight = propertyManager.getMaxAcceptableWeight();

		/** If the parameter is null, throw an invalid parameter exception **/
		if(maxAcceptableWeight == null || getCurrentValue() == null || getBaseweight() == null){
			throw new InvalidParameterException("The Maximum acceptable percentage is null");
		}

		LOG.debug("Current value: " + getCurrentValue() + " Base weight: " + getBaseweight() + maxAcceptableWeight);

		/**  **/
		if(1- getBaseweight() / getCurrentValue() > maxAcceptableWeight/100){
			return true;
		}

		return false;
	}

	/**
	 * @see org.pako.egen.weight.rules.WrightRule#execute()
	 */
	@Override
	public void execute() throws InvalidParameterException{
		//		EXECUTE MONGO DB
		System.out.println("Execute mondo db insert");
	}
}
