package designpattern.builder;

/**
 * Created by john(Zhewei) on 2016/12/15.
 */
public class Product {
    private String mBoard;//主板
    private String mDisplay;//显示器
    private String mOS;//操作系统

    @Override
    public String toString() {
        return "Product{" +
                "mBoard='" + mBoard + '\'' +
                ", mDisplay='" + mDisplay + '\'' +
                ", mOS='" + mOS + '\'' +
                '}';
    }

    public String getBoard() {
        return mBoard;
    }

    public void setBoard(String board) {
        mBoard = board;
    }

    public String getDisplay() {
        return mDisplay;
    }

    public void setDisplay(String display) {
        mDisplay = display;
    }

    public String getOS() {
        return mOS;
    }

    public void setOS(String OS) {
        mOS = OS;
    }
}
