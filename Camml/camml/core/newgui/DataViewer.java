/*
 *  [The "BSD license"]
 *  Copyright (c) 2002-2011, Rodney O'Donnell, Lloyd Allison, Kevin Korb
 *  Copyright (c) 2002-2011, Monash University
 *  All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions
 *  are met:
 *    1. Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *    2. Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *    3. The name of the author may not be used to endorse or promote products
 *       derived from this software without specific prior written permission.*
 *
 *  THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 *  IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 *  OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 *  IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 *  INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 *  NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 *  DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 *  THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 *  (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 *  THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package camml.core.newgui;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import cdms.core.Value;

/**Very basic data viewer.
 * Basically a JFrame with a JTable in it that displays the contents of the
 * 	selected data.
 * @author Alex Black
 */
public class DataViewer extends JFrame{
	private static final long serialVersionUID = 3193733811587674115L;
	
	private final Value.Vector data;
	String[] colNames;
	TableModel tableModel;
	JTable table;
	
	/**Constructor for the data viewer. Expects a CDMS Value.Vector data
	 * object - i.e. what CaMML uses to represent data.
	 */
	public DataViewer( Value.Vector data ){
		this.data = data;
		
		colNames = ((cdms.core.Type.Structured)((cdms.core.Type.Vector)data.t).elt).labels;
		
		tableModel = new DataTable();
		table = new JTable( tableModel );
		add( new JScrollPane( table ) );
	}
	
	
	public class DataTable extends AbstractTableModel{
		private static final long serialVersionUID = 9173322447957720914L;

		public int getColumnCount() {
			return colNames.length;
		}

		public int getRowCount() {
			return data.length();
		}

		public Object getValueAt(int row, int col) {
			return data.cmpnt(col).elt(row);
		}
		
		public String getColumnName( int col ){
			return colNames[ col ];
		}
	}
}
