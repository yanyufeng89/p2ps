package com.jobplus.service.impl;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.jobplus.dao.PersonalSkillMapper;
import com.jobplus.pojo.PersonalSkill;
import com.jobplus.pojo.User;
import com.jobplus.service.IPersonalSkillService;
import com.jobplus.service.ISequenceService;
import com.jobplus.service.ISkillLibraryService;
import com.jobplus.service.IUserService;

@Service("personalSkillService")
public class PersonalSkillServiceImpl implements IPersonalSkillService {

	@Resource
	private PersonalSkillMapper personalSkilldao;
	@Resource
	private ISequenceService seqService;
	@Resource
	private IUserService userService;
	@Resource
	private ISkillLibraryService skillLibraryService;

	@Override
	public int deleteByPrimaryKey(Integer id) {

		return personalSkilldao.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(PersonalSkill record) {
		int ret = 0;
		int id = seqService.getSeqByTableName("tbl_personalskill");
		record.setId(id);
		ret= personalSkilldao.insert(record);
		return ret;
	}

	@Override
	public int insertSelective(PersonalSkill record) {

		return personalSkilldao.insertSelective(record);
	}

	@Override
	public PersonalSkill selectByPrimaryKey(Integer id) {

		return personalSkilldao.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(PersonalSkill record) {

		return personalSkilldao.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKeyWithBLOBs(PersonalSkill record) {

		return personalSkilldao.updateByPrimaryKeyWithBLOBs(record);
	}

	@Override
	public int updateByPrimaryKey(PersonalSkill record) {

		return personalSkilldao.updateByPrimaryKey(record);
	}

	@Override
	public PersonalSkill selectByRecord(PersonalSkill record) {
		return personalSkilldao.selectByRecord(record);
	}
	/**
	 * 新增或者修改个人技能
	 */
	@Transactional
	@Override
	public int insertOrUpdSkill(PersonalSkill record,String skillIds) {
		int ret = 0;
		try {
			PersonalSkill tmp = this.selectByPrimaryKey(record.getId());
			if(tmp == null){
				ret = this.insert(record);
			}else{
				if(StringUtils.isBlank(record.getSkillitem())){
					//技能项为空  相当于删除记录
					ret = this.deleteByPrimaryKey(record.getId());
				}else{
					//更改记录
					ret = this.updateByPrimaryKeySelective(record);
				}
			}	
		} catch (DuplicateKeyException e) {
//			e.printStackTrace();
			//更改记录
			ret = this.updateByPrimaryKeySelective(record);
		}
			
		// 更新用户 updatetime
		User user = new User();
		user.setUserid(record.getUserid());
		user.setUpdatetime(new java.sql.Timestamp(System.currentTimeMillis()));
		userService.updateByPrimaryKeySelective(user);
		//增加技能使用的次数
		if(!StringUtils.isBlank(skillIds))
			skillLibraryService.updSkillUsesum(skillIds);
		
		return ret;
	}

}
