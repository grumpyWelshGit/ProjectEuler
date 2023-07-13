//package uk.org.landeg.projecteuler.problems;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import uk.org.landeg.projecteuler.ProblemContext;
//import uk.org.landeg.projecteuler.ProblemSolution;
//
//@ContextConfiguration(classes=SolutionTest.TestConfiguration.class)
//@RunWith(SpringJUnit4ClassRunner.class)
//class SolutionTest {
//	@Configuration
//	public static class TestConfiguration {
//		@Mock
//		private ProblemContext context;
//		private ProblemSolution solution;
//		
//		@Bean
//		@Autowired
//		public ProblemContext getProblemContext (ProblemSolution<Object> problemSolution) {
//			ProblemContext context = Mockito.mock(ProblemContext.class);
//			Mockito.when(context.getSolution()).thenReturn(problemSolution);
//			return context;
//		}
//		
//		@Bean
//		public ProblemSolution<Object> problemSolution () {
//			return Mockito.mock(ProblemSolution.class);
//		}
//	}
//
//	
//	@Test
//	public void AssertSolution001 () {
//		assertEquals(233168, new Problem001().solve().intValue());
//		assertEquals(4613732, new Problem002().solve().intValue());
//		assertEquals(6857, new Problem003().solve().intValue());
//		assertEquals(906609, new Problem004().solve().intValue());
//		assertEquals(232792560, new Problem005().solve().intValue());
//		assertEquals(25164150, new Problem006().solve().intValue());
//		
//	}
//}
