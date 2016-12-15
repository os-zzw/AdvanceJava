package designpattern.builder;

/**
 * Created by john(Zhewei) on 2016/12/15.
 */
public class Builder {
    private Product mProduct;

    public void buildBoard(String board) {
        mProduct.setBoard(board);
    }

    public void buildDisplay(String display) {
        mProduct.setDisplay(display);
    }

    public void buildOS(String os) {
        mProduct.setOS(os);
    }

    public Product createProduct() {
        return mProduct;
    }
}
