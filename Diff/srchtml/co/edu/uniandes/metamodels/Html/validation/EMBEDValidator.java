/**
 *
 * $Id$
 */
package co.edu.uniandes.metamodels.Html.validation;


/**
 * A sample validator interface for {@link co.edu.uniandes.metamodels.Html.EMBED}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface EMBEDValidator {
	boolean validate();

	boolean validateSrc(String value);
	boolean validateWidth(String value);
	boolean validateHeight(String value);
	boolean validateAlign(String value);
	boolean validateVspace(String value);
	boolean validateHspace(String value);
	boolean validateBorder(String value);
}
