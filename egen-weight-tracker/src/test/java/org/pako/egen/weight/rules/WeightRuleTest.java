/**
 *
 */
package org.pako.egen.weight.rules;

import static org.easyrules.core.RulesEngineBuilder.aNewRulesEngine;

import org.easyrules.api.RulesEngine;
import org.easyrules.core.CompositeRule;
import org.junit.Test;
import org.junit.runner.RunWith;
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

	@Autowired
	private OverWeightRule overWeightRule;

	@Autowired
	private UnderWeightRule underWeightRule;

	private static final Integer baseWeight = 50;

	private static final Integer lowerMinReadWeight = 25;

	private static final Integer higherMinReadWeight = 47;

	private static final Integer lowerMaxReadWeight = 54;

	private static final Integer higherMaxReadWeight = 77;

	//	@Autowired
	//	RulesEngine rulesEngine;

	//	@Test
	public void testMinWeight(){
		//		SpringBootContextLoader context = new SpringBootContextLoader();
		CompositeRule compositeRule = new CompositeRule("cr");
		RulesEngine rulesEngine = aNewRulesEngine().build();


		//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		//		UnderWeightRule underWeightRule = context.getBean(UnderWeightRule.class);

		System.out.println("The underweight rule injected " + underWeightRule);
		System.out.println("The underweight rule injected END END END END ");
		underWeightRule.setBaseweight(baseWeight);
		underWeightRule.setCurrentValue(lowerMinReadWeight);



		//		overWeightRule.setBaseweight(baseWeight);
		//		overWeightRule.setCurrentValue(readWeight);

		//		compositeRule.addRule(overWeightRule);
		//		compositeRule.addRule(underWeightRule);
		//        compositeRule.addRule(rule2);


		rulesEngine.registerRule(underWeightRule);

		overWeightRule.setBaseweight(baseWeight);
		overWeightRule.setCurrentValue(lowerMaxReadWeight);

		//		compositeRule.addRule(overWeightRule);


		//		rulesEngine.registerRule(compositeRule);

		rulesEngine.fireRules();



		underWeightRule.setBaseweight(baseWeight);
		underWeightRule.setCurrentValue(higherMinReadWeight);

		compositeRule.addRule(underWeightRule);

		rulesEngine.registerRule(compositeRule);

		rulesEngine.fireRules();

		//		rulesEngine.fireRules();
	}

	//	@Test
	public void testMaxWeight(){
		//		SpringBootContextLoader context = new SpringBootContextLoader();
		CompositeRule compositeRule = new CompositeRule("cr");
		RulesEngine rulesEngine = aNewRulesEngine().build();


		//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		//		UnderWeightRule underWeightRule = context.getBean(UnderWeightRule.class);

		System.out.println("The underweight rule injected " + underWeightRule);
		System.out.println("The underweight rule injected END END END END ");
		underWeightRule.setBaseweight(baseWeight);
		underWeightRule.setCurrentValue(lowerMinReadWeight);

		overWeightRule.setBaseweight(baseWeight);
		overWeightRule.setCurrentValue(lowerMinReadWeight);

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

		//		CompositeRule compositeRule = new CompositeRule("cr");
		RulesEngine rulesEngine = aNewRulesEngine().withSkipOnFirstAppliedRule(true)
				.withSkipOnFirstFailedRule(true)
				.build();

		underWeightRule.setBaseweight(baseWeight);
		underWeightRule.setCurrentValue(higherMinReadWeight);
		overWeightRule.setBaseweight(baseWeight);
		overWeightRule.setCurrentValue(lowerMaxReadWeight);

		//		compositeRule.addRule(underWeightRule);

		//		compositeRule.addRule(overWeightRule);

		rulesEngine.registerRule(underWeightRule);
		rulesEngine.registerRule(overWeightRule);

		rulesEngine.fireRules();
	}


}
