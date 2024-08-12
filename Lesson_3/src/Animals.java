class Animal {
    protected String name;
    protected int runLimit;
    protected int swimLimit;

    static int animalCount = 0;
    static int dogCount = 0;
    static int catCount = 0;

    public Animal(String name) {
        this.name = name;
        animalCount++;
    }

    public void run(int distance) {
        if (distance <= runLimit) {
            System.out.println(name + " пробежал " + distance + " м.");
        } else {
            System.out.println(name + " не может пробежать " + distance + " м.");
        }
    }

    public void swim(int distance) {
        if (swimLimit == 0) {
            System.out.println(name + " не умеет плавать.");
        } else if (distance <= swimLimit) {
            System.out.println(name + " проплыл " + distance + " м.");
        } else {
            System.out.println(name + " не может проплыть " + distance + " м.");
        }
    }
}

class Cat extends Animal {
    private boolean isFull; // Сытость

    public Cat(String name) {
        super(name);
        this.runLimit = 200;
        this.swimLimit = 0; // Коты не умеют плавать
        this.isFull = false;
        catCount++;
    }

    public void eat(Plate plate, int foodAmount) {
        if (plate.getFood() >= foodAmount) {
            plate.decreaseFood(foodAmount);
            isFull = true;
            System.out.println(name + " поел " + foodAmount + " ед. еды и теперь сыт.");
        } else {
            System.out.println(name + " не поел, так как еды недостаточно.");
        }
    }

    public boolean isFull() {
        return isFull;
    }
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);
        this.runLimit = 500;
        this.swimLimit = 10;
        dogCount++;
    }
}

class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public int getFood() {
        return food;
    }

    public void addFood(int amount) {
        if (amount > 0) {
            food += amount;
            System.out.println("В миску добавлено " + amount + " ед. еды.");
        }
    }

    public void decreaseFood(int amount) {
        if (food >= amount) {
            food -= amount;
        } else {
            System.out.println("Недостаточно еды в миске!");
        }
    }

    public void info() {
        System.out.println("В миске осталось " + food + " ед. еды.");
    }
}

public class Animals {
    public static void main(String[] args) {
        // Выполнение первоначального условия
        Dog dogBobik = new Dog("Бобик");
        Dog dogSharik = new Dog("Шарик");
        Cat catMurzik = new Cat("Мурзик");
        Cat catBarsik = new Cat("Барсик");

        dogBobik.run(150);
        dogSharik.swim(5);
        catMurzik.run(100);
        catBarsik.swim(2);

        System.out.println("\nВсего животных: " + Animal.animalCount);
        System.out.println("Всего собак: " + Dog.dogCount);
        System.out.println("Всего котов: " + Cat.catCount);

        // Выполнение расширенного варианта задачи
        Plate plate = new Plate(20); // Создаем миску с 20 единицами еды
        Cat[] cats = {
                new Cat("Мурзик"),
                new Cat("Барсик"),
                new Cat("Рыжик"),
                new Cat("Снежок")
        };

        for (Cat cat : cats) {
            cat.eat(plate, 10); // Каждый кот пытается съесть 10 единиц еды
            plate.info(); // Информация о количестве еды в миске
        }

        System.out.println("\nСостояние котов:");
        for (Cat cat : cats) {
            System.out.println(cat.name + " сыт: " + cat.isFull());
        }

        System.out.println("\nДобавляем еду в миску...");
        plate.addFood(30); // Добавляем 30 единиц еды в миску
        plate.info();

        System.out.println("\nКоты снова пытаются поесть:");
        for (Cat cat : cats) {
            cat.eat(plate, 10);
            plate.info();
        }

        System.out.println("\nСостояние котов после второй попытки:");
        for (Cat cat : cats) {
            System.out.println(cat.name + " сыт: " + cat.isFull());
        }
    }
}
