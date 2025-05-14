public class Friend {
    
    static int numOfFrineds;
    String name;

    Friend(String name){

        this.name = name;
        numOfFrineds++;
    }
    static void showFriends(){
        System.out.println("You have " + numOfFrineds + " total friends");
    }
}
