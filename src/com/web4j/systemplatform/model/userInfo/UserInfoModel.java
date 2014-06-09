package com.web4j.systemplatform.model.userInfo;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.HtmlEmail;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web4j.exception.DoErrorException;
import com.web4j.systemplatform.dao.role.RoleDao;
import com.web4j.systemplatform.dao.userInfo.UserInfoDao;
import com.web4j.systemplatform.entity.TbRole;
import com.web4j.systemplatform.entity.TbUserInfo;
import com.web4j.systemplatform.model.logs.LogsModel;
import com.web4j.systemplatform.pojo.logs.LogsPojo;
import com.web4j.systemplatform.pojo.userInfo.UserInfoPojo;
import com.web4j.util.LoginUserUtils;
import com.web4j.util.MD5Util;
import com.web4j.util.SysConfig;

@Service("userInfoModel")
public class UserInfoModel {

	@Autowired
	private UserInfoDao userInfoDao;
	@Autowired
	private LogsModel logsModel;
	@Autowired
	private RoleDao roleDao;

	public void login(UserInfoPojo userInfoPojo) throws Exception {
		TbUserInfo tbUserInfo = new TbUserInfo();
		tbUserInfo.setUserName(userInfoPojo.getUserName());
		tbUserInfo.setPassword(MD5Util.MD5(userInfoPojo.getPassword()));
		tbUserInfo = userInfoDao.findTbUserInfo(tbUserInfo);
		if (tbUserInfo != null) {
			if (SysConfig.USER_STATE_OK.equals(tbUserInfo.getState())) {
				// 存在就保存在SESSION中
				BeanUtils.copyProperties(userInfoPojo, tbUserInfo);
				//增加角色
				userInfoPojo.setRoleId(tbUserInfo.getTbRole().getId());
				userInfoPojo.setRoleName(tbUserInfo.getTbRole().getRoleName());
				userInfoPojo.setRoleType(Integer.parseInt(tbUserInfo.getTbRole().getRoleType()));
				addLoginLogs(userInfoPojo.getUserName());
				ServletActionContext.getContext().getSession().put(
						SysConfig.LOGIN_USER, userInfoPojo);
			} else {
				throw new DoErrorException("此用户不可用，请通之管理员解决！");
			}
		} else {
			throw new DoErrorException("用户名或密码错误！");
		}
	}

	/**
	 * 保存登录信息
	 * @author : hepx 
	 * @date : 2011-3-17下午05:45:44
	 * @description : TODO
	 * @param user
	 * @throws Exception : void
	 */
	private void addLoginLogs(String user) throws Exception {
		String ip = ServletActionContext.getRequest().getRemoteAddr();
		LogsPojo logsPojo = new LogsPojo(null, user, ip, new Date(),
				SysConfig.LOGS_TYPE_USERLOGIN);
		logsModel.saveLogsPojo(logsPojo);
	}

	/**
	 * 
	 * @author : hepx
	 * @date : 2011-3-16上午11:50:30
	 * @description : 用户登录后。正常修改密码操作
	 * @param oldPwd
	 * @param newPwd
	 * @throws Exception
	 *             : void
	 */
	public void modifyPwd(String oldPwd, String newPwd) throws Exception {
		UserInfoPojo userInfoPojo = LoginUserUtils.getLoginUserInfo();
		if (userInfoPojo != null) {
			if (MD5Util.MD5(oldPwd).equals(userInfoPojo.getPassword())) {
				userInfoPojo.setPassword(MD5Util.MD5(newPwd));
				userInfoPojo.setUpdateTime(new Date());
				TbUserInfo tbUserInfo = new TbUserInfo();
				BeanUtils.copyProperties(tbUserInfo, userInfoPojo);
				tbUserInfo.setTbRole(new TbRole(userInfoPojo.getRoleId()));
				userInfoDao.saveOrUpdateUserInfo(tbUserInfo);
			} else {
				throw new DoErrorException("输入的旧密码不正确！");
			}
		}
	}

	/**
	 * 
	 * @author : hepx
	 * @date : 2011-3-16上午11:49:49
	 * @description : 修改密码。。。找回密码。调用。
	 * @param userId
	 * @param pwd
	 *            : void
	 */
	public void modifyPwd(String userId, String email,String pwd) throws Exception {
		if(SysConfig.vmap==null){
			throw new DoErrorException("系统发生未知异常，导至数据丢失！");
		}
		if(SysConfig.vmap.containsKey(userId)&&SysConfig.vmap.containsKey(email)){
			Long id=Long.parseLong(SysConfig.vmap.get(userId));
			pwd = MD5Util.MD5(pwd);
			userInfoDao.modifyPwd(id, pwd);
		}else{
			throw new DoErrorException("数据不正确，请重新试着找回！");
		}
	}

