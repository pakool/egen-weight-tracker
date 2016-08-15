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
import org.pako.egen.weight.db.dao.AlertsDao;
import org.pako.egen.weight.db.entity.AlertEntity;
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

	@Autowired
	protected AlertsDao alertsDao;

	/** Return true if an alert is created **/
	protected boolean isAlertCreated;

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
		LOG.debug("Current value: " + getCurrentValue() + " Base weight: " + getBaseweight() + " Max Acceptable " + maxAcceptableWeight + " Min Acceptable " + mixAcceptableWeight);

		/** The value is higher than expected **/
		if(1- getBaseweight() / getCurrentValue() > maxAcceptableWeight/100){
			isHigherRule = true;
		}

		/** The value is lower than expected **/
		if(1- (float)getCurrentValue()/getBaseweight() > (float)mixAcceptableWeight/100){
			isLowerRule = true;
		}

		return isHigherRule || isLowerRule;
	}

	/** Fire the rule if the condition is met **/
	@Override
	@Action
	public void execute() throws InvalidParameterException{
		isAlertCreated = true;

		AlertEntity alert = new AlertEntity();
		alert.setReportingTime(System.currentTimeMillis());
		alert.setReportingClass(this.getClass().getName());
		String alertMessage = null;

		/** Lower rule is Underweight **/
		if(isLowerRule){
			alert.setReportingType("UNDERWEIGHT");
			alertMessage = "The current weight " + getCurrentValue() + " is below the minimum expected: " + (getBaseweight() - propertyManager.getMinAcceptableWeight()/100 * getBaseweight());
		}

		/** Lower rule is Overweight **/
		if(isHigherRule){
			alert.setReportingType("OVERWEIGHT");
			alertMessage = "The current weight " + getCurrentValue() + " is above the maximum expected: " + (getBaseweight() + propertyManager.getMaxAcceptableWeight()/100 * getBaseweight());
			System.out.println("This is a higher rule. . .");
		}

		alert.setAlertMessage(alertMessage);
		LOG.debug(alertMessage);

		try {
			alertsDao.saveAlert(alert);
		} catch (ParameterException e) {
			LOG.error("There was an error saving the alert in the DB ", e);
		}

	}

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
