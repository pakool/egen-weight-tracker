/**
 *
 */
package org.pako.egen.weight.rules;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.easyrules.annotation.Action;
import org.easyrules.annotation.Condition;
import org.easyrules.core.BasicRule;
import org.pako.egen.weight.exception.ParameterException;
import org.pako.egen.weight.util.PropertyManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Pako Castillo
 *
 */
public abstract class WrightRule extends BasicRule {

	/** Static Logger **/
	protected static Logger LOG = LogManager.getLogger();

	/** Injected manager to extract the limit for the rule **/
	@Autowired
	protected PropertyManager propertyManager;

	/** Base weight **/
	private Integer baseweight;

	/** Current value provided by the sensor **/
	private Integer currentValue;

	/** Asses the base weight against the current value
	 * @throws ParameterException **/
	@Override
	@Condition
	public abstract boolean evaluate();

	/** Fire the rule if the condition is met **/
	@Override
	@Action
	public abstract void execute();

	/**
	 * @return the baseweight
	 */
	public Integer getBaseweight() {
		return baseweight;
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

}
