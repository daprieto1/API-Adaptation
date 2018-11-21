/**
 *
 * $Id$
 */
package co.edu.uniandes.metamodel.Html.validation;

import co.edu.uniandes.metamodel.Html.BODYElement;
import co.edu.uniandes.metamodel.Html.HTML;

import org.eclipse.emf.common.util.EList;

/**
 * A sample validator interface for {@link co.edu.uniandes.metamodel.Html.BODY}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface BODYValidator {
	boolean validate();

	boolean validateBackground(Object value);
	boolean validateBgcolor(Object value);
	boolean validateText(Object value);
	boolean validateLink(Object value);
	boolean validateAlink(Object value);
	boolean validateVlink(Object value);
	boolean validateBodyElements(EList<BODYElement> value);
	boolean validateHtml(HTML value);
}
