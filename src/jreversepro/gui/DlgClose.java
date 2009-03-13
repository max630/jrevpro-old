/*
 * @(#)DlgClose.java
 *
 * JReversePro - Java Decompiler / Disassembler.
 * Copyright (C) 2000 Karthik Kumar.
 * EMail: akarthikkumar@hotmail.com
 *
 * This program is free software; you can redistribute it and/or modify
 * it , under the terms of the GNU General Public License as published
 * by the Free Software Foundation; either version 2 of the License,
 * or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with this program.If not, write to
 *  The Free Software Foundation, Inc.,
 *  59 Temple Place - Suite 330,
 *  Boston, MA 02111-1307, USA.
 */
package jreversepro.gui;

import java.awt.Window;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;


/**
 * Listener to close the dialogs.
 * @author Karthik Kumar
 * @version 1.3
 **/
public class DlgClose extends WindowAdapter {

    /**
     * @param aEvent Windoe event generated.
     **/
    public void windowClosing(WindowEvent aEvent ) {
        if ( aEvent.getSource() instanceof Window  ) {
            Window win = (Window)( aEvent.getSource() );
            win.setVisible(false);
        }
    }
}