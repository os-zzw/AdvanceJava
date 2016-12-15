package designpattern.builder;

/**
 * Created by john(Zhewei) on 2016/12/15.
 * 统一组装过程
 */
public class Director {
    Builder mBuilder = null;

    public Director(Builder builder) {
        mBuilder = builder;
    }

    /**
     * 构建对象
     */
    public void construct(String board,String display,String os){
        mBuilder.buildBoard(board);
        mBuilder.buildDisplay(display);
        mBuilder.buildOS(os);
    }

}
