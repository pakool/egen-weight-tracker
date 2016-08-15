package org.pako.egen.weight.rules;

import java.io.Serializable;
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
public class OverWeightRule extends WrightRule implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1955508490651234848L;

	/**
	 * Return true if the value exceeds the threshold
	 *
	 * @see org.pako.egen.weight.rules.WrightRule#evaluate()
	 */
	@Override
	public boolean evaluate(){
		Integer maxAcceptableWeight = propertyManager.getMaxAcceptableWeight();
		System.out.println("Inside Over Weight");
		/** If the parameter is null, throw an invalid parameter exception **/
		if(maxAcceptableWeight == null || getCurrentValue() == null || getBaseweight() == null){
			return false;
		}

		System.out.println("Emulating higher. . ");

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
		// TODO Auto-generated method stub
		System.out.println("Weight [" + getCurrentValue() + "] is higher than the required size. Maximym size is  " + propertyManager.getMaxAcceptableWeight()/100 * getBaseweight() );

		System.out.println("\n\n\n************************************************************************************************");
		System.out.println("\t\t\tEmulating mongo db activity save . . .");
		System.out.println("************************************************************************************************");

	}
}
