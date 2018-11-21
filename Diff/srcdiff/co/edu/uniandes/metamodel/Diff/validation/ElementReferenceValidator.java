/**
 *
 * $Id$
 */
package co.edu.uniandes.metamodel.Diff.validation;

import java.math.BigInteger;

/**
 * A sample validator interface for {@link co.edu.uniandes.metamodel.Diff.ElementReference}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface ElementReferenceValidator {
	boolean validate();

	boolean validateEObject(String value);
	boolean validateLineNumber(BigInteger value);
	boolean validateValue(String value);
	boolean validatePath(String value);
}
