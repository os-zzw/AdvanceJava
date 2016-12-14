package designpattern.decorator;

/**
 * Created by john(Zhewei) on 2016/12/14.
 * 这是原始类,里边有一些操作
 */
public class Component {

    public void operate() {
        System.out.println("我进行了一些操作!,但是还有一些操作我不想改我自己,找别人来帮忙");
    }
}
