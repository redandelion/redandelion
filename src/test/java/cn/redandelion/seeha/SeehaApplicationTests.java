package cn.redandelion.seeha;

import cn.redandelion.seeha.core.sys.basic.dto.BaseDto;
import cn.redandelion.seeha.core.sys.basic.service.impl.ServiceRequest;
import cn.redandelion.seeha.core.sys.dto.Function;
import cn.redandelion.seeha.core.sys.dto.FunctionResource;
import cn.redandelion.seeha.core.sys.mapper.FunctionMapper;
import cn.redandelion.seeha.core.sys.mapper.FunctionResourceMapper;
import cn.redandelion.seeha.core.sys.mapper.ResourceMapper;
import cn.redandelion.seeha.core.sys.service.IFunctionService;
import cn.redandelion.seeha.core.user.dto.User;
import cn.redandelion.seeha.core.user.mapper.UserMapper;
import cn.redandelion.seeha.core.user.service.IUserService;
import cn.redandelion.seeha.core.util.DtoUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;


import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Component
public class SeehaApplicationTests {

	@Autowired
	private UserMapper userMapper;
	@Autowired
	private FunctionMapper functionMapper;
	@Autowired
	private IUserService userService;
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private IFunctionService functionService;

	@Autowired
	private ResourceMapper resourceMapper;
	@Autowired
	private FunctionResourceMapper functionResourceMapper;
	Logger log = LoggerFactory.getLogger(this.getClass());
	@Test
	public void insert(){
//		User user = new User();
//		user.setAge((long) 30);
//		user.setStudentName("大海");
//		user.setGrade("G1");
//		user.setDept((long) 342);
//		int num = userMapper.insertSelective(user);
//		log.info("[-----------查询一个人]"+user);

//		Resource resource = new Resource();
//		resource.setUrl("sdas");
//		resource.setName("工作流");
//		DtoUtils.setUpdateInfo(resource);
//		resourceMapper.insert(resource);
//		log.info("============{}",resource);
		FunctionResource f = new FunctionResource();
		f.setFunctionId(213L);
		f.setResourceId(8495L);
		DtoUtils.setUpdateInfo(f);
		functionResourceMapper.insert(f);
	}
	@Test
	public void insertFunction(){
		BaseDto baseDto= applicationContext.getBean(BaseDto.class);
		log.info("[-----------更新人]"+baseDto.getLastUpdatedBy());
		Function f = new Function();
		f.setFunctionId((long) 10);
		f.setFunctionCode("hr111");
		f.setFunctionIcon("fa fa user");
		f.setFunctionName("系统功能1");
		f.setModuleCode("hr");
//		DtoUtils.setUpdateInfo(f);
//		functionMapper.insertSelective(f);

		functionMapper.updateByPrimaryKey(f);
		log.info("[-----------最后登录一个人]"+f.getLastUpdateLogin());
		log.info("[-----------最后更新一个人]"+f.getLastUpdatedBy());
		log.info("[-----------插入一个人]"+f);
	}
	@Test
	public void selectAllFunction(){
		BaseDto baseDto= applicationContext.getBean(BaseDto.class);
		log.info("[-----------更新人]"+baseDto.getLastUpdatedBy());
		List<User> list =  userService.selectAll();

		log.info("[-----------查询一个人]"+list);
	}
	@Test
	public void selectAllFunctionSerivce(){
		ServiceRequest request = (ServiceRequest) applicationContext.getBean("iRequestHelper");
		Function f = new Function();
		f.setFunctionId(32L);
		f.setModuleCode("223531");
		f.setFunctionName("任务管理MMMMM");
		f.setResourceId(12314343L);
		f.setFunctionCode("2wt371e3ro74o");



	//	DtoUtils.setUpdateInfo(f);
//		functionService.updateByPrimaryKey(request,f);
//		functionService.insert(request,f);
//		List<Function> list = functionService.select(request,f,1,10);
		//List<User> list =  userService.selectAll();
//		List<Function> list = functionService.select(request,f,1,1);

		//log.info("[-----------查询一个人]"+list);
		Field[] field = f.getClass().getDeclaredFields();
		for(int i=0 ;i < field.length; i++){
			try {
				boolean accessFlag = field[i].isAccessible();
				PropertyDescriptor pd = new PropertyDescriptor(field[i].getName(), f.getClass());
				String name = pd.getReadMethod().getName();
				field[i].setAccessible(true);
				String value = String.valueOf(field[i].get(f));
				log.info("========" + value);

				field[i].getName();
				field[i].setAccessible(accessFlag);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
