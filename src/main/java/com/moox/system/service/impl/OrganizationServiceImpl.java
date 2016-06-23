package com.moox.system.service.impl;

import com.moox.system.dao.OrganizationDao;
import com.moox.system.entity.Organization;
import com.moox.system.entity.User;
import com.moox.system.service.OrganizationService;
import com.moox.system.service.UserService;
import com.moox.system.util.JsonUtil;
import com.moox.system.util.tree.SelectTreeUtil;
import com.moox.system.util.tree.SimpleJsonTree;
import com.moox.system.util.tree.TreeObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 接口实现
 */

@Service
public class OrganizationServiceImpl implements OrganizationService {


    @Resource
    private OrganizationDao organizationDao;
    @Resource
    private UserService userService;

    @Override
    public List GetParentsIdQueryChildren(Long parentsId) {
        // TODO Auto-generated method stub
        return organizationDao.GetParentsIdQueryChildren(parentsId);
    }

    @Override
    public List<Organization> GetParentsIdQueryChildrenParentsExsit(Long parentsId) {
        List list = organizationDao.GetParentsIdQueryChildrenParentsExsit(parentsId);
        List<Organization> organList = new ArrayList<>();
        for (Organization organ : organList) {
            Map<String, Object> map = (Map<String, Object>) organ;
            Organization org = new Organization();
            org.setId(Long.parseLong(map.get("id").toString()));
            org.setName(map.get("name").toString());
            org.setPid(Long.parseLong(map.get("pid").toString()));
            organList.add(org);
        }
        return organList;
    }

    @Override
    public int GetChildrenQueryParentsId(Long childId) {
        // TODO Auto-generated method stub
        return organizationDao.GetChildrenQueryParentsId(childId);
    }

    @Override
    public boolean add(Organization organization) {
        boolean result=false;
        try{
            organizationDao.add(organization);
            result=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;

    }

    @Override
    public String findTreeList() {
        List<Organization> list=organizationDao.findTreeList();
        List<SimpleJsonTree> sjt = new ArrayList<SimpleJsonTree>();
        String json ="";
        if (null!=list&&list.size()>0) {
            for(Organization orga:list) {
                SimpleJsonTree s = null;
                if (0 == orga.getPid()) {
                    s = new SimpleJsonTree(orga.getId().intValue(), orga.getPid().intValue(), orga.getName(), false);
                } else {
                    s = new SimpleJsonTree(orga.getId().intValue(), orga.getPid().intValue(), orga.getName(), false);
                }
                sjt.add(s);
            }
            json = JsonUtil.toJSONString(sjt);
        }
        return json;
    }

    @Override
    public boolean modify(Organization organization) {
        boolean result=false;
        try{
            organizationDao.update(organization);
            result=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;

    }

    @Override
    public Organization findById(Long id) {
        // TODO Auto-generated method stub
        return organizationDao.findById(id);
    }

    @Override
    public boolean delete(Organization organization) {
        boolean result=false;
        try{
            organizationDao.delete(organization);
            result=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<TreeObject> findToSelectTree() {
        List<Organization> organizations = organizationDao.findTreeList();
        List<TreeObject> list = new ArrayList<TreeObject>();
        for (Organization org : organizations) {
            TreeObject ts = new TreeObject();
            ts.setId(org.getId().intValue());
            ts.setName(org.getName());
            ts.setParentId(org.getPid().intValue());
            list.add(ts);
        }
        SelectTreeUtil selectTreeUtil = new SelectTreeUtil();
        List<TreeObject> orgTree = selectTreeUtil.listToTree(list, 0);
        return orgTree;
    }

    @Override
    public List<Organization> findByIds(String ids) {
        String hql="from Organization o where o.id in ("+ids+")";
        return organizationDao.findList(hql);
    }

    @Override
    public int GetChildrenQueryBusinessDepartment(Long childId) {
        return organizationDao.GetChildrenQueryBusinessDepartment(childId);
    }

    @Override
    public List<Organization> findDivision() {
        String sql = "SELECT * FROM s_organization o WHERE o.pid IN(SELECT id FROM s_organization WHERE pid=0)";
        List list = organizationDao.findBySql(sql);
        List<Organization> organList = new ArrayList<>();
        for (Object object : list) {
            Map<String, Object> map = (Map<String, Object>) object;
            Organization org = new Organization();
            org.setId(Long.parseLong(map.get("id").toString()));
            org.setName(map.get("name").toString());
            org.setPid(Long.parseLong(map.get("pid").toString()));
            organList.add(org);
        }
        return organList;
    }

    @Override
    public List<Organization> findByOrganId(Long id) {
        String sql="SELECT o.id,o.`name`,o.pid FROM s_organization o WHERE FIND_IN_SET(o.id,queryChildOrgan("+id+"))";
        List list = organizationDao.findBySql(sql);
        List<Organization> organList = new ArrayList<>();
        for (Object object : list) {
            Map<String, Object> map = (Map<String, Object>) object;
            Organization org = new Organization();
            org.setId(Long.parseLong(map.get("id").toString()));
            org.setName(map.get("name").toString());
            org.setPid(Long.parseLong(map.get("pid").toString()));
            organList.add(org);
        }
        return organList;
    }

	@Override
	public String findOrgUserTree() {
		 List<Organization> list=organizationDao.findTreeList();
	        List<SimpleJsonTree> sjt = new ArrayList<SimpleJsonTree>();
	        List<User> userList = null;
	        String json ="";
	        if (null!=list&&list.size()>0) {
	            for(Organization orga:list) {
	                SimpleJsonTree s = null;
	                if (0 == orga.getPid()) {
	                    s = new SimpleJsonTree(orga.getId().intValue(), orga.getPid().intValue(), orga.getName(), true,"../css/zTreeStyle/img/branch.png",0);
	                } else {
	                    s = new SimpleJsonTree(orga.getId().intValue(), orga.getPid().intValue(), orga.getName(), false,"../css/zTreeStyle/img/zbranch.png",0);
	                }
	                sjt.add(s);
	                userList = userService.getOrganId(orga.getId());
	                if(userList != null && userList.size() > 0){
                        System.out.println(userList.size());
                        for (User user : userList) {
                            System.out.println(user.getId()+"/"+user.getName()+"/"+user.getSex());
	                		SimpleJsonTree s1 = null;
	                		if("女".equals(user.getSex())){
	                			s1 = new SimpleJsonTree(user.getId().intValue(), orga.getPid().intValue(), user.getName(), false,"../css/zTreeStyle/img/gril.png",1);
	                		}else{
	                			s1 = new SimpleJsonTree(user.getId().intValue(), orga.getPid().intValue(), user.getName(), false,"../css/zTreeStyle/img/boy.png",1);
	                		}
	                		 
	                		 sjt.add(s1);
						}
	                }
	                
	            }
	            json = JsonUtil.toJSONString(sjt);
	        }
	        return json;
	}

}
