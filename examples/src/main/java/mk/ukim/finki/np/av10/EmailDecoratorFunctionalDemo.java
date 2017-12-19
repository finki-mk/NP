package mk.ukim.finki.np.av10;


import java.util.Base64;
import java.util.function.Function;

public class EmailDecoratorFunctionalDemo {
    interface EmailDecorator extends Function<TextEmail, TextEmail> {
    }

    static class TextEmail {
        private final String text;

        TextEmail(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }

    static EmailDecorator spamEmail = email -> new TextEmail(email.getText().toUpperCase());

    static EmailDecorator signatureEmail = email -> new TextEmail(email.getText() + "\nMy Signature");

    static EmailDecorator base64Email = email -> new TextEmail(Base64.getEncoder().encodeToString(email.getText().getBytes()));

    public static void main(String[] args) {

        System.out.println("Base64 spam email");
        TextEmail email = new TextEmail("text email");
        System.out.println(base64Email.andThen(spamEmail).apply(email));
        System.out.println("Spam with signature");
        System.out.println(spamEmail.andThen(signatureEmail).apply(email));
    }

}
