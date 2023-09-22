package hello.springmvc.response;

import hello.springmvc.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller
public class ResponseBodyController {

    // "/response-body-string-v1" 경로로 GET 요청이 들어올 때,
    // "ok" 문자열을 직접 HTTP 응답 바디에 쓰는 메서드.
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    /**
     * HttpEntity, ResponseEntity(Http Status 추가)
     *
     * @return
     */
    // "/response-body-string-v2" 경로로 GET 요청이 들어올 때,
    // ResponseEntity를 사용하여 "ok" 문자열을 반환.
    // ResponseEntity는 HTTP 상태 코드(HttpStatus)를 함께 반환할 수 있는 방법.
    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2() {
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    // "/response-body-string-v3" 경로로 GET 요청이 들어올 때,
    // @ResponseBody를 사용하여 "ok" 문자열을 반환.
    // @ResponseBody 어노테이션은 메서드가 직접 HTTP 응답 바디에 데이터를 쓸 수 있도록 해줌.
    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3() {
        return "ok";
    }

    // "/response-body-json-v1" 경로로 GET 요청이 들어올 때,
    // ResponseEntity를 사용하여 JSON 형식의 HelloData 객체를 반환.
    // ResponseEntity는 HTTP 상태 코드(HttpStatus)를 함께 반환할 수 있으며,
    // JSON 데이터를 반환할 수 있음.
    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    // "/response-body-json-v2" 경로로 GET 요청이 들어올 때,
    // @ResponseStatus와 @ResponseBody를 사용하여 JSON 형식의 HelloData 객체를 반환.
    // @ResponseStatus 어노테이션을 사용하여 HTTP 상태 코드(HttpStatus)를 지정할 수 있음.
    // @ResponseBody 어노테이션은 메서드가 직접 HTTP 응답 바디에 데이터를 쓸 수 있도록 함.
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);
        return helloData;
    }
}