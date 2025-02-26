public class Main {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n = (int) (Math.random() * 1_000_000);
        n = n * 3;
        int binaryNr = 0b10101;
        n = n + binaryNr;
        int hexNr = 0xFF;
        n = n + hexNr;
        n = n * 6;
        int sum = n;
        while (n > 9){
            sum = 0;
            while (n > 0){
                sum += n % 10;
                n /= 10;
            }
            n = sum;
        }
        System.out.println("Willy-nilly, this semester I will learn " + languages[n]);
    }
}