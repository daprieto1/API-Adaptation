/**
 *
 * $Id$
 */
package co.edu.uniandes.metamodels.Html.validation;


/**
 * A sample validator interface for {@link co.edu.uniandes.metamodels.Html.OBJECT}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface OBJECTValidator {
	boolean validate();

	boolean validateClassid(String value);
	boolean validateData(String value);
	boolean validateType(String value);
	boolean validateStandby(String value);
}
