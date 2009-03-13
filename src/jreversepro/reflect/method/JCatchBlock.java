/*
 * JCatchBlock.java
 *
 * Created on September 4, 2002, 2:56 PM
 * JReversePro - Java Decompiler / Disassembler.
 * Copyright (C) 2002 pazandak@objs.com
 * EMail: pazandak@objs.com
 *
 * This program is free software; you can redistribute it and/or modify
 * it , under the terms of the GNU General   License as published
 * by the Free Software Foundation; either version 2 of the License,
 * or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General   License for more details.
 * You should have received a copy of the GNU General   License
 * along with this program.If not, write to
 *  The Free Software Foundation, Inc.,
 *  59 Temple Place - Suite 330,
 *  Boston, MA 02111-1307, USA.
 */

package jreversepro.reflect.method;
import jreversepro.reflect.*;
import jreversepro.revengine.JBranchEntry;

/**
 *
 * @author  pazandak@objs.com -- Copyright 2002.
 */
public class JCatchBlock extends JBlockObject {
    
    /**
     * Exception class name
     */
    private String exceptionCls;

    /**
     * Exception variable name
     */
    private String exceptionVar;

    /**
     * Associated Branch Entry
     */
    private JBranchEntry branch;
    
    /** 
     * Creates a new instance of JCatchBlock
     * 
     * @param _cls The class name for the exception
     * @param _var The variable name for the exception instance
     */
    public JCatchBlock(JBranchEntry _jbe, String _cls, String _var) {
        branch = _jbe;
        exceptionCls = _cls;
        exceptionVar = _var;
    }

    /**
     * Outputs any starting code to open the block
     */
    protected String getEntryCode() {return "catch ("+ exceptionCls + " " + exceptionVar +") {\n";}

    /**
     * Outputs any terminating code to close the block
     */
    protected String getExitCode() {return "}\n";}

     
    
}
