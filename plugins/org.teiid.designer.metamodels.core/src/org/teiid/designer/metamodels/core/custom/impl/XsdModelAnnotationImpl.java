/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.metamodels.core.custom.impl;

import java.util.Iterator;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.teiid.designer.metamodels.core.extension.XPackage;
import org.teiid.designer.metamodels.core.impl.ModelAnnotationImpl;

/**
 * mmDefect_12555 - Created XsdModelAnnotationImpl so that an Resource reference was available in the ModelAnnotationItemProvider
 * since ModelAnnotation instances associated with XSD resources return null for the eResource reference.
 * 
 * @since 8.0
 */
public class XsdModelAnnotationImpl extends ModelAnnotationImpl {

    protected Resource xsdResource;

    /**
     * @since 4.2
     */
    public XsdModelAnnotationImpl() {
        super();
    }

    /**
     * @since 4.2
     */
    public XsdModelAnnotationImpl( final Resource xsdResource ) {
        super();
        this.xsdResource = xsdResource;
    }

    /**
     * {@inheritDoc}
     * 
     * @see org.eclipse.emf.ecore.impl.BasicEObjectImpl#eResource()
     */
    @Override
    public Resource eResource() {
        // since this object is NOT added to the resource contents it does not have an eResource. Our framework for finding
        // an object given a UUID (and vice versa) requires a resource.
        return getResource();
    }

    /**
     * @since 4.2
     */
    public Resource getResource() {
        return this.xsdResource;
    }

    /**
     * @since 4.2
     */
    public void setResource( final Resource xsdResource ) {
        this.xsdResource = xsdResource;
    }

    @Override
    public void setExtensionPackage( XPackage newExtensionPackage ) {
        super.setExtensionPackage(newExtensionPackage);

        // mmDefect_12555 - Perform some get/set trickery on the xsd resource so that it is marked as requiring save
        if (this.xsdResource != null) {
            this.xsdResource.setModified(true);
            EObject schema = this.getSchema(this.xsdResource);
            if (schema != null) {
                EAttribute eAttrib = this.getTargetNamespaceAttribute(schema);
                if (eAttrib != null) {
                    Object origValue = schema.eGet(eAttrib);
                    schema.eSet(eAttrib, null);
                    schema.eSet(eAttrib, origValue);
                }
            }
        }
    }

    private EObject getSchema( final Resource resource ) {
        for (Iterator iter = resource.getContents().iterator(); iter.hasNext();) {
            Object root = iter.next();
            if (root instanceof EObject) {
                final EClass eClass = ((EObject)root).eClass();
                if (eClass.getName().equalsIgnoreCase("XSDSchema")) { //$NON-NLS-1$
                    return (EObject)root;
                }
            }
        }
        return null;
    }

    private EAttribute getTargetNamespaceAttribute( final EObject eObject ) {
        final EClass eClass = eObject.eClass();
        return (EAttribute)eClass.getEStructuralFeature("targetNamespace"); //$NON-NLS-1$
    }
}
