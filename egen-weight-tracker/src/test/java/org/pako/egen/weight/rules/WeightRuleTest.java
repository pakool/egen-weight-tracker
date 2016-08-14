/**
 *
 */
package org.pako.egen.weight.rules;

import static org.easyrules.core.RulesEngineBuilder.aNewRulesEngine;

import org.easyrules.api.RulesEngine;
import org.easyrules.core.CompositeRule;
import org.junit.Test;
import org.pako.egen.weight.util.ApplicationConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import junit.framework.TestCase;

/**
 * @author Pako Castillo
 *
 */
//@RunWith(SpringRunner.class)
//SpringApplicationConfiguration(classes = ServiceRunner.class)
public class WeightRuleTest extends TestCase {

	//	@Autowired
	//	private OverWeightRule overWeightRule;
	//
	//	@Autowired
	//	private UnderWeightRule underWeightRule;

	private static final Integer baseWeight = 50;

	private static final Integer readWeight = 25;

	//	@Autowired
	//	RulesEngine rulesEngine;

	@Test
	public void testMinWeight(){

		CompositeRule compositeRule = new CompositeRule("cr");
		RulesEngine rulesEngine = aNewRulesEngine().build();


		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		UnderWeightRule underWeightRule = context.getBean(UnderWeightRule.class);

		System.out.println(underWeightRule);
		underWeightRule.setBaseweight(baseWeight);
		underWeightRule.setCurrentValue(readWeight);

		compositeRule.addRule(underWeightRule);
		//        compositeRule.addRule(rule2);

		rulesEngine.registerRule(compositeRule);

		rulesEngine.fireRules();

		//		rulesEngine.fireRules();
	}
}
