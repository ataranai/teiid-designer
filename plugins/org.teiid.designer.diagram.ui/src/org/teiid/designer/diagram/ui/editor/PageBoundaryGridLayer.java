/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package org.teiid.designer.diagram.ui.editor;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.FigureUtilities;
import org.eclipse.draw2d.Graphics;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.SnapToGrid;
import org.eclipse.gef.editparts.GridLayer;
import org.eclipse.swt.SWT;


/** 
 * @since 8.0
 */
public class PageBoundaryGridLayer extends GridLayer {

    protected int iDefaultGridCellWidth    = 1250;
    protected int iDefaultGridCellHeight   = 1300;
    
    protected int iGridCellWidth    = iDefaultGridCellWidth;
    protected int iGridCellHeight   = iDefaultGridCellHeight;

    /** 
     * 
     * @since 4.3
     */
    public PageBoundaryGridLayer() {
        super();
        setForegroundColor(ColorConstants.darkBlue);
        
        // default to false; make them turn us on
        setVisible( false );
    }
    

    /**     [[we may or may not need to override this one.  it is just here to study]]
     * Paints the grid.  Sub-classes can override to customize the grid's look.  If this layer
     * is being used with SnapToGrid, this method will only be invoked when the {@link
     * SnapToGrid#PROPERTY_GRID_VISIBLE visibility} property is set to true.
     * 
     * @param   g   The Graphics object to be used to do the painting
     * @see FigureUtilities#paintGrid(Graphics, IFigure, Point, int, int)
     */
    @Override
    protected void paintGrid(Graphics g) {

        int iOldLineStyle = g.getLineStyle();
        
        g.setLineStyle( SWT.LINE_DASH );
        
        FigureUtilities.paintGrid( g, this, origin, iGridCellWidth, iGridCellHeight );

        // restore previous values
        g.setLineStyle( iOldLineStyle );
    }

    /**
     * Sets the horizontal and vertical spacing of the grid.  A grid spacing of 0 will be
     * replaced with the {@link SnapToGrid#DEFAULT_GRID_SIZE default} spacing.  A negative
     * spacing will cause no grid lines to be drawn for that dimension.
     * 
     * @param   spacing     A Dimension representing the horizontal (width) and vertical 
     *                      (height) gaps
     */
    @Override
    public void setSpacing( Dimension spacing ) {
        // just a test!
//        System.out.println("[PageBoundaryGridLayer.setSpacing] spacing: " + spacing );  //$NON-NLS-1$

        if ( spacing == null ) {
            iGridCellWidth    = iDefaultGridCellWidth;
            iGridCellHeight   = iDefaultGridCellHeight;
        } else {
            iGridCellWidth    = spacing.width;
            iGridCellHeight   = spacing.height;       
        }

//        System.out.println("[PageBoundaryGridLayer.setSpacing] new iGridCellWidth: " + iGridCellWidth );  //$NON-NLS-1$
//        System.out.println("[PageBoundaryGridLayer.setSpacing] new iGridCellHeight: " + iGridCellHeight );  //$NON-NLS-1$
        setOrigin( new Point( 0, 0 ) );
        repaint();
    }


}
