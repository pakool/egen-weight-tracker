/**
 *
 */
package org.pako.egen.weight.rules;

import static org.easyrules.core.RulesEngineBuilder.aNewRulesEngine;

import org.easyrules.api.RulesEngine;
import org.easyrules.core.CompositeRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.pako.egen.weight.db.entity.MetricEntity;
import org.pako.egen.weight.util.ApplicationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import junit.framework.TestCase;

/**
 * @author Pako Castillo
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationConfig.class)
@AutoConfigureMockMvc
//@SpringBootContextLoader(classes = ServiceRunner.class)
public class WeightRuleTest extends TestCase {

	/** Rules Engine **/
	private RulesEngine ruleEngine =aNewRulesEngine().withSkipOnFirstAppliedRule(true)
			.withSkipOnFirstFailedRule(true)
			.build();

	@Autowired
	private WeightRule overWeightRule;

	//	@Autowired
	//	private UnderWeightRule underWeightRule;

	private static final Integer baseWeight = 50;

	private static final Integer lowerMinReadWeight = 25;

	private static final Integer higherMinReadWeight = 47;

	private static final Integer lowerMaxReadWeight = 54;

	private static final Integer higherMaxReadWeight = 77;

	private void registerVaueAndFire(WeightRule rule, MetricEntity value){
		ruleEngine.registerRule(rule);
		rule.setMetric(value);
		ruleEngine.fireRules();
	}

	//	@Test
	public void testMinWeight(){
		RulesEngine rulesEngine = aNewRulesEngine().build();

		//		System.out.println("The underweight rule injected " + underWeightRule);
		//		System.out.println("The underweight rule injected END END END END ");
		//		underWeightRule.setBaseweight(baseWeight);
		//		underWeightRule.setCurrentValue(lowerMinReadWeight);
		//
		//		rulesEngine.registerRule(underWeightRule);
		//
		//		rulesEngine.fireRules();
	}

	//	@Test
	public void testMaxWeight(){
		//		SpringBootContextLoader context = new SpringBootContextLoader();
		CompositeRule compositeRule = new CompositeRule("cr");
		RulesEngine rulesEngine = aNewRulesEngine().build();


		//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		//		UnderWeightRule underWeightRule = context.getBean(UnderWeightRule.class);

		//		System.out.println("The underweight rule injected " + underWeightRule);
		System.out.println("The underweight rule injected END END END END ");
		//		underWeightRule.setBaseweight(baseWeight);
		//		underWeightRule.setCurrentValue(lowerMinReadWeight);
		//
		//		overWeightRule.setBaseweight(baseWeight);
		//		overWeightRule.setCurrentValue(lowerMinReadWeight);

		compositeRule.addRule(overWeightRule);
		//		compositeRule.addRule(underWeightRule);
		//        compositeRule.addRule(rule2);

		rulesEngine.registerRule(compositeRule);

		//		rulesEngine.fireRules();

		//		rulesEngine.fireRules();
	}

	@Test
	public void testBothWeights(){
		System.out.println("Both Weights. . . ");
		MetricEntity metric = new MetricEntity();
		metric.setBaseWeight(baseWeight);
		metric.setCalculatedWeight(higherMaxReadWeight);

		registerVaueAndFire(overWeightRule, metric);

		System.out.println("Was alert created? " + overWeightRule.isAlertCreated);

		//		metric = new MetricEntity();
		//		metric.setBaseWeight(baseWeight);
		//		metric.setCalculatedWeight(lowerMaxReadWeight);
		//
		//		registerVaueAndFire(overWeightRule, metric);
		//
		//		System.out.println("Was alert created? " + overWeightRule.isAlertCreated);

		metric = new MetricEntity();
		metric.setBaseWeight(baseWeight);
		metric.setCalculatedWeight(lowerMinReadWeight);

		registerVaueAndFire(overWeightRule, metric);

		System.out.println("Was alert created? " + overWeightRule.isAlertCreated);

		//		metric = new MetricEntity();
		//		metric.setBaseWeight(baseWeight);
		//		metric.setCalculatedWeight(higherMinReadWeight);
		//
		//		registerVaueAndFire(underWeightRule, metric);
		//
		//		System.out.println("Was alert created? " + overWeightRule.isAlertCreated);
	}


}
