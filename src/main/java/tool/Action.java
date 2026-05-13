package tool;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public abstract class Action {

    // HttpServletRequest, HttpServletResponse を受け取る抽象メソッド
    public abstract void execute(HttpServletRequest req, HttpServletResponse res) throws Exception;

}