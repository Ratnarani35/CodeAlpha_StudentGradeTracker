public class Student {
    private final String name;
    private final double[] marks;

    public Student(String name, double[] marks) {
        this.name = name;
        this.marks = marks.clone();
    }

    public String getName() {
        return name;
    }

    public double[] getMarks() {
        return marks.clone();
    }

    public double getTotal() {
        double total = 0;
        for (double mark : marks) total += mark;
        return total;
    }

    public double getAverage() {
        return getTotal() / marks.length;
    }

    public String getGrade() {
        double avg = getAverage();
        if (avg >= 90) return "A+";
        if (avg >= 80) return "A";
        if (avg >= 70) return "B";
        if (avg >= 60) return "C";
        if (avg >= 50) return "D";
        return "F";
    }
}
