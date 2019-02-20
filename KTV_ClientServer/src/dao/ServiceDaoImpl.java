package dao;

import bean.MusicBean;
import bean.Roomer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


//大刀 各种数据库sql操作
public class ServiceDaoImpl {
	public int LoginAccount(String RoomID, String Password) {
		String sql = "Select * from t_room where room_id = ? and room_psw = ?";
		PreparedStatement pstmt = null;
		ResultSet set = null;
		Roomer roomer = null;
		try {
			// 3.预编译sql对象 --- 目的是为了执行Sql语句，生成执行对象
			pstmt = DbUtil.getDbUtil().getConnection().prepareStatement(sql);
			pstmt.setString(1, RoomID);
			pstmt.setString(2, Password);
			// 执行sql语句
			set = pstmt.executeQuery();
			if (set.next()) {
				int State = set.getInt("login_state");
				return State;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			// 释放资源
			DbUtil.getDbUtil().release(pstmt, null);
		}
		return 3;   //登录失败
	}

	public boolean UpdateRoomState(String Login_state, String Room_id) {
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String sql = "update t_room set login_state = ? where room_id = ?";
		try {
			pstmt = DbUtil.getDbUtil().getConnection().prepareStatement(sql);
			pstmt.setString(1, Login_state);
			pstmt.setString(2, Room_id);
			int res = pstmt.executeUpdate();
			if (res > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			DbUtil.getDbUtil().release(pstmt, set);
		}
		return false;
	}

	public ArrayList<MusicBean> SelectMusicArraylist() {
		ArrayList<MusicBean> musicBeanArrayList = new ArrayList<>();
		PreparedStatement pstm = null;
		ResultSet set = null;
		String sql = "SELECT * from t_music";
		try {
			pstm = DbUtil.getDbUtil().getConnection().prepareStatement(sql);
			set = pstm.executeQuery();
			while (set.next()) {
				MusicBeanFill(musicBeanArrayList, set);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			DbUtil.getDbUtil().release(pstm, set);
		}
		return musicBeanArrayList;
	}

	public ArrayList<MusicBean> SelectSecondUpdataTime(String time) {
		ArrayList<MusicBean> musicBeanArrayList = new ArrayList<>();
		String sql = "select * from t_music where  update_time > ?";
		PreparedStatement pstm = null;
		ResultSet set = null;
		try {
			pstm = DbUtil.getDbUtil().getConnection().prepareStatement(sql);
			pstm.setString(1, time);
			set = pstm.executeQuery();
			while (set.next()) {
				MusicBeanFill(musicBeanArrayList, set);
			}
//			for (MusicBean i :musicBeanArrayList){
//				System.out.println(i.getUpdate_time());
//			}
			return musicBeanArrayList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			DbUtil.getDbUtil().release(pstm, set);
		}
		return null;
	}


	private void MusicBeanFill(ArrayList<MusicBean> musicBeanArrayList, ResultSet set) throws SQLException {
		String music_id = set.getString("music_id");
		String music_name = set.getString("music_name");
		String music_time = set.getString("music_time");
		String music_path = set.getString("music_path");
		String music_length = set.getString("music_length");
		String spell = set.getString("spell");
		String singer_id = set.getString("singer_id");
		String type_id = set.getString("type_id");
		String update_time = set.getString("update_time");
		MusicBean musicBean = new MusicBean(music_id, music_name, music_time, music_path, music_length, spell, singer_id, type_id, update_time);
		musicBeanArrayList.add(musicBean);
	}

	public boolean InserIntoPlayList(String musicID, String RoomID) {
		String sql = "INSERT into t_list values (null ,?,?,?,?)";
		PreparedStatement pstm = null;
		try {
			pstm = DbUtil.getDbUtil().getConnection().prepareStatement(sql);
			pstm.setString(1, musicID);
			pstm.setString(2, RoomID);
			pstm.setString(3, "0");
			pstm.setString(4, "0");
			int ret = pstm.executeUpdate();
			if (ret > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			DbUtil.getDbUtil().release(pstm, null);
		}
		return false;
	}

	public ArrayList<MusicBean> SelectHotMusic(int CurrentPage, int Offset) {
		ArrayList<MusicBean> musicBeanArrayList = new ArrayList<>();
		String sql = "SELECT count(A.music_id) as Hot,A.music_id,music_name,music_time,music_length,spell,singer_id,type_id,update_time\n" +
				"from t_list A inner join t_music B where A.music_id = B.music_id \n" +
				"group by A.music_id order by count(A.music_id) desc limit ? offset ? ;";
		PreparedStatement pstm = null;
		ResultSet set = null;
		try {
			pstm = DbUtil.getDbUtil().getConnection().prepareStatement(sql);
			pstm.setInt(1,Offset);
			pstm.setInt(2,CurrentPage * Offset);
			set = pstm.executeQuery();
			while (set.next()) {
				String music_id = set.getString("music_id");
				String music_name = set.getString("music_name");
				String music_time = set.getString("music_time");
				String music_length = set.getString("music_length");
				String spell = set.getString("spell");
				String singer_id = set.getString("singer_id");
				String type_id = set.getString("type_id");
				String update_time = set.getString("update_time");
				String Hot = set.getString("Hot");
				MusicBean musicBean = new MusicBean(music_id, music_name, music_time, null, music_length, spell, singer_id, type_id, update_time);
				musicBean.setHot(Hot);
				musicBeanArrayList.add(musicBean);
			}
			return musicBeanArrayList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			DbUtil.getDbUtil().release(pstm, set);
		}
		return null;
	}

	public ArrayList<MusicBean> SelectHistoryMusic(int CurrentPage, int Offset, String Room_ID) {
		ArrayList<MusicBean> musicBeanArrayList = new ArrayList<>();
		String sql = "SELECT list_id,A.music_id,music_name,music_time,music_length,spell,singer_id,music_path,type_id,update_time from\n" +
				"t_list A inner join t_music B where A.music_id = B.music_id and play_state = 0 and room_id = ?\n" +
				"order by sort desc limit ? offset ?";
		PreparedStatement pstm = null;
		ResultSet set = null;
		try {
			pstm = DbUtil.getDbUtil().getConnection().prepareStatement(sql);
			pstm.setString(1, Room_ID);
			pstm.setInt(2, Offset);
			pstm.setInt(3, Offset * CurrentPage);
			set = pstm.executeQuery();
			int i = 0;
			while (set.next()) {
				MusicBeanFill(musicBeanArrayList, set);
				String list_id = set.getString("list_id");
				musicBeanArrayList.get(i).setList_id(list_id);
				i++;
			}
			return musicBeanArrayList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			DbUtil.getDbUtil().release(pstm, set);
		}
		return null;
	}

	public int HistoryPageCount(String room_id) {
		String sql = "SELECT count(list_id) as Count from t_list where play_state = 0 and room_id = ?";
		PreparedStatement pstm = null;
		ResultSet set = null;
		try {
			pstm = DbUtil.getDbUtil().getConnection().prepareStatement(sql);
			pstm.setString(1, room_id);
			set = pstm.executeQuery();
			while (set.next()) {
				return set.getInt("Count");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			DbUtil.getDbUtil().release(pstm, set);
		}
		return 0;
	}

	public boolean DeleteIDFromHistory(int deleteID) {
		String sql = "DELETE from t_list where list_id = ?";
		PreparedStatement pstm = null;
		ResultSet set = null;
		try {
			pstm = DbUtil.getDbUtil().getConnection().prepareStatement(sql);
			pstm.setInt(1, deleteID);
			int ret = pstm.executeUpdate();
			if (ret > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			DbUtil.getDbUtil().release(pstm, set);
		}
		return false;
	}

	public boolean TopMusicOperator(String room_id, String list_id) {
		String sql = "update t_list set sort = (select max(sort) as maxSort from t_list where room_id = ? order by sort desc )+1\n" +
				"where list_id = ?";
		PreparedStatement pstm = null;
		try {
			pstm = DbUtil.getDbUtil().getConnection().prepareStatement(sql);
			pstm.setString(1, room_id);
			pstm.setString(2, list_id);
			int ret = pstm.executeUpdate();
			return ret > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			DbUtil.getDbUtil().release(pstm, null);
		}
		return false;
	}

	public int CountListUnplay(String room_id) {
		String sql = "SELECT count(list_id) as count  from\n" +
				"t_list A inner join t_music B where A.music_id = B.music_id and play_state = 0 and room_id = ?";
		PreparedStatement pstm = null;
		ResultSet set = null;
		int count = -1;
		try {
			pstm = DbUtil.getDbUtil().getConnection().prepareStatement(sql);
			pstm.setString(1, room_id);
			set = pstm.executeQuery();
			while (set.next()) {
				count = set.getInt("count");
			}
			return count;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			DbUtil.getDbUtil().release(pstm, set);
		}
		return count;
	}

	public boolean UpdataNewPlayStateOnClose(String room_id) {
		String sql = "UPDATE t_list set play_state = 1 where room_id = ?";
		PreparedStatement pstm = null;
		try {
			pstm = DbUtil.getDbUtil().getConnection().prepareStatement(sql);
			pstm.setString(1, room_id);
			int ret = pstm.executeUpdate();
			if (ret > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

//	查询热门页数总数
	public int SelectHotCountNumPage(){
		String sql = "select count(count) as NumCount\n" +
				"from (SELECT count(music_id) as count from t_list group by music_id)";
		PreparedStatement pstm = null;
		ResultSet set = null;
		int PageCount = 0;
		try {
			pstm = DbUtil.getDbUtil().getConnection().prepareStatement(sql);
			set = pstm.executeQuery();
			while (set.next()){
				PageCount = set.getInt("NumCount");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return PageCount;
	}

}

