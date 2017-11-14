package uk.org.landeg.projecteuler;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Hello world!
 *
 */
public class App 
{
	@org.springframework.context.annotation.Configuration
	@ComponentScan
    public static class Configuration {

	}

	public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(App.Configuration.class);
    }
}
