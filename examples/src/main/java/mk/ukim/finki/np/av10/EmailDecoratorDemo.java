package mk.ukim.finki.np.av10;

import java.util.Base64;

public class EmailDecoratorDemo {
    interface Email {
        String getContent();
    }

    static abstract class EmailDecorator implements Email {
        private final Email email;

        protected EmailDecorator(Email email) {
            this.email = email;
        }

        @Override
        public String getContent() {
            return email.getContent();
        }
    }

    static class TextEmail implements Email {
        private final String text;

        TextEmail(String text) {
            this.text = text;
        }

        @Override
        public String getContent() {
            return text;
        }
    }

    static class SpamEmail extends EmailDecorator {

        SpamEmail(Email email) {
            super(email);
        }

        @Override
        public String getContent() {
            return super.getContent().toUpperCase();
        }
    }

    static class SignatureEmail extends EmailDecorator {

        SignatureEmail(Email email) {
            super(email);
        }

        @Override
        public String getContent() {
            return super.getContent() + " \nMy Signature";
        }
    }

    static class Base64Email extends EmailDecorator {

        protected Base64Email(Email email) {
            super(email);
        }

        @Override
        public String getContent() {
            return Base64.getEncoder().encodeToString(super.getContent().getBytes());
        }
    }

    public static void main(String[] args) {
        Email textEmail = new TextEmail("text email");
        System.out.println("Base64 spam email");
        Email base64Spam = new Base64Email(new SpamEmail(textEmail));
        System.out.println(base64Spam.getContent());
        System.out.println("Spam with signature");
        Email spamWithSignature = new SpamEmail(new SignatureEmail(textEmail));
        System.out.println(spamWithSignature.getContent());
    }

}
