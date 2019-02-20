package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PlayMusicManagerTable extends JTable {
	private DefaultTableModel model;
	private JTable table;
	private JScrollPane pane;

//	表格类单独设定
	public PlayMusicManagerTable() {
		String[] colname = {"歌曲ID", "歌名", "歌曲长度", "歌曲路径", "歌曲大小","歌曲拼音检索","歌手","歌曲类型" };
		model = new DefaultTableModel();
		model.setColumnCount(8);// 设置列数为8
		model.setColumnIdentifiers(colname);// 设置表头
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
