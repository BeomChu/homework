package sharetreat.v1.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;




class CodeTest {

    @Test
    @DisplayName("20개의 임의의 코드를 생성한다")
    public void codeCreateTest(){
        Code code = new Code();
        String[] codeArr = code.createCode();
        assertEquals(20,codeArr.length);
    }

    @Test
    @DisplayName("10개의 코드를 제공받는다. 각 코드는 9글자이다.")
    public void getCodeTest(){
        Code code = new Code();
        String[] originCodeArr = code.createCode();
        String[] getCodeArr = code.getCodeArr();


        assertEquals(10, getCodeArr.length);
        assertEquals(20, originCodeArr.length);
        assertEquals(originCodeArr[0], getCodeArr[0]);
        assertEquals(getCodeArr[0].length(), 9);
    }


    @Test
    @DisplayName("이미 교환된 코드는 안내해준다.")
    public void codeInfo(){
        Code code = new Code();
        String[] codeArr = code.createCode();

        //임의의 상점 코드
        String storeCode = "ABCDef";

        code.changeCode(storeCode, codeArr[0]);

        Set<String> messageSet = code.checkCode(codeArr[0]);
        assertEquals(messageSet.size(), 3);
    }


    @Test
    @DisplayName("교환 가능한 코드는 안내해준다.")
    public void canChange(){
        Code code = new Code();
        String[] codeArr = code.createCode();

        Set<String> messageSet = code.checkCode(codeArr[0]);
        assertEquals(messageSet.size(), 1);
    }


    @Test
    @DisplayName("없는 코드는 교환할 수 없다")
    public void noCode(){
        Code code = new Code();
        String[] codeArr = code.createCode();

        String notCode = "beomchu";

        Set<String> messageSet = code.checkCode(notCode);
        assertEquals(messageSet.size(), 1);
    }

    @Test
    @DisplayName("help test")
    public void test1(){
        System.out.println("=============");

        Code code = new Code();
        code.createCode();

        String input = "Help";

        code.action(input);

    }

    @Test
    @DisplayName("check test bad case")
    public void checkTest1(){
        System.out.println("=============");

        Code code = new Code();
        code.createCode();

        String input = "Check 132 421 122";

        code.action(input);

    }

    @Test
    @DisplayName("check good case")
    public void checkTest2(){
        System.out.println("=============");

        Code code = new Code();
        code.createCode();

        String[] codeArr = code.getCodeArr();


        String input = "Check" + codeArr[0];

        code.action(input);
    }


    @Test
    @DisplayName("chaim bad case")
    public void claimTest1(){
        System.out.println("=============");

        Code code = new Code();
        code.createCode();

        String[] codeArr = code.getCodeArr();

        String input = "claIm ABcde 1231 2312 123";

        code.action(input);
    }

    @Test
    @DisplayName("chaim good case")
    public void claimTest2(){
        System.out.println("=============");

        Code code = new Code();
        code.createCode();

        String[] codeArr = code.getCodeArr();

        String input = "claIm ABcdef "+ codeArr[0];

        code.action(input);
    }

}