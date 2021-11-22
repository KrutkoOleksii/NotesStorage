package com.example.notesStorage.swagger;

import com.example.notesStorage.auth.User;
import com.example.notesStorage.auth.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springfox.boot.starter.autoconfigure.SpringfoxConfigurationProperties;
import springfox.documentation.RequestHandler;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.configuration.Swagger2JacksonModule;

import javax.validation.constraints.NotNull;
import java.util.*;


//@EnableSwagger2WebMvc
//@Import(SpringDataRestConfiguration.class)
//@EnableSwagger2
//@Import(BeanValidatorPluginsConfiguration.class)
//class SpringFoxConfig {
//
//}
//@EnableWebMvc

@Controller
@RequestMapping("api")
@Api(value = "user")
@EnableSwagger2
public class UserSwaggerController {

    @Autowired
    private UserService userService;

    public SwaggerResource getSwaggerResource() {
        return swaggerResource;
    }

    SpringfoxConfigurationProperties springfoxConfigurationProperties;
    private SwaggerResource swaggerResource;

//    @Qualifier("inMemorySwaggerResourcesProvider")
//    private InMemorySwaggerResourcesProvider inMemorySwaggerResourcesProvider;

    private Swagger2JacksonModule swagger2JacksonModule;
//    public UserSwaggerController(DocumentationCache documentationCache, ServiceModelToSwagger2Mapper mapper, JsonSerializer jsonSerializer, PluginRegistry<WebFluxSwaggerTransformationFilter, DocumentationType> transformations) {
//        super(documentationCache, mapper, jsonSerializer, transformations);
//    }


//    @ApiOperation (value = "Получить сведения о пользователе", notes = "Получить сведения о пользователе в соответствии с идентификатором URL-адреса")
//    @ApiImplicitParam (name = "id", value = "User ID", required = true, dataType = "Integer", paramType = "path")
    @ApiOperation("List Users")
    @RequestMapping(value = "/users",method = RequestMethod.GET)
//    @RequestBody
    public List< User> userList(@NotNull Model model){
//        String moduleName = swagger2JacksonModule.getModuleName();
//        System.out.println(moduleName);
//        Iterable<? extends Module> dependencies = swagger2JacksonModule.getDependencies();
//        System.out.println(dependencies);
//        List<User> userList = userService.findAll();
//        System.out.println(userList);
//model.addAttribute("users", userService.findAll());
//        return userService.findAll();
        String name = getSwaggerResource().getName();
        System.out.println(name);
        Comparator<RequestHandler> reversed = RequestHandler.byOperationName().reversed();
        System.out.println(reversed);
        List< User> userList = userService.findAll();
        model.addAttribute("users", userList);
        return  userService.findAll();
    }

    @PostMapping("/users/")
    public Object saveUser(@RequestParam @NotNull String userName,
                           @RequestParam Map<String, String> form,
                           @RequestParam("userId") User user,
                           Model model){
        return userService.save(user);
    }

    @GetMapping("error")
    public Object userError(@RequestParam User user, Model model){
        return user;
    }

    @GetMapping("user/{user}")
    public Object userEditForm(@PathVariable User user,UUID uuid,String username,  Model model){
        return userService.save(user);
//        return user;
    }

//    @Override
//    @GetMapping("swagger/resources")
//    public List<SwaggerResource> get() {
//        return inMemorySwaggerResourcesProvider.get();
//    }


//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("swagger-ui.html")
//                .addResourceLocations("classpath:/META-INF/resources/");
//
//        registry.addResourceHandler("/webjars/**")
//                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//    }


    static Map<Integer, User> users = Collections.synchronizedMap(new HashMap<Integer, User>());

//    @Override
//    public List<SwaggerResource> get() {
//        return null;
//    }

