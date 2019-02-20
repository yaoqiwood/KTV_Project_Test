package dao;

import bean.MusicBean;
import bean.TypeBean;

import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


//大刀  数据数据sql查询 更改 增加
public class daoServiceImpl {
	public boolean UpdataTableClient(MusicBean musicBean) {
		String sql = "insert into t_music values (?,?,?,?,?,?);";
		PreparedStatement pstm = null;
		try {
			pstm = DbUtil.getDbUtil().getConnection().prepareStatement(sql);
			pstm.setString(1, musicBean.getMusic_id());
			pstm.setString(2, musicBean.getMusic_name());
			pstm.setString(3, musicBean.getSpell());
			pstm.setString(4, musicBean.getSinger());
			pstm.setString(5, musicBean.getType_id());
			pstm.setString(6, musicBean.getUpdate_time());
			int ret = pstm.executeUpdate();
			if (ret > 0) {
				return true;
			}
		} catch (SQLException e) {
			if (e.getErrorCode() != 19) {
				e.printStackTrace();
			} else {
//				System.out.println(musicBean.getMusic_id());
				System.out.println("插入失败，ID已存在");
				if (UpdateNewMusic(musicBean)) {
					System.out.println("但是修改成功了~");
				}
			}

//			System.out.println(e.getErrorCode());
		} finally {
			DbUtil.getDbUtil().release(pstm, null);
		}
		return false;
	}

	public boolean UpdateNewMusic(MusicBean musicBean) {
		String sql = "update t_music set music_name = ?,spell = ?,singer_id = ?,type_id = ?,update_time = ? where music_id = ?";
		PreparedStatement pstm = null;
		try {
			pstm = DbUtil.getDbUtil().getConnection().prepareStatement(sql);
			pstm.setString(1, musicBean.getMusic_name());
			pstm.setString(2, musicBean.getSpell());
			pstm.setString(3, musicBean.getSinger());
			pstm.setString(4, musicBean.getType_id());
			pstm.setString(5, musicBean.getUpdate_time());
			pstm.setString(6, musicBean.getMusic_id());
			int ret = pstm.executeUpdate();
			if (ret > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String SelectMaxTimeMusic() {
		String sql = "select max(update_time) from t_music";
		PreparedStatement pstm = null;
		ResultSet set = null;
		try {
			pstm = DbUtil.getDbUtil().getConnection().prepareStatement(sql);
			set = pstm.executeQuery();
			while (set.next()) {
				String time = set.getString(1);
				return time;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.getDbUtil().release(pstm, set);
		}
		return null;
	}

	public ArrayList<MusicBean> SelectFromTMus(int CurrentPage, int Offset) {
		ArrayList<MusicBean> musicBeanArrayList = new ArrayList<>();
		String sql = "select * from t_music limit ?  offset ?;";
		PreparedStatement pstm = null;
		ResultSet set = null;
		try {
			pstm = DbUtil.getDbUtil().getConnection().prepareStatement(sql);
			pstm.setString(1, String.valueOf(Offset));
			pstm.setString(2, String.valueOf(CurrentPage * Offset));
			set = pstm.executeQuery();
			while (set.next()) {
				MusicBeanFill(musicBeanArrayList, set);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.getDbUtil().release(pstm, set);
		}
		return musicBeanArrayList;
	}

	public ArrayList<MusicBean> SelectFromTMusSpell(String TempChar, int CurrentPage, int Offset) {
		ArrayList<MusicBean> musicBeanArrayList = new ArrayList<>();
		String sql = "select * from t_music where spell like ? limit ?  offset ?;";
		PreparedStatement pstm = null;
		ResultSet set = null;
		try {
			pstm = DbUtil.getDbUtil().getConnection().prepareStatement(sql);
			pstm.setString(1, '%' + TempChar + '%');
			pstm.setString(2, String.valueOf(Offset));
			pstm.setString(3, String.valueOf(CurrentPage * Offset));
			set = pstm.executeQuery();
			while (set.next()) {
				MusicBeanFill(musicBeanArrayList, set);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.getDbUtil().release(pstm, set);
		}
		return musicBeanArrayList;
	}

	private void MusicBeanFill(ArrayList<MusicBean> musicBeanArrayList, ResultSet set) throws SQLException {
		String music_id = set.getString("music_id");
		String music_name = set.getString("music_name");
		String spell = set.getString("spell");
		String singer_id = set.getString("singer_id");
		String type_id = set.getString("type_id");
		String update_time = set.getString("update_time");
		musicBeanArrayList.add(new MusicBean(music_id, music_name, null, null, null, spell, singer_id, type_id, update_time));
	}

	public ArrayList<TypeBean> TypeComboxfill() {
		ArrayList<TypeBean> typeBeanArrayList = new ArrayList<>();
		String sql = "select * from t_type";
		PreparedStatement pstm = null;
		ResultSet set = null;
		try {
			pstm = DbUtil.getDbUtil().getConnection().prepareStatement(sql);
			set = pstm.executeQuery();
			while (set.next()) {
				String type_id = set.getString("type_id");
				String type_name = set.getString("type_name");
				typeBeanArrayList.add(new TypeBean(type_id, type_name));
			}
			return typeBeanArrayList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.getDbUtil().release(pstm, set);
		}
		return null;
	}

	public ArrayList<MusicBean> TypeSelectID(String type_id) {
		ArrayList<MusicBean> musicBeanArrayList = new ArrayList<>();
		String sql = "select * from t_music where type_id = ?";
		PreparedStatement pstm = null;
		ResultSet set = null;
		try {
			pstm = DbUtil.getDbUtil().getConnection().prepareStatement(sql);
			pstm.setString(1, type_id);
			set = pstm.executeQuery();
			while (set.next()) {
				MusicBeanFill(musicBeanArrayList, set);
			}
			return musicBeanArrayList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.getDbUtil().release(pstm, set);
		}
		return null;
	}

	public int SelectCount() {
		String sql = "Select count(music_id) as Count from t_music";
		PreparedStatement pstm = null;
		ResultSet set = null;
		int Count = 0;
		try {
			pstm = DbUtil.getDbUtil().getConnection().prepareStatement(sql);
			set = pstm.executeQuery();
			while (set.next()) {
				Count = set.getInt("Count");
			}
			return Count;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.getDbUtil().release(pstm, set);
		}
		return 0;
	}

	public int PageSpellCount(String TempChar) {
		String sql = "select count(music_id) as Count from t_music where spell like ?";
		PreparedStatement pstm = null;
		ResultSet set = null;
		int Count = 0;
		try {
			pstm = DbUtil.getDbUtil().getConnection().prepareStatement(sql);
			pstm.setString(1, '%' + TempChar + '%');
			set = pstm.executeQuery();
			while (set.next()) {
				Count = set.getInt("Count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DbUtil.getDbUtil().release(pstm, set);
		}
		return Count;
	}


}
