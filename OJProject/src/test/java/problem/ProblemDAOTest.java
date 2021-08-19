package problem;

import org.junit.Test;

import java.util.List;

public class ProblemDAOTest {
    @Test
    public void insert() {
        Problem problem = new Problem();
        problem.setTitle("无重复字符的最长子串");
        problem.setLevel("中等");
        problem.setDescription("给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。\n" +
                "\n" +
                " \n" +
                "\n" +
                "示例 1:\n" +
                "\n" +
                "输入: s = \"abcabcbb\"\n" +
                "输出: 3 \n" +
                "解释: 因为无重复字符的最长子串是 \"abc\"，所以其长度为 3。\n" +
                "示例 2:\n" +
                "\n" +
                "输入: s = \"bbbbb\"\n" +
                "输出: 1\n" +
                "解释: 因为无重复字符的最长子串是 \"b\"，所以其长度为 1。\n" +
                "示例 3:\n" +
                "\n" +
                "输入: s = \"pwwkew\"\n" +
                "输出: 3\n" +
                "解释: 因为无重复字符的最长子串是 \"wke\"，所以其长度为 3。\n" +
                "     请注意，你的答案必须是 子串 的长度，\"pwke\" 是一个子序列，不是子串。\n" +
                "示例 4:\n" +
                "\n" +
                "输入: s = \"\"\n" +
                "输出: 0\n" +
                " \n" +
                "\n" +
                "提示：\n" +
                "\n" +
                "0 <= s.length <= 5 * 104\n" +
                "s 由英文字母、数字、符号和空格组成");
        problem.setTemplateCode("class Solution {\n" +
                "    public int lengthOfLongestSubstring(String s) {\n" +
                "\n" +
                "    }\n" +
                "}");
        problem.setTestCode("    public static void main(String[] args) {\n" +
                "        Solution solution = new Solution();\n" +
                "        String str=\"pwwkew\";\n" +
                "        int result = solution.lengthOfLongestSubstring(str);\n" +
                "        if (result==3) {\n" +
                "            System.out.println(\"TestCase OK!\");\n" +
                "        } else {\n" +
                "            System.out.println(\"TestCase Failed! String=\\\"pwwkew\\\"\");\n" +
                "        }\n" +
                "\n" +
                "        String str2=\"abcabcbb\";\n" +
                "        int result2 = solution.lengthOfLongestSubstring(str);\n" +
                "        if (result2==3) {\n" +
                "            System.out.println(\"TestCaseOK!\");\n" +
                "        } else {\n" +
                "            System.out.println(\"TestCase Failed! String=\\\"abcabcbb\\\"\");\n" +
                "        }\n" +
                "    }");
        ProblemDAO problemDAO = new ProblemDAO();
        problemDAO.insert(problem);
    }

    @Test
    public void delete() {
        ProblemDAO problemDAO = new ProblemDAO();
        boolean res=problemDAO.delete(10);
        System.out.println(res);
    }

    @Test
    public void selectAll() {
        ProblemDAO problemDAO = new ProblemDAO();
        List<Problem> problems = problemDAO.selectAll();
        System.out.println(problems);
    }

    @Test
    public void selectOne() {
        ProblemDAO problemDAO = new ProblemDAO();
        Problem problem = problemDAO.selectOne(4);
        System.out.println(problem);
    }
}