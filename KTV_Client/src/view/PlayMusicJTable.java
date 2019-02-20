package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;

//���Ÿ�����
public class PlayMusicJTable extends JTable {
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane pane;

	public PlayMusicJTable() {
		String[] colname = { "����ID","����", "����", "��������", "�������ʱ��" };
		model = new DefaultTableModel();
		model.setColumnCount(5);// ��������Ϊ5
		model.setColumnIdentifiers(colname);// ���ñ�ͷ
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(300, 300));
		pane = new JScrollPane();
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		pane.setViewportView(table);
		pane.setBounds(140, 32, 410, 250);

		TableColumnModel columnModel = table.getColumnModel();
		TableColumn column = columnModel.getColumn(1);
		column.setMaxWidth(0);
		column.setMinWidth(0);
		column.setWidth(0);
		column.setPreferredWidth(0);
	}




	@Override
	public DefaultTableModel getModel() {
		return model;
	}

	public void setModel(DefaultTableModel model) {
		this.model = model;
	}

	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JScrollPane getPane() {
		return pane;
	}

	public void setPane(JScrollPane pane) {
		this.pane = pane;
	}
}
