/**
 *
 */
package org.pako.egen.weight.rules;

import static org.easyrules.core.RulesEngineBuilder.aNewRulesEngine;

import org.easyrules.api.RulesEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pako.egen.weight.core.ServiceRunner;
import org.pako.egen.weight.db.entity.MetricEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import junit.framework.TestCase;

/**
 * Testcase for the validation rules
 *
 * @author Pako Castillo
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ServiceRunner.class)
@AutoConfigureMockMvc
public class WeightRuleTest extends TestCase {

	/** Rules Engine **/
	private RulesEngine ruleEngine =aNewRulesEngine().withSkipOnFirstAppliedRule(true)
			.withSkipOnFirstFailedRule(true)
			.build();

	@Autowired
	private WeightRule weightRule;

	private static final Integer baseWeight = 50;
	private static final Integer lowerMinReadWeight = 25;
	private static final Integer higherMaxReadWeight = 77;

	private void registerVaueAndFire(WeightRule rule, MetricEntity value){
		ruleEngine.registerRule(rule);
		rule.setMetric(value);
		ruleEngine.fireRules();
	}

	/**
	 * Test the effectiveness of the validation rules
	 */
	@Test
	public void testBothWeights(){
		System.out.println("Testing overweight rule . . .");
		MetricEntity metric = new MetricEntity();
		metric.setBaseWeight(baseWeight);
		metric.setCalculatedWeight(higherMaxReadWeight);

		registerVaueAndFire(weightRule, metric);

		/** Assess that the overweight rule and alert flags are enabled and the under weight disabled **/
		assertTrue(weightRule.isAlertCreated);
		assertTrue(weightRule.hasOverWeight());
		assertFalse(weightRule.hasUnderWeight());

		metric = new MetricEntity();
		metric.setBaseWeight(baseWeight);
		metric.setCalculatedWeight(lowerMinReadWeight);

		registerVaueAndFire(weightRule, metric);

		/** Assess that the underweight rule and alert flags are enabled and the over weight disabled **/
		assertTrue(weightRule.isAlertCreated);
		assertTrue(weightRule.hasUnderWeight());
		assertFalse(weightRule.hasOverWeight());

	}


}