	/**
	 * 所有用户信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<UserInfoPojo> queryUserInfoList() throws Exception {
		List<TbUserInfo> list = userInfoDao.queryUserInfoList();
		List<UserInfoPojo> userInfoList = new ArrayList<UserInfoPojo>();
		for (TbUserInfo t : list) {
			UserInfoPojo userInfoPojo = new UserInfoPojo(t.getId(), t
					.getUserName(), t.getCreateTime(), t.getUpdateTime(), t
					.getState(), t.getTbRole() == null ? null : t.getTbRole()
					.getRoleName(), t.getTbRole() == null ? null : t
					.getTbRole().getId(), t.getPhone(), t.getEmail());
			userInfoList.add(userInfoPojo);
		}
		return userInfoList;
	}

	/**
	 * 根据ID删除用户
	 * 
	 * @param userInfoPojo
	 * @throws Exception
	 */
	public void deleteUserInfo(UserInfoPojo userInfoPojo) throws Exception {
		if (userInfoPojo.getId() != null || !"".equals(userInfoPojo.getId())) {
			userInfoDao.deleteUserInfo(userInfoPojo.getId());
		}
	}

	/**
	 * 保存或更新用户
	 * 
	 * @param userInfoPojo
	 * @throws Exception
	 */
	public Long saveOrUpdateUserInfo(UserInfoPojo userInfoPojo)
			throws Exception {
		TbUserInfo tbUserInfo = new TbUserInfo();
		if (userInfoPojo.getId() == null || "".equals(userInfoPojo.getId())) {
			tbUserInfo.setCreateTime(new Date());
			if (!userInfoDao.isExist(userInfoPojo.getUserName())) {
				tbUserInfo.setUserName(userInfoPojo.getUserName());
			} else {
				throw new DoErrorException("用户名已存在。请重新输入！");
			}
		} else {
			tbUserInfo.setCreateTime(userInfoPojo.getCreateTime());
			tbUserInfo.setUpdateTime(new Date());
			tbUserInfo.setUserName(userInfoPojo.getUserName());
		}
		tbUserInfo.setId(userInfoPojo.getId());
		tbUserInfo.setPhone(userInfoPojo.getPhone());
		tbUserInfo.setEmail(userInfoPojo.getEmail());
		// 用户状态
		if (userInfoPojo.getState() != null) {
			tbUserInfo.setState(userInfoPojo.getState());
		} else {
			tbUserInfo.setState(SysConfig.USER_STATE_OK);
		}
		// 密码
		if (userInfoPojo.getPassword() != null) {
			tbUserInfo.setPassword(MD5Util.MD5(userInfoPojo.getPassword()));
		} else {
			tbUserInfo.setPassword(LoginUserUtils.getLoginUserInfo().getPassword());
		}
		// 如果是在平台外注册用户。那么默认为普通用户的角色。。如果是在平台内注册，则根据选择的角色分配置。
		if (userInfoPojo.getRoleId() != null) {
			tbUserInfo.setTbRole(new TbRole(userInfoPojo.getRoleId()));
		} else {
			tbUserInfo
					.setTbRole(roleDao.queryTbRole(SysConfig.ROLE_TYPE_3));
		}

		userInfoDao.saveOrUpdateUserInfo(tbUserInfo);
		return tbUserInfo.getId();
	}
	
	public Long saveUserInfo(UserInfoPojo userInfoPojo) throws Exception{
		if (userInfoDao.isExist(userInfoPojo.getUserName())) {
			throw new DoErrorException("用户名已存在。请重新输入！");
		}
		TbUserInfo tbUserInfo = new TbUserInfo();
		tbUserInfo.setUserName(userInfoPojo.getUserName());
		tbUserInfo.setPassword(MD5Util.MD5(userInfoPojo.getPassword()));
		tbUserInfo.setPhone(userInfoPojo.getPhone());
		tbUserInfo.setEmail(userInfoPojo.getEmail());
		tbUserInfo.setCreateTime(new Date());
		tbUserInfo.setTbRole(new TbRole(userInfoPojo.getRoleId()));
		tbUserInfo.setState(SysConfig.USER_STATE_OK);
		userInfoDao.save(tbUserInfo);
		return tbUserInfo.getId();
	}

