
// Course Registration Timetable Builder
// Madhavi mam slides: classes, objects, constructors, arrays, scanner

import java.util.Scanner;

class Course {
    String code;
    String name;
    int credits;
    int day;    // 0 to 4 (mon to fri)
    int time;   // 0 to 2

    // default constructor
    Course() {
        code = "NA";
        name = "NA";
        credits = 0;
        day = 0;
        time = 0;
    }

    // parameterized constructor
    Course(String c, String n, int cr, int d, int t) {
        code = c;
        name = n;
        credits = cr;
        day = d;
        time = t;
    }

    void display() {
        System.out.println(code + "  " + name + "  credits: " + credits);
    }
}

class Student {
    String name;
    int roll;
    Course c[] = new Course[6];   // array of objects
    int n = 0;

    Student(String name, int roll) {
        this.name = name;
        this.roll = roll;
    }

    // register a course, check clash first
    void addCourse(Course cat[], String code) {
        int i, pos = -1;
        for (i = 0; i < cat.length; i++) {
            if (cat[i].code.equals(code)) {
                pos = i;
                break;
            }
        }
        if (pos == -1) {
            System.out.println("course not found");
            return;
        }
        if (n >= 6) {
            System.out.println("max 6 courses only");
            return;
        }
        // clash check
        for (i = 0; i < n; i++) {
            if (c[i].day == cat[pos].day && c[i].time == cat[pos].time) {
                System.out.println("clash with " + c[i].name);
                return;
            }
        }
        c[n] = new Course(cat[pos].code, cat[pos].name, cat[pos].credits, cat[pos].day, cat[pos].time);
        n++;
        System.out.println("registered " + code);
    }

    void showTimetable() {
        String days[] = {"Mon", "Tue", "Wed", "Thu", "Fri"};
        String tt[][] = new String[5][3];  // 2d array

        int i, j;
        for (i = 0; i < 5; i++)
            for (j = 0; j < 3; j++)
                tt[i][j] = "-";

        for (i = 0; i < n; i++)
            tt[c[i].day][c[i].time] = c[i].code;

        System.out.println("\nTimetable of " + name + " (" + roll + ")");
        for (i = 0; i < 5; i++) {
            System.out.print(days[i] + " : ");
            for (j = 0; j < 3; j++)
                System.out.print(tt[i][j] + "   ");
            System.out.println();
        }
    }

    void totalCredits() {
        int s = 0, i;
        for (i = 0; i < n; i++)
            s = s + c[i].credits;
        System.out.println("total credits = " + s);
    }
}

public class TimetableBuilder {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        Course cat[] = new Course[7];
        cat[0] = new Course("CS101", "Java", 4, 0, 0);
        cat[1] = new Course("CS102", "DSA", 4, 0, 1);
        cat[2] = new Course("MA201", "Maths", 3, 1, 0);
        cat[3] = new Course("CS103", "DBMS", 3, 1, 1);
        cat[4] = new Course("CS104", "OS", 4, 2, 2);
        cat[5] = new Course("HS101", "Comm Skills", 2, 3, 0);
        cat[6] = new Course("CS105", "Networks", 3, 4, 1);

        System.out.print("enter name: ");
        String nm = sc.nextLine();
        System.out.print("enter roll no: ");
        int r = sc.nextInt();

        Student s = new Student(nm, r);

        int ch;
        do {
            System.out.println("\n1. show courses");
            System.out.println("2. register");
            System.out.println("3. timetable");
            System.out.println("4. credits");
            System.out.println("5. exit");
            System.out.print("enter choice: ");
            ch = sc.nextInt();

            switch (ch) {
                case 1:
                    for (int i = 0; i < cat.length; i++)
                        cat[i].display();
                    break;
                case 2:
                    System.out.print("enter course code: ");
                    String cd = sc.next();
                    s.addCourse(cat, cd);
                    break;
                case 3:
                    s.showTimetable();
                    break;
                case 4:
                    s.totalCredits();
                    break;
                case 5:
                    System.out.println("bye");
                    break;
                default:
                    System.out.println("wrong choice");
            }
        } while (ch != 5);
    }
}
