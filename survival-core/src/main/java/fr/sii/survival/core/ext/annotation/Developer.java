package fr.sii.survival.core.ext.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Developer {
	/**
	 * The nickname of the developer
	 * 
	 * @return the developer nickname
	 */
	public String value();
	
	/**
	 * The name of the developer
	 * 
	 * @return the developer name
	 */
	public String name() default "";
	
	/**
	 * The email of the developer
	 * 
	 * @return the developer email
	 */
	public String email() default "";
}
