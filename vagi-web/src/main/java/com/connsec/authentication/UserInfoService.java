/**
 * 
 */
package com.connsec.authentication;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


/**
 * @author Administrator
 *
 */
public class UserInfoService {
	private static final Logger _logger = LoggerFactory.getLogger(UserInfoService.class);
	private final JdbcTemplate jdbcTemplate;
	

	private static final String DEFAULT_SELECT_STATEMENT = "SELECT *  FROM USERINFO WHERE USERNAME = ?";
	
	
	public UserInfoService(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate=jdbcTemplate;	
	}
	
	public UserInfo loadUserInfo(String username){
		List<UserInfo> listUserInfo=jdbcTemplate.query(
				DEFAULT_SELECT_STATEMENT,
				new UserInfoRowMapper(),
				username);
		_logger.debug("query UserInfo List "+listUserInfo);
		if(listUserInfo!=null&&listUserInfo.size()>0){
			return listUserInfo.get(0);
		}
		return null;
	}
	
	
	private final class UserInfoRowMapper  implements RowMapper<UserInfo> {
		@Override
		public UserInfo mapRow(ResultSet rs, int rowNum)
				throws SQLException {
			UserInfo u=new UserInfo();
			u.setId(rs.getString("ID"));
			u.setUsername(rs.getString("USERNAME"));
			u.setPassword(rs.getString("PASSWORD"));
			u.setDisplayName(rs.getString("DISPLAYNAME"));
			u.setBadPasswordCount(rs.getInt("BADPASSWORDCOUNT"));
			u.setBadPasswordTime(rs.getString("BADPASSWORDTIME"));
			u.setPasswordLastSetTime(rs.getString("PASSWORDLASTSETTIME"));
			u.setUnLockTime(rs.getString("UNLOCKTIME"));
			u.setIsLocked(rs.getInt("ISLOCKED"));
			u.setLastLoginTime(rs.getString("LASTLOGINTIME"));
			u.setLastLogoffTime(rs.getString("LASTLOGOFFTIME"));
			u.setLoginCount(rs.getInt("LOGINCOUNT"));
			u.setLastLoginIp(rs.getString("LASTLOGINIP"));
			u.setStatus(rs.getInt("STATUS"));
			return u;
		}
	}

}
