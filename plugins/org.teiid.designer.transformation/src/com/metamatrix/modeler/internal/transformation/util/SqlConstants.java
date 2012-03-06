/*
 * JBoss, Home of Professional Open Source.
 *
 * See the LEGAL.txt file distributed with this work for information regarding copyright ownership and licensing.
 *
 * See the AUTHORS.txt file distributed with this work for a full listing of individual contributors.
 */
package com.metamatrix.modeler.internal.transformation.util;

/**
 * SqlConstants
 */
public interface SqlConstants {

    public static final String TAB = "\t"; //$NON-NLS-1$
    public static final String CR = "\n"; //$NON-NLS-1$
    public static final String BLANK = ""; //$NON-NLS-1$
    public static final String DBL_SPACE = "  "; //$NON-NLS-1$
    public static final String SPACE = " "; //$NON-NLS-1$
    public static final String COMMA = ","; //$NON-NLS-1$
    public static final String RETURN = "\n"; //$NON-NLS-1$
    public static final String SELECT = "SELECT"; //$NON-NLS-1$
    public static final String FROM = "FROM"; //$NON-NLS-1$
    public static final String WHERE = "WHERE"; //$NON-NLS-1$
	public static final char DOT = '.';
	public static final char STAR = '*';
    public static final char L_PAREN = '(';
    public static final char R_PAREN = ')';
    public static final char S_QUOTE = '\'';
    public static final String AS = "AS"; //$NON-NLS-1$
    public static final String COLUMNS = "COLUMNS"; //$NON-NLS-1$
    public static final String BEGIN = "BEGIN"; //$NON-NLS-1$
    public static final String END = "END"; //$NON-NLS-1$
    public static final String XMLELEMENT = "XMLELEMENT"; //$NON-NLS-1$
    public static final String NAME = "NAME"; //$NON-NLS-1$
    public static final String XMLNAMESPACES = "XMLNAMESPACES"; //$NON-NLS-1$
    public static final String DEFAULT = "DEFAULT"; //$NON-NLS-1$
    public static final String XMLTABLE = "XMLTABLE"; //$NON-NLS-1$
    public static final String TEXTTABLE = "TEXTTABLE"; //$NON-NLS-1$
    
    public static final String PATH = "PATH"; //$NON-NLS-1$
    public static final String FOR_ORDINALITY = "FOR ORDINALITY"; //$NON-NLS-1$
    public static final String DEFAULT_XQUERY = "/"; //$NON-NLS-1$
    public static final String GET = "GET"; //$NON-NLS-1$
    public static final String PASSING = "PASSING"; //$NON-NLS-1$

    
    public static final String SQL_TYPE_SELECT_STRING = "SELECT"; //$NON-NLS-1$
    public static final String SQL_TYPE_UPDATE_STRING = "UPDATE"; //$NON-NLS-1$
    public static final String SQL_TYPE_INSERT_STRING = "INSERT"; //$NON-NLS-1$
    public static final String SQL_TYPE_DELETE_STRING = "DELETE"; //$NON-NLS-1$
    public static final String SQL_TYPE_UNKNOWN_STRING = "UNKNOWN"; //$NON-NLS-1$
}
