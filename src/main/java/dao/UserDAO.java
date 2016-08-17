package dao;

import model.Member;

public interface UserDAO {

	/**
	 * 查詢使用者帳戶
	 * 
	 * @param account
	 * @param password
	 * @return
	 * @author Shan
	 */
	public Member getUserByAccount(String account, String password);

	/**
	 * 建立使用者帳戶
	 * 
	 * @param member
	 * @return
	 * @author Shan
	 */
	public int createOrUpdateUser(Member member);
	
	public Member getAllUser(String account);
	
	public int updatePassword(String account,String password,String email);
	
	public boolean checkUser(String account);
	
}