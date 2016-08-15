package org.pako.egen.weight.rules;

import java.io.Serializable;

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
public class UnderWeightRule extends WrightRule implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = -5616893656218415426L;

	/**
	 * @see org.pako.egen.weight.rules.WrightRule#evaluate()
	 */
	@Override
	public boolean evaluate() {
		Integer mixAcceptableWeight = propertyManager.getMinAcceptableWeight();

		//		System.out.println("Current value: " + getCurrentValue() + " Base weight: " + getBaseweight() + " Min Acceptable " + mixAcceptableWeight);

		/** If the parameter is null, throw an invalid parameter exception **/
		if(mixAcceptableWeight == null || getCurrentValue() == null || getBaseweight() == null){
			return false;
		}

		LOG.debug("Current value: " + getCurrentValue() + " Base weight: " + getBaseweight() + " Min Acceptable " + mixAcceptableWeight);

		System.out.println("Weight [" + getCurrentValue() + "] is lower than the required size. [Acceptable percentage " +  propertyManager.getMinAcceptableWeight() + ", Baseline " + getBaseweight() + "] Minimum size is  " + (float)propertyManager.getMinAcceptableWeight()/100 * getBaseweight() + " Current Value / Base " + (float)getCurrentValue()/getBaseweight());
		System.out.println(1- (float)getCurrentValue()/getBaseweight() + " > " + (float)mixAcceptableWeight/100);

		/**  **/
		if(1- (float)getCurrentValue()/getBaseweight() > (float)mixAcceptableWeight/100){
			return true;
		}

		System.out.println("Rule didn't match. . . ");
		return false;
	}

	/**
	 * @see org.pako.egen.weight.rules.WrightRule#execute()
	 */
	@Override
	public void execute() {
		// TODO Auto-generated method stub
		//		System.out.println("Weight [" + getCurrentValue() + "] is lower than the required size. [Acceptable percentage " +  propertyManager.getMinAcceptableWeight() + ", Baseline " + getBaseweight() + "] Minimum size is  " + (float)propertyManager.getMinAcceptableWeight()/100 * getBaseweight() );

		System.out.println("\n\n\n************************************************************************************************");
		System.out.println("\t\t\tEmulating mongo db activity save . . .");
		System.out.println("************************************************************************************************");
	}

}
