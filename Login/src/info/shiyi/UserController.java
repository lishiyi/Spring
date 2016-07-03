package info.shiyi;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * springMVC加入了JSR303（Bean Validate的功能）
 * @author admin
 */
@Controller
@RequestMapping("/user")
@SessionAttributes(value="loginUser")
public class UserController {
    private final static Map<String, User> users = new HashMap<String, User>();
    public UserController(){
        users.put("ldh", new User("ldh", "123", "ldh@163.com", "华仔"));
        users.put("zxy", new User("zxy", "123", "ldh@163.com", "学友"));
        users.put("gfc", new User("gfc", "123", "ldh@163.com", "郭富城"));
        users.put("lm", new User("lm", "123", "ldh@163.com", "黎明"));
    }
    /**
     * 获取用户列表
     * @param model
     * @return
     */
    @RequestMapping({"/users","/"})
    public String list(Model model){
        model.addAttribute("users",users);
        return "user/list";
    }
    /**
     * 跳转到添加页面
     * @param model
     * @return
     */
    @RequestMapping(value="/add",method=RequestMethod.GET)
    public String add(Model model){
        model.addAttribute(new User());
        return "user/add";
    }

    /**
     *
     * 添加页面提交请求，post方式
     * @param user
     * @param files 上传文件方式
     * @param binding 接收错误信息的(必须要在要进行验证的对象之后)
     * @return
     */
    //参数user与页面的form表单中的modelAttribute的值一样
    @RequestMapping(value="/add",method=RequestMethod.POST)
    public String add(@Valid User user,BindingResult binding,@RequestParam MultipartFile[] files,HttpServletRequest req){
        if (binding.hasErrors()) {
            return "user/add";
        }
//      System.out.println(file.getContentType()+","+file.getName()+","+file.getOriginalFilename());
        String realPath = req.getSession().getServletContext().getRealPath("/resources/upload");
        /*
        for(MultipartFile file:files){
            try {
                System.out.println(realPath);
                //FileUtils.copyInputStreamToFile(file.getInputStream(), new File(realPath+"/"+file.getOriginalFilename()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } */
        users.put(user.getUsername(), user);
        //执行客户端跳转
        return InternalResourceViewResolver.REDIRECT_URL_PREFIX+"/user/users";
    }

    /**
     * 显示用户信息
     * @param username
     * @param model
     * @return
     */
    @RequestMapping(value="/{username}",method=RequestMethod.GET)
    public String show(@PathVariable String username,Model model){
        model.addAttribute(users.get(username));
        return "user/show";
    }

    /**
     * 跳转到更新页面
     * @param username
     * @param model
     * @return
     */
    @RequestMapping(value="/{username}/update",method=RequestMethod.GET)
    public String update(@PathVariable String username,Model model){
        model.addAttribute(users.get(username));
        return "user/update";
    }
    /**
     * 更新页面提交请求
     * @param username
     * @param user
     * @param br 接收错误信息的
     * @return
     */
    //参数user与页面的form表单中的modelAttribute的值一样
    @RequestMapping(value="/{username}/update",method=RequestMethod.POST)
    public String update(@PathVariable String username,@Valid User user,BindingResult br){
        if (br.hasErrors()) {
            return "user/update";
        }
        users.put(username, user);
        return InternalResourceViewResolver.REDIRECT_URL_PREFIX+"/user/users";
    }

    /**
     * 删除操作
     * @param username
     * @return
     */
    @RequestMapping(value="/{username}/delete",method=RequestMethod.GET)
    public String delete(@PathVariable String username){
        users.remove(username);
        return InternalResourceViewResolver.REDIRECT_URL_PREFIX+"/user/users";
    }

    /**
     * 跳转到登录页面
     * @return
     */
    @RequestMapping(value="/login",method=RequestMethod.GET)
    public String login(){
        return "user/login";
    }
    /**
     * 登录提交页面
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value="/login",method=RequestMethod.POST)
    public String login(String username,String password,Model model){
        if (!users.containsKey(username)) {
            throw new UserException("用户名不存在");
        }
        if (!password.equals(users.get(username).getPassword())) {
            throw new UserException("用户名密码不正确");
        }
        model.addAttribute("loginUser",users.get(username));
        return InternalResourceViewResolver.REDIRECT_URL_PREFIX+"/user/users";
    }

    /**
     * 捕获异常执行的方法
     * @return
     */
    @ExceptionHandler(UserException.class)
    public String handlerException(Exception ex,HttpServletRequest req){
        req.setAttribute("ex", ex);
        return "error";
    }

    /**
     * redirect方式传request范围的对象
     * @param model
     * @param ra
     * @return
     */
    @RequestMapping(value="/redir")
    public String redir(Model model,RedirectAttributes ra){
        ra.addFlashAttribute("redir","我的redirect方式传request范围的对象");
        return InternalResourceViewResolver.REDIRECT_URL_PREFIX+"/user/users";
    }
}