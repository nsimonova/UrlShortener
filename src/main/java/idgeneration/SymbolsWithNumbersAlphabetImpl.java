package idgeneration;

import java.util.HashMap;

public class SymbolsWithNumbersAlphabetImpl implements Alphabet {

    public SymbolsWithNumbersAlphabetImpl() {
        alphabet = new HashMap<Character, Integer>();
        for (int i = 0; i < alphas.length(); i++) {
            alphabet.put(alphas.charAt(i), i);
        }
    }

    @Override
    public long GetCharacterCode(Character c) {
        return alphabet.get(c);
    }

    @Override
    public char GetCorrespondingCharacter(int remainder)
    {
        return alphas.charAt(remainder);
    }

    @Override
    public int getCount() {
        return alphas.length();
    }

    //Maybe should be used specialized collection with primitive types
    private HashMap<Character, Integer> alphabet;
    private String alphas = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefjhijklmnopqrstuvwxyz";
}