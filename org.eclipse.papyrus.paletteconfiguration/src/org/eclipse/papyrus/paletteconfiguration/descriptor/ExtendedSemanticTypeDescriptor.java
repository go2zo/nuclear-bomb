package org.eclipse.papyrus.paletteconfiguration.descriptor;

import java.net.MalformedURLException;
import java.net.URL;

import org.eclipse.gmf.runtime.emf.type.core.IContainerDescriptor;
import org.eclipse.gmf.runtime.emf.type.core.IElementMatcher;
import org.eclipse.gmf.runtime.emf.type.core.IElementType;
import org.eclipse.gmf.runtime.emf.type.core.ISpecializationTypeDescriptor;
import org.eclipse.gmf.runtime.emf.type.core.edithelper.IEditHelperAdvice;
import org.eclipse.papyrus.paletteconfiguration.Activator;
import org.osgi.framework.Bundle;


/**
 * new descriptor implementation for on-the-fly added descriptor
 */
public class ExtendedSemanticTypeDescriptor implements ISpecializationTypeDescriptor {

	/** id of the type */
	public static final String TYPE_ID = "org.eclipse.papyrus.uml.testSpecializationType";

	/**
	 * {@inheritDoc}
	 */
	public String getId() {
		return TYPE_ID;
	}

	/**
	 * {@inheritDoc}
	 */
	public URL getIconURL() {
		Bundle bundle = Activator.getDefault().getBundle();
		URL result = bundle.getEntry("/icons/elementTypeIcon.gif");

		if(result == null) {
			try {
				result = new URL("/icons/elementTypeIcon.gif");
			} catch (MalformedURLException e) {
				result = null;
			}
		}
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getName() {
		return "MyTestType";
	}

	/**
	 * {@inheritDoc}
	 */
	public String getKindName() {
		return "org.eclipse.gmf.runtime.emf.type.core.IHintedType";
	}

	/**
	 * {@inheritDoc}
	 */
	public String getParamValue(String paramName) {
		return "ExtendedElements";
	}

	/**
	 * {@inheritDoc}
	 */
	public IContainerDescriptor getContainerDescriptor() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public IElementType[] getSpecializedTypes() {
		// retrieve element types from the registry


		// return Arrays.asList(UMLElementTypes.CLASS).toArray(new IElementType[]{});
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public IElementMatcher getMatcher() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public IEditHelperAdvice getEditHelperAdvice() {
		return new ExtendedEditHelperAdvice();
	}
	
}
