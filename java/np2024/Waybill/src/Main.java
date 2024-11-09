public class Main {
    public static void main(String[] args) {
        Coupang coupang = new Coupang();
        Ddestination d1 = new Ddestination("서울특별시 종로구 00로 00-00","홍길동", new NormalDelivery());
        Ddestination d2 = new Ddestination("서울특별시 성북구 XX로 XX-XX", "김건탁", new RocketDelivery());
        Ddestination d3 = new Ddestination("서울특별시 은평구 XX로 XX-XX", "강감찬", new QuickService());

        coupang.deliver(d1);
        coupang.deliver(d2);
        coupang.deliver(d3);
    }
}