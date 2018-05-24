package dmi.pmf.novica.mvpwiseass.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;


// RetentionPolicy.CLASS
// will be retained till compiling the code,
// and discarded during runtime

@Scope
@Retention(RetentionPolicy.CLASS)
public @interface RandomUserApplicationScope {
}
