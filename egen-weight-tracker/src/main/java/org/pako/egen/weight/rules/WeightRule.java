/**
 *
 */
package org.pako.egen.weight.rules;

import java.security.InvalidParameterException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.annotation.Rule;
import org.easyrules.core.BasicRule;
import org.easyrules.spring.SpringRule;
import org.pako.egen.weight.db.entity.MetricEntity;
import org.pako.egen.weight.exception.ParameterException;
import org.pako.egen.weight.util.PropertyManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Pako Castillo
 *
 */
@Component
@SpringRule
@Rule(name="Weight Rules",description="This rule will be triggered when the base weight is 10% below the base")
public class WeightRule extends BasicRule {

	/** Static Logger **/
	protected static Logger LOG = LogManager.getLogger();

	/** Injected manager to extract the limit for the rule **/
	@Autowired
	protected PropertyManager propertyManager;

	/** Base weight **/
	private Integer baseweight;

	/** Current value provided by the sensor **/
	private Integer currentValue;

	/** Return true if the under weight rule was executed **/
	private boolean isLowerRule;

	/** Return true if the over weight rule was executed **/
	private boolean isHigherRule;

	/** Asses the base weight against the current value
	 * @throws ParameterException **/
	@Override
	@Condition
	public boolean evaluate(){

		Integer maxAcceptableWeight = propertyManager.getMaxAcceptableWeight();
		Integer mixAcceptableWeight = propertyManager.getMinAcceptableWeight();

		System.out.println("Inside Over Weight");
		/** If the parameter is null, throw an invalid parameter exception **/
		if(maxAcceptableWeight == null || mixAcceptableWeight == null || getCurrentValue() == null || getBaseweight() == null){
			return false;
		}
		LOG.debug("Current value: " + getCurrentValue() + " Base weight: " + getBaseweight() + maxAcceptableWeight);

		/**  **/
		if(1- getBaseweight() / getCurrentValue() > maxAcceptableWeight/100){
			System.out.println("Emulating higher. . " + this.getClass());
			isHigherRule = true;
		}



		System.out.println("Emulating smaller. . " + this.getClass());

		LOG.debug("Current value: " + getCurrentValue() + " Base weight: " + getBaseweight() + " Min Acceptable " + mixAcceptableWeight);

		System.out.println("Weight [" + getCurrentValue() + "] is lower than the required size. [Acceptable percentage " +  propertyManager.getMinAcceptableWeight() + ", Baseline " + getBaseweight() + "] Minimum size is  " + (float)propertyManager.getMinAcceptableWeight()/100 * getBaseweight() + " Current Value / Base " + (float)getCurrentValue()/getBaseweight());
		System.out.println(1- (float)getCurrentValue()/getBaseweight() + " > " + (float)mixAcceptableWeight/100);

		/**  **/
		if(1- (float)getCurrentValue()/getBaseweight() > (float)mixAcceptableWeight/100){
			System.out.println("Emulating lower. . " + this.getClass());
			isLowerRule = true;
		}

		return isHigherRule || isLowerRule;
	}

	/** Fire the rule if the condition is met **/
	@Override
	@Action
	public void execute() throws InvalidParameterException{
		isAlertCreated = true;

		if(isLowerRule){
			System.out.println("This is a lower rule. . .");
		}

		if(isHigherRule){
			System.out.println("This is a higher rule. . .");
		}

		// TODO Auto-generated method stub
		System.out.println("Weight [" + getCurrentValue() + "] is higher than the required size. Maximym size is  " + propertyManager.getMaxAcceptableWeight()/100 * getBaseweight() );

		System.out.println("\n\n\n************************************************************************************************");
		System.out.println("\t\t\tEmulating mongo db activity save . . .");
		System.out.println("************************************************************************************************");

	}

	/** Return true if an alert is created **/
	protected boolean isAlertCreated;

	/** Metric to be assessed **/
	//	private MetricEntity metric;

	/**
	 * @return the baseweight
	 */
	public Integer getBaseweight() {
		return baseweight;
	}

	public void setMetric(MetricEntity metric){
		//		this.metric = metric;
		baseweight = metric.getBaseWeight();
		currentValue = metric.getCalculatedWeight();
	}

	/**
	 * @param baseweight the baseweight to set
	 */
	public void setBaseweight(Integer baseweight) {
		this.baseweight = baseweight;
	}

	/**
	 * @return the currentValue
	 */
	public Integer getCurrentValue() {
		return currentValue;
	}

	/**
	 * @param currentValue the currentValue to set
	 */
	public void setCurrentValue(Integer currentValue) {
		this.currentValue = currentValue;
	}

	/**
	 * Return the current flag of alert creation
	 * @return
	 */
	public boolean hasAlertCreated(){
		return isAlertCreated;
	}

	/**
	 * Return the overWeight flag
	 * @return
	 */
	public boolean hasOverWeight(){
		return isHigherRule;
	}

	/**
	 * Return the underWeight flag
	 *
	 * @return
	 */
	public boolean hasUnderWeight(){
		return isLowerRule;
	}
}
