package com.raeshi.springboot.myfirstwebapp.hello;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {
    @RequestMapping("say-hello")
    @ResponseBody
    public String sayHello() {
        return "Hello. What are you doing now";
    }

    @RequestMapping("say-hello-html")
    @ResponseBody
    public String sayHelloHtml() {
        StringBuffer sb = new StringBuffer();
        sb.append("<html lang=\"en\">");
        sb.append("<head>\n");
        sb.append("<title>Document</title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append(" Rae Shi - change");
        sb.append(" </body>");
        sb.append("</html>");
        return sb.toString();
    }

    //JSP sayHello.jsp
    //"say-hello-jsp" => sayHello.jsp
//src/main/resources/META-INF/resources/WEB-INF/jsp/sayHello.jsp
    @RequestMapping("say-hello-jsp")
    public String sayHelloJsp() {
        return "sayHello";
    }
    
    
}
