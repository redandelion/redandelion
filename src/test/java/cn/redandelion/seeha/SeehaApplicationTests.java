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

import org.apache.poi.hssf.usermodel.HSSFPrintSetup;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
		users = users.stream().filter(x->x.getUserPassword()!=null).filter(x->"admin".equals(x.getUserPassword())).collect(Collectors.toList());
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

		//int str2= str.lastIndexOf("333");

		String stre = str.substring(str.length()-3,str.length());
//		System.out.println(a);
//		for(String s:a){
//			System.out.println(s);
//		}
		System.out.println(stre);
		System.out.println(users.get(0).getUserPassword());

	}
	@Test
	public void excel() throws IOException {
		List<User> users = new ArrayList<>();
		User u1 = new User();
		User u2 = new User();
		User u3 = new User();
		u1.setUserId(-10L);
		u1.setPhone("1234");
		u1.setUserPassword("admin");
		u2.setUserId(20L);
		u2.setPhone("1234");
		u2.setUserPassword("redandelion");
		u3.setUserPassword("admin");
		u3.setUserId(30L);
		u3.setPhone("1234");
		users.add(u1);
		users.add(u2);
		users.add(u3);
//		FileOutputStream fos = new FileOutputStream("d:/excel/poi.xlsx");
//		XSSFWorkbook book = new XSSFWorkbook();
//		XSSFSheet sheet1= book.createSheet("学生信息1");
//		XSSFSheet sheet2= book.createSheet("学生信息2");
//		book.close();
		Map<String, Map<String, List<User>>> collect = users.stream().collect(Collectors.groupingBy(User::getPhone, Collectors.groupingBy(User::getUserPassword)));
		collect.forEach((k,v)->{
			v.forEach((x,y)->{
				Optional.ofNullable(y).isPresent();
				System.out.println(x);
				System.out.println(y);
			});
		});

		Map<String, List<User>> collect1 = users.stream().collect(Collectors.groupingBy(User::getPhone));
//
// 	NumberFormat percent = NumberFormat.getPercentInstance();
//		percent.setMaximumFractionDigits(2);
//
//		System.out.println(percent.format(new BigDecimal("0.2457")));
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


	@Test
	public void regex(){
//		String password = "kafsgfajg2134";
//		String regex1 = ".*[0-9].*";
//		boolean flag1= password.matches(regex1);
//		DecimalFormat df = new DecimalFormat("#.00");
//		String s = "Pol PRORRATEO Oct-18 10514";
//		int index = s.indexOf("|");
//		System.out.println(index);
//		String str= s.substring(0,index);
//		Double d = Double.valueOf(df.format(Long.parseLong(s)));
//
//		String str = DecimalFormat.getNumberInstance().format(Double.valueOf(s));
////
//////		str = df.format((Double.parseDouble(str)));
//		String currecy = NumberFormat.getCurrencyInstance(Locale.US).format(Long.parseLong(s));
//		System.out.println(currecy);
//		currecy = currecy.substring(1,currecy.length());
//		System.out.println(str);
		String str = "-34.32";
		String field4 = NumberFormat.getCurrencyInstance(Locale.US).format(Double.valueOf(str));
		System.out.println(field4);
	}
//	public boolean generaPdfContrasena(String urlPlantilla, String urlReporteGenerado, String urlComprobante) throws GeneraPdfExcepcion {
//
//
//		boolean respuesta;
//		respuesta = true;
//
//		JasperReport jasperReport;
//		jasperReport = null;
//
//		JasperPrint jasperPrint;
//		jasperPrint = null;
//
//		JasperDesign jasperDesign;
//		jasperDesign = null;
//
//		Map<String, Object> parametros;
//		parametros = new HashMap<>();
//
//		ComprobanteDocument comprobante;
//		comprobante = null;
//
//		SimpleDateFormat formato;
//		formato = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss");
//
//		try {
//
//			comprobante = GeneraPdfUtilerias.convierteXmlAObjeto(urlComprobante);
//
//			SimplePdfExporterConfiguration configurationPdf;
//			configurationPdf = new SimplePdfExporterConfiguration();
//
//			configurationPdf.set128BitKey(true);
//			configurationPdf.setEncrypted(true);
//			configurationPdf.setUserPassword("test");
//			configurationPdf.setOwnerPassword("test");
//
//			parametros.put("FECHA_SOLICITUD", formato.format(new Date()));
//			parametros.put("FECHA_EMISION", "asdfadsf");
//			parametros.put("RFC_EMISOR", "XAXX010101000");
//			parametros.put("UUID", "2AEE9A26-FE76-4794-9093-89328FC95916");
//			parametros.put("CODIGO", "201");
//			parametros.put("SELLO_SAT",
//					"cPLoqSjNiIO9CTFY5yY8PSjhVDYm7FhsVCqIKs10L7ZvqvVg5grtXUf0TLen8WdW64XF0gFBsoeVij+Q/TaUNOfpiS/a7T84anvIu5SrRutAyPx5ZQ0wz51AVaTX4ZdSA4m7ekxVdje6bj0+rnS7rMpglObKPpafEg6qU+KnzuIJ7mR//HgfS4y90tiPAHJIMBpfPPPzUxG5QzuW6V3s4UmrF/1QekWZc2FcHoUAdFZmOkNZBT3rdP3EN+GgmJA8gg17OqCMtv0BFih5GHyZiO6qtXoOpfnvp5tAYrer7U0DjZxVLAhlQkPk/HOK+RAYWw7yCCN5xOf0k0kcV3zZmw==");
//
//			if(comprobante.getComprobante().getComplementoArray().length > 0) {
//				Complemento[] complementos = comprobante.getComprobante().getComplementoArray();
//
//				String texto;
//				texto = "";
//
//				for(Complemento complemento : complementos) {
//					texto = texto + complemento.toString();
//				}
//
//				parametros.put("COMPLEMENTO_SAT", texto);
//			}else {
//				parametros.put("COMPLEMENTO_SAT", "Sin datos");
//			}
//
//			JRPdfExporter exporter = new JRPdfExporter();
//
//			jasperDesign = JRXmlLoader.load(urlPlantilla);
//
//			jasperReport = JasperCompileManager.compileReport(jasperDesign);
//
//			jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, new JREmptyDataSource());
//
//			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//			exporter.setConfiguration(configurationPdf);
//			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(urlReporteGenerado));
//			exporter.exportReport();
//
//			System.out.println("Termina");
//
//		} catch (JRException e) {
//			System.err.println(e.getMessage());
//			throw new GeneraPdfExcepcion();
//		}
//
//		return respuesta;
//	}
	public void excelPoi(){
		HSSFWorkbook wb = new HSSFWorkbook();

		HSSFSheet sheet  = wb.createSheet("sheel1");

		HSSFPrintSetup print = (HSSFPrintSetup) sheet.getPrintSetup();

	}

}

