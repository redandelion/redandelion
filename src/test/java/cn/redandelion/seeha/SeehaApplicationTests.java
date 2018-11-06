package cn.redandelion.seeha;

import cn.redandelion.seeha.core.sys.basic.dto.BaseDto;
import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.sys.basic.service.impl.ServiceRequest;
import cn.redandelion.seeha.core.sys.function.dto.Function;
import cn.redandelion.seeha.core.sys.function.dto.FunctionRole;

import cn.redandelion.seeha.core.sys.function.dto.MenuItem;
import cn.redandelion.seeha.core.sys.function.dto.Resource;
import cn.redandelion.seeha.core.sys.function.mapper.FunctionMapper;
import cn.redandelion.seeha.core.sys.function.mapper.FunctionResourceMapper;
import cn.redandelion.seeha.core.sys.function.mapper.ResourceMapper;
import cn.redandelion.seeha.core.sys.function.service.IFunctionRoleService;
import cn.redandelion.seeha.core.sys.function.service.IFunctionService;
import cn.redandelion.seeha.core.sys.function.service.IResourceService;
import cn.redandelion.seeha.core.user.dto.User;
import cn.redandelion.seeha.core.user.mapper.UserMapper;
import cn.redandelion.seeha.core.user.service.IUserService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;


import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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
	private ResourceMapper resourceMapper;
	@Autowired
	private FunctionResourceMapper functionResourceMapper;
	@Autowired
	private IFunctionService functionService;
	@Autowired
	private IFunctionRoleService functionRoleService;
	@Autowired
	private IResourceService resourceService;
	Logger log = LoggerFactory.getLogger(this.getClass());

	@Test
	public void menu(){
		ServiceRequest request = (ServiceRequest) applicationContext.getBean("iRequestHelper");
		Long[] a = new Long[]{10001L, 10002L};
		request.setAllRoleId(a);
		List<MenuItem> list= functionService.selectRoleFunctions(request);

	}

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
//		FunctionResource f = new FunctionResource();
//		f.setFunctionId(213L);
//		f.setResourceId(8495L);
//		DtoUtils.setUpdateInfo(f);
//		functionResourceMapper.insert(f);
		Consumer<String> consumer = (String s) ->  System.out.println(s);
		consumer.accept("你大爷");

		java.util.function.Function f= s-> s;
		System.out.println(f.apply("redandelion"));
	}
	@Test
	public void insertFunction(){

//		BaseDto baseDto= applicationContext.getBean(BaseDto.class);
//		log.info("[-----------更新人]"+baseDto.getLastUpdatedBy());
		Function f = new Function();
//		f.setFunctionId((long) 10);
//		f.setFunctionCode("hr111");
//		f.setFunctionIcon("fa fa user");
//		f.setFunctionName("系统功能1");
		f.setModuleCode("hr");
//		DtoUtils.setUpdateInfo(f);
//		functionMapper.insertSelective(f);
//functionMapper
        functionMapper.select(f);
        log.info(f.toString());
//		functionMapper.updateByPrimaryKey(f);
//		log.info("[-----------最后登录一个人]"+f.getLastUpdateLogin());
//		log.info("[-----------最后更新一个人]"+f.getLastUpdatedBy());
//		log.info("[-----------插入一个人]"+f);
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
		java.util.function.Function f1 = (fun) -> {
			Field[] field = fun.getClass().getDeclaredFields();
			StringBuilder builder = new StringBuilder();
			for(int i=0 ;i < field.length; i++) {
				boolean accessFlag = field[i].isAccessible();
				PropertyDescriptor pd = null;
				try {
					pd = new PropertyDescriptor(field[i].getName(), fun.getClass());
				} catch (IntrospectionException e) {
					e.printStackTrace();
				}
				String name = pd.getReadMethod().getName();
				field[i].setAccessible(true);
				String value = null;
				try {
					value = String.valueOf(field[i].get(fun));
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}

				if(!value.equals("null")){
					builder.append(value+"|");
				}

				log.info("========" + value);
				field[i].setAccessible(accessFlag);
			}
			return builder.toString();};
			String s = f1.apply(f).toString();
			System.out.println(s);

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
	@Test
	public void BigSerivce(){
		BigDecimal b1  = new BigDecimal("1");
		BigDecimal b2  = new BigDecimal("0.5553");
		List<BigDecimal> list = new ArrayList<>();
		List<User> users = new ArrayList<>();
		User u1 = new User();
		User u2 = new User();
		User u3 = new User();
		u1.setUserId(-10L);
		u2.setUserId(20L);
		u3.setUserPassword("admin");
		users.add(u1);
		users.add(u2);
		users.add(u3);
//		BigDecimal d = new BigDecimal("0");
//		BigDecimal e = new BigDecimal("-3");
//		BigDecimal f = new BigDecimal("-3");
//		int com = d.compareTo(e);
//		System.out.println(com);
//		list.add(d);
//		list.add(e);
//		list.add(f);

		BigDecimal bigDecimal = list.stream().reduce(BigDecimal.ZERO,BigDecimal::add);
		System.out.println(bigDecimal);
		String str = new String("weqy33333");
//		String[] a = str.split("-");
////		String a1 = str.substring(str.indexOf("-")+1,str.indexOf("-")+5);
//		String a1 = str.substring(str.indexOf("-",str.indexOf("-")+1)-4,str.indexOf("-",str.indexOf("-")+1));

		int str2= str.lastIndexOf("333");

		str.substring(0,str2);
//		System.out.println(a);
//		for(String s:a){
//			System.out.println(s);
//		}
		System.out.println(str.substring(0,str.length()-3));
	}
	@Test
	public void menuTest() {
		List<Function> functions = new ArrayList<>();
		List<FunctionRole> functionList = new ArrayList<>();
		List<Long> ids = new ArrayList<>();
		Set<Long> allRoleFunctionIds = new HashSet<>();
		Long[] allRoleId = new Long[]{10001L, 10002L};
		for (Long roleId : allRoleId) {
			FunctionRole functionRole = new FunctionRole();
			functionRole.setRoleId(roleId);
			functionList = functionRoleService.selectByCondition(functionRole);
			functionList.forEach((v) -> ids.add(v.getFunctionId()));
			allRoleFunctionIds.addAll(ids);
		}
//		选出角色有所有功能
		List<MenuItem> menuItemList = new ArrayList<>();
		List<MenuItem> menuItemList1 = new ArrayList<>();
		functions = functionService.selectAll();
		List<Function> collect = functions.stream()
				.filter(x -> {
					return allRoleFunctionIds.stream().anyMatch(y -> y.equals(x.getFunctionId()));
				})
				.collect(Collectors.toList());

		for (Function function : collect) {
			MenuItem menu = new MenuItem();
			menu.setText(function.getFunctionName());
			menu.setIcon(function.getFunctionIcon());
			menu.setFunctionCode(function.getFunctionCode());
			if (function.getResourceId() != null) {
				Resource resource = new Resource();
				resource.setResourceId(function.getResourceId());
				resource = resourceService.selectByPrimaryKey(null, resource);
				if (resource != null) {
					menu.setUrl(resource.getUrl());
					System.out.println("2");
				}
			}
			menu.setId(function.getFunctionId());
			menu.setScore(function.getFunctionSequence());
			menu.setShortcutId(function.getParentFunctionId());
			menuItemList.add(menu);
		}

		Map<Long, List<MenuItem>> menu = menuItemList.stream()
										.filter(x -> x.getShortcutId() != null)
										.collect(Collectors.groupingBy(MenuItem::getShortcutId));

		List<MenuItem> itemList = new ArrayList<>();
		itemList = menuItemList.stream().filter(x -> x.getShortcutId() == null).collect(Collectors.toList());
		menuItemList.forEach(x->{
			if(Optional.ofNullable(menu.get(x.getId())).isPresent()){
				x.setChildren(menu.get(x.getId()));
			}
		});

	}

}