    /**
     * Запрос пользователей на основе идентификатора
     * @param id
     * @return
     */
//    @ApiOperation (value = "Получить сведения о пользователе", notes = "Получить сведения о пользователе в соответствии с идентификатором URL-адреса")
//    @ApiImplicitParam (name = "id", value = "User ID", required = true, dataType = "Integer", paramType = "path")
//    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
//    public ResponseEntity<JsonResult> getUserById (@PathVariable(value = "id") Integer id){
//        JsonResult r = new JsonResult();
////        try {
////            User user = users.get(id);
////            r.setResult(user);
////            r.setStatus("ok");
////        } catch (Exception e) {
////            r.setResult(e.getClass().getName() + ":" + e.getMessage());
////            r.setStatus("error");
////            e.printStackTrace();
////        }
////        return ResponseEntity.ok(r);
//    }

//    /**
//     * Список пользователей запроса
//     * @return
//     */
//    @ApiOperation (value = "получить список пользователей", notes = "получить список пользователей")
//    @RequestMapping(value = "users", method = RequestMethod.GET)
//    public ResponseEntity<JsonResult> getUserList (){
//        JsonResult r = new JsonResult();
////        try {
////            List<User> userList = new ArrayList<User>(users.values());
////            r.setResult(userList);
////            r.setStatus("ok");
////        } catch (Exception e) {
////            r.setResult(e.getClass().getName() + ":" + e.getMessage());
////            r.setStatus("error");
////            e.printStackTrace();
////        }
//        return ResponseEntity.ok(r);
//    }
//
//    /**
//     * Добавить пользователя
//     * @param user
//     * @return
//     */
//    @ApiOperation (value = "Создать пользователя", notes = "Создать пользователя на основе объекта" Пользователь ")
//            @ApiImplicitParam (name = "user", value = "подробный пользовательский объект пользователя", required = true, dataType = "User")
//    @RequestMapping(value = "user", method = RequestMethod.POST)
//    public ResponseEntity<JsonResult> add (@RequestBody User user){
//        JsonResult r = new JsonResult();
////        try {
////            users.put(user.getId(), user);
////            r.setResult(user.getId());
////            r.setStatus("ok");
////        } catch (Exception e) {
////            r.setResult(e.getClass().getName() + ":" + e.getMessage());
////            r.setStatus("error");
////            e.printStackTrace();
////        }
//        return ResponseEntity.ok(r);
//    }
//    /**
//     * Удаление пользователей по идентификатору
//     * @param id
//     * @return
//     */
//    @ApiOperation (value = "удалить пользователя", notes = "указать удалить пользователя в соответствии с идентификатором URL")
//    @ApiImplicitParam (name = "id", value = "User ID", required = true, dataType = "Long", paramType = "path")
//    @RequestMapping(value = "user/{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<JsonResult> delete (@PathVariable(value = "id") Integer id){
//        JsonResult r = new JsonResult();
////        try {
////            users.remove(id);
////            r.setResult(id);
////            r.setStatus("ok");
////        } catch (Exception e) {
////            r.setResult(e.getClass().getName() + ":" + e.getMessage());
////            r.setStatus("error");
////            e.printStackTrace();
////        }
//        return ResponseEntity.ok(r);
//    }
//    /**
//     * Измените информацию о пользователе в соответствии с идентификатором
//     * @param user
//     * @return
//     */
//    @ApiOperation (value = "обновить информацию", notes = "указать обновлять информацию о пользователе в соответствии с идентификатором URL")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "id", value = "User ID", required = true, dataType = "Long", paramType = "path"),
//            @ApiImplicitParam (name = "user", value = "user entity user", required = true, dataType = "User")
//    })
//    @RequestMapping(value = "user/{id}", method = RequestMethod.PUT)
//    public ResponseEntity<JsonResult> update (@PathVariable("id") Integer id, @RequestBody User user){
//        JsonResult r = new JsonResult();
////        try {
////            User u = users.get(id);
////            u.setUsername(user.getUsername());
////            u.setAge(user.getAge());
////            users.put(id, u);
////            r.setResult(u);
////            r.setStatus("ok");
////        } catch (Exception e) {
////            r.setResult(e.getClass().getName() + ":" + e.getMessage());
////            r.setStatus("error");
////            e.printStackTrace();
////        }
//        return ResponseEntity.ok(r);
//    }
//    @ ApiIgnore // Используйте эту аннотацию, чтобы игнорировать этот API
//    @RequestMapping(value = "/hi", method = RequestMethod.GET)
//    public String  jsonTest() {
//        return " hi you!";
//    }
}

