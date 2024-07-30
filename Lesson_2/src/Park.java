public class Park {
    private String parkName;
    private Attraction[] attractions;

    public Park(String parkName, int numberOfAttractions) {
        this.parkName = parkName;
        this.attractions = new Attraction[numberOfAttractions];
    }

    public void addAttraction(int index, String name, String workingHours, double cost) {
        if (index >= 0 && index < attractions.length) {
            attractions[index] = new Attraction(name, workingHours, cost);
        } else {
            System.out.println("Invalid index.");
        }
    }

    public void printAttractions() {
        System.out.println("Парк: " + parkName);
        for (Attraction attraction : attractions) {
            if (attraction != null) {
                attraction.printInfo();
            }
        }
    }

    public class Attraction {
        private String name;
        private String workingHours;
        private double cost;

        public Attraction(String name, String workingHours, double cost) {
            this.name = name;
            this.workingHours = workingHours;
            this.cost = cost;
        }

        public void printInfo() {
            System.out.println("Название аттракциона: " + name);
            System.out.println("Время работы: " + workingHours);
            System.out.println("Стоимость: " + cost);
        }
    }

    public static void main(String[] args) {
        Park park = new Park("Dreamland", 3);

        park.addAttraction(0, "Roller Coaster1", "10:00 - 22:00", 150.0);
        park.addAttraction(1, "Ferris Wheel", "10:00 - 22:00", 100.0);
        park.addAttraction(2, "Haunted House", "12:00 - 23:00", 200.0);

        park.printAttractions();
    }
}