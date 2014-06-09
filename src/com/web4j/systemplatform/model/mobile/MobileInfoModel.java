package com.web4j.systemplatform.model.mobile;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web4j.systemplatform.dao.mobile.MobileInfoDao;
import com.web4j.systemplatform.entity.TbMobileInfo;
import com.web4j.systemplatform.pojo.mobile.MobileInfo;

@Service("mobileInfoModel")
public class MobileInfoModel {

	@Autowired
	private MobileInfoDao mobileInfoDao;

	/**
	 * 
	 * @author : hepx
	 * @date : 2011-4-8上午10:52:09
	 * @description : 查询所有手机用户信息
	 * @return
	 * @throws Exception
	 *             : List<MobileInfo>
	 */
	public List<MobileInfo> queryMobileInfos() throws Exception {
		List<TbMobileInfo> tbMobileInfos = mobileInfoDao
				.queryTbMobileInfos();
		List<MobileInfo> mobileInfos = null;
		if (tbMobileInfos != null && tbMobileInfos.size() > 0) {
			mobileInfos = new ArrayList<MobileInfo>();
			for (TbMobileInfo tbMobileInfo : tbMobileInfos) {
				MobileInfo mobileInfo = new MobileInfo();
				BeanUtils.copyProperties(tbMobileInfo, mobileInfo);
				mobileInfos.add(mobileInfo);
			}
		}
		return mobileInfos;
	}

	/**
	 * 
	 * @author : hepx
	 * @date : 2011-4-8上午10:56:48
	 * @description : 分页查询手机用户信息
	 * @param start
	 * @param limit
	 * @return
	 * @throws Exception
	 *             : List<MobileInfo>
	 */
	public List<MobileInfo> queryMobileInfos(MobileInfo mobileInfo_s,
			Integer start, Integer limit) throws Exception {
		TbMobileInfo tbMobileInfo_s = new TbMobileInfo();
		if (mobileInfo_s != null) {
			BeanUtils.copyProperties(mobileInfo_s, tbMobileInfo_s);
		}
		List<TbMobileInfo> tbMobileInfos = mobileInfoDao
				.queryTbMobileInfos(tbMobileInfo_s, start, limit);
		List<MobileInfo> mobileInfos = null;
		if (tbMobileInfos != null && tbMobileInfos.size() > 0) {
			mobileInfos = new ArrayList<MobileInfo>();
			for (TbMobileInfo tbMobileInfo : tbMobileInfos) {
				MobileInfo mobileInfo = new MobileInfo();
				BeanUtils.copyProperties(tbMobileInfo, mobileInfo);
				mobileInfos.add(mobileInfo);
			}
		}
		return mobileInfos;
	}

	/**
	 * 
	 * @author : hepx
	 * @date : 2011-4-11下午05:51:58
	 * @description : 查询手机总用户数
	 * @param mobileInfo
	 * @return
	 * @throws Exception
	 *             : Long
	 */
	public Long queryMobileInofoCount(MobileInfo mobileInfo) throws Exception {
		TbMobileInfo tbMobileInfo = new TbMobileInfo();
		if (mobileInfo != null) {
			BeanUtils.copyProperties(mobileInfo, tbMobileInfo);
		}
		return mobileInfoDao.queryTbMobileInfoCount(tbMobileInfo);
	}

	/**
	 * 
	 * @author : hepx
	 * @date : 2011-4-8上午10:59:57
	 * @description : 保存或更新手机用户信息(单个)
	 * @param mobileInfo
	 * @throws Exception
	 *             : void
	 */
	public void saveOrUpdateMobileInfo(MobileInfo mobileInfo) throws Exception {
		TbMobileInfo tbMobileInfo = new TbMobileInfo();
		BeanUtils.copyProperties(mobileInfo, tbMobileInfo);
		mobileInfoDao.saveOrUpdateMobileInfo(tbMobileInfo);
	}

	/**
	 * 
	 * @author : hepx
	 * @date : 2011-4-8上午11:25:53
	 * @description : 保存或更新手机用户信息(多个)
	 * @param mobileInfos
	 * @throws Exception
	 *             : void
	 */
	public void saveOrUpdateMobileInfos(List<MobileInfo> mobileInfos)
			throws Exception {
		List<TbMobileInfo> tbMobileInfos = null;
		if (mobileInfos != null && mobileInfos.size() > 0) {
			tbMobileInfos = new ArrayList<TbMobileInfo>();
			for (MobileInfo mobileInfo : mobileInfos) {
				TbMobileInfo tbMobileInfo = new TbMobileInfo();
				BeanUtils.copyProperties(mobileInfo, tbMobileInfo);
				tbMobileInfos.add(tbMobileInfo);
			}
		}
		mobileInfoDao.saveOrUpdateMobileInfos(tbMobileInfos);
	}

	/**
	 * 
	 * @author : hepx
	 * @date : 2011-4-8上午11:01:27
	 * @description : 删除手机用户信息
	 * @param mobileInfo
	 * @throws Exception
	 *             : void
	 */
	public void deleteMobileInfo(MobileInfo mobileInfo) throws Exception {
		TbMobileInfo tbMobileInfo = new TbMobileInfo();
		BeanUtils.copyProperties(mobileInfo,tbMobileInfo);
		mobileInfoDao.deleteMobileInfo(tbMobileInfo);
	}
}
