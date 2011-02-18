package org.teiid.datatools.connectivity.sql;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.teiid.datatools.models.teiidsqlmodel.impl.DocumentImpl;

public class TeiidDocument extends DocumentImpl {

    private Boolean columnsLoaded = Boolean.FALSE;
    private final Object documentColumns_LOCK = new Object();
	@SuppressWarnings("rawtypes")
	private SoftReference documentColumnLoaderRef;

	public TeiidDocument() {
		super();
	}
	
    @SuppressWarnings("rawtypes")
	public EList getColumns() {
    	synchronized (documentColumns_LOCK) {
			if (!columnsLoaded.booleanValue())
				loadColumns();
		}
		return super.getColumns();
    }

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private final TeiidDocumentColumnLoader getDocumentColumnLoader() {
		if (documentColumnLoaderRef == null || documentColumnLoaderRef.get() == null) {
			documentColumnLoaderRef = new SoftReference(
					createDocumentLoader());
		}
		return (TeiidDocumentColumnLoader) documentColumnLoaderRef.get();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void loadColumns() {
		synchronized (documentColumns_LOCK) {
			boolean deliver = eDeliver();
			try {
				List container = super.getColumns();
				List existingColumns = new ArrayList(container);

				eSetDeliver(false);

				container.clear();
				getDocumentColumnLoader().loadColumns(container, existingColumns);
				getDocumentColumnLoader().clearColumns(existingColumns);

				columnsLoaded = Boolean.TRUE;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				eSetDeliver(deliver);
			}
		}
	}
	
	private TeiidDocumentColumnLoader createDocumentLoader() {
		return new TeiidDocumentColumnLoader(this);
	}

}