	/**
	 * 
	 * @author : hepx
	 * @date : 2011-3-15下午12:50:15
	 * @description : 找回密码
	 * @param userInfoPojo
	 *            : void
	 */
	String md5_userId;
	String md5_email;

	public void findPwd(UserInfoPojo userInfoPojo) throws Exception {
		TbUserInfo tbUserInfo = new TbUserInfo();
		tbUserInfo.setUserName(userInfoPojo.getUserName());
		tbUserInfo.setEmail(userInfoPojo.getEmail());
		tbUserInfo = userInfoDao.findTbUserInfo(tbUserInfo);
		if (tbUserInfo != null) {
			buildVmap(tbUserInfo.getId().toString(), tbUserInfo.getEmail());
			String url = builUrl(md5_userId, md5_email);
			String content = "点击此超链接修改密码：<a href='" + url + "'>" + url + "</a>";
			sendEmail(tbUserInfo.getEmail(), tbUserInfo.getUserName(), content);
		} else {
			throw new DoErrorException("输入的用户名和邮箱地址不匹配！");
		}
	}

	/**
	 * 
	 * @author : hepx
	 * @date : 2011-3-17下午05:07:54
	 * @description : 把用户ID和email加密缓存在一个MAP里。
	 * @param userId
	 * @param email
	 *            : void
	 */
	public void buildVmap(String userId, String email) {
		if (SysConfig.vmap == null) {
			SysConfig.vmap = new HashMap<String, String>();
		}
		md5_userId = MD5Util.MD5(userId);
		md5_email = MD5Util.MD5(email);
		SysConfig.vmap.put(md5_userId, userId);
		SysConfig.vmap.put(md5_email, email);
	}

	/**
	 * 构建一个URL地址
	 * 
	 * @author : hepx
	 * @date : 2011-3-17下午04:19:13
	 * @description : TODO
	 * @param userId
	 * @param email
	 * @return
	 * @throws Exception
	 *             : String
	 */
	private String builUrl(String md5_uid, String md5_ein) throws Exception {
		HttpServletRequest req = ServletActionContext.getRequest();
		String protocol = "HTTP";
		int port = req.getLocalPort()==0?8088:req.getLocalPort();
		String host = req.getLocalAddr().equals("0.0.0.0")?"localhost":req.getLocalAddr();
		String info = "/proDs/main/modifyInitPwd.action?uid=" + md5_uid
				+ "&ein=" + md5_ein;
		URL u = new URL(protocol, host, port, info);
		return u.toString().trim();
	}

	/**
	 * 
	 * @author : hepx
	 * @date : 2011-3-17下午05:06:57
	 * @description : 发送邮件
	 * @param address
	 * @param name
	 * @param content
	 * @throws Exception
	 *             : void
	 */
	private static void sendEmail(String address, String name, String content)
			throws Exception {
		Email email = new HtmlEmail();
//		email.setDebug(true);
		// 设置邮箱主机地址
		email.setHostName("smtp.163.com");
		// 设置端口号
		email.setSmtpPort(25);
		// 设置主题
		email.setCharset("UTF-8");
		email.setSubject("找回密码");
		email.setFrom("hepanxi@163.com", "平台管理员");
		String userName = "hepanxi";
		String password = "19850208@";
		email.setAuthenticator(new DefaultAuthenticator(userName, password));
		email.setTLS(true);
		email.addTo(address, name);
		email.setMsg(content);
		email.setSentDate(new Date());
		email.send();
	}

//	public static void main(String[] orgs) {
//		String address = "kaikaixinxin1314@163.com";
//		String name = "kaixin";
//		String id = MD5Util.MD5("1");
//		String e = MD5Util.MD5("hepanxi");
//		try {
//			String u = new URL("HTTP", "localhost", 8088,
//					"/proDs/main/modifyInitPwd.action?u=" + id + "&e=" + e)
//					.toString().trim();
//			System.out.println(u);
////			String s = "点击此超链接修改密码：<a href='" + u + "'>" + u + "</a>";
////			UserInfoModel.sendEmail(address, name, s);
//		} catch (Exception z) {
//			z.printStackTrace();
//		}
//	}

}
