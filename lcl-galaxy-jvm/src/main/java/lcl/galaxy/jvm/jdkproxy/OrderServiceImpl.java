package lcl.galaxy.jvm.jdkproxy;

public class OrderServiceImpl implements OrderService{
    @Override
    public String getOrderName(String s) {
        System.out.println("==========" + s);

        return "==========" + s;
    }
}
