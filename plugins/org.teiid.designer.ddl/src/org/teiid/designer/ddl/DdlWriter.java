/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.ddl;

import java.io.OutputStream;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.ecore.resource.Resource;
import org.teiid.designer.core.workspace.ModelResource;
import org.teiid.designer.core.workspace.ModelWorkspace;
import org.teiid.designer.core.workspace.ModelWorkspaceSelections;

/**
 * DdlWriter
 *
 * @since 8.0
 */
public interface DdlWriter {

    /**
     * Get the options that this writer is currently using
     * 
     * @return the options; never null
     */
    public DdlOptions getOptions();

    /**
     * Write out the supplied model as DDL to the supplied stream.
     * 
     * @param emfResource the {@link org.teiid.designer.metamodels.relational.RelationalPackage relational} EMF resource that contains
     *        the model to be written out; may not be null
     * @param modelName the name of the model in the resource; may not be null
     * @param modelFilename the name of the model resource
     * @param stream the stream to which the DDL is to be written; may not be null
     * @param monitor the monitor the should be used; may be null
     * @return a status of the process with any {@link IStatus#WARNING warnings}, {@link IStatus#ERROR errors} or
     *         {@link IStatus#INFO information messages}, or which will be {@link IStatus#isOK() marked as OK} if there were no
     *         warnings, errors or other messages.
     */
    public IStatus write( Resource emfResource,
                          String modelName,
                          String modelFilename,
                          OutputStream stream,
                          IProgressMonitor monitor );

    /**
     * Write out the supplied model as DDL to the supplied stream.
     * 
     * @param model the {@link org.teiid.designer.metamodels.relational.RelationalPackage relational} model that is to be written out;
     *        may not be null
     * @param stream the stream to which the DDL is to be written; may not be null
     * @param monitor the monitor the should be used; may be null
     * @return a status of the process with any {@link IStatus#WARNING warnings}, {@link IStatus#ERROR errors} or
     *         {@link IStatus#INFO information messages}, or which will be {@link IStatus#isOK() marked as OK} if there were no
     *         warnings, errors or other messages.
     */
    public IStatus write( ModelResource model,
                          OutputStream stream,
                          IProgressMonitor monitor );

    /**
     * Write out the selected objects in the {@link ModelWorkspace model workspace} as DDL to the supplied stream.
     * 
     * @param model the {@link org.teiid.designer.metamodels.relational.RelationalPackage relational} model that is to be written out;
     *        may not be null
     * @param stream the stream to which the DDL is to be written; may not be null
     * @param monitor the monitor the should be used; may be null
     * @return a status of the process with any {@link IStatus#WARNING warnings}, {@link IStatus#ERROR errors} or
     *         {@link IStatus#INFO information messages}, or which will be {@link IStatus#isOK() marked as OK} if there were no
     *         warnings, errors or other messages.
     */
    public IStatus write( ModelWorkspaceSelections selections,
                          OutputStream stream,
                          IProgressMonitor monitor );
}
