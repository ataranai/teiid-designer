/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.core.index;

import org.teiid.designer.core.index.AbstractIndexSelector;
import org.teiid.designer.core.index.Index;

/**
 * NullIndexSelector returns an emtpy list for
 *
 * @since 8.0
 */
public class NullIndexSelector extends AbstractIndexSelector {

    private static final Index[] EMPTY_INDEX_ARRAY = new Index[0];

    /**
     * Construct an instance of NullIndexSelector
     */
    public NullIndexSelector() {
    }

    /**
     * @see org.teiid.designer.core.index.IndexSelector#getIndexes()
     */
    @Override
    public Index[] getIndexes() {
        return EMPTY_INDEX_ARRAY;
    }

}
