/*
 * generated by Xtext
 */
package org.eclipse.papyrus.common.editor.xtext.ui;

import org.eclipse.xtext.ui.guice.AbstractGuiceAwareExecutableExtensionFactory;
import org.osgi.framework.Bundle;

import com.google.inject.Injector;

/**
 * This class was generated. Customizations should only happen in a newly
 * introduced subclass. 
 */
public class UmlCommonExecutableExtensionFactory extends AbstractGuiceAwareExecutableExtensionFactory {

	@Override
	protected Bundle getBundle() {
		return org.eclipse.papyrus.common.editor.xtext.ui.internal.UmlCommonActivator.getInstance().getBundle();
	}
	
	@Override
	protected Injector getInjector() {
		return org.eclipse.papyrus.common.editor.xtext.ui.internal.UmlCommonActivator.getInstance().getInjector("org.eclipse.papyrus.common.editor.xtext.UmlCommon");
	}
	
}
