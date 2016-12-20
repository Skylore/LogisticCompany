package utils;

public class KeyFactory {

    public String generateKey(int size) {
        char[] mas = new char[size];

        for (int i = 0; i < size; i++) {
            int sel = ((int) (Math.random() * 2));

            mas[i] = sel == 0 ? ((char) (122 - (25 * Math.random()))) :
                    ((char) (57 - (9 * Math.random())));
        }

        return new String(mas);
    }
}
