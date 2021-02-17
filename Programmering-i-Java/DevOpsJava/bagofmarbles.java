    public class BagofMarbles {
        String color;
        int marbles;
        
        
        public BagofMarbles(String newColor, 
                            int newMarbles)  {
            color = newColor;
            marbles = newMarbles;
        }
        
        public void printMarbles(String message) {
            System.out.println(message + marbles);
        }
        
        public void printColor(String message) {
            System.out.println(message + color);
        }
        
        public String getAllMarbleInfo() {
        String marbleInfo = "The bag of " 
                            + color 
                            + " marbles contain " 
                            + marbles 
                            + " marbles";
        
        return marbleInfo;
        }
        
        public String toString() {
            return getAllMarbleInfo();
        }
        
        public int getMarbles() {
            return marbles;
        }
        
        
        public void addMarbles(int amount) {
            System.out.println("You wish to add " + amount + " " + color + " marbles to the bag of " + color + " marbles");
            if ((amount + marbles) > 50) {
                marbles = 50;
                System.out.println("The bag cannot contain more than 50 marbles");
            } else {
            this.marbles = marbles + amount;
            }
        }
        public void takeMarbles(int amount) {
            System.out.println("You wish to take " + amount + " " + color + " marbles");
            if (amount > marbles) {
                System.out.println("The bag of " + color + " marbles only had " + marbles );
                marbles = 0;
            } else {
            this.marbles = marbles - amount;
            }
        }
       
    }
    