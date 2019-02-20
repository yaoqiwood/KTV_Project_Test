package dao;

import bean.ClassifyBean;
import bean.FileBean;
import bean.MusicBean;

import java.sql.*;
import java.util.ArrayList;

public class FileDaoImpl {

//	// �����ļ�
	public boolean importFile(FileBean fileBean) {
		PreparedStatement pstem = null;
		ResultSet set = null;
		int key = 0;
		// ����SQL���
		String sql = "insert into t_music (music_name,music_path,music_length) values (?,?,?)";
		try {
			pstem = DbUtil.getDbUtil().getConnection().prepareStatement(sql);
			pstem.setString(1, fileBean.getMusic_name());
			pstem.setString(2, fileBean.getMusic_path());
			pstem.setString(3, fileBean.getMusic_length());
			// ִ��SQL���
			key = pstem.executeUpdate();
			// ��ȡ���ؽ��
			if (key > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			if (e.getErrorCode() == 19) {
//				System.out.println("��·���Ѵ���");
			} else {
				e.printStackTrace();
			}
		} finally {
			// �ͷ���Դ
			DbUtil.getDbUtil().release(pstem, set);
		}

		return false;
	}

	public ArrayList<MusicBean> GetTabelArraylist(int limit, int offset) {
		ArrayList<MusicBean> filesList = new ArrayList<MusicBean>();
		PreparedStatement pstem = null;
		ResultSet set = null;
		// ����Sql���
		String sql = "SELECT * from t_music limit ? offset ?";
		Connection connection = DbUtil.getDbUtil().getConnection();

		try {
			// ִ��SQL���
			pstem = connection.prepareStatement(sql);
			pstem.setInt(1, limit);
			pstem.setInt(2, offset);
			set = pstem.executeQuery();
			// ���ؽ����
			while (set.next()) {
//				FileBean fileBean = new FileBean();
//				filesList.add(fileBean);
				String music_id = set.getString("music_id");
				String music_name = set.getString("music_name");
				String music_time = set.getString("music_time");
				String music_path = set.getString("music_path");
				String music_length = set.getString("music_length");
				String spell = set.getString("spell");
				String singer_id = set.getString("singer_id");
				String type_id = set.getString("type_id");
				String update_time = set.getString("update_time");

				MusicBean musicBean = new MusicBean(music_id,music_name,music_time,music_path,music_length,spell,singer_id,type_id,update_time);
				filesList.add(musicBean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// �ͷ���Դ
			DbUtil.getDbUtil().release(pstem, set);
		}
		return filesList;
	}

	public ArrayList<ClassifyBean> SelectClassifyList(){
		ArrayList<ClassifyBean> classifyBeans = new ArrayList<>();
		PreparedStatement pstm = null;
		ResultSet set = null;
		// ����Sql���
		String sql = "SELECT * from t_type";
		Connection connection = DbUtil.getDbUtil().getConnection();
		try {
			pstm = connection.prepareStatement(sql);
			set =  pstm.executeQuery();
			while (set.next()){
				String type_id = set.getString("type_id");
				String type_name = set.getString("type_name");
				classifyBeans.add(new ClassifyBean(type_id,type_name));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return classifyBeans;
	}

	public String SelectIDFromType(String ID) {     //����ID��������Ϣ
		String type_name = null;
		PreparedStatement pstm = null;
		ResultSet set = null;
		String sql = "SELECT * from t_type where type_id = ?";
		try {
			pstm = DbUtil.getDbUtil().getConnection().prepareStatement(sql);
			pstm.setString(1, ID);
			set = pstm.executeQuery();
			while (set.next()) {
				type_name = set.getString("type_name");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return type_name;
	}

	public String SelectNameFromType(String name){      //���������ҷ�����Ϣ
		String type_id = null;
		PreparedStatement pstm = null;
		ResultSet set = null;
		String sql = "SELECT * from t_type where type_name = ?";
		try {
			pstm = DbUtil.getDbUtil().getConnection().prepareStatement(sql);
			pstm.setString(1, name);
			set = pstm.executeQuery();
			while (set.next()) {
				type_id = set.getString("type_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return type_id;
	}

	public int UpdateEditor(String spell,String type_id,String music_id){       //���±༭��Ϣ
		PreparedStatement pstm = null;
		int res = -1;
		String sql = "update t_music set spell = ?, type_id = ?,update_time = datetime('now') where music_id = ?";
		try {
			pstm = DbUtil.getDbUtil().getConnection().prepareStatement(sql);
			pstm.setString(1,spell);
			pstm.setString(2,type_id);
			pstm.setString(3,music_id);
			res = pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

	public int SelectCount_page(){      //ͳ��ҳ��
		int count = -1;
		String sql = "select count(music_id) as Count from t_music";
		PreparedStatement pstm = null;
		ResultSet set = null;
		try {
			pstm = DbUtil.getDbUtil().getConnection().prepareStatement(sql);
			set = pstm.executeQuery();
			while (set.next()){
				count = set.getInt("Count");
			}
			return count;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
}
