package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PlayMusicManagerTable extends JTable {
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane pane;

//	����൥���趨
	public PlayMusicManagerTable() {
		String[] colname = {"����ID", "����", "��������", "����·��", "������С","����ƴ������","����","��������" };
		model = new DefaultTableModel();
		model.setColumnCount(8);// ��������Ϊ8
		model.setColumnIdentifiers(colname);// ���ñ�ͷ
		table = new JTable(model);
		table.setPreferredScrollableViewportSize(new Dimension(700, 300));
		table.setRowHeight(310/6);
		pane = new JScrollPane();
		pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		pane.setViewportView(table);
		pane.setBounds(50, 100, 700, 310);
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
