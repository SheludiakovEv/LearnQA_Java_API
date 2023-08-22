//import io.restassured.RestAssured;
//import io.restassured.response.Response;
//import org.junit.jupiter.api.Test;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.junit.runner.RunWith;
//import org.junit.runners.Parameterized;
//
//@RunWith(Parameterized.class)
//public class PasswordTest {
//
//    private final String login;
//    private final String password;
//
//    public PasswordTest(String login, String password) {
//        this.login = login;
//        this.password = password;
//    }
//
//    @Parameterized.Parameters
//    public static Object[][] getPassword() {
//        return new Object[][] {
//                {"super_admin", "123456"},
//                {"super_admin", "123456789"},
//                {"super_admin", "12345"},
//                {"super_admin", "qwerty"},
//                {"super_admin", "password"},
//                {"super_admin", "12345678"},
//                {"super_admin", "111111"},
//                {"super_admin", "123123"},
//                {"super_admin", "1234567890"},
//                {"super_admin", "1234567"},
//                {"super_admin", "qwerty123"},
//                {"super_admin", "000000"},
//                {"super_admin", "1q2w3e"},
//                {"super_admin", "aa12345678"},
//                {"super_admin", "abc123"},
//                {"super_admin", "password1"},
//                {"super_admin", "1234"},
//                {"super_admin", "qwertyuiop"},
//                {"super_admin", "123321"},
//                {"super_admin", "password123"}
//        };
//    }
//
//        //Задание №9
//        @Test
//        public  void passwordTest(){
////            PasswordTest password = new PasswordTest();
////            Map<String, String> data = new HashMap<>();
////            data.put("login", login);
////            data.put("password", password);
////            Response response1 = RestAssured
////                    .given()
////                    .body(login, password)
////                    .post("https://playground.learnqa.ru/ajax/api/get_secret_password_homework")
////                    .andReturn();
//
//            response1.prettyPrint();
//            String cookie = response1.getHeader("Set-Cookie");
//            System.out.println(cookie);
//
//            Map<String, String> cookies = new HashMap<>();
//            if(cookie != null) {
//            cookies.put("Set-Cookie", cookie);
//            }
//
//            Response response2 = RestAssured
//                    .given()
//                    .cookies(cookies)
//                    .post("https://playground.learnqa.ru/ajax/api/check_auth_cookie")
//                    .andReturn();
//
//            response2.print();
//        }
//}