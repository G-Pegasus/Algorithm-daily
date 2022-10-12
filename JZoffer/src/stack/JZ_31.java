package stack;

import java.util.HashMap;
import java.util.Stack;

public class JZ_31 {

    // 模拟题，思考两个列表的关系
    public static boolean validateStackSequences(int[] pushed, int[] popped) {
//        HashMap<Integer, Integer> map1 = new HashMap<>();
//        HashMap<Integer, Integer> map2 = new HashMap<>();
//
//        for (int i = 0; i < pushed.length; i++) {
//            map1.put(pushed[i], i);
//        }
//
//        for (int i = 0; i < pushed.length; i++) {
//            map2.put(popped[i], map1.get(popped[i]));
//        }
//
//        for (int i = 0; i < pushed.length; i++) {
//            for (int j = i + 1; j < pushed.length; j++) {
//                if (map2.get(popped[j]) < map2.get(popped[i])) {
//                    if (i > 0) {
//                        if (map2.get(popped[i]) > map2.get(popped[i - 1])) {
//                            break;
//                        }
//                    }
//                    if (map2.get(popped[j]) != map2.get(popped[i]) - 1) {
//                        return j == pushed.length - 1;
//                    } else {
//                        break;
//                    }
//                }
//            }
//        }
//
//        return true;

        Stack<Integer> stack = new Stack<>();
        for (int i = 0, j = 0; i < pushed.length; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && stack.peek() == popped[j]) {
                stack.pop();
                j++;
            }
        }

        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(validateStackSequences(new int[]{2,1,3,0}, new int[]{1,0,3,2}));
    }
}
