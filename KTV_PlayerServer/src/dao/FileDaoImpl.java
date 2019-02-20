package dao;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Set;

import bean.FileBean;
import bean.MusicFile;
import bean.RoomID;

//大刀  各种sql数据处理 增删改查
public class FileDaoImpl {


	public ArrayList<RoomID> SelectRoomID() {
		ArrayList<RoomID> roomIDArrayList = new ArrayList<>();
		String sql = "SELECT room_id from t_room where login_state = 1 and con_state = 0";
		PreparedStatement pstm = null;
		ResultSet set = null;
		try {
			pstm = DbUtil.getDbUtil().getConnection().prepareStatement(sql);
			set = pstm.executeQuery();
			while (set.next()) {
				RoomID roomID = new RoomID(set.getString("room_id"));
				roomIDArrayList.add(roomID);
			}
			return roomIDArrayList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			DbUtil.getDbUtil().release(pstm, set);
		}
		return null;
	}

	public ArrayList<MusicFile> SelectPlaylist(String room_id) {
		ArrayList<MusicFile> musicFileArrayList = new ArrayList<>();
		String sql = "SELECT min(list_id) as min ,A.music_id,room_id,play_state,sort,music_name,music_time,music_path,music_length\n" +
				"from t_list A inner join t_music B where A.music_id = B.music_id " +
				"and sort = (SELECT max(sort) from t_list where play_state = 0) and room_id = ? and play_state = 0";
		PreparedStatement pstm = null;
		ResultSet set = null;
		try {
			pstm = DbUtil.getDbUtil().getConnection().prepareStatement(sql);
			pstm.setString(1, room_id);
			set = pstm.executeQuery();
			while (set.next()) {
				String list_id = set.getString("min");
				String music_id = set.getString("music_id");
				String play_state = set.getString("play_state");
				String sort = set.getString("sort");
				String music_name = set.getString("music_name");
				String music_time = set.getString("music_time");
				String music_path = set.getString("music_path");
				String music_length = set.getString("music_length");
				MusicFile musicFile = new MusicFile(list_id, music_id, room_id, play_state, sort, music_name, music_time, music_path, music_length);
				musicFileArrayList.add(musicFile);
			}
			return musicFileArrayList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			DbUtil.getDbUtil().release(pstm, set);
		}
		return null;
	}

	public boolean PlayerUpdate(String list_id) {
		String sql = "UPDATE t_list set play_state = 1  where list_id = ?";
		PreparedStatement pstm = null;
		try {
			pstm = DbUtil.getDbUtil().getConnection().prepareStatement(sql);
			pstm.setString(1, list_id);
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

	public boolean BindRoomID(String room_id) {
		String sql = "UPDATE t_room set con_state = ? where room_id = ?";
		PreparedStatement pstm = null;
		try {
			pstm = DbUtil.getDbUtil().getConnection().prepareStatement(sql);
			pstm.setString(1, "1");
			pstm.setString(2, room_id);
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

	public boolean recoveryRoomIDState(String room_id) {
		String sql = "UPDATE t_room set con_state = 0 where room_id = ?";
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

	public boolean SelectRoomIDConnSt(String room_id) {
		String sql = "SELECT con_state from t_room where room_id = ?";
		PreparedStatement pstm = null;
		ResultSet set = null;
		try {
			pstm = DbUtil.getDbUtil().getConnection().prepareStatement(sql);
			pstm.setString(1, room_id);
			set = pstm.executeQuery();
			while (set.next()) {
				if (set.getInt("con_state") == 0) {
					return true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			DbUtil.getDbUtil().release(pstm, set);
		}
		return false;
	}

	public boolean UpdateLogServiceState(String roomID) {
		String sql = "UPDATE t_room set con_state = ? where room_id = ?";
		PreparedStatement pstm = null;
		try {
			pstm = DbUtil.getDbUtil().getConnection().prepareStatement(sql);
			pstm.setString(1, "0");
			pstm.setString(2, roomID);
			if (pstm.executeUpdate() > 0) {
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

	public boolean AbnormalPlayList(String list_id) {
		String sql = "UPDATE t_list set play_state = 0 where list_id = ?";
		PreparedStatement pstm = null;
		try {
			pstm = DbUtil.getDbUtil().getConnection().prepareStatement(sql);
			pstm.setString(1, list_id);
			int ret = pstm.executeUpdate();
			if (ret > 0) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public ArrayList<MusicFile> SelectDelectName(String room_id) {
		ArrayList<MusicFile> musicFileArrayList = new ArrayList<>();
		String sql = "SELECT list_id,A.music_id,room_id,play_state,sort,music_name,music_time,music_path,music_length\n" +
				"from t_list A inner join t_music B where A.music_id = B.music_id\n" +
				"and room_id = ? and play_state = 0 order by sort desc";
		PreparedStatement pstm = null;
		ResultSet set = null;
		try {
			pstm = DbUtil.getDbUtil().getConnection().prepareStatement(sql);
			pstm.setString(1, room_id);
			set = pstm.executeQuery();
			while (set.next()) {
				String list_id = set.getString("list_id");
				String music_id = set.getString("music_id");
				String play_state = set.getString("play_state");
				String sort = set.getString("sort");
				String music_name = set.getString("music_name");
				String music_time = set.getString("music_time");
				String music_path = set.getString("music_path");
				String music_length = set.getString("music_length");
				MusicFile musicFile = new MusicFile(list_id, music_id, room_id, play_state, sort, music_name, music_time, music_path, music_length);
				musicFileArrayList.add(musicFile);
			}
			return musicFileArrayList;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			DbUtil.getDbUtil().release(pstm, set);
		}
		return null;
	}


}
