/**
 *
 * $Id$
 */
package co.edu.uniandes.metamodels.Html.validation;

import co.edu.uniandes.metamodels.Html.TR;

import org.eclipse.emf.common.util.EList;

/**
 * A sample validator interface for {@link co.edu.uniandes.metamodels.Html.TABLE}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface TABLEValidator {
	boolean validate();

	boolean validateBorder(String value);
	boolean validateWidth(String value);
	boolean validateCellspacing(String value);
	boolean validateCellpadding(String value);
	boolean validateTrs(EList<TR> value);
}
