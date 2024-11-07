public class Mobile {
    private String manufactor;
    private String phoneNumber;
    private os onlineStrategy;

    public Mobile(String manufactor, String phoneNumber){
        this.manufactor=manufactor;this.phoneNumber=phoneNumber;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public void setOnlineStrategy(os onlineStrategy){
        this.onlineStrategy = onlineStrategy;
    }

    public void connect(){

    }

    class wifiOnline implements os{
        @Override
        public void call(String number) {

        }

        @Override
        public void ring(String number){

        }
    }

    class lteOnline implements os{

        @Override
        public void call(String number) {

        }

        @Override
        public void ring(String number) {

        }
    }

}
