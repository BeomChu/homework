package sharetreat.v1.util;

import lombok.Getter;

import java.util.*;

public class Code {

    public static String[] codeArr = new String[20];
    public static Map<String, String> codeStore = new HashMap<>();

    public static String storeCode = "STORE";


    public void action(String request) {
        Set<String> messageSet = new HashSet<>();
        String input = replaceInput(request);

        System.out.println("======"+input);


        if (input.startsWith("help")) {
            for (String message : Message.HELP_MESSAGE) {
                messageSet.add(message);
                System.out.println(message);
            }
        } else if (input.startsWith("check")) {
            checkCode(input.substring(5, 14)); // check 제외 이후 9자리 get

        } else if (input.startsWith("claim")) {
            String storeCode = "";
            String itemCode = "";

            try {
                storeCode = input.substring(5, 11);
                itemCode = input.substring(11, 20);
            } catch (Exception e) {
                messageSet.add("잘못된 요청입니다.");
                System.out.println("잘못된 요청입니다.");
            }

            Set<String> message = checkCode(itemCode);
            if (message.contains("교환 가능한 상품 코드입니다."))
                changeCode(storeCode, itemCode);
        } else {
            System.out.println("잘못된 요청입니다.");
        }
    }

    /**
     * 상품 코드는 9개의 숫자 문자열로 구성된 총 20개를 개발자가 임의로 제공합니다.
     * 상품 코드는 문자열 Array또는 파일이든 어떠한 형태로 제공이 되어도 관계 없습니다.
     * <p>
     * 상품 코드는 0~9 자연수 글자로 이루어져있으며 9문자로 이루어져 있습니다.
     */
    public String[] createCode() {
        Random rd = new Random();
        String code = "";

        //20개의 코드목록
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 9; j++) {
                code += rd.nextInt(9);
            }
            codeArr[i] = code;
            code = "";
        }
        return codeArr;
    }

    /**
     * 상품 코드는 10개가 준비되면 고객에게 10개까지만 제공됩니다.
     */
    public String[] getCodeArr() {
        return Arrays.copyOf(codeArr, 10);
    }

    /**
     * 상품 교환을 할 때는 어떤 상점에서 교환하였는지 상점 코드를 알아야합니다.
     */
    public void changeCode(String store, String code) {
        codeStore.put(store, code);
        System.out.println("교환된 상점 코드 : " + store);
    }

    /**
     * 고객은 상품 콛르ㅡㄹ 이용하여 상품 교환이 1번 이루어지면, 다시 해당 상품 코드로는 상품 교환을 할 수 없습니다.
     * 고객은 상품코드를 사용하기 전에 미리 상품을 교환할 수 있는지 확인이 가능합니다.
     * 테스트코드 작성을 위해 리턴값을 Set으로 지정
     */
    public Set<String> checkCode(String code) {
        Set<String> messageSet = new HashSet<>();
        for (String key : codeStore.keySet()) {
            if (codeStore.get(key).equals(code)) {
                messageSet.add("이미 교환된 상품 코드입니다.");
                messageSet.add("교환된 상점 코드 : " + key);
            }
        }

        boolean check = Arrays.asList(codeArr).contains(code);
        if (Arrays.asList(codeArr).contains(code) && messageSet.isEmpty()) {
            messageSet.add("교환 가능한 상품 코드입니다.");
        } else {
            messageSet.add("교환할 수 없는 상품 코드입니다.");
        }


        for (String message : messageSet) {
            System.out.println(message);
        }

        return messageSet;
    }


    private String replaceInput(String input) {
        String replace = input.replaceAll("\\s", "");
        return replace.toLowerCase();
    }


}
