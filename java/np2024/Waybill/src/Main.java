public class Main {
    public static void main(String[] args) {
        PostOffice postOffice = new PostOffice();
        Ddestination d1 = new Ddestination("서울특별시 종로구 00로 00-00","홍길동", new NormalDelivery());
        Ddestination d2 = new Ddestination("서울특별시 성북구 XX로 XX-XX", "김건탁", new RocketDelivery());
        Ddestination d3 = new Ddestination("서울특별시 은평구 XX로 XX-XX", "강감찬", new QuickService());

        postOffice.deliver(d1);
        postOffice.deliver(d2);
        postOffice.deliver(d3);
    }
}