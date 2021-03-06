/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.core.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import org.teiid.core.designer.util.LRUCache;

/**
 *  The <code>OverflowingLRUCache</code> is an LRUCache which attempts
 *  to maintain a size equal or less than its <code>fSpaceLimit</code>
 *  by removing the least recently used elements.
 *
 *  <p>The cache will remove elements which successfully close and all
 *  elements which are explicitly removed.
 *
 *  <p>If the cache cannot remove enough old elements to add new elements
 *  it will grow beyond <code>fSpaceLimit</code>. Later, it will attempt to 
 *  shrink back to the maximum space limit.
 *
 *  The method <code>close</code> should attempt to close the element.  If
 *  the element is successfully closed it will return true and the element will
 *  be removed from the cache.  Otherwise the element will remain in the cache.
 *
 *  <p>The cache implicitly attempts shrinks on calls to <code>put</code>and
 *  <code>setSpaceLimit</code>.  Explicitly calling the <code>shrink</code> method
 *  will also cause the cache to attempt to shrink.
 *
 *  @see LRUCache
 *
 * @since 8.0
 */
public abstract class OverflowingLRUCache<K, V> extends LRUCache<K, V> {
	
	/**
     */
    private static final long serialVersionUID = 1L;

    public OverflowingLRUCache(int maxSize) {
		super(maxSize);
	}
	
	@Override
	protected boolean removeEldestEntry(Entry<K, V> eldest) {
		if (!super.removeEldestEntry(eldest)) {
			return false;
		}
		
		Iterator<Map.Entry<K, V>> entryIter = this.entrySet().iterator();
		
		int targetSize = (int)(maxSize * .66f);
		
		while (size() > targetSize && entryIter.hasNext()) {
			if (close(entryIter.next())) {
				entryIter.remove();
			}
		}
		
		return false;
	}
	
	protected abstract boolean close(Entry<K, V> entry);
	
	public int getOverflow() {
		return Math.max(0, size() - maxSize);
	}
}
