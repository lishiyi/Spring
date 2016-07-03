package info.shiyi;

/**
 * Created by Administrator on 2016/7/3 0003.
 */
import java.util.List;

import javax.annotation.Resource;

//import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/main")
public class MainController {

    //protected static Logger logger = Logger.getLogger("controller");

    @Resource(name="personService")
    private PersonService personService;

    /**
     *获得所有的Person并返回到指定JSP页面
     *
     * @return the name of the JSP page
     */
    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public String getPersons(Model model) {

        //logger.debug("Received request to show all persons");

        // 调用personService中的getAll获得所有的Person
        List<Person> persons = personService.getAll();

        // 把Person装入一个指定的model
        model.addAttribute("persons", persons);

        // 解析 /WEB-INF/jsp/personspage.jsp
        return "personspage";
    }

    /**
     *根据页面传递过来的值新增一Person并跳转到指定页面.
     */
    @RequestMapping(value = "/persons/add", method = RequestMethod.GET)
    public String add(
            @RequestParam(value="firstname", required=true) String firstName,
            @RequestParam(value="lastname", required=true) String lastName,
            @RequestParam(value="money", required=true) Double money) {

       //logger.debug("Received request to add new person");

        personService.add(firstName, lastName, money);

        return "addedpage";
    }

    /**
     * 根据接收的ID删除Person
     */
    @RequestMapping(value = "/persons/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value="id", required=true) Integer id,
                         Model model) {

        //logger.debug("Received request to delete existing person");

        personService.delete(id);

        model.addAttribute("id", id);

        return "deletedpage";
    }

    /**
     * edit指定的Person
     */
    @RequestMapping(value = "/persons/edit", method = RequestMethod.GET)
    public String edit(
            @RequestParam(value="id", required=true) Integer id,
            @RequestParam(value="firstname", required=true) String firstName,
            @RequestParam(value="lastname", required=true) String lastName,
            @RequestParam(value="money", required=true) Double money,
            Model model){

        //logger.debug("Received request to edit existing person");

        personService.edit(id, firstName, lastName, money);

        model.addAttribute("id", id);

        return "editedpage";
    }

}
