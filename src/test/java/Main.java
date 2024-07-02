import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.meal.config.HibernateConfig;
import com.meal.dao.UserDao;
import com.meal.enums.Gender;
import com.meal.model.User;

public class Main {
	
	public static void main(String args[]) {
		Logger log=Logger.getLogger("Main");
		log.info("main is runnning");
		
		AnnotationConfigApplicationContext ac=new AnnotationConfigApplicationContext(HibernateConfig.class);
		UserDao ud=ac.getBean(UserDao.class);
		
		User user=new User();
		user.setFirstName("Raghvendra singh");
		user.setLastName("goud");
		user.setGender(Gender.Male);
		
		ud.save(user);
//		List<User> users=ud.getAllusers();
//		
//		for(User user1:users)
//			log.info(user1.toString());
		
	}

}
